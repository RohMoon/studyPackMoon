package com.moon.java.shareStudioReservation;

public class Reservation {
    Customer booker;
    Studio reservedStudio;

    public Reservation(Customer customer, Studio studio) {
        this.booker = customer;
        this.reservedStudio = studio;
    }


}
