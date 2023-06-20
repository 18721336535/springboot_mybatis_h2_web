package com.zbq.thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ThreadPoolExecutorTest {
    public int pp=0;
    ThreadPoolExecutorTest(int h){
        pp =h;
    }
    public  void main1() throws ExecutionException, InterruptedException {
        int poolSize=1;
        int m = 1;
        AtomicInteger i = new AtomicInteger();
        ExecutorService executorService = new ThreadPoolExecutor(poolSize, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(m), r -> {
                    Thread t = new Thread( r);
                    int g = i.getAndAdd(1);
                    t.setName("MatrixReleseThreadName"+g);
                    t.setDaemon(true);
                    return t;
                }
                ,  new ThreadPoolExecutor.AbortPolicy()/*(r, executor) ->{
            System.out.println(r.toString()+"is rejected! in "+Thread.currentThread().getName());
            try {
//                Thread.sleep(0);
//                executor.getQueue().take();
                executor.getQueue().put(r);
                System.out.println("reput tast to ues!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        );


        List<String> lst = Arrays.asList("n1.csv","n2.csv","n3.csv","n4.csv");
        List<CompletableFuture> futureList = new ArrayList<>();
//        Function<String,String> getCSVFromRemote =(x)->{
//            System.out.println("--getCSVFromRemote--");
//            addfilecontent("-exe-getCSVFromRemote--");
//            if(x.isEmpty()) return "name";
//            else return ""; };

//        Function<String,List<String>> parseCSV =(x)->{
//            System.out.println("--parseCSV--");
//            addfilecontent("-exe-parseCSV--");
//            if(x.isEmpty()) return new ArrayList<String>();
//        else return Arrays.asList("zb","bc");};

        Function<String,Boolean> insertCsvToDB =(x)->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--insertCsvToDB--");
            addfilecontent(x);

            if(x.length()>1)
            return true;
            else return false;
        };

        Function<String,List<String>> parseCSV = (x) ->{
            try {
                Thread.sleep(1);
                System.out.println(x);
                addfilecontent(x + "\r\n");
                if (x.isEmpty()) return new ArrayList<String>();
                else return Arrays.asList(x);
            } catch (InterruptedException e) {
                return null;
            }
        };
        Function<String,Boolean> parseCSV1 = (x) ->{
                    try {
                        System.out.println("executed  ! in "+Thread.currentThread().getName());
                        if(x.isEmpty()) Thread.sleep(10000);
                        else Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("--x--"+x);
                    System.out.println("--i--"+i+pp);

                    addfilecontent(x);
                    i.getAndSet(i.get() + 1);
                    if(x.length()>=1)
                        return true;
                    else return false;
                };
        lst.parallelStream().forEach(
                x ->{ CompletableFuture f = CompletableFuture.supplyAsync(()->parseCSV1.apply(x),executorService);
//                    .thenApply((y) -> parseCSV)
//                    .thenApply(z ->insertCsvToDB);
                    futureList.add(f);
                });
        boolean j= true,r;
        for(CompletableFuture k :futureList){
            System.out.println("----"+k.get().toString());
            System.out.println("-end-i--"+i);
            System.out.println("--pp--"+pp);
        }
        executorService.shutdown();
    }

    public static void addfilecontent(String s) {
                FileWriter fw;
                try {
                    String p = "C:\\Users\\zbq\\Desktop\\gitTest\\Springboot_H2_web_repository_1\\springboot_mybatis_h2_web\\threadPool\\log.txt";
                     fw = new FileWriter(p, true); //就是这个构造方法的第二个参数，为true则是追加内容

                          BufferedWriter bw = new BufferedWriter(fw);
                          bw.write(s);
                          bw.close();
                          fw.close();
                     } catch (IOException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                    }


             }

//    static List<String> parseCSV(String x) {
//        try {
//            Thread.sleep(5000);
//            System.out.println(x);
//            addfilecontent(x + "\r\n");
//            if (x.isEmpty()) return new ArrayList<String>();
//            else return Arrays.asList(x);
//        } catch (InterruptedException e) {
//            return null;
//        }
//    }
    class RunnableImpl implements Runnable{
    String name = "";
    public  RunnableImpl(String name){
        this.name = name;
    };
    @Override
    public void run() {

    }
}


}
