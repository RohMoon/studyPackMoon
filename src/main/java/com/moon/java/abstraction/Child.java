package com.moon.java.abstraction;

public class Child extends Parent {

    int c;

    public Child(int c) {
        this.c = c;
    }

    @Override
    void display() {
        super.display();
        System.out.println("\n a : " + getA() +
                        "\n b : " + getB() +
                        "\n c : " + c);
    }

    public static void main(String[] args) {

        Child child0 = new Child(77);
        child0.display();
        System.out.println("\n--------------------------\n");

        Parent parent = new Child(10);
        Child child1 = (Child) parent;
//        Child child = new Parent();  < 타입 에러


    }

}
