package com.moon.java.shareStudioReservation;

public class Customer {
    String name;
    int phoneNumber;
    int howManyPeople;
    String paymentMethod;

    public Customer(String name, int phoneNumber, int howManyPeople, String paymentMethod) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.howManyPeople = howManyPeople;
        this.paymentMethod = paymentMethodChecker(paymentMethod);

    }

    private String paymentMethodChecker(String paymentMethod){
        if(paymentMethod.contains("naver")){
            this.paymentMethod =new PaymentMethod().naverPay;
        }

        if(paymentMethod.contains("cash")){
            this.paymentMethod =new PaymentMethod().cash;
        }

        if(paymentMethod.contains("payco")){
            this.paymentMethod =new PaymentMethod().payco;
        }

        if(paymentMethod.contains("card")){
            this.paymentMethod =new PaymentMethod().creditCard;
        }

        return this.paymentMethod;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setPhoneNumber (int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setHowManyPeople (int peopleCount){
        this.howManyPeople = peopleCount;
    }

    public void setPaymentMethod (String paymentMethod){
        this.paymentMethod = paymentMethodChecker(paymentMethod);
    }


}
