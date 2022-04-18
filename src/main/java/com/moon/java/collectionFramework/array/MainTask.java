package com.moon.java.collectionFramework.array;

import com.moon.java.collectionFramework.arrayList.ArrayList;

public class MainTask {
    public static void main(String[] args) {
        System.out.println("=====짧은머리 개발자=====");
        ArrayList<Integer> arr = new ArrayList<Integer>();
        System.out.println("배열 크기 : " + arr.getCapacity());
        System.out.println("데이터 삽입 1~5");
        for(int i = 0; i < 5; i++) {
            arr.add((i+1));
        }

        //출력
        System.out.println("\n==출력==");
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(i + "번째 : " + arr.get(i) + " | ");
        }
        System.out.println("\n배열 크기 : " + arr.getCapacity());



        arr.add(1, 6);
        arr.add(4, 7);
        System.out.println("\n==출력2==");
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(i + "번째 : " + arr.get(i) + " | ");
        }
        System.out.println("\n배열 크기 : " + arr.getCapacity());



        System.out.println("1번째 요소 삭제 : " + arr.remove(1));
        System.out.println("\n==출력3==");
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(i + "번째 : " + arr.get(i) + " | ");
        }
        System.out.println("\n배열 크기 : " + arr.getCapacity());



        System.out.println("\n==출력4==");
        while(arr.size() > 0) {
            System.out.println("0번째 삭제: " + arr.remove(0));
        }
        System.out.println("\n배열 크기 : " + arr.getCapacity());


        System.out.println("데이터 삽입 1~5");
        for(int i = 0; i < 5; i++) {
            arr.add((i*2));
        }

        //출력
        System.out.println("\n==출력6==");
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(i + "번째 : " + arr.get(i) + " | ");
        }
        System.out.println("\n배열 크기 : " + arr.getCapacity());


        System.out.println("초기화");
        arr.reset();
        System.out.println("\n==출력7==");
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(i + "번째 : " + arr.get(i) + " | ");
        }
        System.out.println("\n배열 크기 : " + arr.getCapacity());
    }
}
