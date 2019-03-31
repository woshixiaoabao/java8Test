package com.abao.java8.chapter5;

import com.abao.java8.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/11 18:03
 */
public class StreamDemo {

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

        List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        // 去除重复数字
//        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 2, 4, 5, 5, 4, 2, 3, 3, 1);
//        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2) // 跳过两个
                .collect(Collectors.toList());
        System.out.println(dishes);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        //given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25]
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> targetNumbers = numbers.stream().map(n -> n * n).collect(Collectors.toList());

        //given a
        //list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For
        //simplicity, you can represent a pair as an array with two elements.
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(pairs);

        //How would you extend the previous example to return only pairs whose sum is divisible by 3?
        //For example, (2, 4) and (3, 3) are valid.
        List<int[]> pairs1 = numbers1.stream().
                flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(pairs1);

        //Optional
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dish);

        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));

        // 可以被3整除
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst();
        System.out.println(firstSquareDivisibleByThree);

        // int sum = someNumbers.stream().reduce(1, (a, b) -> a + b);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum：" + sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("product:" + product);

        Optional<Integer> sum1 = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sum1);

        // Maximum and minimum
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        int count = menu.stream().map(dish1 -> 1).reduce(0, (a, b) -> a + b);
        System.out.println("count:" + count);
    }
}
