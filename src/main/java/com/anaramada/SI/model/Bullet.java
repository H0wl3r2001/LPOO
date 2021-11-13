package com.anaramada.SI.model;

public class Bullet extends Element{
    private final boolean isAlien;
    public Bullet(int x, int y, char character, boolean isAlien) {
        super(x,y,character);
        this.isAlien = isAlien;
    }


    public boolean isAlien() {
        return isAlien;
    }

}
