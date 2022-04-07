package com.moon.tdd;

public class Bank {


    Money reduce(Expression source, String to){
        Sum sum = (Sum) source;
        int amount = sum.augend.amount + sum.addend.amount;
        return new Money(amount, to);
    }
//    private Hashtable rates = new Hashtable<Pair, Integer>();
//
//    void addRate(String from, String to, int rate) {
//        rates.put(new Pair(from, to), rate);
//    }
//
//    int rate(String from, String to) {
//        Integer rate = (Integer) rates.get(new Pair(from, to));
//        return rate.intValue();
//    }
}
