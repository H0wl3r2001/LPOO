package com.anaramada.SI.model;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private int wave;

    private SpaceShip ss;
    private SuperAlien superAlien;
    private List<Alien> aliens;
    private List<Bullet> bullets;
    private List<Wall> walls;
    private List<Asteroid> asteroids;

    public Arena(int width, int height, List<Wall> walls)
    {
        this.width = width;
        this.height = height;
        this.walls = walls;
        this.wave = 1;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public SpaceShip getSs() {
        return ss;
    }

    public void setSs(SpaceShip ss) {
        this.ss = ss;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public List<Wall> getWalls(){ return walls; }

    public void setWalls(List<Wall> walls){ this.walls = walls; }

    public boolean isEmpty(Position position){
        //TODO
        for (Wall wall: walls)
            if (wall.getPosition().equals(position)) return false;
        return true;
    }


    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public void addAsteroid(Asteroid a){asteroids.add(a);}

    public List<Bullet> getBullets(){
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public  void killAlien(Alien alien){
        aliens.remove(alien);
    }

    public void nextWave() {
        this.wave ++;
        WaveBuilder waveBuilder = new WaveBuilder( this);

    }

    public  int getWave(){
        return wave;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public void removeBullets(List<Bullet> bulletsToRemove) {
        for(Bullet b: bulletsToRemove){
            bullets.remove(b);
        }
    }

    public SuperAlien getSuperAlien(){ return superAlien; }

    public void setSuperAlien(SuperAlien superAlien){ this.superAlien = superAlien; }

    public void removeSuperAlien() {
        superAlien.setPosition(new Position(width + 100 , height + 100) );
    }
}
