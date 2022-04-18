package com.moon.java.collectionFramework.doubleLinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    @Test
    public void ifMakeNewDoublyLinkedListItHaveToSameNumberInThere (){
        //Arrange(준비) 준비 
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();

        //  Act 실행
        doublyLinkedList.add(10); // list {10}
        doublyLinkedList.addFirst(20); // list {20,10}
        doublyLinkedList.addLast(30); // list {20,10,30}
        doublyLinkedList.add(1,70); // list {20,70,10,30}

        // Assert(검증);
        assertTrue(doublyLinkedList.get(0)==20);
        assertTrue(doublyLinkedList.get(1)==70);
        assertTrue(doublyLinkedList.get(2)==10);
        assertTrue(doublyLinkedList.get(3)==30);

    }
    @Test
    public void ifChangeAndRemoveNumberItHaveToSameResult(){
        //Arrange(준비) 준비
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();


        doublyLinkedList.add(10); // list {10}
        doublyLinkedList.addFirst(20); // list {20,10}
        doublyLinkedList.addLast(30); // list {20,10,30}
        doublyLinkedList.add(1,70); // list {20,70,10,30}


        for (int i = 0; i < doublyLinkedList.size(); i++) {
            System.out.println(doublyLinkedList.get(i));
        }
        System.out.println("\n\n");

        //  Act 실행
        doublyLinkedList.remove(); //  list {70,10,30}
        doublyLinkedList.remove(2);//  list {70,10}
        System.out.println("\n\n");
        doublyLinkedList.remove("70");//  list {10}
        System.out.println("\n\n");
        doublyLinkedList.set(0,77); // list {77}

        //  ASSERT
        assertEquals(doublyLinkedList.get(0),77);
        assertTrue(doublyLinkedList.contains(77));
        assertEquals(0,doublyLinkedList.indexOf(77));
        System.out.println(doublyLinkedList.size());
        assertEquals(2,doublyLinkedList.size());
        assertFalse(doublyLinkedList.isEmpty());
    }
}