package com.anaramada.SI.model;

public abstract class Element {
    private Position position;
    private int height;
    private int length;

    public Element(int x, int y, char character){

        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public int getLenght() {
        return length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
