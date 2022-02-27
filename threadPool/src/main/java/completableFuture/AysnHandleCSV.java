package completableFuture;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class AysnHandleCSV {
    int pp=0;
    AysnHandleCSV(int h){
        pp =h;
    }
    public  void main1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(10,20,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), r -> {
            Thread t = new Thread(r);
            return t;
        },new ThreadPoolExecutor.AbortPolicy());

        AtomicInteger i = new AtomicInteger();
        List<String> lst = Arrays.asList("n1.csv","n2.csv","n3.csv","1","j");
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
                Thread.sleep(10000);
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
                Thread.sleep(5000);
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
                        if(x.isEmpty()) Thread.sleep(10000);
                        else Thread.sleep(1000);
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
                x ->{ CompletableFuture f = CompletableFuture.supplyAsync(()->parseCSV1.apply(x)
                    /*{
                    try {
                        if(x.isEmpty()) Thread.sleep(10000);
                        else Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("--x--"+x);
                    System.out.println("--i--"+i);

                    addfilecontent(x);
                    i.getAndSet(i.get() + 1);
                    if(x.length()>=1)
                        return true;
                    else return false;
                }*/,executorService);
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


}
