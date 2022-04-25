package com.moon.java.comparableComparator;


    import java.util.Arrays;
import java.util.Comparator;

    public class ComparatorTest {
        public static void main(String[] args) {
            MyIntegers[] arr = new MyIntegers[10];

            //객체 배열 초기화 (랜덤 값으로 )
            for (int i = 0; i < 10; i++) {
                arr[i] = new MyIntegers((int) (Math.random() * 100));
            }

            //정렬 이전
            System.out.println("정렬 전 : ");
            for (int i = 0; i < 10; i++) {
                System.out.print(arr[i].value + " ");
            }
            System.out.println();

            Arrays.sort(arr); // MyInteger에 대한 Comparable 을 사용하여 정렬
            //정렬 이후
            System.out.print("정렬 후 : ");
            for (int i = 0; i < 10; i++) {
                System.out.print(arr[i].value + "  ");
            }
            System.out.println();
        }

    }

    class MyIntegers implements Comparable<MyIntegers> {
        int value;

        public MyIntegers(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(MyIntegers o) {
            return o.value - this.value;
        }
    }
