package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//This Class adds background functionality to the JPanel Class
public class MainScreen extends JPanel {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static Dimension FRAME_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    private static final int BUTTON_WIDTH = 267;
    private static final int BUTTON_HEIGHT = 50;

    private static final ImageIcon START_ICON = new ImageIcon("images/Start.png");
    private static final ImageIcon START_HOVER_ICON = new ImageIcon("images/StartHover.png");
    private static final ImageIcon HISCORE_ICON = new ImageIcon("images/Hiscores.png");
    private static final ImageIcon HISCORE_HOVER_ICON = new ImageIcon("images/HiscoresHover.png");
    private static final ImageIcon SETTINGS_ICON = new ImageIcon("images/Settings.png");
    private static final ImageIcon SETTINGS_HOVER_ICON = new ImageIcon("images/SettingsHover.png");
    private static final ImageIcon SHIPS_ICON = new ImageIcon("images/Ships.png");
    private static final ImageIcon SHIPS_HOVER_ICON = new ImageIcon("images/ShipsHover.png");
    private static final ImageIcon RULES_ICON = new ImageIcon("images/Rules.png");
    private static final ImageIcon RULES_HOVER_ICON = new ImageIcon("images/RulesHover.png");
    private static final ImageIcon EXIT_ICON = new ImageIcon("images/Exit.png");
    private static final ImageIcon EXIT_HOVER_ICON = new ImageIcon("images/ExitHover.png");
    private JFrame frame;


    public MainScreen() throws IOException {
        super();

        //Create frame that will hold all UI elements for the mainscreen
        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_DIMENSION);
        frame.setResizable(false);

        // Set Mainscreen background
        JPanel back = new JPanelWithBackground("images/Background.png");
        back.setLayout(null);

        ImgButton startBut = new ImgButton(START_ICON, START_HOVER_ICON);
        startBut.setBounds(500, 160,BUTTON_WIDTH,BUTTON_HEIGHT );

        startBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
            }
        });

        ImgButton hiscoreBut = new ImgButton(HISCORE_ICON, HISCORE_HOVER_ICON);
        hiscoreBut.setBounds(500, 240,BUTTON_WIDTH,BUTTON_HEIGHT );

        ImgButton settingsBut = new ImgButton(SETTINGS_ICON, SETTINGS_HOVER_ICON);
        settingsBut.setBounds(500, 290,BUTTON_WIDTH,BUTTON_HEIGHT );

        ImgButton shipsBut = new ImgButton(SHIPS_ICON, SHIPS_HOVER_ICON);
        shipsBut.setBounds(500, 340,BUTTON_WIDTH,BUTTON_HEIGHT );

        ImgButton rulesBut = new ImgButton(RULES_ICON, RULES_HOVER_ICON);
        rulesBut.setBounds(500, 390,BUTTON_WIDTH,BUTTON_HEIGHT );

        ImgButton exitBut = new ImgButton(EXIT_ICON, EXIT_HOVER_ICON);
        exitBut.setBounds(500, 480,BUTTON_WIDTH,BUTTON_HEIGHT );

        exitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        back.add(startBut);
        back.add(hiscoreBut);
        back.add(settingsBut);
        back.add(shipsBut);
        back.add(rulesBut);
        back.add(exitBut);

        frame.add(back);
        frame.setVisible(true);
    }
}
