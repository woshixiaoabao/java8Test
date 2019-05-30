package com.abao.java8.designpattern.responsibility;

import java.util.Random;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/27 16:34
 */
public class TeamLeader extends Interviewer {


    public TeamLeader(String name) {
        super(name);
    }

    @Override
    public void handleInterview(Interviewee interviewee) {
        System.out.println("组长[" + this.name + "]面试[" + interviewee.getName() + "]同学");
        interviewee.setTeamLearderOpinion(new Random().nextBoolean());
        if (interviewee.isTeamLearderOpinion()) {
            System.out.println("[" + interviewee.getName() + "]同学组长轮面试通过");
            this.nextInterviewer.handleInterview(interviewee);
        }else{
            System.out.println("[" + interviewee.getName() + "]同学组长轮面试不通过");
        }
    }
}
