package com.moon.java.wrapperClass;

public class WrapperClassCompareSample {
    public static void main(String[] args) {

        Integer num = new Integer(10); //래퍼 클래스1
        Integer num2 = new Integer(10); //래퍼 클래스2
        int i = 10; // 기본 타입
        
        System.out.println("래퍼 클래스 == 기본 타입 : "+ (num ==i ));// true
        System.out.println("래퍼 클래스.equals(기본 타입) : "+num.equals(i));// true
        System.out.println("래퍼 클래스 == 래퍼 클래스 : "+ (num == num2));// false
        System.out.println("래퍼 클래스.equals(래퍼클래스) : "+num.equals(num2));// true

    }
}
