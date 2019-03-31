package com.abao.java8.chapter6;

import com.abao.java8.chapter4.Dish;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/13 14:13
 */
public class Chapter6Test {

    public static List<Transaction> transactions = Arrays.asList(new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0));

    @Getter
    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }

    public static void main(String[] args) {
        // Grouping transactions by currency in imperative style
//        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<Currency, List<Transaction>>();
//        for (Transaction transaction : transactions) {
//            Currency currency = transaction.getCurrency();
//            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
//
//            if(transactionsByCurrencies==null){
//                transactionsForCurrency = new ArrayList<>();
//                transactionsByCurrencies.put(currency,transactionsForCurrency);
//            }
//            transactionsForCurrency.add(transaction);
//        }
        Map<Currency, List<Transaction>> transactionsByCurrencies
                = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));

        long howManyDishes = Dish.menu.stream().collect(Collectors.counting());
        // long howManyDishes = menu.stream().count();

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = Dish.menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));

        int totalCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        double avgCalories =
                Dish.menu.stream().collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics =
                Dish.menu.stream().collect(summarizingInt(Dish::getCalories));

        //Joining Strings
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);
        int totalCalories1 = Dish.menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));
        Optional<Dish> mostCalorieDish =
                Dish.menu.stream().collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        int totalCalories2 = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

        int totalCalories3 =
                Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int totalCalories4 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();

        // Joining strings with reducing
        String shortMenu1 = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());
//        String shortMenu2 = Dish.menu.stream()
//                .collect(reducing((d1, d2) -> d1.getName() + d2.getName())).get();
        String shortMenu3 = Dish.menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));

        //group by
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));

        //Multilevel grouping

        //count the number of Dishes in the menu for each type, by passing the counting
        //collector as a second argument to the groupingBy collector
        Map<Dish.Type, Long> typeCount = Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));

        // to find the highest-calorie dish in the menu
        // to achieve a similar result, but now classified by the type of dish
        Map<Dish.Type, Optional<Dish>> highestCalorieDish =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));

        //to adapt the result returned by a collector to a different type, you could use the
        //collector returned by the Collectors.collectingAndThen factory method
        Map<Dish.Type, Dish> mostCaloricByType = Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Integer> totalCaloriesByType =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        //which CaloricLevels are available in the menu for each type of Dish
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                Dish.menu.stream().collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, toSet()))); // result:{OTHER=[DIET, NORMAL], MEAT=[DIET, NORMAL, FAT], FISH=[DIET, NORMAL]}


        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType1 =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType1);

        // Supplier、Accumulator、Combiner
        List<Dish> dishes = Dish.menu.stream().collect(ArrayList::new, List::add, List::addAll);
        System.out.println(dishes);
    }
}
