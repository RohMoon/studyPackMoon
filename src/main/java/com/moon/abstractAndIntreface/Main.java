package com.moon.abstractAndIntreface;

public class Main {

    public static void main(String[] args) {
        Pigeon p = new Pigeon(5,10,14);
        p.printInfo();
        p.age();
        p.move(100);
        p.fly(5);
        p.printInfo();
        p.flyMode(10,20);
        p.printInfo();
        p.attack();
        System.out.println();

        Kevin kev = new Kevin(0,0,35);
        kev.printInfo();
        kev.age();
        kev.move(10);
        kev.printInfo();
        kev.attack();
        kev.coding();
        kev.swimDown(20);
        kev.printInfo();
        kev.talk();
        System.out.println();

        Turtle tur = new Turtle(100,-10,95);
        tur.printInfo();
        tur.age();
        tur.move(-100);
        tur.printInfo();
        tur.attack();
        tur.swimDown(1000);
        tur.printInfo();
    }
}
