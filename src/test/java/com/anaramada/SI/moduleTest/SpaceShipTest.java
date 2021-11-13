package com.anaramada.SI.moduleTest;

import com.anaramada.SI.model.SpaceShip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpaceShipTest
{
    @Test
    public void testCreation()
    {
        SpaceShip ss = new SpaceShip(10, 12, 'X');
        assertEquals(10, ss.getPosition().getX());
        assertEquals(12, ss.getPosition().getY());
        assertEquals(3, ss.getHealthPoints());
    }

    @Test
    public void testDeath()
    {
        SpaceShip ss = new SpaceShip(10, 12, 'X');

        for(int i = 0; i <= 2; i++)
            ss.decreasePoints();

        assertEquals(true, ss.getHealthPoints() == 0);
    }
}
