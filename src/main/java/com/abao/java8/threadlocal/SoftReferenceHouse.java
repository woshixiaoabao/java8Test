package com.abao.java8.threadlocal;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anpei
 * @version 1.0
 * @Description: 软引用
 * @date 2019/5/15 13:59
 */
public class SoftReferenceHouse {

    public static void main(String[] args) {
        List<SoftReference> houses = new ArrayList<>();
        int i = 0;

        while (true) {
            SoftReference<House> buyer2 = new SoftReference<>(new House());
            houses.add(buyer2);
            System.out.println("i=" + (++i));
        }
    }

    static class House {
        private static final Integer DOOR_NUMBER = 2000;
        public Door[] doors = new Door[DOOR_NUMBER];

        class Door {
        }
    }
}
