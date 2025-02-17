package volatile_test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class VolatileTest {

    private static volatile int sv = 3;

    public static void add(){
        sv += 3;
    }

    public static void sub(){
        sv -=3;
    }

    public static void func1(){
    }

}
