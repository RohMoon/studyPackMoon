package com.moon.abstractAndIntreface;

public abstract class Human extends Creature implements Talkable{
    public Human(int x, int y, int age) {
        super(x, y, age);
    }

    @Override
    public void talk() {
        System.out.println("사람은 말을 할 수 있다.");
    }

    @Override
    public void attack() {
        System.out.println("도구를 사용.");
    }
}
