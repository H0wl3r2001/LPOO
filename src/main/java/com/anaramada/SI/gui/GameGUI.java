package com.anaramada.SI.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.anaramada.SI.model.Position;
import com.anaramada.SI.model.SpaceShip;
import com.anaramada.SI.model.Arena;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class GameGUI implements GUI {

    private TerminalScreen screen;
    private int width;
    private int height;
    private final int FONT_SIZE = 8;
    private Position mousePos;
    private boolean mousePressed;
    public GameGUI(int width, int height) throws URISyntaxException, FontFormatException, IOException {
        this.width = width;
        this.height = height;
        this.mousePos = new Position(this.width / 2, this.height - 6);
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(fontConfig);
        try {
            screen = createScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not create screen.");
        }
        setupMouseMotionListener(terminal);
        setupMouseListener(terminal);

        initScreen();
    }

    private TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    //Depois talvez mandar uma configuração para a fonte
    private Terminal createTerminal( AWTTerminalFontConfiguration fontConfig){
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = (new DefaultTerminalFactory()).setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = null;
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not create terminal.");
        }
        return terminal;
    }

    //TODO (tratar das exceções localmente e não no com.anaramada.SI.Game principal)
    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.BOLD, FONT_SIZE);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    private void initScreen(){
        this.screen.clear();
        this.screen.newTextGraphics().setBackgroundColor(TextColor.Factory.fromString("#000000"));
        this.screen.newTextGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
    }

    private void setupMouseMotionListener(Terminal terminal){
        MouseMotionAdapter mouseMotionListener = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){

                //screen.setCursorPosition(new TerminalPosition(e.getX()/(FONT_SIZE), e.getY() /(FONT_SIZE )));
                mousePos.setX(e.getX()/FONT_SIZE);
                mousePos.setY(e.getY()/FONT_SIZE);
            }
        };

        ((AWTTerminalFrame)terminal).getComponent(0).addMouseMotionListener(mouseMotionListener);
    }

    private void setupMouseListener(Terminal terminal){
        ((AWTTerminalFrame)terminal).getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;

                //System.out.println("x: " + x + ", y: " + y);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //System.out.println("x: " + x + ", y: " + y);
                mousePressed = false;
            }
        });
    }

    @Override
    public Position getMousePosition() {

        return mousePos;
    }

    @Override
    public ACTION getNext() throws IOException {
        KeyStroke key = screen.pollInput(); //different from read input, doesnt block

        if (mousePressed){
            mousePressed = false;
            return ACTION.SHOOT;
        }
        if(key == null) return ACTION.NONE;

        if(key.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') return ACTION.QUIT;

        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') return ACTION.UP;
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') return ACTION.RIGHT;
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 's') return ACTION.DOWN;
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') return ACTION.LEFT;

        if(key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') return ACTION.SHOOT;



        return ACTION.NONE;
    }


    @Override
    public void drawSS(Position p) {

        String text = "   /\\\n" +
                "  (  )\n" +
                "  (  )\n" +
                "  (  )\n" +
                " /|/\\|\\\n" +
                "/_||||_\\";
        drawText(p, text, "#00CCFF");
    }

    @Override
    public void drawAlien(Position p) {

        //drawChar(p, 'A', "#00FF00" );
        String text =  " _____ \n/ @ @ \\\n\\_   _/\n  \\_/  ";
        drawText(p, text, "#00FF00");
    }

    @Override
    public void drawAsteroid(Position p){
        drawChar(p, '0', "#FF0000" );
    }

    @Override
    public void drawBullet(Position p){
        drawChar(p, '|', "#FF0000");
    }

    @Override
    public void drawWall(Position p){ drawChar(p, '*', "#FFFFFF"); }

    //Remember to make this private
    @Override
    public void drawChar(Position p, char c, String color){
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(p.getX(), p.getY() + 1, "" + c);
    }

    @Override
    public void drawText(Position p, String text, String color){
        int x = p.getX(), y = p.getY();
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == '\n'){
                y++;
                x = p.getX();
                continue;
            }
            tg.putString(x, y, "" + text.charAt(i));
            x ++;
        }
    }

    @Override
    public void drawHealthPoints(SpaceShip ss, Position p){
        String text = "Health Points: ";
        drawText(p, text + ss.getHealthPoints(), "#E66969");
    }
    @Override
    public void drawHealthPoints( Position p, Arena arena){
        String text = "Wave: ";
        drawText(p, text + arena.getWave(), "#E66969");
    }

    @Override
    public void drawSuperAlien(Position p, int health){
        String color = "#00FF00";
        String text = "               _____\n" +
                "             ,-\"     \"-.\n" +
                "            / o       o \\\n" +
                "           /   \\     /   \\\n" +
                "          /     )-\"-(     \\\n" +
                "         /     ( 6 6 )     \\\n" +
                "        /       \\ \" /       \\\n" +
                "       /         )=(         \\\n" +
                "      /   o   .--\"-\"--.   o   \\\n" +
                "     /    I  /  -   -  \\  I    \\\n" +
                " .--(    (_}y/\\       /\\y{_)    )--.\n" +
                "(    \".___l\\/__\\_____/__\\/l___,\"    )\n" +
                " \\                                 /\n" +
                "  \"-._      o O o O o O o      _,-\"\n" +
                "      `--Y--.___________.--Y--'\n" +
                "         |==.___________.==|\n" +
                "         `==.___________.==' ";
        if(health == 5){
            color = "#00FF00";
        }else if(health == 4){
            color = "#2E8B57";
        }else if(health == 3){
             color = "#ADFF2F";
        }else if(health == 2){
            color = "#FFAE42";
        }else if(health == 1){
            color = "#FF0000";
        }
        drawText(p, text, color);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
