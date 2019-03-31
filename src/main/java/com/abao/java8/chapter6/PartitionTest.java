package com.abao.java8.chapter6;

import com.abao.java8.chapter4.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/15 10:30
 */
public class PartitionTest {
    public static void main(String[] args) {
        Map<Boolean, List<Dish>> patitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(patitionedMenu + ",true:" + patitionedMenu.get(true));

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetaarianDishesByType = Dish.menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        //{false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]},
        //true={OTHER=[french fries, rice, season fruit, pizza]}}
        System.out.println(vegetaarianDishesByType);

        //to find the most caloric dish among
        //both vegetarian and nonvegetarian dishes:
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian, collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));


        Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500)));

        //This won’t compile because partitioningBy requires a predicate, a function returning a
        //boolean. And the method reference Dish::getType can’t be used as a predicate.
//        Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
//                partitioningBy(Dish::getType)));
        Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                counting()));

    }

    // 判断素数与非素数
    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
    }

}
