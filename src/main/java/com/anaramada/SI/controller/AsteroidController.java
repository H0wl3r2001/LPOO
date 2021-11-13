package com.anaramada.SI.controller;
import com.anaramada.SI.model.Arena;
import com.anaramada.SI.model.Asteroid;
import com.anaramada.SI.model.Position;

import java.util.Random;
//import java.util.Random;

public class AsteroidController extends GameController{
    private int spawnRate;
    private CollisionController collisionController;

    public AsteroidController(Arena arena){
        super(arena);
        collisionController = new CollisionController(arena);
    }

    public void moveLeft(Asteroid a){a.setPosition(a.getPosition().getLeft());}

    public void moveRight(Asteroid a){a.setPosition(a.getPosition().getRight());}

    public void spawn()
    {
        Random rand = new Random();
        int spawnX = rand.nextInt(2);
    ///Mudar
        if(spawnX == 1)
            spawnX = arena.getWidth();

        int maxAsteroidH = arena.getHeight()/2;
        int minAsteroidH = arena.getHeight() - 5;
        //((Math.random() * (max - min)) + min);
        int randomHeight = rand.nextInt(minAsteroidH - maxAsteroidH) +  maxAsteroidH;

        Asteroid a = new Asteroid(spawnX,randomHeight,'0');

        arena.addAsteroid(a);
    }


    public void move()
    {
        for(Asteroid a: arena.getAsteroids())
        {
            if(a.isSpawnXPoint())
                moveLeft(a);
            else
                moveRight(a);
            if(checkCollision(a.getPosition()))
                arena.getSs().dies();
        }

    }
    
    private boolean checkCollision(Position asteroidPos)
    {
        return (collisionController.isIn(asteroidPos, arena.getSs()));
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }
}
