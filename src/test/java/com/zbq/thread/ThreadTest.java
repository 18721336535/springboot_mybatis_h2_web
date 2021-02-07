package com.zbq.thread;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class ThreadTest implements Runnable {
    private String url;
    private int startCount=0;
    private int endCount =0;
    private Map<String,Integer> countMap;

    ThreadTest(String url,int startCount,int endCount ,Map<String,Integer> cuntMp){
        this.url = url;
        this.startCount = startCount;
        this.endCount = endCount;
        this.countMap = cuntMp;
    }
    @Override
    public void run() {
        for(int i = startCount; i< endCount ; i++){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countMap.put("count"+ (++startCount),startCount);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Map<String,Integer> resultMp = new HashMap<>();
        ThreadTest taskt = new ThreadTest("http://baidu.com",0,10,resultMp);
        Thread td1 = new Thread(taskt);
        td1.start();

        ThreadTest taskt2 = new ThreadTest("http://baidu.com",10,20,resultMp);
        Thread td2 = new Thread(taskt2);
        td2.start();
//        Thread.sleep(1000);
        System.out.println(JSON.toJSONString(resultMp));
        System.out.println(JSON.toJSONString(resultMp.entrySet().size()));
    }
}
