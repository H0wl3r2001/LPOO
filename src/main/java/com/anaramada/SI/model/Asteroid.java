package com.anaramada.SI.model;

public class Asteroid extends Element {
    private final boolean spawnXPoint; // if false, it's x = 0; if true, it's the arena's width.

    public Asteroid(int x, int y, char character)
    {
        super(x, y, character);
        this.spawnXPoint = !(x == 0);
    }

    public boolean isSpawnXPoint() {
        return spawnXPoint;
    }
}
