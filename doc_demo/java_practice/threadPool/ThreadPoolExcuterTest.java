//package threadPool;
//
//import java.util.Arrays;
//import java.util.concurrent.*;
//
//public class ThreadPoolExcuterTest {
//
//    public void main(String args[]){
//
//        Arrays.stream(new String[20]).forEach( threadName-> {
//            Runnable runnable = new TaskBusyWithoutResult(threadName);
//            threadPoolExecutor.submit(runnable);
//        });
//    }
//
//
//    private ExecutorService threadPoolExecutor  = new ThreadPoolExecutor(
//            1, 2,
//        1000, 1,
//        new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),
//        new ThreadPoolExecutor.AbortPolicy()
//    );
//
//    public static ExecutorService newFixedThreadPool(int var0) {
//        return new ThreadPoolExecutor(var0, var0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
//    }
//
//    public static ExecutorService newSingleThreadExecutor() {
//        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
//    }
//
//    public static ExecutorService newCachedThreadPool() {
//        return new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue());
//    }
//
//    public static ScheduledExecutorService newScheduledThreadPool(int var0) {
//        return new ScheduledThreadPoolExecutor(var0);
//    }
//
//    class TaskBusyWithoutResult implements Runnable {
//        String threadName;
//        public TaskBusyWithoutResult(String threadName) { this.threadName = threadName;}
//        @Override
//        public void run() {
//            System.out.println("线程"+Thread.currentThread()+"开始运行");
//            int i=10000*10000;
//            while(i>0) {i--;}
//            System.out.println("线程"+Thread.currentThread()+"运行结束");
//        }
//    }
//}
//
