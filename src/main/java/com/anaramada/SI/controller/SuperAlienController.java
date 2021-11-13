package com.anaramada.SI.controller;

import com.anaramada.SI.model.*;

public class SuperAlienController extends GameController {
    private SuperAlien superAlien;
    private boolean movingLeft;
    public SuperAlienController(Arena arena) {
        super(arena);
        this.superAlien = arena.getSuperAlien();
        movingLeft = true;
    }

    private void moveRight(){
        if(arena.isEmpty(superAlien.getPosition().getRight()))
            moveSuperAlien(superAlien.getPosition().getRight());
    }

    private void moveLeft(){
        if(arena.isEmpty(superAlien.getPosition().getLeft()))
            moveSuperAlien(superAlien.getPosition().getLeft());
    }

    private boolean canMoveRight() {
        return superAlien.getPosition().getX() + superAlien.getLenght() < arena.getWidth();
    }

    public boolean canMoveLeft() {
        return superAlien.getPosition().getX() != 1;
    }

    private void moveSuperAlien(Position position){
        superAlien.setPosition(position);
    }

    public void  reset(){
        this.superAlien = arena.getSuperAlien();
    }

    public void move(){
        if(movingLeft){
            moveLeft();
            if(!canMoveLeft())
                movingLeft = false;
        }else{
            moveRight();
            if (!canMoveRight())
                movingLeft = true;
        }
    }



    public void shoot()
    {
        Bullet bullet = new Bullet(superAlien.getPosition().getX()+ superAlien.getLenght()- 27, superAlien.getPosition().getY() + superAlien.getHeight() - 3, 'I', true);
        arena.addBullet(bullet);
        bullet = new Bullet(superAlien.getPosition().getX()+ superAlien.getLenght() -11 , superAlien.getPosition().getY() + superAlien.getHeight() -3, 'I', true);
        arena.addBullet(bullet);

    }


}
