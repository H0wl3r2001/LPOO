package com.anaramada.SI.viewerTest;

import com.anaramada.SI.gui.GameGUI;
import com.anaramada.SI.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.anaramada.SI.viewer.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArenaTest
{
    private GameGUI gui;
    private ArenaViewer viewer;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10, new ArrayList<>());
        gui = Mockito.mock(GameGUI.class);
        viewer = new ArenaViewer(gui);

        arena.setWalls(Arrays.asList(new Wall(1, 2, '*'), new Wall(2, 3, '*'), new Wall(3, 4, '*')));
        arena.setAliens(Arrays.asList(new Alien(4, 5, 'A'), new Alien(5, 6, 'A')));
        arena.setSs(new SpaceShip(5, 8, 'X'));
        arena.setAsteroids(Arrays.asList(new Asteroid(10, 2, '0'), new Asteroid(0, 8, '0')));
        arena.setBullets(Arrays.asList(new Bullet(3,5,'I', true), new Bullet(4,7,'I', true), new Bullet(7,3,'I', true)));
        arena.setSuperAlien(new SuperAlien(5, 5, 'S'));
    }


    @Test
    void drawWalls() throws IOException {
        viewer.drawArena(arena);

        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(1, 2));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(2, 3));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(3, 4));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawAliens() throws IOException {
        viewer.drawArena(arena);

        Mockito.verify(gui, Mockito.times(1)).drawAlien(new Position(4, 5));
        Mockito.verify(gui, Mockito.times(1)).drawAlien(new Position(5, 6));
        Mockito.verify(gui, Mockito.times(2)).drawAlien(Mockito.any(Position.class));
    }

    @Test
    void drawSpaceShip() throws IOException {
        viewer.drawArena(arena);

        Mockito.verify(gui, Mockito.times(1)).drawSS(new Position(5, 8));
        Mockito.verify(gui, Mockito.times(1)).drawSS(Mockito.any(Position.class));
    }

    @Test
    void drawBullets() throws IOException
    {
        viewer.drawArena(arena);

        Mockito.verify(gui, Mockito.times(1)).drawBullet(new Position(3,5));
        Mockito.verify(gui, Mockito.times(1)).drawBullet(new Position(4,7));
        Mockito.verify(gui, Mockito.times(1)).drawBullet(new Position(7,3));

        Mockito.verify(gui, Mockito.times(3)).drawBullet(Mockito.any(Position.class));
    }

    @Test
    void drawAsteroids() throws IOException
    {
        viewer.drawArena(arena);

        Mockito.verify(gui, Mockito.times(1)).drawAsteroid(new Position(10, 2));
        Mockito.verify(gui, Mockito.times(1)).drawAsteroid(new Position(0, 8));

        Mockito.verify(gui, Mockito.times(2)).drawAsteroid(Mockito.any(Position.class));
    }

    @Test
    void refreshClearClose() throws IOException {
        viewer.drawArena(arena);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();

        viewer.close();

        Mockito.verify(gui, Mockito.times(1)).close();
    }
}

