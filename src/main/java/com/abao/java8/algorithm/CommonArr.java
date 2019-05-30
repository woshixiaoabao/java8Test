package com.abao.java8.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author anpei
 * @version 1.0
 * @Description: 两个数组中找出相同的数据，输出数组
 * @date 2019/5/19 11:19
 * https://www.cnblogs.com/ASPNET2008/p/6034561.html
 */
public class CommonArr {

    public static void main(String[] args) {
        int[] arrA = {5, 7, 9, 1, 12, 10, 8, 6};
        int[] arrB = {4, 9, 2, 1, 3, 11, 7, 5, 13, 20};

        Set<Integer> intersectionSet = new HashSet<>();
        Arrays.sort(arrA);
        Arrays.sort(arrB);

        int indexArrayA = 0;
        int indexArrayB = 0;
        int sizeArrayA = arrA.length;
        int sizeArrayB = arrB.length;

        while (indexArrayA < sizeArrayA) {
            for (int i = indexArrayB; i < sizeArrayB; i++) {
                if (arrA[indexArrayA] == arrB[i]) {
                    intersectionSet.add(arrA[indexArrayA]);
                    indexArrayA++;
                    indexArrayB++;
                    break;
                } else if (arrA[indexArrayA] < arrB[i]) {
                    indexArrayA++;
                    break;
                } else if (i == sizeArrayB - 1) {
                    indexArrayA++;
                }
            }
        }

        System.out.println(intersectionSet);
    }
}
