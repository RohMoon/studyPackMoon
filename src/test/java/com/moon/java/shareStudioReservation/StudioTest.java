package com.moon.java.shareStudioReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudioTest {


    @Test
    void makeStudioThenStudioIsExist() {
        Studio studio = new Studio("ansan", "jungang");

        assertEquals(studio.local, "ansan");
        assertEquals(studio.local, "junang");
    }

    @Test
    void getSizeTest() {
        Studio studio = new Studio("ansan", "jungang");

        studio.addRoom("conference", 10, 3000);
        studio.addRoom("port", 10, 5000);
        studio.addRoom("dock", 10, 7000);
        studio.addRoom("taskRoom", 10, 8000);
        studio.addRoom("FairRoom", 10, 10000);

        assertEquals(5,studio.size());
    }

    @Test
    void isAddRoomThoseWorking() {
        Studio studio = new Studio("ansan", "jungang");

        studio.addRoom("conference", 10, 3000);
        studio.addRoom("port", 10, 5000);
        studio.addRoom("dock", 10, 7000);
        studio.addRoom("taskRoom", 10, 8000);
        studio.addRoom("FairRoom", 10, 10000);

        assertEquals("conference", studio.getRoom(0).name);
        assertEquals(10, studio.getRoom(0).maxCapacity);
        assertEquals(3000, studio.getRoom(0).standardPrice);

        assertEquals("port", studio.getRoom(1).name);
        assertEquals(10, studio.getRoom(1).maxCapacity);
        assertEquals(5000, studio.getRoom(1).standardPrice);

        assertEquals("dock", studio.getRoom(2).name);
        assertEquals(10, studio.getRoom(2).maxCapacity);
        assertEquals(7000, studio.getRoom(2).standardPrice);

        assertEquals("taskRoom", studio.getRoom(3).name);
        assertEquals(10, studio.getRoom(3).maxCapacity);
        assertEquals(8000, studio.getRoom(3).standardPrice);

        assertEquals("FairRoom", studio.getRoom(4).name);
        assertEquals(10000, studio.getRoom(4).standardPrice);
        assertEquals(10, studio.getRoom(4).maxCapacity);

        for (int i = 0; i < studio.size(); i++) {
            System.out.println(studio.getRoom(i).name);
        }
    }

    @Test
    void isRemoveTest() {
        Studio studio = new Studio("ansan", "jungang");

        studio.addRoom("conference", 10, 3000);
        studio.addRoom("port", 10, 5000);
        studio.addRoom("dock", 10, 7000);

        assertTrue(studio.remove("port"));
        studio.remove("dock");
        assertFalse(studio.remove("dock"));
    }

    @Test
    void setLocalAndSuburbTest() {
        Studio studio = new Studio("ansan", "jungang");

        assertTrue(studio.local.equals("ansan"));

        studio.setLocalAndSuburb("newYork","queens");

        assertFalse(studio.local.equals("ansan"));
        assertTrue(studio.local.equals("newYork"));
    }

    @Test
    void setLocalTest() {
        Studio studio = new Studio("ansan", "jungang");

        assertTrue(studio.local.equals("ansan"));

        studio.setLocal("newYork");

        assertFalse(studio.local.equals("ansan"));
        assertTrue(studio.local.equals("newYork"));
    }

    @Test
    void setSuburbTest() {
        Studio studio = new Studio("ansan", "jungang");

        assertTrue(studio.suburb.equals("jungang"));

        studio.setSuburb("queens");

        assertFalse(studio.suburb.equals("jungang"));
        assertTrue(studio.suburb.equals("queens"));
    }

    @Test
    void isEmptyTest() {
        Studio studio = new Studio("ansan", "jungang");

        assertTrue(studio.isEmpty());
    }

    @Test
    void isGetNameTest(){
        Studio studio = new Studio("ansan", "jungang");

        studio.addRoom("conference", 10, 3000);
        studio.addRoom("port", 10, 5000);
        studio.addRoom("dock", 10, 7000);

        assertEquals("port",studio.getName(1));
    }
}