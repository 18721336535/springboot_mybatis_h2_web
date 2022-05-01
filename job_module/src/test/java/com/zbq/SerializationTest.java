package com.zbq;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationTest {
    /*151个建议:12-P17总结：
      序列化/反序列化 注意：
      序列对象版本号默认每个版本类对应的序列号都不一样，如果显示地声明则可以避免因新版本新增属性产生反序列化异常，提高程序的健壮性。
      显示声明版本号保证每个版本的版本号都一样，“善意的谎言：告诉JVM类版本没有改变”。
      1.final 直接量 属性 反序列化时会根据最新版本类重新计算其值
      2.反序列化时不会执行构造函数
      总之， 一般属性反序列化时取旧版本值。final 和 static或者static final属性 反序列化时 取新版本值；但是有
      三种情况例外：构造方法赋值，一般方法赋值，复杂对象属性（非简单对象，简单对象：8基本类型，数组，非new String()的string对象）
     其它：序列化的类 属性加上transient关键字，该类就会失去分布式部署的功能。
     */
    public static void main(String[] args)throws Exception{
//        Serialize();
        List list=Deserialize();
        for (int i=0;i<list.size();i++){
            Person p=(Person)list.get(i);
            System.out.println(p.toSting());
        }
    }
    //序列化方法
    public static void Serialize() throws Exception{
        //将多个对象保存在一个list中，然后将list当作一个对象存入到文件中（list已经实现了Serializable接口）
        List<Person> list= new ArrayList<Person>();
        for (int i=0;i<1;i++){
//            Person p=new Person("name1","林冲");
            Person p=new Person();
            list.add(p);
        }
        ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(new File("D:/person.txt"),true));
        oo.writeObject(list);
        oo.close();
    }
    //反序列化方法
    public static List Deserialize() throws Exception{
        ObjectInputStream oi=new ObjectInputStream(new FileInputStream(new File("D:/person.txt")));
        List list=(List)oi.readObject();
        return list;
    }
}

class Person implements Serializable {
    private static final long serialVersionId = 37569L;
    private int id = 0;
    private  String name = "name1";
//    private  String name = "宋江1";
    private  final String name2 = "name2";
//    private  final String name2 = "宋江2";
//    Person(String name, String name2){
//        this.name2 = name2;
//        this.name = name;
//    }
    public String toSting(){
        return name+name2;
    }
}
