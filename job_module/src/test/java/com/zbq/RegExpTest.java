package com.zbq;

public class RegExpTest {
    public static void main(String args[]){
        System.out.println("00".matches("^[0-9|\\-]?(\\d)+(\\.)?(\\d)+$"));
        String str = "00";
        System.out.println(Float.parseFloat(str));
//        System.out.println(Double.parseDouble(str));
//        Double d= 1/1.1d;
        String s1= null+"";
        String s2= null+"";
        System.out.println(s1.equals(s2));

    }
}
