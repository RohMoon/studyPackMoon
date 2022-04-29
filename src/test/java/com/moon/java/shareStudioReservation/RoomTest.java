package com.moon.java.shareStudioReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void isMakingRoomObject(){ //룸 생성하면 룸이 생성되는가?

        Room room = new Room(10,5000);


        for (int i = 0; i < room.timeTable.length(); i++) {

            System.out.println("room.timeTable["+i+"]  ===> " + room.timeTable.get(i));
            assertTrue(room.timeTable.get(i)==null);
        }

        assertEquals(10,room.maxCapacity);
        assertEquals(5000,room.standardPrice);
    }

    @Test
    void getTimeCountTest() {// it is basically private Method
        Room room = new Room(10,5000);

        // startTime < endTime
//        assertEquals(2,room.getTimeCount(3,5));

        // endTime < StartTime
//        assertEquals(4,room.getTimeCount(23,3));

    }

    @Test
    void theseTimeEmptyCheck() {
        Room room = new Room(10,5000);

        room.addTime(4,0,"dr.ironMan");

        assertFalse("ironMan".equals(room.timeTable.get(4)));
        assertFalse("dr.Strange".equals(room.timeTable.get(3)));
        assertEquals(null,(room.timeTable.get(0)));

    }


    @Test
    void isRoomAddTest(){
        Room room = new Room(10,5000);
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




}