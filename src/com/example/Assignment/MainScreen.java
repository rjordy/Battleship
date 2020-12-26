package com.example.Assignment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainScreen {
    private JFrame frame = new JFrame("Battleship");
    private int rows = 8;
    private int cols = 8;


    public MainScreen() {}

    private void drawMainScreen(JFrame frame) throws IOException {
        /*
        Create a panel with a background image painted on it.
        This will be used as the basis for the main screen.
         */
        JPanel background = new JPanelWithBackground("images/Background.png");
//        JPanel back = new JPanel();
        background.setLayout(null);

        /*
        Adding different buttons to the main screen:
        Start, Hiscores, Settings, Ship placement, Rules and Exit
         */

        /*
        When the start button is pressed it should clear the current frame and
        insert a new panel that contains the grid that will be used for playing the game.
         */
        ImgButton startBut = new ImgButton(Constants.START_ICON, Constants.START_HOVER_ICON);
        startBut.setBounds(500, 160,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        startBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameBoard();

            }
        });

        ImgButton hiscoreBut = new ImgButton(Constants.HISCORE_ICON, Constants.HISCORE_HOVER_ICON);
        hiscoreBut.setBounds(500, 240,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        /*
        When the rules button is pressed it should open a new frame displaying the rules of the game.
         */
        ImgButton rulesBut = new ImgButton(Constants.RULES_ICON, Constants.RULES_HOVER_ICON);
        rulesBut.setBounds(500, 290,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        rulesBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRules();
            }
        });

        /**
        *When the Grid Size button is pressed it should open up a new frame.
        *This Frame will display 2 dropdown fields allowing the user to choose their grid size
         * Limited from 5x5 to 15x15
         */
        ImgButton gridBut = new ImgButton(Constants.GRID_ICON, Constants.GRID_ICON_HOVER);
        gridBut.setBounds(500, 390,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        gridBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridSize gs = new GridSize();
                rows = gs.getRows();
                cols = gs.getCols();
            }
        });


        ImgButton shipsBut = new ImgButton(Constants.SHIPS_ICON, Constants.SHIPS_HOVER_ICON);
        shipsBut.setBounds(500, 340,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        ImgButton exitBut = new ImgButton(Constants.EXIT_ICON, Constants.EXIT_HOVER_ICON);
        exitBut.setBounds(500, 480,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        /*
        When the exit button is pressed it should stop the program.
         */
        exitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*
        Add all the buttons to the panel containing the background image
         */
        background.add(startBut);
        background.add(hiscoreBut);
        background.add(gridBut);
        background.add(shipsBut);
        background.add(rulesBut);
        background.add(exitBut);

        /*
        Add the panel to the frame and display it to the user.
         */
        frame.getContentPane().add(background);
        frame.setVisible(true);
    }

    public void setMainScreen() throws IOException {
//        JFrame frame = new JFrame("Battleship:");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_DIMENSION);
        frame.setResizable(false);
        drawMainScreen(frame);
    }
    public void setMainScreen(JFrame frame) throws IOException {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_DIMENSION);
        frame.setResizable(false);
        drawMainScreen(frame);
        frame.validate();
        frame.repaint();
    }

    private void setGameBoard(){
        frame.getContentPane().removeAll();
        GameBoard gb = new GameBoard(frame);
    }

    private void setSettings(){

    }

    private void setHiscore(){

    }

    private void showRules(){
        new Rules();
    }

    private void setShips() {

    }
}
