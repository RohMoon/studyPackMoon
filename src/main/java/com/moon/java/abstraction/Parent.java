package com.moon.java.abstraction;

public class Parent {
    private  int a = 10;
    private int b = 20;



    void display(){
        System.out.println("Parent display()");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
