package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainScreen {
    private JFrame frame;
    private JPanel background;
    private int rows = 8;
    private int cols = 8;
    private boolean randomization = true;

    private ImgButton startButton;
    private ImgButton hiscoreButton;
    private ImgButton rulesButton;
    private ImgButton settingsButton;
    private ImgButton shipsButton;
    private ImgButton exitButton;

    private Ship[] ships;

    public MainScreen(JFrame frame) {
        this.frame = frame;
    }


    private void initButtons(){
        this.startButton = new ImgButton(Constants.START_ICON, Constants.START_HOVER_ICON);
        this.startButton.setBounds(500, 160,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setStartButtonFunc();

        this.hiscoreButton = new ImgButton(Constants.HISCORE_ICON, Constants.HISCORE_HOVER_ICON);
        this.hiscoreButton.setBounds(500, 240,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setHiscoreButtonFunc();

        this.rulesButton = new ImgButton(Constants.RULES_ICON, Constants.RULES_HOVER_ICON);
        this.rulesButton.setBounds(500, 290,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setRulesButtonFunc();

        this.settingsButton = new ImgButton(Constants.SETTINGS_ICON, Constants.SETTINGS_ICON_HOVER);
        this.settingsButton.setBounds(500, 390,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setSettingsButton();

        this.shipsButton = new ImgButton(Constants.SHIPS_ICON, Constants.SHIPS_HOVER_ICON);
        this.shipsButton.setBounds(500, 340,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setShipsButtonFunc();

        this.exitButton = new ImgButton(Constants.EXIT_ICON, Constants.EXIT_HOVER_ICON);
        this.exitButton.setBounds(500, 480,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setExitButtonFunc();
    }

    private void setStartButtonFunc(){
        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(background);
                GameBoard gb = new GameBoard(frame, rows, cols, ships);
            }
        });
    }

    private void setHiscoreButtonFunc(){
    }

    private void setRulesButtonFunc(){
        this.rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rules();
            }
        });
    }

    private void setSettingsButton() {
        this.settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings();
                rows = settings.getRows();
                cols = settings.getCols();
                ships = settings.getShips();
            }
        });
    }

    private void setShipsButtonFunc(){
        this.shipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame boardPlace = new JFrame();
                JDialog d = new JDialog(boardPlace, "Ship placement on board", true);
                d.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                JLabel boardSource = new JLabel("Current board source: ");
                JLabel source = new JLabel("Randomized board");
                source.setFont(new Font("Dialog", Font.PLAIN, 12));
                JButton changeSource = new JButton("Change source");
                changeSource.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        randomization = !randomization;
                        if (randomization) {
                            source.setText("Randomized board");
                        } else {
                            source.setText("Based on user-provided file");
                        }
                    }
                });
                gbc.insets = new Insets(10,40,10,0);
                d.add(boardSource, gbc);
                gbc.insets = new Insets(10,5,10,40);
                d.add(source,gbc);
                gbc.gridy=1;
                gbc.gridwidth=2;
                d.add(changeSource,gbc);
                d.pack();
                d.setVisible(true);
            }
        });
    }

    private void setExitButtonFunc(){
        this.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void drawButtons(){
        background.add(this.startButton);
        background.add(this.hiscoreButton);
        background.add(this.rulesButton);
        background.add(this.settingsButton);
        background.add(this.shipsButton);
        background.add(this.exitButton);
    }
    private void drawMainScreen(JFrame frame) throws IOException {
        /*
        Create a panel with a background image painted on it.
        This will be used as the basis for the main screen.
         */
        this.background = new Background("images/Background.png");
        this.background.setLayout(null);
        initButtons();
        drawButtons();

        /*
        Add the panel to the frame and display it to the user.
         */
        frame.getContentPane().add(this.background);
        frame.setVisible(true);
    }

    public void setMainScreen() throws IOException {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_DIMENSION);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        drawMainScreen(frame);
    }

    private void showHiscore(){

    }

    private void setShips() {
    }

    private void setRows(int rows){
        this.rows = rows;
    }

    private void setCols(int cols){
        this.cols = cols;
    }

    private int getRows(){
        return this.rows;
    }

    private int getCols(){
        return this.cols;
    }
}
