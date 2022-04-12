package com.moon.java.collectionFramework;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayList3Test {

    @Before
    void CreateArray(){
        ArrayList3<Integer> array = new ArrayList3<Integer>(5);
        assertTrue(array.capacity==5);

    }
    @Test
    void testAddToArray(){
        ArrayList3<Integer> array = new ArrayList3<Integer>(5);
        array.add(0,1);
        array.add(1,2);
        array.add(2,3);
        array.add(3,4);
        array.add(4,5);

        assertTrue(array.get(3).equals(4));

//        array.add("baeam");
        assertFalse(array.get(5).equals("baeam"));
        System.out.println(array.get(5).equals("baeam"));
    }

    @Test
    void expandArray() {

    }

}