package com.bluemsun.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class JWTUtils {

    /**
     * 公钥
     */
    private static String SECRET = "dengcs";  //此处随便设置一个自己的加密符号
    public static String createToken(Integer id) throws Exception {
        // 签发时间
        Date iatDate = new Date();

        // 过期时间，2hour
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 24);
        Date experiesDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", id)    //用户id
                .withExpiresAt(experiesDate) // 设置过期的日期
                .withIssuedAt(iatDate) // 签发时间
                .sign(Algorithm.HMAC256(SECRET)); // 加密
        return token;
    }

    /**
     * 解密
     */

    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);  //核实token
        } catch (Exception e) {
            throw new Exception("登录过期");
        }
        return jwt.getClaims();  //返回的是解析完的token，是一个map，里面有id，username，type键值对
    }

    public static long getRemainingMinutes(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expireDate = jwt.getExpiresAt();
            long diff = expireDate.getTime() - System.currentTimeMillis();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            return minutes;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 返回-1表示token无效或解析出错
        }
    }



}