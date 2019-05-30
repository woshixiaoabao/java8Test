package com.abao.java8.designpattern.responsibility;

import java.util.Random;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/27 16:48
 */
public class HR extends Interviewer {
    public HR(String name) {
        super(name);
    }

    @Override
    public void handleInterview(Interviewee interviewee) {
        System.out.println("HR[" + this.name + "]面试[" + interviewee.getName() + "]同学");
        interviewee.setHrOpinion(new Random().nextBoolean());
        if (interviewee.isHrOpinion()) {
            System.out.println("[" + interviewee.getName() + "]同学HR轮面试通过, 恭喜拿到 Offer");
        } else {
            System.out.println("[" + interviewee.getName() + "]同学HR轮面试不通过");
        }
    }
}
