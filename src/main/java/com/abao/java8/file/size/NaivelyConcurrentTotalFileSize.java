package com.abao.java8.file.size;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/26 21:11
 */
public class NaivelyConcurrentTotalFileSize {
    public static String fileName = "E:\\BaiduNetdiskDownload\\01、【初级架构搭建】SpringCloud从零搭建微服务架构视频教程";
    private long getTotalSizeOfFilesInDir(final ExecutorService service,
                                          final File file) throws InterruptedException, ExecutionException,
            TimeoutException {
        if (file.isFile()){
            System.out.println(file.getName());
            return file.length();
        }
        long total = 0;
        final File[] children = file.listFiles();
        if (children != null) {
            final List<Future<Long>> partialTotalFutures = new ArrayList<Future<Long>>();
            for (final File child : children) {
                partialTotalFutures.add(service.submit(new Callable<Long>() {
                    public Long call() throws InterruptedException,
                            ExecutionException, TimeoutException {
                        return getTotalSizeOfFilesInDir(service, child);
                    }
                }));
            }
            for (final Future<Long> partialTotalFuture : partialTotalFutures)
                total += partialTotalFuture.get(100, TimeUnit.SECONDS);
        }
        return total;
    }
    private long getTotalSizeOfFile(final String fileName)
            throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            return getTotalSizeOfFilesInDir(service, new File(fileName));
        } finally {
            service.shutdown();
        }
    }
    public static void main(final String[] args) throws InterruptedException,
            ExecutionException, TimeoutException {
        final long start = System.nanoTime();
        final long total = new NaivelyConcurrentTotalFileSize()
                .getTotalSizeOfFile(fileName);
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }
}
