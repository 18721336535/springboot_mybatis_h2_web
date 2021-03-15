package com.zbq;

import com.mysql.jdbc.StringUtils;
import com.zbq.domain.User;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUsage {
    List<Userx> lst = Stream.of(new Userx(),
            new Userx("121","songj",18),
            new Userx("122","songj",18),
            new Userx("123","songj",19),
            new Userx("124","songj",19),
            new Userx("125","sxongj",20),
            new Userx("126","sxongj",20),
            new Userx("121","songj",20),
            new Userx("121","songj",20),
            new Userx("127","songjx",21))
            .collect(Collectors.toList());
    @Test
    public void filterTest(){
//        Set<Userx> set = new HashSet<>();
//        set.add(new Userx("121","songj",18));
//        set.add(new Userx("121","songj",18));
//        System.out.println(set.size());

        List<String> strs = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee","aa","bb");
        strs.parallelStream().filter(x->x.length()!=3).forEach(System.out::println);




        List<Userx>  list = lst.parallelStream()
                .filter(x -> !StringUtils.isNullOrEmpty(x.getUserIdNo()))
                .filter(x -> !"sxongj".equals(x.getUserName())).collect(Collectors.toList());
        list= list.parallelStream().distinct().collect(Collectors.toList());
        list =list.parallelStream().limit(3).collect(Collectors.toList());
        list = list.parallelStream().skip(1).collect(Collectors.toList());

    }
    @Test
    public void StreamClassMethodTest(){
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);
        stream2.forEach(System.out::println); // 0 2 4 6 8 10

        Stream<Double> stream3 = Stream.generate(Math::random).limit(2);
        stream3.forEach(System.out::println);
    }
    @Test
    public void StreamCacWordsF() throws FileNotFoundException {
        Map<String,Long> map = new ConcurrentHashMap<>();
//        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/zbq/Desktop/Words.txt"));
//        Stream<String> lineStream = reader.lines();

//        List<String> lst = Arrays.asList("java","1","java","1","2");
//        lst.parallelStream().forEach(x->{
//            if(!StringUtils.isNullOrEmpty(x)) map.put(x,(map.get(x)+1));
//        });


//        lineStream.forEach(System.out::println);
//        lineStream.forEach(x->{map.put(x,(map.get(x)+1));});
//        map.keySet().forEach(System.out::println);

        List<Userx> listt = lst;//Collections.synchronizedList(new ArrayList<Userx>());
                long t4 = System.currentTimeMillis();
                listt.parallelStream().forEach(tsm -> {//若是 forEachOrdered会保持原来list顺序
                         //            listt.add(tsm);//输出的listt会有元素丢失，线程安全的list则不会
                         try {
//                             System.out.println(tsm.getUserIdNo());
                             if(null != tsm.getUserIdNo()) {
                                 map.put(tsm.getUserIdNo(), null == map.get(tsm.getUserIdNo())? 1: map.get(tsm.getUserIdNo())+ 1);
                             }
                            } catch (Exception e) {
                                 e.printStackTrace();
                             }
                     });
        System.out.println(map.toString());
        //转成map,注:key不能相同，否则报错
//        Map<String, Integer> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
    }




}
     class Userx{
//        static int inc=0;
        private String userIdNo;
        private String userName;
        private int age;

        public String getUserIdNo() {
            return userIdNo;
        }

        public void setUserIdNo(String userIdNo) {
            this.userIdNo = userIdNo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Userx(){

        }
        public  Userx(String _userIdNo,String _userName,int _age){
//            inc +=1;
            userIdNo = _userIdNo;
            userName = _userName;
            age = _age;
        }
        @Override
        public int hashCode(){
            int result = 1;
            int prime = 3;
//            return inc;
            return result*prime+ (userName == null ? 0:userName.hashCode()+(userIdNo == null ? 0:userIdNo.hashCode()));
        }
        @Override
        public boolean equals(Object obj){
            Userx other = (Userx)obj;
            boolean a =  Objects.equals(userName,other.userName);
            boolean b= Objects.equals(userIdNo,other.userIdNo);
            return a && b;
//            return  a;
        }
        public String toSting(){
            return userIdNo+userName;
        }
    }