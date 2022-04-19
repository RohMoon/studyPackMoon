package com.moon.java.collectionFramework.queue.linkedListQueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {

    @Test
    public void isLinkedListQueueConstructorWorkingThenExistQueue(){
        // Assignment
        LinkedListQueue<Integer> linkedListQueue ;

        // Act
        linkedListQueue = new LinkedListQueue<Integer>();

        //Assert
        assertTrue(linkedListQueue.isEmpty());

    }

    @Test
    public void isOfferWorkingThenSameNumberInQueue(){
        // Assignment
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();

        // Act
        for (int i = 1; i < 51; i++) {
            linkedListQueue.offer(i);
        }

        //Assert
        assertEquals(50,linkedListQueue.size());
        assertEquals(1,linkedListQueue.peek());

    }
    @Test
    public void isPollWorkingThenSameNumberInQueue(){
        // Assignment
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();

        // Act
        for (int i = 1; i < 51; i++) {
            linkedListQueue.offer(i);
        }
        linkedListQueue.poll();

        //Assert
        assertEquals(2,linkedListQueue.peek());


    }

    @Test
    public void isClearWorkingThenSameNumberInQueue(){
        // Assignment
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();

        // Act
        for (int i = 1; i < 51; i++) {
            linkedListQueue.offer(i);
        }
        linkedListQueue.clear();

        //Assert
        assertEquals(null,linkedListQueue.peek());

    }

    @Test
    public void isContainsWorkingThenSameNumberInQueue(){
        // Assignment
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();

        // Act
        for (int i = 1; i < 51; i++) {
            linkedListQueue.offer(i);
        }

        //Assert
        assertTrue(linkedListQueue.contains(47));

    }
}