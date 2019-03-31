package com.abao.java8.chapter5;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/12 18:23
 */
public class internalAndTerminalTest {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> transactionList =
                transactions.stream().filter(t -> t.getYear() == 2011).
                        sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        //2. What are all the unique cities where the traders work?
        List<String> uniqueCity = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());

        Set<String> cities =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .collect(toSet());

        //3. Find all traders from Cambridge and sort them by name.
        List<Trader> allTraders = transactions.stream().map(trader -> trader.getTrader()).
                filter(trader -> trader.getCity() == "Cambridge").sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());

        //4. Return a string of all traders’ names sorted alphabetically.
        String traderNamesStr = transactions.stream().map(trader -> trader.getTrader().getName())
                .distinct().sorted().
                        reduce("", (a, b) -> a + "," + b);
        System.out.println("aa：" + traderNamesStr);
//        String traderStr = // 效率高
//                transactions.stream()
//                        .map(transaction -> transaction.getTrader().getName())
//                        .distinct()
//                        .sorted()
//                        .collect(Collectors.joining());

        //5. Are any traders based in Milan?
        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        //6. Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity())).map(Transaction::getValue).forEach(System.out::println);

        //7. What’s the highest value of all the transactions?
        Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);

        //8. Find the transaction with the smallest value.
//        Optional<Transaction> smallestTransaction = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .min(Comparator.comparing(Transaction::getValue));
    }
}
