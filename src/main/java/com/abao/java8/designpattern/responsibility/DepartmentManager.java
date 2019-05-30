package com.abao.java8.designpattern.responsibility;

import java.util.Random;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/27 16:46
 */
public class DepartmentManager extends Interviewer {

    public DepartmentManager(String name) {
        super(name);
    }

    @Override
    public void handleInterview(Interviewee interviewee) {
        System.out.println("部门经理[" + this.name + "]面试[" + interviewee.getName() + "]同学");
        interviewee.setDepartmentManagerOpinion(new Random().nextBoolean());
        if (interviewee.isDepartmentManagerOpinion()) {
            System.out.println("[" + interviewee.getName() + "]同学部门经理轮面试通过");
            this.nextInterviewer.handleInterview(interviewee);
        } else {
            System.out.println("[" + interviewee.getName() + "]同学部门经理轮面试不通过");
        }
    }

}
