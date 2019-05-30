package com.abao.java8.file.size;

import java.io.File;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/26 20:09
 */
public class TotalFileSizeSequential {
    public static String fileName = "E:\\BaiduNetdiskDownload";

    /**
     * 递归方式 计算文件大小
     *
     * @param file
     * @return
     */
    private long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile()) {
            return file.length();
        }
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null) {
            for (File child : children) {
                total += getTotalSizeOfFilesInDir(child);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        final long start = System.nanoTime();
        final long total = new TotalFileSizeSequential().getTotalSizeOfFilesInDir(new File(fileName));
        final long end = System.nanoTime();
        System.out.println("总大小：" + total);
        System.out.println("耗时：" + (end - start) / 1.0e9);
    }
}
