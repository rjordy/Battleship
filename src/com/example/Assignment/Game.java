package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final Dimension FRAME_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public Game() throws IOException {

        //Create frame that will hold all UI elements for the mainscreen
        MainScreen ms = new MainScreen();
        ms.setMainScreen();
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
        int[][] newArray2D = new int[64][2];
        int k = 0;
        for (int i = 1; i<=8; i++){
            for (int j = 1; j<=8;j++){
                newArray2D[k][0] = i;
                newArray2D[k][1] = j;
                k++;
            }
        }
        for (int[] var : newArray2D){
            System.out.println("X: " + var[0] + " Y: " + var[1]);
        }

    }

    public void Start(){

    }
}
