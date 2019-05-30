package com.abao.java8.file.size;

import java.io.File;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/27 11:36
 */
public class BlockingQueueTotalFileSize {
    public static String fileName = "E:\\BaiduNetdiskDownload";

    private ExecutorService executorService;
    final private BlockingQueue<Long> fileSizes = new ArrayBlockingQueue<Long>(
            500);

    final AtomicLong pendingFileVisits = new AtomicLong();

    private void startExploreDir(final File file) {
        pendingFileVisits.incrementAndGet();
        executorService.execute(new Runnable() {
            public void run() {
                exploreDir(file);
            }
        });
    }
    private void exploreDir(final File file) {
        long fileSize = 0;
        if (file.isFile())
            fileSize = file.length();
        else {
            final File[] children = file.listFiles();
            if (children != null)
                for (final File child : children) {
                    if (child.isFile())
                        fileSize += child.length();
                    else {
                        startExploreDir(child);
                    }
                }
        }
        try {
            fileSizes.put(fileSize);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        pendingFileVisits.decrementAndGet();
    }
    private long getTotalSizeOfFile(final String fileName)
            throws InterruptedException {
        executorService = Executors.newFixedThreadPool(100);
        try {
            startExploreDir(new File(fileName));
            long totalSize = 0;
            while (pendingFileVisits.get() > 0 || fileSizes.size() > 0) {
                final Long size = fileSizes.poll(10, TimeUnit.SECONDS);
                totalSize += size;
            }
            return totalSize;
        } finally {
            executorService.shutdown();
        }
    }
    public static void main(final String[] args) throws InterruptedException {
        final long start = System.nanoTime();
        final long total = new BlockingQueueTotalFileSize()
                .getTotalSizeOfFile(fileName);
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }
}
