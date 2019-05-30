package com.abao.java8.chapter12;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * @author anpei
 * @version 1.0
 * @Description: http://www.codeceo.com/article/java-8-20-datetime.html
 * @date 2019/5/25 10:11
 */
public class DateUtils {

    public static void main(String[] args) {

        // 重复日期判断
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");

        }
    }
}
