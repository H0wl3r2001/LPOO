package com.anaramada.SI.viewer;

import com.anaramada.SI.model.Wall;
import com.anaramada.SI.gui.GUI;

public class WallViewer implements ElementViewer<Wall>{
    @Override
    public void draw(Wall wall, GUI gui){
        gui.drawWall(wall.getPosition());
    }
}
