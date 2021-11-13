package com.anaramada.SI.moduleTest;

import com.anaramada.SI.model.Bullet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulletTest
{
    @Test
    public void testCreation()
    {
        Bullet b = new Bullet(10, 12, 'I', false);
        assertEquals(10, b.getPosition().getX());
        assertEquals(12, b.getPosition().getY());
    }
}
