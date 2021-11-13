package com.anaramada.SI.controller;
import com.anaramada.SI.model.*;

import java.util.ArrayList;
import java.util.List;

public class BulletController extends GameController {
    private final CollisionController collisionController;
    public BulletController(Arena arena){
        super(arena);
        collisionController = new CollisionController(arena);
    }

    public void move(){

        for(Bullet b: arena.getBullets()){
            if(b.isAlien()) //Bullet from a Alien
                b.setPosition(b.getPosition().getDown()); //Moving Bullet Down
            else //From the spaceShip
                b.setPosition(b.getPosition().getUp()); //Moving Up

        }
        removeBullets();

    }

    private void removeBullets(){
        List<Bullet > bulletsToRemove = new ArrayList<>();
        for(Bullet b: arena.getBullets()){
            if(collisionController.bulletCollisions(b)){
                bulletsToRemove.add(b);
            }
        }
        arena.removeBullets(bulletsToRemove);

    }




}
