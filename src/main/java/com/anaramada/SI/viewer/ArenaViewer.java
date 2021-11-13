package com.anaramada.SI.viewer;
import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.Arena;

import java.io.IOException;
import com.anaramada.SI.model.Element;
import com.anaramada.SI.model.Position;

import java.util.List;


public class ArenaViewer {
    private final GUI gui;

    private Position p;

    public ArenaViewer(GUI gui){
        this.gui = gui;
    }

    public void drawArena(Arena arena) throws IOException {
        gui.clear();
        drawElements(arena.getWalls(), new WallViewer());
        drawElements(arena.getAliens(), new AlienViewer());
        drawElement(arena.getSs(), new SpaceShipViewer());
        if(arena.getWave() % 5 == 0)
            drawElement(arena.getSuperAlien(), new SuperAlienViewer());
        drawElements(arena.getAsteroids(), new AsteroidViewer());
        drawElements(arena.getBullets(), new BulletViewer());
        gui.drawHealthPoints(arena.getSs(), new Position(5, arena.getHeight()-2));
        gui.drawHealthPoints( new Position(80, arena.getHeight()-2),  arena);
        gui.refresh();

    }

    private<E extends Element> void drawElements(List<E> elements, ElementViewer<E> viewer){
        for(E el: elements)
            viewer.draw(el, gui);
    }

    private <E extends Element> void drawElement(E element, ElementViewer<E> viewer){
        viewer.draw(element, gui);
    }

    public void close() throws IOException{
        gui.close();
    }
}

