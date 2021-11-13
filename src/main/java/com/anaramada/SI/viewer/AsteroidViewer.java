package com.anaramada.SI.viewer;

import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.Asteroid;

public class AsteroidViewer implements ElementViewer<Asteroid>{
    @Override
    public void draw(Asteroid asteroid, GUI gui){
        gui.drawAsteroid(asteroid.getPosition());
    }
}
