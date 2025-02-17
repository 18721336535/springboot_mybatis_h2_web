package hashmap_test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapMissingData {

        static  int[] sie = {16};
        public static void main(String[] args) throws InterruptedException {

            HashMap<String, Object> mp = new HashMap();

            Thread t1 = new Thread(() -> mp.put("1", "1"), "thread1");
            Thread t2 = new Thread(() -> mp.put("a", "a"),"thread2");

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("");
        }

}
