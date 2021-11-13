package com.anaramada.SI.gui;

import com.anaramada.SI.model.Arena;
import com.anaramada.SI.model.Position;
import com.anaramada.SI.model.SpaceShip;

import java.io.IOException;


public interface GUI
{
    Position getMousePosition();

    enum ACTION {UP, DOWN, RIGHT, LEFT, NONE, QUIT, SHOOT}

    ACTION getNext() throws IOException;

    void drawSS(Position p);

    void drawAlien(Position p);

    void drawAsteroid(Position p);

    void drawBullet(Position p);

    void drawChar(Position p, char c, String color);

    void drawText(Position c, String text, String color);

    void drawWall(Position p);

    void drawHealthPoints(SpaceShip ss, Position p);

    void drawHealthPoints( Position p, Arena arena);

    void drawSuperAlien(Position p, int health);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}
