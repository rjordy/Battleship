package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static Dimension FRAME_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public Game() throws IOException {

        //Create frame that will hold all UI elements for the mainscreen
//        MainScreen ms = new MainScreen();
//        ms.setMainScreen();
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("test"), BorderLayout.PAGE_START);
        frame.add(new Grid());
        frame.setSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void Start(){

    }
}
