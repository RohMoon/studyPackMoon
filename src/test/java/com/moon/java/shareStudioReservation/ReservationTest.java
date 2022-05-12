package com.moon.java.shareStudioReservation;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
    static StudioList studioList = new StudioList();

    @Test
    static void isMakeStudioList() {
        Studio ansanStudio = new Studio("ansan","sungpo");
        Studio suwonStudio = new Studio("suwon","yunmoo");
        Studio seoulStudio = new Studio("seoul","apgujeong");

        ansanStudio.addRoom("pong",10,5000);
        ansanStudio.addRoom("ping",17,8000);
        ansanStudio.addRoom("star",7,7000);
        suwonStudio.addRoom("war",10,5000);
        seoulStudio.addRoom("diablo",10,5000);

        studioList.add(ansanStudio);
        studioList.add(suwonStudio);
        studioList.add(seoulStudio);

        assertEquals("ansan",studioList.get(0).local);
        assertEquals("suwon",studioList.get(1).local);
        assertEquals("seoul",studioList.get(2).local);

        for (int i = 0; i < studioList.size(); i++) {
            System.out.println(studioList.get(i).local);
            System.out.println(studioList.get(i).suburb);
            for (int j = 0; j < studioList.get(i).size(); j++) {
                System.out.println(studioList.get(i).getRoom(j).name);
            }
        }


    }

    @Test
    void makeReservation() {
        isMakeStudioList();
        Customer customer = new Customer("pika",01012341234,2,"naver");
        Reservation reservation = new Reservation(customer,studioList.get(0));
        reservation.reservedStudio.getRoom(0).addTime(1,3, customer.name);

        assertEquals("pika",reservation.booker.name);
        assertEquals(01012341234,reservation.booker.phoneNumber);
        assertEquals(2,reservation.booker.howManyPeople);
        assertEquals("naverPay",reservation.booker.paymentMethod);

        assertEquals("pika",reservation.reservedStudio.getRoom(0).timeTable.get(1));
        System.out.println("-------------------------");
        System.out.println(reservation.reservedStudio.getRoom(0).timeTable.get(1));
        System.out.println(reservation.reservedStudio.getRoom(0).timeTable.get(2));


    }

}