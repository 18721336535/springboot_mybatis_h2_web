package com.zbq;
public class VolatileTest extends Thread{
    public VolatileTest(){}

    @Override
    public void run() {
            try {
                TestC.one();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    public static void main(String args[]) throws InterruptedException {
        VolatileTest rn1 = new  VolatileTest();
        VolatileTest0 rn2 = new  VolatileTest0();
        rn1.start();
        rn2.start();
    }
}

 class VolatileTest0 extends Thread {
    int num = 0;

    public VolatileTest0() {}

    @Override
    public void run() {
        try {
            TestC.two();
        } catch (Exception e) {
            e.printStackTrace();
        }
//            THREAD.YIELD();
//        }
    }
}
class TestC {
    static volatile int i = 0, j = 1000;
    static void one() throws InterruptedException { System.out.println(Thread.currentThread().getId()+">one i="+ ++i);/*Thread.sleep(100);*/ }
    static void two() throws InterruptedException {
        System.out.println(Thread.currentThread().getId()+"》two i=" + i);
    }
}
// class TaskOne implements Runnable{
//
//    public void run(){
//        VolatileTest.dto.a();
//    }
//}
//
//class TaskTwo implements Runnable{
//
//    public void run(){
//        VolatileTest.dto.a();
//    }
//}
//
//class Dto {
//    int i = 0;
//    int j = 0;
//
//    public void a(){
//         i = 10;
//        System.out.println(Thread.currentThread().getId()+":"+i);
//    }
//
//    public void b() throws InterruptedException {
//        //线程2执行的代码
//        j = i;
//        System.out.println(Thread.currentThread().getId()+":"+j);
//    }


//}
