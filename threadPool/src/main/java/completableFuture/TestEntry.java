package completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestEntry implements Runnable{
    int pp = 9;
    TestEntry(int i){
        pp = i;
    }
    public static void main(String args[]){
        int i =0;
        ExecutorService exe= Executors.newFixedThreadPool(1);
        exe.submit(new TestEntry(8));
        exe.shutdown();
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
