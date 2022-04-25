package com.moon.java.comparableComparator;

import java.util.Arrays;

public class Comparator {

    public static void main(String[] args) {
        MyIntegerss [] arr = new MyIntegerss[10];
        //객체 배열 초기화 (랜덤 값으로)
        for (int i = 0; i < 10; i++) {
            arr[i] = new MyIntegerss((int)(Math.random()*100));
        }

        //정렬 이전
        System.out.print("정렬 전 : ");
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i].value + "  ");
        }
        System.out.println();

        Arrays.sort(arr.comp);
        //정렬 이후
        System.out.print("정렬 후 :  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i].value + " " );
        }
        System.out.println();
    }

    static java.util.Comparator<MyIntegerss> comp = new java.util.Comparator<MyIntegerss>() {
        @Override
        public int compare(MyIntegerss o1, MyIntegerss o2) {
            return o2.value - 01.value;
        }
    };
}
class MyIntegerss{
    int value;
    public MyIntegerss (int value){
        this.value = value;
    }
}
