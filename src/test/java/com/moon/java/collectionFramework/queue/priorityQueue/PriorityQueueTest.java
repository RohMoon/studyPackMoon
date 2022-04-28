package com.moon.java.collectionFramework.queue.priorityQueue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void PriorityQueueTestMethod(){
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();

        priorityQueue.offer(11);
        priorityQueue.offer(22);
        priorityQueue.offer(223);
        priorityQueue.offer(72);
        priorityQueue.offer(17);

        System.out.println(Arrays.toString(priorityQueue.toArray()));

        assertEquals(11,priorityQueue.peek());
        assertTrue(priorityQueue.size()==5);

    }
    @Test
    void thesePriorityWorkingTest (){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        for (int i = 100; i > 1; i--) {
            priorityQueue.offer(i);
        }
        System.out.println(Arrays.toString(priorityQueue.toArray()));
        assertEquals(2,priorityQueue.peek());
    }
    @Test
    void thesePriorityAfterPollWorkingTest (){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        for (int i = 100; i > 1; i--) {
            priorityQueue.offer(i);
        }
        priorityQueue.poll();

        System.out.println(Arrays.toString(priorityQueue.toArray()));
        assertEquals(3,priorityQueue.peek());
    }

}