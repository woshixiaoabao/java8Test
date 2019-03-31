package com.abao.java8.enumoperation;

import java.util.Optional;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/2/3 10:54
 */
public class EnumTest {
    public static void main(String[] args) {
        double x = 2d;
        double y = 3d;
        for (Operation op :
                Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
        System.out.println(Operation.fromString("+"));

    }
}