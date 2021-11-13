package com.anaramada.SI.gameMechanic;

import com.anaramada.SI.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpaceShipShotTest
{
    public void moveBullet(Bullet b, SpaceShip ss, Alien a)
    {
        if(b.isAlien())
        {
            for(int i = 0; i < Math.abs(ss.getPosition().getY() - a.getPosition().getY()); i++)
            {
                b.setPosition(b.getPosition().getDown());
            }
        }
        else
        {
            for(int i = 0; i < Math.abs(a.getPosition().getY() - ss.getPosition().getY()); i++)
            {
                b.setPosition(b.getPosition().getUp());
            }
        }
    }

    public void moveAsteroid(SpaceShip ss, Asteroid ast)
    {
        if(ss.getPosition().getX() > ast.getPosition().getX())
        {
            for(int i = 0; i < Math.abs(ss.getPosition().getX() - ast.getPosition().getX()); i++)
            {
                ast.setPosition(ast.getPosition().getRight());
            }
        }

        if(ss.getPosition().getX() < ast.getPosition().getX())
        {
            for(int i = 0; i < Math.abs(ss.getPosition().getX() - ast.getPosition().getX()); i++)
            {
                ast.setPosition(ast.getPosition().getLeft());
            }
        }

    }

    public boolean collision(Position position, Element element){
        int x1 = element.getPosition().getX(),
                y1 = element.getPosition().getY(),
                x2 = x1 + element.getLenght(),
                y2 = y1 +  element.getHeight();
        return (position.getX() >= x1 && position.getX() <= x2 && position.getY() >= y1 && position.getY() <= y2);

    }


    @Test
    public void shotOnce()
    {
        Alien a = new Alien(10, 12, 'A');
        SpaceShip ss = new SpaceShip(10, 24, 'X');
        Bullet b = new Bullet(a.getPosition().getX()+ a.getLenght()/2  , a.getPosition().getY() + a.getHeight() - 1, 'I', true);
        int comp = ss.getHealthPoints();
        moveBullet(b,ss,a);
        if(collision(b.getPosition(), ss))
            ss.decreasePoints();

        assertEquals(1, comp - ss.getHealthPoints()); //NOT YET DECREASED HP

    }


    @Test
    public void suddenDeath()
    {
        SpaceShip ss = new SpaceShip(90, 24, 'X');
        Asteroid ast = new Asteroid(100, 24, '0');

        moveAsteroid(ss, ast);

        assertEquals(true, collision(ast.getPosition(), ss));
    }

    @Test
    public void notShot1()
    {
        Alien a = new Alien(10, 24, 'A');
        SpaceShip ss = new SpaceShip(10, 12, 'X');
        Bullet b = new Bullet(a.getPosition().getX()+ a.getLenght()/2  , a.getPosition().getY() + a.getHeight() - 1, 'I', true);
        moveBullet(b,ss,a);

        assertEquals(false, collision(b.getPosition(), ss));
        assertEquals(3, ss.getHealthPoints());
    }

    @Test
    public void notShot2()
    {
        Alien a = new Alien(10, 12, 'A');
        SpaceShip ss = new SpaceShip(10, 24, 'X');
        Bullet b = new Bullet(a.getPosition().getX()+ a.getLenght()/2  , a.getPosition().getY() + a.getHeight() - 1, 'I', true);
        ss.setPosition(new Position(ss.getPosition().getX()+(ss.getLenght()), ss.getPosition().getY()));
        moveBullet(b,ss,a);

        assertEquals(false, collision(b.getPosition(), ss));
        assertEquals(3, ss.getHealthPoints());
    }
}
