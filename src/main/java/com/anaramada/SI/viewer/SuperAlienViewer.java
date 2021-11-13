package com.anaramada.SI.viewer;

import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.SuperAlien;

public class SuperAlienViewer implements ElementViewer<SuperAlien>{

    @Override
    public void draw(SuperAlien sa, GUI gui) {
        gui.drawSuperAlien(sa.getPosition(), sa.getHealth());
    }
}
