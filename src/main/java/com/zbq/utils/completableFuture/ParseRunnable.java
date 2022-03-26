package com.zbq.utils.completableFuture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseRunnable implements Runnable {
    private static Logger logger = Logger.getLogger("ParseRunnable");
    private RunnableParamDto runnableParamDto;
    private int coreSize = 2;
    private int maxSize = 10;
    private int queueSize = 100;

    ParseRunnable(RunnableParamDto paramDto){
        runnableParamDto = paramDto;
    }
    AtomicInteger number  = new AtomicInteger(0);
    private ExecutorService executorService = new ThreadPoolExecutor(
            coreSize,
            maxSize,
            30,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(queueSize),
            r ->{
                Thread t = new Thread(r);
                t.setName("matrixReleaseThread"+number.getAndAdd(1));
                return t;
            },
            (r,exeCuter) ->{
                try{
                    logger.warning("runnable is rejected!");//Thread.currentThread().getName()
                    Thread.sleep(3000);
                    exeCuter.getQueue().put(r);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

    );

    Function<List<File>,Boolean> execUnit = files ->{
        AtomicBoolean allResult = new AtomicBoolean(true);
        files.stream().forEach(csvFile ->{
            //do parse and save data
            boolean result = parseAndSave(csvFile);
            if(!result) allResult.set(false);
        });
        return allResult.get();
    };

    @Override
    public void run() {
        List<CompletableFuture<Boolean>> futureList = new ArrayList<>();
        runnableParamDto.getFileGroups().parallelStream().forEach(fileGroup ->{
            CompletableFuture<Boolean> f = CompletableFuture.supplyAsync(()->execUnit.apply(fileGroup),executorService);
            futureList.add(f);
        });
        AtomicBoolean allResult = new AtomicBoolean(true);
        futureList.stream().forEach(future -> {
            try {
                allResult.set(future.get() && allResult.get());
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        });
        if(!allResult.get()) {
            //get error file and zipIt then send email to user
            new File("iem/error/Error.zip");
            //ZipUtility.zipIt();
        }
        //send success email to user
    }

    protected boolean parseAndSave(File csvFile){
        //todo
        return true;
    }
}
