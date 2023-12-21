package com.bluemsun.service.impl;

import com.bluemsun.dao.*;
import com.bluemsun.entity.*;
import com.bluemsun.entity.dto.FinalScore;
import com.bluemsun.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    JudgeDao judgeDao;

    @Autowired
    CandidateDao candidateDao;
    @Autowired
    CaseDiscussionDao caseDiscussionDao;
    @Autowired
    TheoreticalPresentationDao theoreticalPresentationDao;
    @Autowired
    TalkDao talkDao;

    @Override
    public boolean login(Judge judge) {
        if("".equals(judge.getName()) || judge.getName()==null
        || "".equals(judge.getPassword()) || judge.getPassword()==null){
            return false;
        } else {
            Judge judge1 = judgeDao.selectOne(judge);
            if(judge1 != null) {
                judge.setId(judge1.getId());
                judgeDao.set1(judge);
                return true;
            }
            return false;
        }
    }

    /**
     * 1:打分成功
     * 0：打分失败
     * -1：已打分
     * @param score
     * @return
     */
    @Override
    public int score(Score score) {

        if(score.getScores() == null){
            return -1;
        }
        if(score.getTurn() == 2){
            // 案例讨论
            // 查看当前评委是否提交
            CaseDiscussion caseDiscussion = new CaseDiscussion();
            caseDiscussion.setScores(score.getScores());
            caseDiscussion.setCandidateId(score.getCandidateId());
            caseDiscussion.setJudgeId(score.getJudgeId());
            int i = caseDiscussionDao.getConfirmed(caseDiscussion);
            if(i > 0){
                return 0;
            }
            //查看是否提交过
            int f = caseDiscussionDao.selectIsJudged(caseDiscussion);

            if(f>0){
                int r = caseDiscussionDao.updateScore(caseDiscussion);
                // 查看当前请求是否是提交
                if(score.getIsConfirmed() == 1){
                    caseDiscussionDao.setIsConfirmed1(caseDiscussion);
                }
                if (r == 1) return 1;
            }else {
                int r = caseDiscussionDao.insetOne(caseDiscussion);
                // 查看当前请求是否是提交
                if(score.getIsConfirmed() == 1){
                    caseDiscussionDao.setIsConfirmed1(caseDiscussion);
                }
                if (r == 1) return 1;
            }

        }  else if(score.getTurn() == 3){
            // 谈心谈话
            Talk talk = new Talk();
            talk.setScores(score.getScores());
            talk.setCandidateId(score.getCandidateId());
            talk.setJudgeId(score.getJudgeId());
            int i = talkDao.getConfirmed(talk);
            if(i > 0){
                return 0;
            }
            int f = talkDao.selectIsJudged(talk);
            if(score.getIsConfirmed() == 1){
                talkDao.setIsConfirmed1(talk);
            }
            if(f>0){
                int r = talkDao.updateScore(talk);
                // 查看当前请求是否是提交
                if(score.getIsConfirmed() == 1){
                    talkDao.setIsConfirmed1(talk);
                }
                if (r == 1) return 1;
            }else{
                int r = talkDao.insetOne(talk);
                // 查看当前请求是否是提交
                if(score.getIsConfirmed() == 1){
                    talkDao.setIsConfirmed1(talk);
                }
                if (r == 1) return 1;
            }
        }
        return 0;
    }
    /**
     * 1:已提交
     * 0：未提交
     */
    @Override
    public int isConfirmed(Score score) {


        if(score.getTurn() == 2){
            // 案例讨论
            CaseDiscussion caseDiscussion = new CaseDiscussion();
            caseDiscussion.setCandidateId(score.getCandidateId());
            caseDiscussion.setJudgeId(score.getJudgeId());
            int i = caseDiscussionDao.getConfirmed(caseDiscussion);
            if(i > 0){
                return 1;
            }

        }  else if(score.getTurn() == 3){
            // 谈心谈话
            Talk talk = new Talk();

            talk.setCandidateId(score.getCandidateId());
            talk.setJudgeId(score.getJudgeId());
            int i = talkDao.getConfirmed(talk);
            if(i > 0){
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<Talk> getaAllTalkofThisJudge(Integer judgeId) {
        List<Talk> result = new ArrayList<>();
        List<Talk> talkList = talkDao.selectJudgeinfo(judgeId);
        Iterator<Talk> iterator = talkList.iterator();

        while (iterator.hasNext()) {
            Talk talk = iterator.next();
            Integer id;
            Integer candidateId;
            Integer judgeId1;
            Double score1;
            Double scoreTotal; // 总分
            Integer isConfirmed;//提交，默认为0，提交为1

            id = talk.getId();
            candidateId = talk.getCandidateId();
            judgeId1 = talk.getJudgeId();
            score1 = talk.getScore1();
            scoreTotal = talk.getScoreTotal();
            isConfirmed = talk.getIsConfirmed();
            Talk talk1 = new Talk(id, candidateId,judgeId1,score1,scoreTotal,isConfirmed);
            result.add(talk1);
        }
        return result;
    }

    @Override
    public List<CaseDiscussion> getaAllCaseDiscussionofThisJudge(Integer judgeId) {
        List<CaseDiscussion> result = new ArrayList<>();
        List<CaseDiscussion> caseDiscussionList = caseDiscussionDao.selectJudgeinfo(judgeId);
        Iterator<CaseDiscussion> iterator = caseDiscussionList.iterator();

        while (iterator.hasNext()) {
            CaseDiscussion caseDiscussion = iterator.next();
            Integer id;
            Integer candidateId;
            Integer judgeId1;
            Double score1;  //  提问分数
            Double score2;  //  作答分数
            Double scoreTotal;  // 总分
            Integer isConfirmed;//提交，默认为0，提交为1
            id = caseDiscussion.getId();
            candidateId = caseDiscussion.getCandidateId();
            judgeId1 = caseDiscussion.getJudgeId();
            score1 = caseDiscussion.getScore1();
            score2 = caseDiscussion.getScore2();
            scoreTotal = caseDiscussion.getScoreTotal();
            isConfirmed = caseDiscussion.getIsConfirmed();
            CaseDiscussion CaseDiscussion1 = new CaseDiscussion(id, candidateId,judgeId1,score1,score2,scoreTotal,isConfirmed);
            result.add(CaseDiscussion1);
        }
        return result;
    }

    /**
     * 0: 还有评委没有打分
     * 1： 所有评委都已打分，并且分数统计完成
     * -1： 所有评委都已打分，但是分数统计失败
     * 2: 案例讨论中所有选手都已
     * @param turn
     * @param candidateId
     * @return
     */
    @Override
    public int isDone(Integer turn, Integer candidateId) {
        int result = 0;
        if(turn == 2){
            // 案例讨论
            // 默认处于会场1
            int judgeNum = judgeDao.getJudgeNum(1);
            int r = caseDiscussionDao.getJudgedNum(candidateId);
            if(r == judgeNum){
                // 统计案例讨论的分数
                Double score = caculate2(candidateId);
                Candidate candidate = new Candidate();
                candidate.setId(candidateId);
                candidate.setScore_2(score);
                int i = candidateDao.setScore2(candidate);
                if(i == 1){
                    result = 1;
                } else {
                    result = -1;
                }
            }
        }  else if(turn == 3){
            // 谈心谈话
            // 默认处于会场1
            int judgeNum = judgeDao.getJudgeNum(1);
            int r = talkDao.getJudgedNum(candidateId);
            if(r == judgeNum){
                // 统计谈心谈话的分数
                Double score = caculate4(candidateId);
                int i = talkDao.updateScore4ByCandidateId(score, candidateId);
                if(i == 1){
                    result = 1;
                    // 谈心谈话结束，统计决赛成绩
                } else {
                    result = -1;
                }
            }
        }
        return result;
    }

    @Override
    public boolean logout(Integer judgeId) {
        Judge judge = new Judge();
        judge.setId(judgeId);
        int i = judgeDao.set0(judge);
        return i==1;
    }


    public Double caculate4(Integer candidateId){
        List<Talk> talks = talkDao.getScoreByCandidateId(candidateId);
        List<Double> scores = new ArrayList<>();
        for(Talk c : talks){
            double score1 = c.getScore1();;
            scores.add(score1);
        }

        if(scores.size() > 5){
            Collections.sort(scores);
            scores = scores.subList(1, scores.size() - 1);
        }

        OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
        if (average.isPresent()) {
            System.out.println("平均值: " + average.getAsDouble());
            DecimalFormat df = new DecimalFormat("#.00");
            String str = df.format(average.getAsDouble());
            return Double.parseDouble(str);
        } else {
            System.out.println("列表为空");
            return null;
        }
    }

    public Double caculate3(Integer candidateId){
        List<TheoreticalPresentation> theoreticalPresentations = theoreticalPresentationDao.getScoreByCandidateId(candidateId);
        List<Double> scores = new ArrayList<>();
        for(TheoreticalPresentation c : theoreticalPresentations){
            double score1 = c.getScore1();
            double score2 = c.getScore2();
            double score3 = c.getScore3();
            double score4 = c.getScore4();
            double score5 = c.getScore5();

            double score = score1 + score2 + score3 + score4 + score5;
            scores.add(score);
        }

        if(scores.size() > 5){
            Collections.sort(scores);
            scores = scores.subList(1, scores.size() - 1);
        }

        OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
        if (average.isPresent()) {
            System.out.println("平均值: " + average.getAsDouble());
            DecimalFormat df = new DecimalFormat("#.00");
            String str = df.format(average.getAsDouble());
            return Double.parseDouble(str);
        } else {
            System.out.println("列表为空");
            return null;
        }
    }

    public Double caculate2(Integer candidateId){
        List<CaseDiscussion> caseDiscussions = caseDiscussionDao.getScoreByCandidateId(candidateId);
        List<Double> scores = new ArrayList<>();
        for(CaseDiscussion c : caseDiscussions){
            double score1 = c.getScore1();
            double score2 = c.getScore2();

            double score = score1 + score2;
            scores.add(score);
        }

        if(scores.size() > 5){
            // 评委数大于5，去掉1个最低分，去掉1个最高分
            Collections.sort(scores);
            scores = scores.subList(1, scores.size() - 1);
        }

        OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
        if (average.isPresent()) {
            System.out.println("平均值: " + average.getAsDouble());
            DecimalFormat df = new DecimalFormat("#.00");
            String str = df.format(average.getAsDouble());
            return Double.parseDouble(str);
        } else {
            System.out.println("列表为空");
            return null;
        }
    }

    /**
     * 将案例讨论的分数归一化
     * @return
     */
    public boolean normalize(Integer hallId){
        // 有问题
        boolean result = true;
        List<Candidate> candidates = candidateDao.selectAllCandidate(hallId);
        // 按照案例讨论原始分数进行升序排序
        candidates.sort(Comparator.comparing(Candidate::getScore_2_origin));
        double low = candidates.get(0).getScore_2_origin();
        double high = candidates.get(candidates.size()-1).getScore_2_origin();
        for (Candidate candidate : candidates) {
            // 计算归一化之后的得分
            double finalScore = 75.0 + 20.0 * (candidate.getScore_2_origin() - low) / (high - low);
            finalScore = Math.round(finalScore * 1000) / 1000.0; // 保留3位小数
            candidate.setScore_2(finalScore);

            // 持久化到数据库
            int i = candidateDao.setScore2(candidate);

            result = i==1;
        }
        return result;
    }
}


