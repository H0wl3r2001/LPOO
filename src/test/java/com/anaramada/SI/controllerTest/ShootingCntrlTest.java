package com.anaramada.SI.controllerTest;

import com.anaramada.SI.controller.AlienController;
import com.anaramada.SI.controller.BulletController;
import com.anaramada.SI.controller.SpaceShipController;
import com.anaramada.SI.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShootingCntrlTest
{
    @Test
    public void moveCorrectly1()
    {
        Arena a = new ArenaBuilder().createArena();

        SpaceShipController ssc = new SpaceShipController(a);
        BulletController bc = new BulletController(a);

        ssc.shoot();

        int compSs = a.getBullets().get(0).getPosition().getY();

        System.out.println(a.getBullets().size());
        bc.move();

        assertEquals(1, compSs-a.getBullets().get(0).getPosition().getY());
    }

    @Test
    public void moveCorrectly2()
    {
        Arena a = new ArenaBuilder().createArena();
        Alien al1 = new Alien(10,0, 'a');
        Alien al2 = new Alien (5, 10, 'a');
        List<Alien> l1 = new ArrayList<>();

        l1.add(al1);
        l1.add(al2);
        a.setAliens(l1);

        AlienController ac = new AlienController(a);
        BulletController bc = new BulletController(a);

        ac.shoot();

        int compAl = a.getBullets().get(0).getPosition().getY();

        System.out.println(a.getBullets().size());
        bc.move();

        assertEquals(-1, compAl - a.getBullets().get(0).getPosition().getY());
    }

}
