package com.abao.java8.chapter5;

import com.abao.java8.chapter4.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/13 9:52
 */
public class NumericStreamTest {
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

        // mapping to a numeric stream
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();

        System.out.println(ClassLoader.getSystemResource("data.txt").getFile());
        // Converting back to a stream of objects
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        // OptionalInt
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

        // 如果没有最大值，自定义个默认值
        int max = maxCalories.orElse(1);

        //Numeric ranges
        //range is exclusive, whereas rangeClosed is inclusive.
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        // 毕达哥拉斯三元数组-勾股定理（求100之内的所有）
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        //Building streams
        Stream<String> stream1 = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream1.map(String::toUpperCase).forEach(System.out::println);

        // Streams from arrays
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        // Streams from files
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("F:\\java88\\src\\main\\resources\\data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(uniqueWords);
        } catch (IOException e) {
            System.out.println(e);
        }

        //  Streams from functions: creating infinite streams!
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        // Fibonacci tuples series
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        // Generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntStream ones = IntStream.generate(() -> 1);
        System.out.println("one:" + ones);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
