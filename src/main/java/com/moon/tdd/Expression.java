package com.moon.tdd;

public interface Expression {
    Money reduce(Bank bank, String to);
}
