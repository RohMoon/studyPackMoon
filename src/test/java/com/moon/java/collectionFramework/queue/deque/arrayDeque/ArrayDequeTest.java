package com.moon.java.collectionFramework.queue.deque.arrayDeque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDequeTest {

    @Test
    void isConstructorWorkingThenExistDeque() {

        ArrayDeque<Integer> defaultCapacityArrayDeque = null;
        ArrayDeque<Integer> arrayDeque = null;

        defaultCapacityArrayDeque = new ArrayDeque<Integer>(); // default Capacity
        arrayDeque = new ArrayDeque<Integer>(200);// pick capacity

        assertTrue(defaultCapacityArrayDeque.isEmpty());
        assertTrue(arrayDeque.size() == 0);
        assertTrue(defaultCapacityArrayDeque.peekFirst() == null);
        assertTrue(defaultCapacityArrayDeque.peekLast() == null);

    }

    @Test
    void isOfferWorkingThenExistDeque() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>(500);// pick capacity

        for (int i = 0; i < 250; i++) {
            arrayDeque.offerFirst(i);
            if (i % 5 == 0) {
                System.out.print("\n");
            }
            System.out.print(" >" + i + "< ");
        }
        for (int i = 500; i > 250; i--) {
            arrayDeque.offerLast(i);
            if (i % 5 == 0) {
                System.out.print("\n");
            }
            System.out.print(" >" + i + "< ");
        }

        assertEquals(500, arrayDeque.size());

    }

    @Test
    void isPollWorkingThenExistDeque() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>(500);// pick capacity

        for (int i = 0; i < 251; i++) {
            arrayDeque.offerFirst(i);
            if (i % 5 == 0) {
                System.out.print("\n");
            }
            System.out.print(" >" + i + "< ");
        }
        arrayDeque.pollLast();
        arrayDeque.pollFirst();
        System.out.print("  peekLast=====>"+arrayDeque.peekLast());
        System.out.print("  peekFirst=====>"+arrayDeque.peekFirst());
        System.out.print("  Size=====>"+arrayDeque.size());
        assertEquals(249, arrayDeque.peekLast());

    }
}