package com.moon.java.collectionFramework.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    public void numberShouldBeThereifSomeNumberPushToStack(){
        //준비
        Stack stack = new Stack(5);
        //실행
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        //검증
        assertEquals(40, stack.pop());
        assertEquals(30,stack.pop());
        stack.clear();
        assertEquals(0,stack.size());
        assertTrue(stack.empty());
    }
}