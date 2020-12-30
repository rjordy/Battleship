package com.example.Assignment;

import javax.swing.*;
import java.io.IOException;

public class Game {

    private JFrame gameScreen;

    public Game(){
        //Create frame that will hold all UI elements for the mainscreen
        initFrame();
    }

    public void initFrame(){
        /**
         * Initialize a frame that will hold the base game
         */
        this.gameScreen = new JFrame("Battleship");
        this.gameScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameScreen.setSize(Constants.FRAME_DIMENSION);
        this.gameScreen.setLocationRelativeTo(null);
        this.gameScreen.setResizable(false);
    }

    public void start() throws IOException {
        MainScreen ms = new MainScreen(this.gameScreen);
        ms.drawMainScreen();
    }
}
