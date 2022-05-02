package com.moon.java.shareStudioReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void isCustomerConstructorWork() {

        Customer customer = new Customer("pikachu",01022775612,2,"card");

        System.out.println(customer.name);
        System.out.println(customer.phoneNumber);
        System.out.println(customer.howManyPeople);
        System.out.println(customer.paymentMethod);

        assertEquals("pikachu",customer.name);
        assertEquals(01022775612,customer.phoneNumber);
        assertEquals("creditCard",customer.paymentMethod);
    }


    @Test
    void isSetCustomerInfo() {
        Customer customer = new Customer("pikachu",01022775612,2,"card");

        System.out.println(customer.name);
        System.out.println(customer.phoneNumber);
        System.out.println(customer.howManyPeople);
        System.out.println(customer.paymentMethod);

        customer.setName("raichu");
        customer.setPhoneNumber(01012341234);
        customer.setHowManyPeople(5);
        customer.setPaymentMethod("naver");

        assertEquals("raichu",customer.name);
        assertEquals(01012341234,customer.phoneNumber);
        assertEquals(5,customer.howManyPeople);
        assertEquals("naverPay",customer.paymentMethod);

        System.out.println(customer.name);
        System.out.println(customer.phoneNumber);
        System.out.println(customer.howManyPeople);
        System.out.println(customer.paymentMethod);
    }

}