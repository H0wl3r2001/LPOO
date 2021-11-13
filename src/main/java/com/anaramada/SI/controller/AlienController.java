package com.anaramada.SI.controller;
import com.anaramada.SI.model.Alien;
import com.anaramada.SI.model.Arena;
import com.anaramada.SI.model.Bullet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AlienController extends GameController {
    public AlienController(Arena arena){
        super(arena);
    }

    public void shoot()
    {
        if(arena.getAliens().isEmpty())
            return;
        List<Integer> randTriggers = generateRandom();
        for(int i: randTriggers) {
            Alien alien = arena.getAliens().get(i);
            Bullet bullet = new Bullet(alien.getPosition().getX()+ alien.getLenght()/2  , alien.getPosition().getY() + alien.getHeight() - 1, 'I', true);
            arena.addBullet(bullet);
        }
    }

    //Não sei se fica aqui
    private List<Integer> generateRandom(){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < arena.getAliens().size() / 2; i++)
        {
           Random rand = new Random();
           int n = rand.nextInt(arena.getAliens().size());
           if(!result.contains(n)) result.add(n);
        }
        return result;
    }

}
