package com.anaramada.SI.model;

import java.util.ArrayList;
import java.util.List;

public class WaveBuilder {
    private final int wave;
    private final Arena arena;
    public WaveBuilder( Arena arena){
        this.arena = arena;
        this.wave = arena.getWave();
        arena.setAliens(createAliens());

        if(wave % 5 == 0)
            arena.setSuperAlien(createSuperAlien());
        resetBullets();
    }

    private SuperAlien createSuperAlien()
    {
        return new SuperAlien(arena.getWidth()/2 - 20, 10, 'S');
    }
    private void resetBullets(){
        arena.setBullets(new ArrayList<>());
    }

    private List<Alien> createAliens(){

        List<Alien> aliens = new ArrayList<>();
        if(wave % 5 == 0) return aliens;
        int aliensLastLine = (wave % 2 == 0) ? 9 : 5;
        int distanceX = (wave % 2 == 0) ? 11 : 20; //5 if wave is odd, 9 otherwise
        int nLines = (wave % 2 == 0) ? wave/2 : (wave +1) /2;
        for(int j = 0; j < nLines; j++){
            int aliensPerLine = (j+1 == nLines) ? aliensLastLine : 9;
            int dist = (j+1 == nLines) ? distanceX : 11;
            for(int i = 0; i < aliensPerLine; i++){

                aliens.add(new Alien(i * dist + 3, j * 4 + 1,  'A'));
            }

        }
        return aliens;
    }
}
