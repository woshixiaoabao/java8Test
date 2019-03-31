package com.abao.java8.chapter5;

import lombok.Getter;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/12 18:24
 */
@Getter
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    private String currency;

    public Transaction(Trader trader, int year, int value,String currency){
        this.trader = trader;
        this.year = year;
        this.value = value;
        this.currency = currency;
    }

    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }



    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}
