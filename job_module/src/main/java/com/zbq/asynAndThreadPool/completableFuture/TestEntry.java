package com.zbq.asynAndThreadPool.completableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestEntry implements Runnable{
    int pp = 9;
    TestEntry(int i){
        pp = i;
    }
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        int i =0;
//        ExecutorService exe= Executors.newFixedThreadPool(1);
//        exe.submit(new TestEntry(8));
//        exe.shutdown();
        CompletableFuture<List<String>> f = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
                System.out.println("hi andy");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Arrays.asList("d","f","5");
        }).thenApplyAsync((x)->{System.out.println("hello-");return new ArrayList<>();});
        System.out.println(f.get());
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
    }

    @Override
    public void run() {
        try {
//            Thread.sleep(10000);
            new AysnHandleCSV(pp).main1();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
