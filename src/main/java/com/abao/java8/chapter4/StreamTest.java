package com.abao.java8.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/11 16:49
 */
public class StreamTest {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        List<String> threeHighCaloricDishNames = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println("caloric最高的前三个" + threeHighCaloricDishNames);
        Stream<String> s = threeHighCaloricDishNames.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println); // 异常 java.lang.IllegalStateException: stream has already been operated upon or closed

        // 中间操作与终止操作
        List<String> names = menu.stream().filter(d -> {
            System.out.println("过滤" + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("mapping" + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println(names);
    }
}
