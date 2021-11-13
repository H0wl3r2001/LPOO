package com.anaramada.SI.model;

public class SuperAlien extends Element{
    private int health = 5;
    public SuperAlien(int x, int y, char character) {
        super(x, y, character);
        setHeight(17);
        setLength(38);
    }
    public int getHealth(){
        return health;
    }
    public boolean isDead(){
        return  health == 0;
    }

    public void shoot(){
        health--;
    }
}
