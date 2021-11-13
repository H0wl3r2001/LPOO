package com.anaramada.SI.moduleTest;

import com.anaramada.SI.model.Alien;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienTest
{
    @Test
    public void testCreation()
    {
        Alien a = new Alien(10, 12, 'A');
        assertEquals(10, a.getPosition().getX());
        assertEquals(12, a.getPosition().getY());
        assertEquals(4, a.getHeight());
        assertEquals(7, a.getLenght());
    }
}
