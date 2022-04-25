package com.moon.java.comparableComparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    void comaparableStudentTest() {
        Student a = new Student(17, 2); // 17살 2반
        Student b = new Student(18, 1); // 18살 1반

        int isBig = a.compareTo(b); // a 자기자신과 b객체를 비교한다.

        if (isBig > 0) {
            System.out.println("a객체가 b객체보다 크다." );
        }
        else if(isBig == 0 ){
            System.out.println("두 객체의 크기가 같다.");
        }
        else {
            System.out.println("a객체가 b객체보다 작다.");
        }

        assertEquals(-1,isBig);

    }

    @Test
    void intMinMaxValue (){
        int min = Integer.MIN_VALUE; // MIN_VALUE 는  -2,147,483,648 이다.
        int max = Integer.MAX_VALUE; // MAX_VALUE 는 2,147,483,647 이다.

        System.out.println("min - 1 = " + (min - 1));
        System.out.println("max + 1 = " + (max + 1));
    }

    @Test
    void comparatorTest (){

        Student a = new Student(17, 2);	// 17살 2반
        Student b = new Student(18, 1);	// 18살 1반
        Student c = new Student(15, 3);	// 15살 3반

        // a객체와는 상관 없이 b와 c객체를 비교한다.
        int isBig = a.compare(b, c);

        if(isBig > 0) {
            System.out.println("b객체가 c객체보다 큽니다.");
        }
        else if(isBig == 0) {
            System.out.println("두 객체의 크기가 같습니다.");
        }
        else {
            System.out.println("b객체가 c객체보다 작습니다.");
        }
    }
}