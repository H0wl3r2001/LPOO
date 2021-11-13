package com.anaramada.SI.gameMechanic;

import com.anaramada.SI.controller.WaveController;
import com.anaramada.SI.model.Arena;
import com.anaramada.SI.model.ArenaBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WaveMechanicTest
{
    @Test
    public void incrementWave()
    {
        Arena a = new ArenaBuilder().createArena();
        WaveController wc = new WaveController(a);

        int comp = a.getWave();

        while(!wc.getArena().getAliens().isEmpty())
        {
            a.killAlien(a.getAliens().get(0));
        }


        if(wc.getArena().getAliens().isEmpty())
        {
            wc.getArena().nextWave();
        }


        assertEquals(1, a.getWave()-comp);
    }
}
