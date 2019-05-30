package com.abao.java8.designpattern.responsibility;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/27 16:49
 */
public class ChainOfResponsibilityTest {

    public static void main(String[] args) {
        Interviewee interviewee = new Interviewee("小明");
        TeamLeader teamLeader = new TeamLeader("老刚");
        DepartmentManager departMentManager = new DepartmentManager("老孙");
        HR hr = new HR("老刘");
        // 设置面试流程
        teamLeader.setNextInterviewer(departMentManager);
        departMentManager.setNextInterviewer(hr);
        // 开始面试
        teamLeader.handleInterview(interviewee);
    }
}
