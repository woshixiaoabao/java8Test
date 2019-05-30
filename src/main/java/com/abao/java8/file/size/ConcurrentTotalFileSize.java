package com.abao.java8.file.size;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author anpei
 * @version 1.0
 * @Description: Executors.newFixedThreadPool、callable多线程实现
 * @date 2019/5/26 20:29
 */
public class ConcurrentTotalFileSize {
    public static String fileName = "E:\\BaiduNetdiskDownload";


    class SubDirectoriesAndSize {
        final public long size;
        final public List<File> subDirectories;

        public SubDirectoriesAndSize(final long totalSize,
                                     final List<File> theSubDirs) {
            size = totalSize;
            subDirectories = Collections.unmodifiableList(theSubDirs);
        }
    }

    private SubDirectoriesAndSize getTotalAndSubDirs(final File file) {
        long total = 0;
        final List<File> subDirectories = new ArrayList<>();
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    if (child.isFile()) {
                        total += child.length();
                    } else {
                        subDirectories.add(child);
                    }
                }
            }
        }
        return new SubDirectoriesAndSize(total, subDirectories);
    }

    private long getTotalSizeOfFilesInDir(final File file) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<>();
            directories.add(file);
            while (!directories.isEmpty()) {
                final ArrayList<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<>();
                for (final File direcotry : directories) {
                    partialResults.add(executorService.submit(
                            new Callable<SubDirectoriesAndSize>() {
                                @Override
                                public SubDirectoriesAndSize call() throws Exception {
                                    return getTotalAndSubDirs(direcotry);
                                }
                            }
                    ));
                }
                directories.clear();
                for (Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture.get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        }finally {
            executorService.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.nanoTime();
        final long total = new ConcurrentTotalFileSize()
                .getTotalSizeOfFilesInDir(new File(fileName));
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }
}
