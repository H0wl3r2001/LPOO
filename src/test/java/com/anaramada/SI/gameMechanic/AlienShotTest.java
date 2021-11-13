package com.anaramada.SI.gameMechanic;

import com.anaramada.SI.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlienShotTest {

    public boolean moveBullet(Bullet b, Element e1, Element e2)
    {
        boolean col = false;
        if(b.isAlien())
        {
            for(int i = 0; i < Math.abs(e1.getPosition().getY() - e2.getPosition().getY()); i++)
            {
                b.setPosition(b.getPosition().getDown());
                col = collision(b.getPosition(), e1);
            }
        }
        else
        {
            for(int i = 0; i < Math.abs(e2.getPosition().getY() - e1.getPosition().getY()); i++)
            {
                b.setPosition(b.getPosition().getUp());
                col = collision(b.getPosition(), e2);
            }
        }
        return col;
    }

    public boolean collision(Position position, Element element){
        int x1 = element.getPosition().getX(),
                y1 = element.getPosition().getY(),
                x2 = x1 + element.getLenght(),
                y2 = y1 +  element.getHeight();
        return (position.getX() >= x1 && position.getX() <= x2 && position.getY() >= y1 && position.getY() <= y2);

    }

    @Test
    public void shot()
    {
        Alien a = new Alien(10, 12, 'A');
        SpaceShip ss = new SpaceShip(10, 24, 'X');
        Bullet b = new Bullet(ss.getPosition().getX(), ss.getPosition().getY(), 'I', false);
        boolean check_ = moveBullet(b,ss,a);
        assertEquals(12, b.getPosition().getY());
        assertEquals(true, check_);
    }

    @Test
    public void notShot()
    {
        Alien a1 = new Alien(10, 12, 'A');
        Alien a2 = new Alien(10, 24, 'A');
        Bullet b = new Bullet(a1.getPosition().getX()+ a1.getLenght()/2  , a1.getPosition().getY() + a1.getHeight() - 1, 'I', true);
        boolean check_ = moveBullet(b, a1, a2);
        assertEquals(false, check_);
    }
}
