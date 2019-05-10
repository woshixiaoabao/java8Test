package com.abao.java8.algorithm;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/3 10:26
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {33, 4, 66, 11, 22, 17, 42};
        bubbleSort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    static void bubbleSort(int[] arr) {

        int length = arr.length;
        int temp;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { /* 相邻元素比较，若逆序则交换（升序为左大于右，逆序反之） */
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
