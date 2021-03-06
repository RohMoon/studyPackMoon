package com.moon.java.shareStudioReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void isMakingRoomObject(){ //룸 생성하면 룸이 생성되는가?

        Room room = new Room("1번방",10,5000);


        for (int i = 0; i < room.timeTable.length(); i++) {

            System.out.println("room.timeTable["+i+"]  ===> " + room.timeTable.get(i));
            assertTrue(room.timeTable.get(i)==null);
        }

        assertEquals(10,room.maxCapacity);
        assertEquals(5000,room.standardPrice);
    }

    @Test
    void getTimeCountTest() {// it is basically private Method
        Room room = new Room("1번방", 10,5000);

        // startTime < endTime
//        assertEquals(2,room.getTimeCount(3,5));

        // endTime < StartTime
//        assertEquals(4,room.getTimeCount(23,3));

    }

    @Test
    void theseTimeEmptyCheck() {
        Room room = new Room("1번방",10,5000);

        room.addTime(4,0,"dr.ironMan");

        assertFalse("ironMan".equals(room.timeTable.get(4)));
        assertFalse("dr.Strange".equals(room.timeTable.get(3)));
        assertEquals(null,(room.timeTable.get(0)));

    }


    @Test
    void isRoomAddTest(){
        Room room = new Room("1번방",10,5000);
        room.addTime(3,5,"dr.Strange");


        for (int i = 0; i < 24; i++) {
            System.out.println(room.timeTable.get(i));
        }


        room.addTime(4,0,"dr.ironMan");


        for (int i = 0; i < 24; i++) {
            System.out.println(room.timeTable.get(i));
        }

        assertFalse("ironMan".equals(room.timeTable.get(4)));
        assertTrue("dr.Strange".equals(room.timeTable.get(3)));


    }

    @Test
    void removeTime() {
        Room room = new Room("1번방",10,5000);

        room.addTime(3,5,"dr.Strange");

        assertEquals("dr.Strange",room.timeTable.get(3));
        assertEquals("dr.Strange",room.timeTable.get(4));

        room.removeTime(3,4,"dr.Strange");
        assertEquals(null,room.timeTable.get(3));
        assertEquals("dr.Strange",room.timeTable.get(4));

        for (int i = 0; i < 24; i++) {
            System.out.println(room.timeTable.get(i));
        }
    }

    @Test
    void clearTest() {
        Room room = new Room("1번방",10,5000);

        room.addTime(1,5,"dr.Strange");
        room.addTime(5,8,"IronMan");
        room.addTime(10,20,"SpiderMan");
        room.addTime(20,1,"CaptainSungpo");

        System.out.println("\n\n");
        System.out.println("Get Size ==> " + room.timeTable.getSize());
        System.out.println("\n\n");

        for (int i = 0; i < 23; i++) {
            System.out.println(room.timeTable.get(i));
        }

        assertTrue(room.timeTable.get(1)!=null);
        assertTrue(room.timeTable.get(3)!=null);
        assertTrue(room.timeTable.get(5)!=null);
        assertTrue(room.timeTable.get(10)!=null);
        assertTrue(room.timeTable.get(23)!=null);

        room.clear();

        assertFalse(room.timeTable.get(1)!=null);
        assertFalse(room.timeTable.get(3)!=null);
        assertFalse(room.timeTable.get(5)!=null);
        assertFalse(room.timeTable.get(10)!=null);
        assertFalse(room.timeTable.get(23)!=null);

        System.out.println("---------------------");
        for (int i = 0; i < 23; i++) {
            System.out.println(room.timeTable.get(i));
        }

    }
}