package com.anaramada.SI.model;

public class SpaceShip extends Element {
    private int healthPoints;
    public SpaceShip(int x, int y, char character) {
        super(x, y, character);
        healthPoints = 3;
        setHeight(6);
        setLength(8);
    }

    /*Takes one point from health
    * @return true if the Space Ship is still alive
    * */
    public void decreasePoints(){
        this.healthPoints --;
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public void dies(){
        this.healthPoints = 0;
    }
}
