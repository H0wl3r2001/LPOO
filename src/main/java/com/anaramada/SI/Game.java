package com.anaramada.SI;

import com.anaramada.SI.controller.ArenaController;
import com.anaramada.SI.gui.GameGUI;
import com.anaramada.SI.gui.GUI;
import com.anaramada.SI.model.ArenaBuilder;
import com.anaramada.SI.viewer.ArenaViewer;
import com.anaramada.SI.model.Arena;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game(100, 80);
    }

    public Game(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        Arena arena = new ArenaBuilder().createArena();
        GUI gui = new GameGUI(width, height);
        ArenaViewer viewer = new ArenaViewer(gui);
        ArenaController controller = new ArenaController(arena, viewer, gui);
        controller.start();
    }

}
