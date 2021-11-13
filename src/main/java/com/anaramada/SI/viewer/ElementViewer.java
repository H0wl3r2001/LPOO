package com.anaramada.SI.viewer;

import com.anaramada.SI.model.Element;
import com.anaramada.SI.gui.GUI;

public interface ElementViewer<E extends  Element> {
    void draw(E element, GUI gui);
}
