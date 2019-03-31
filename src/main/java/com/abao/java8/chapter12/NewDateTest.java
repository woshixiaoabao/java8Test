package com.abao.java8.chapter12;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/17 13:45
 */
public class NewDateTest {
    public static void main(String[] args) {
        //LocalDate, LocalTime, Instant, Duration, and Period
        // Working with LocalDate and LocalTime
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 1000000000));
        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
        //int day = Instant.now().get(ChronoField.DAY_OF_MONTH);

//        Duration d1 = Duration.between(time1, time2);
//        Duration d1 = Duration.between(dateTime1, dateTime2);
//        Duration d2 = Duration.between(instant1, instant2);
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));

        //Creating Durations and Periods
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        //Period
        Period tenDays1 = Period.ofDays(10);
        System.out.println(tenDays); // P10D

        Period threeWeeks = Period.ofWeeks(3);
        System.out.println(threeWeeks);

        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay);


    }
}
