package com.anaramada.SI.viewer;

import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.Bullet;

public class BulletViewer implements ElementViewer<Bullet>{

    @Override
    public void draw( Bullet bullet, GUI gui){
        gui.drawBullet(bullet.getPosition());
    }
}
