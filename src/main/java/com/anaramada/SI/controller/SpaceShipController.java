package com.anaramada.SI.controller;

import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.*;
import com.anaramada.SI.model.Bullet;

public class SpaceShipController extends GameController {
    private SpaceShip ss;
    public SpaceShipController(Arena arena) {
        super(arena);
        this.ss = arena.getSs();

    }

    public void shoot(){
        Bullet bullet = new Bullet(arena.getSs().getPosition().getX() + 3, arena.getSs().getPosition().getY()-1, 'I', false);
        arena.addBullet(bullet);
    }

    private boolean canMoveUp(Position position){
        return (position.getY() > arena.getHeight()/2 );
    }

    private boolean canMoveDown(Position position){
        return position.getY() + ss.getHeight() < arena.getHeight() - 2;
    }

    private boolean canMoveSideways(Position position){
        return position.getX() +ss.getLenght() < arena.getWidth()
                && position.getX() > 0;
    }

    public void moveSpaceShip(Position position){

        Position newY = new Position(ss.getPosition().getX(), position.getY());
        Position newX = new Position(position.getX(), ss.getPosition().getY());
        if(canMoveUp(position) && canMoveSideways(position) && canMoveDown(position))
            ss.setPosition(position);
        else if(canMoveUp(position) && canMoveDown(position))
            ss.setPosition(newY);
        else if(canMoveSideways(position))
            ss.setPosition(newX);
    }

    public void action(GUI.ACTION action){
        if(action == GUI.ACTION.SHOOT) shoot();
    }

}
