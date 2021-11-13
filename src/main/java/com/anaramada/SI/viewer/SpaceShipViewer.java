package com.anaramada.SI.viewer;

import com.anaramada.SI.model.SpaceShip;
import com.anaramada.SI.gui.GUI;

public class SpaceShipViewer implements ElementViewer<SpaceShip>{

    @Override
    public void draw(SpaceShip ss, GUI gui){
        gui.drawSS(ss.getPosition());
    }

}
