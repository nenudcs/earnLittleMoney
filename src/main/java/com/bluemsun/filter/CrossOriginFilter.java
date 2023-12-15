package com.bluemsun.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//跨域过滤器
public class CrossOriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin," +
                        "Access-Control-Request-Headers," +
                        "Access-Control-Allow-Headers," +
                        "Content-Type," +
                        "Keep-Alive," +
                        "User-Agent," +
                        "Cache-Control," +
                        "Cookie," +
                        "token," +
                        "DNT," +
                        "X-Requested-With," +
                        "X-Mx-ReqToken," +
                        "X-Requested-With," +
                        "If-Modified-Since," +
                        "Accept," +
                        "Connection," +
                        "X-XSRF-TOKEN," +
                        "X-CSRF-TOKEN," +
                        "Authorization");

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");

        response.setHeader("Access-Control-Max-Age", "86400");

//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//        response.setHeader("Access-Control-Expose-Headers", "token");

//        if(request.getMethod().toUpperCase().equals("OPTIONS")){
//            return;
//        }
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return ;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
