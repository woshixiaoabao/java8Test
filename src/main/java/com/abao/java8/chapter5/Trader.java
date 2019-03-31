package com.abao.java8.chapter5;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/12 18:23
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
