package com.moon.tdd;

public class Money implements Expression {

    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    String currency(){
        return currency;
    }

    public boolean equals(Object object){
        Money money = (Money) object;
        return this.amount == money.amount &&
                currency().equals(money.currency);
    }
    static Money dollar(int amount){
        return new Money(amount,"USD");
    }
    static Money franc(int amount){
        return new Money(amount,"CHF");
    }

    Money times(int multiplier){
        return new Money(amount *multiplier, currency);
    }
    public String toString (){
        return amount + " "+ currency;
    }

/*    Money plus(Money addend){

        return new Money(amount + addend.amount, currency);
    }*/

    Expression plus(Money addend){
        return new Sum(this, addend);
    }

    public Money reduce(String to) {
        int rate = (currency.equals("CHF")&&to.equals("USD"))
                ?2
                :1;
        return new Money(amount / rate, to);
    }


    @Override
    public Money reduce(Bank bank, String to) {
        return null;
    }
}
