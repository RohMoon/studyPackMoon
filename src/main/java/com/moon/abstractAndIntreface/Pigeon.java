package com.moon.abstractAndIntreface;

public class Pigeon extends Animal implements Flyable{

    public Pigeon(int x, int y, int age) {
        super(x, y, age);
    }

    @Override
    public void fly(int yDistance){
        setY(getY()+yDistance);
    }

    @Override
    public void flyMode(int xDistance, int yDistance){
        setY(getY()+yDistance);
        setX(getX()+xDistance);
    }
    @Override
    public void printInfo(){
        System.out.println("Pigeon =>" + toString());
    }
}
