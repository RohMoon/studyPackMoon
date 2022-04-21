package com.moon.java.collectionFramework.queue.deque.linkedListDeque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListDequeTest {

    @Test
    void offerFirst() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);

        assertEquals(77,linkedListDeque.getFirst());
    }

    @Test
    void offer() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);

        assertEquals(80,linkedListDeque.peekLast());
    }

    @Test
    void offerLast() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(87,linkedListDeque.peekLast());
    }

    @Test
    void pollFirst() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        linkedListDeque.pollFirst();

        assertEquals(80,linkedListDeque.peekFirst());
    }

    @Test
    void remove() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        linkedListDeque.remove();

        assertEquals(80,linkedListDeque.peekFirst());
    }

    @Test
    void removeFirst() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();



        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        linkedListDeque.removeFirst();

        assertEquals(80,linkedListDeque.peekFirst());
    }

    @Test
    void pollLast() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        linkedListDeque.pollLast();

        assertEquals(80,linkedListDeque.peekLast());
    }

    @Test
    void removeLast() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        linkedListDeque.removeLast();

        assertEquals(80,linkedListDeque.peekLast());
    }

    @Test
    void peek() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(77,linkedListDeque.peek());
    }

    @Test
    void peekFirst() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(77,linkedListDeque.peekFirst());
    }

    @Test
    void peekLast() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(77);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(87,linkedListDeque.peekLast());
    }

    @Test
    void getFirst() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(80);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(80,linkedListDeque.getFirst());
    }

    @Test
    void getLast() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(80);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(87,linkedListDeque.getLast());
    }

    @Test
    void size() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(80);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertEquals(3,linkedListDeque.size());
    }

    @Test
    void isEmpty() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        assertTrue(linkedListDeque.isEmpty());
    }

    @Test
    void contains() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(80);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        assertTrue(linkedListDeque.contains(80));

    }

    @Test
    void clear() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<Integer>();
        linkedListDeque.offerFirst(80);
        linkedListDeque.offer(80);
        linkedListDeque.offerLast(87);

        linkedListDeque.clear();

        assertTrue(linkedListDeque.isEmpty());
    }
}