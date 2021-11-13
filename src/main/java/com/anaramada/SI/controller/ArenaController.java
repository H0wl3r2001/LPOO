package com.anaramada.SI.controller;

import com.anaramada.SI.model.Position;
import com.anaramada.SI.viewer.ArenaViewer;
import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.Arena;

import java.io.IOException;

public class ArenaController extends GameController {

    private final SpaceShipController spaceShipController;
    private final WaveController waveController;
    private final BulletController bulletController;
    private final ArenaViewer arenaViewer;
    private final GUI gui;

    public ArenaController(Arena arena, ArenaViewer arenaViewer, GUI gui){
        super(arena);
        this.arenaViewer = arenaViewer;
        this.spaceShipController = new SpaceShipController(arena);
        this.bulletController = new BulletController(arena);
        this.waveController = new WaveController(arena);
        this.gui = gui;
    }

    public void start() throws IOException{
        int FPS = 20;
        int frameTime = 1000 / FPS;

        while(getArena().getSs().getHealthPoints() > 0){
            long startTime = System.currentTimeMillis();
            bulletController.move();

            GUI.ACTION action = gui.getNext();
            if(action == GUI.ACTION.QUIT)
                break;

            Position mousePos =new Position(gui.getMousePosition().getX(), gui.getMousePosition().getY());

            spaceShipController.moveSpaceShip(mousePos);
            spaceShipController.action(action);
            waveController.update();
            arenaViewer.drawArena(getArena());
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) { }

        }

        arenaViewer.close();
    }

}
