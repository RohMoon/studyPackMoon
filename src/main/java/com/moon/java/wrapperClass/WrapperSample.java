package com.moon.java.wrapperClass;

public class WrapperSample {
    public static void main(String[] args) {
        Integer num = new Integer(17); //  박싱
        int n = num.intValue(); //언박싱
        System.out.println(n);
    }
}
