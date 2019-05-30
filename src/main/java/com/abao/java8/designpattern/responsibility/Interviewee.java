package com.abao.java8.designpattern.responsibility;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anpei
 * @version 1.0
 * @Description: 求职者
 * @date 2019/5/27 16:10
 */
@Getter
@Setter
public class Interviewee {
    private String name;
    private boolean teamLearderOpinion;
    private boolean departmentManagerOpinion;
    private boolean hrOpinion;

    public Interviewee(String name) {
        this.name = name;
    }
}
