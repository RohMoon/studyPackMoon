package com.moon.java.collectionFramework.array;

import java.util.Scanner;

public class Array {

    public static void main(String[] args) {
        String[] fruit = new String[3];
        fruit[0] = "apple";
        fruit[1] = "banana";
        fruit[2] = "pear";

        //foreach 자바 1.5버전 부터 자바에도 Foreach가 등장
        for (String eachFruit : fruit) {
            System.out.println(eachFruit);
        }
        int[] score = {93, 75, 95, 76, 70};
        int sum = 0;
        for (int eachScore : score) {
                sum += eachScore;
        }
        double avg = (double) sum/score.length;
        System.out.println("점수 합계 : "+ sum); 
        System.out.println("점수 평균 : "+ avg);

        int[] num = new int[5];

        int max, min;
        Scanner sc = new Scanner(System.in);
        System.out.println("5개의 정수를 입렷하시오");
        for (int i = 0; i < num.length; i++) {
            num[i] = sc.nextInt();
        }
        max = num[0];
        min = num[0];
        for (int nextVal: num) {
            if (max<nextVal){ // 최대값 비교
                max = nextVal;// 최대값 할당
            }
            if (min > nextVal){ // 최소값 비교
                min = nextVal; //최소값 할당
            }
        }
        System.out.println("MaxNum :\n"+max);
        System.out.println("MinNum :\n"+min);

        int [] scores = {78,70,65,98,58};
        int summary = 0;
        for (int i : scores) {
            summary += i;
        }
        System.out.println("Scores total : \n"+summary);
    }

}
