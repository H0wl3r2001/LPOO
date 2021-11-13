package com.anaramada.SI.controller;

import com.anaramada.SI.model.*;

public class CollisionController extends GameController
{
    public CollisionController(Arena arena) {
        super(arena);
    }

    public boolean bulletCollisions(Bullet bullet){
        if (outOfArena(bullet)) return true;


        if(bullet.isAlien()){ //From Alien
            return bulletHeroCollison(bullet);
        }else //From SpaceShip
            return (bulletAlienCollision(bullet) || bulletSuperAlienCollision(bullet));

    }

    private boolean outOfArena(Bullet b){
        return (b.getPosition().getY() > arena.getHeight() || b.getPosition().getY() < 0);
    }

    private boolean bulletHeroCollison(Bullet bullet){
        if(isIn(bullet.getPosition(), arena.getSs())){ //bullet collides with SpaceShip
            arena.getSs().decreasePoints();
            return true;
        }
        return false;
    }

    private boolean bulletAlienCollision(Bullet bullet){
        for(Alien alien: arena.getAliens()){
            if(isIn(bullet.getPosition(), alien)){ //bullet collides with alien
                arena.killAlien(alien);
                return true;
            }
        }
        return false;
    }

    private boolean bulletSuperAlienCollision(Bullet bullet){
        if(arena.getWave() % 5 != 0) return false;
        if(isIn(bullet.getPosition(), arena.getSuperAlien())){ //bullet collides with alien
            arena.getSuperAlien().shoot();
            if(arena.getSuperAlien().isDead())
                arena.removeSuperAlien();
            return true;
        }
        return false;
    }

    public boolean isIn(Position position, Element element){
        int x1 = element.getPosition().getX(),
                y1 = element.getPosition().getY(),
                x2 = x1 + element.getLenght(),
                y2 = y1 +  element.getHeight();
        return (position.getX() > x1 && position.getX() < x2 && position.getY() > y1 && position.getY() < y2);

    }


}
