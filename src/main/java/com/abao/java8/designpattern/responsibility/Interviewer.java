package com.abao.java8.designpattern.responsibility;

/**
 * @author anpei
 * @version 1.0
 * @Description: 面试官
 * @date 2019/5/27 16:06
 */
public abstract class Interviewer {
    protected String name;
    protected Interviewer nextInterviewer;


    public Interviewer(String name) {
        this.name = name;
    }

    public Interviewer setNextInterviewer(Interviewer nextInterviewer) {
        this.nextInterviewer = nextInterviewer;
        return this.nextInterviewer;
    }

    public abstract void handleInterview(Interviewee interviewee);
}
