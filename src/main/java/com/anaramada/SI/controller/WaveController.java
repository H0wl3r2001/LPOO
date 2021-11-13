package com.anaramada.SI.controller;

import com.anaramada.SI.model.Arena;

import java.time.Duration;
import java.time.Instant;

public class WaveController extends GameController{
    private int wave;
    private int alienShootRate = 3000; //FINAL??
    private int asteroidSpawnRate = 4000; //FINAL??
    private int superAlienShootRate = 500; //FINAL??
    private Instant lastShootTime;
    private Instant lastSpawnTime;
    private Instant SAlastShootTime;
    private final AlienController alienController;
    private final AsteroidController asteroidController;
    private final  SuperAlienController superAlienController;

    public WaveController(Arena arena) {
        super(arena);
        this.alienController = new AlienController(arena);
        this.asteroidController = new AsteroidController(arena);
        this.superAlienController = new SuperAlienController(arena);
        this.wave = arena.getWave();
        this.lastShootTime = Instant.now();
        this.SAlastShootTime = Instant.now();
        this.lastSpawnTime = Instant.now();
    }

    private void checkNewWave(){
        if(isSuperAlienWave()){
            if(arena.getSuperAlien().isDead()){
                newWave();
            }
        } else if(arena.getAliens().isEmpty()){
            newWave();
        }
    }

    private void newWave(){
        arena.nextWave();
        this.wave = arena.getWave();
        superAlienController.reset();
        if(isSuperAlienWave())
            superAlienShootRate /= 1.5;
        alienShootRate /= 1.1;
        asteroidSpawnRate /= 1.2;
    }

    public void update(){
        Instant now = Instant.now();
        checkNewWave();
        alienUpdate(now);
        asteroidUpdate(now);
        if(isSuperAlienWave())
            superAlienUpdate(now);
    }

    private void alienUpdate(Instant now){

        if(Duration.between(lastShootTime, now).toMillis() > alienShootRate){
            alienController.shoot();
            lastShootTime = Instant.now();
        }
    }

    private boolean isSuperAlienWave(){
        return (wave % 5 == 0);
    }

    private void asteroidUpdate(Instant now){
        if( wave >= 2 && Duration.between(lastSpawnTime, now).toMillis() > asteroidSpawnRate){
            asteroidController.spawn();
            lastSpawnTime = Instant.now();
        }
        asteroidController.move();
    }


    private  void superAlienUpdate(Instant now){

        superAlienController.move();
        if(Duration.between(SAlastShootTime, now).toMillis() > superAlienShootRate){
            superAlienController.shoot();
            SAlastShootTime = Instant.now();
        }
    }

}
