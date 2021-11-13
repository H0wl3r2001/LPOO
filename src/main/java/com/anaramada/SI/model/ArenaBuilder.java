package com.anaramada.SI.model;
import java.util.ArrayList;
import java.util.List;

public class ArenaBuilder {

    private final int height = 80;
    private final int width = 100;


    public Arena createArena()
    {
        Arena arena = new Arena(this.width, this.height, createWalls());
        WaveBuilder waveBuilder = new WaveBuilder( arena);
        arena.setSs(createSS());
        arena.setBullets(createBullets());
        arena.setWalls(createWalls());
        arena.setAsteroids(createAsteroids());

        return arena;
    }

    private List<Bullet> createBullets(){
        List<Bullet> bullets = new ArrayList<>();
        return bullets;
    }

    private SpaceShip createSS()
    {
        return new SpaceShip(this.width / 2, this.height - 10, 'X');
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for(int y = -1; y < height-3; y++){
            walls.add(new Wall(0, y, '*'));
            walls.add(new Wall(width -1, y, '*'));
        }
        for(int x = 0; x < width -1; x++){
            walls.add(new Wall(x, -1, '*'));
            walls.add(new Wall(x, height-4, '*'));
        }
        return walls;
    }

    private List<Asteroid> createAsteroids(){
        List<Asteroid> asteroids = new ArrayList<>();
        return asteroids;
    }



}
