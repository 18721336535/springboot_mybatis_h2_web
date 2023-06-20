package java_threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyExtendTreadPoolExecutor  extends ThreadPoolExecutor{
    private static Logger logger = Logger.getLogger("MyExtendTreadPoolExecutor");
    public MyExtendTreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime,
                                     TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory,
                                     RejectedExecutionHandler handler){
        super(corePoolSize,maxPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);
    }

    @Override
    public void execute(Runnable task){
        super.execute(wrapMyRunnable(task,trace(),Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task){
        return super.submit(wrapMyRunnable(task,trace(),Thread.currentThread().getName()));
    }

    private Runnable wrapMyRunnable(final Runnable task,Exception trace,String treadName){
        return new Runnable() {
            @Override
            public void run() {
                try{ task.run(); }catch (Exception e){
                    logger.log(Level.SEVERE,treadName+trace.getMessage());
                    throw e;
                }
            }
        };
    }
    private Exception trace(){
        return new Exception("my submit");
    }

    @Override
    protected void beforeExecute(Thread t,Runnable r){
        System.out.println("start execute:"+((MyTask)r).name);
    }

    @Override
    protected void afterExecute(Runnable r,Throwable throwable){
        System.out.println("done execute:"+((MyTask)r).name);
    }
    @Override
    protected void terminated(){
        System.out.println("threadPool exit.");
    }

    static class MyTask implements Runnable{
        public String name;
        public MyTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            //todo
        }
    }

    public static void main(String[] args){
        AtomicInteger i = new AtomicInteger();
        MyTask task= new MyExtendTreadPoolExecutor.MyTask("Task1");
        MyExtendTreadPoolExecutor executor = new MyExtendTreadPoolExecutor(
                        2,
                        3,
                        30,
                        TimeUnit.SECONDS,
                        //队列，在没有空闲线程的时候，task 暂存在队列中。
                        new ArrayBlockingQueue<>(50),
                        //自定义 线程工厂。定义线程池创建线程的方式。
                        r -> {
                            Thread t = new Thread( r);
                            int g = i.getAndAdd(1);
                            t.setName("MatrixReleseThreadName"+g);
                            t.setDaemon(true);//设置守护线程：主线程退出，销毁线程池。
                            return t;
                        },
                        //task 没法提交时（队列已经爆满），线程池如何处理task
                        new ThreadPoolExecutor.AbortPolicy());

        executor.submit(task);

    }

}
