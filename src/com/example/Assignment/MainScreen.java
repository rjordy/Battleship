package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainScreen {

    private final JFrame frame = new JFrame("Battleship");


    public MainScreen() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_DIMENSION);
        frame.setResizable(false);
    }

    public void setMainScreen() throws IOException {
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
        ImgButton startBut = new ImgButton(Constants.START_ICON, Constants.START_HOVER_ICON);
        startBut.setBounds(500, 160,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        ImgButton hiscoreBut = new ImgButton(Constants.HISCORE_ICON, Constants.HISCORE_HOVER_ICON);
        hiscoreBut.setBounds(500, 240,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        ImgButton settingsBut = new ImgButton(Constants.SETTINGS_ICON, Constants.SETTINGS_HOVER_ICON);
        settingsBut.setBounds(500, 290,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        ImgButton shipsBut = new ImgButton(Constants.SHIPS_ICON, Constants.SHIPS_HOVER_ICON);
        shipsBut.setBounds(500, 340,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        ImgButton rulesBut = new ImgButton(Constants.RULES_ICON, Constants.RULES_HOVER_ICON);
        rulesBut.setBounds(500, 390,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        ImgButton exitBut = new ImgButton(Constants.EXIT_ICON, Constants.EXIT_HOVER_ICON);
        exitBut.setBounds(500, 480,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );

        /*
        When the start button is pressed it should clear the current frame and
        insert a new panel that contains the grid that will be used for playing the game.
         */
        startBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameBoard();

            }
        });

        /*
        When the exit button is pressed it should stop the program.
         */
        rulesBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRules();
            }
        });

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
        background.add(settingsBut);
        background.add(shipsBut);
        background.add(rulesBut);
        background.add(exitBut);

        /*
        Add the panel to the frame and display it to the user.
         */
        frame.getContentPane().add(background);
        frame.setVisible(true);
    }

    private void setGameBoard(){
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        JLabel test = new JLabel("Test");
        panel.add(test);
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void setSettings(){

    }

    private void setHiscore(){

    }

    private void setRules(){
        new Rules();
    }

    private void setShips() {

    }
}
