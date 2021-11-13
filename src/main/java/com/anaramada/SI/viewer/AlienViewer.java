package com.anaramada.SI.viewer;

import com.anaramada.SI.model.Alien;
import com.anaramada.SI.gui.GUI;

public class AlienViewer implements ElementViewer<Alien>{

    @Override
    public void draw(Alien alien, GUI gui){
        gui.drawAlien(alien.getPosition());
    }
}
