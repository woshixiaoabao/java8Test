package com.abao.java8.file.size;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author anpei
 * @version 1.0
 * @Description: 使用CountDownLatch、AtomicLong实现多线程下的并发控制
 * @date 2019/5/27 10:34
 */
public class ConcurrentTotalFileSizeWLatch {

    private ExecutorService executorService;
    final private AtomicLong pendingFileVisits = new AtomicLong();
    final private AtomicLong totalSize = new AtomicLong();
    final private CountDownLatch latch = new CountDownLatch(1);
    public static String fileName = "E:\\BaiduNetdiskDownload";

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        long total = new ConcurrentTotalFileSizeWLatch().getTotalSizeOfFile(fileName);
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }

    private long getTotalSizeOfFile(String fileName) throws InterruptedException {
        executorService = Executors.newFixedThreadPool(10);
        pendingFileVisits.incrementAndGet();
        try {
            updateTotalSizeOfFilesInDir(new File(fileName));
            latch.await(100, TimeUnit.SECONDS);
            return totalSize.longValue();
        } finally {
            executorService.shutdown();
        }
    }

    private void updateTotalSizeOfFilesInDir(File file) {
        long fileSize = 0;
        if (file.isFile())
            fileSize = file.length();
        else {
            final File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isFile())
                        fileSize += child.length();
                    else {
                        pendingFileVisits.incrementAndGet();
                        executorService.execute(() -> updateTotalSizeOfFilesInDir(child));
                    }
                }
            }
        }
        totalSize.addAndGet(fileSize);
        if (pendingFileVisits.decrementAndGet() == 0)
            latch.countDown();
    }
}
