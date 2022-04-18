package com.moon.java.collectionFramework.arrayList;

import com.moon.java.collectionFramework.singleLinkedList.SinglyLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    public void checkingSinglyLinkedMethod(){
    SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<Integer>();
        singlyLinkedList.add(91);
        singlyLinkedList.add(17);
        singlyLinkedList.add(7);
        singlyLinkedList.add(22);
        singlyLinkedList.add(2);
        singlyLinkedList.add(12);

        System.out.println("SinglyLinkedList.md In here");

        for (int i = 0; i < singlyLinkedList.size(); i++) {
            System.out.println("in -- > "+singlyLinkedList.get(i));
        }

        singlyLinkedList.add(3,1991);
        assertEquals(1991,singlyLinkedList.get(3));
        assertEquals(22,singlyLinkedList.get(4));
        assertTrue(singlyLinkedList.get(4).equals(22));

        System.out.println("\n\n after add to index");
        for (int i = 0; i < singlyLinkedList.size(); i++) {
            System.out.println("in -- > "+singlyLinkedList.get(i));
        }
        singlyLinkedList.remove(3);
        assertFalse(singlyLinkedList.get(4).equals(22));
        assertTrue(singlyLinkedList.get(3).equals(22));

        System.out.println("\n\n after add to index");
        for (int i = 0; i < singlyLinkedList.size(); i++) {
            System.out.println("in -- > "+singlyLinkedList.get(i));
        }
    }
    @Test
    public void testBeforeCloneMethod() {
        SinglyLinkedList<Integer> original = new SinglyLinkedList<>();
        original.add(10); // original에 10 추가

        SinglyLinkedList<Integer> copy = original;
        copy.add(20); // copy에 20 추가

        System.out.println("original list");
        for (int i = 0; i < original.size(); i++) {
            System.out.println("index "+ i + " data ="+ original.get(i) );
        }
        
        System.out.println("copy List");
        for (int i = 0; i < copy.size(); i++) {
            System.out.println("index "+ i + "data = "+ copy.get(i));
        }

        System.out.println("Original list reference : " + original);

        System.out.println("copy list reference : "+ copy);
    }
/*
    @Test
    public void testAfterCloneMethod() throws CloneNotSupportedException {

        SinglyLinkedList.md<Integer> original = new SinglyLinkedList.md<>();
        original.add(10); // Original에 10 추가

        SinglyLinkedList.md<Integer> copy = original;
        SinglyLinkedList.md<Integer> clone = (SinglyLinkedList.md<Integer>) original.clone();
        
        copy.add(20); // copy에 20 추가
        copy.add(30); // clone에 30추가
        
        System.out.println("original list");
        for (int i = 0; i < original.size(); i++) {
            System.out.println("index  "+i+ " data = "+original.get(i));
        }

        System.out.println("\n copy list ");
        for (int i = 0; i < copy.size(); i++) {
            System.out.println("index  "+i+"  data = "+ copy.get(i));
        }

        System.out.println("\n clone list");
        for (int i = 0; i < clone.size(); i++) {
            System.out.println("index  "+ i + "  data = "+ clone.get(i));
        }

        System.out.println("\n original list reference : " + original);
        System.out.println("copy list reference : "+ copy);
        System.out.println("clone List reference : "+clone );

    }
    */
}