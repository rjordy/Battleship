package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final Dimension FRAME_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    private JFrame gameScreen;

    public Game() throws Exception {

        //Create frame that will hold all UI elements for the mainscreen
//        initFrame();
//        MainScreen ms = new MainScreen(this.gameScreen);
//        ms.setMainScreen();
//        JFrame frame = new JFrame("Testing");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//        frame.add(new JLabel("test"), BorderLayout.PAGE_START);
//        Grid grid = new Grid();
//        grid.setGridSize(12,12);
//        grid.drawGrid();
//        frame.add(grid);
//        frame.setSize(new Dimension(800,600));
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        new Placement().createRandomBoard(8,8);
        Placement pl = new Placement();
        pl.readPlacementFile("Placement.txt");
    }

    public void initFrame(){
        this.gameScreen = new JFrame("Battleship");
        this.gameScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameScreen.setSize(new Dimension(800,600));
        this.gameScreen.setLocationRelativeTo(null);
        this.gameScreen.setResizable(false);
    }

    public void Start(){

    }
}
