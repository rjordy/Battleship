package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainScreen {
    private final JFrame frame;
    private JPanel background;
    private int rows = 8;
    private int cols = 8;
    private boolean randomization = true;
    private boolean scoringRegular = true;

    private ImageButton startButton;
    private ImageButton hiscoreButton;
    private ImageButton rulesButton;
    private ImageButton settingsButton;
    private ImageButton exitButton;

    private Ship[] ships;

    public MainScreen(JFrame frame) {
        this.frame = frame;
        this.rows = 8;
        this.cols = 8;
        Placement placement = new Placement();
        placement.createRandomBoard(rows, cols);
        this.ships = placement.getShips();
    }


    private void initButtons(){
        this.startButton = new ImageButton(Constants.START_ICON, Constants.START_HOVER_ICON);
        this.startButton.setBounds(500, 160,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setStartButtonFunc();

        this.hiscoreButton = new ImageButton(Constants.HISCORE_ICON, Constants.HISCORE_HOVER_ICON);
        this.hiscoreButton.setBounds(500, 240,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setHiscoreButtonFunc();

        this.rulesButton = new ImageButton(Constants.RULES_ICON, Constants.RULES_HOVER_ICON);
        this.rulesButton.setBounds(500, 290,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setRulesButtonFunc();

        this.settingsButton = new ImageButton(Constants.SETTINGS_ICON, Constants.SETTINGS_ICON_HOVER);
        this.settingsButton.setBounds(500, 340,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setSettingsButtonFunc();

        this.exitButton = new ImageButton(Constants.EXIT_ICON, Constants.EXIT_HOVER_ICON);
        this.exitButton.setBounds(500, 480,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setExitButtonFunc();
    }

    private void setStartButtonFunc(){
        this.startButton.addActionListener(e -> {
            JFrame scoreFrame = new JFrame();
            JDialog scoreDialog = new JDialog(scoreFrame, "Choose your scoring system:", true);
            scoreDialog.setLayout(new FlowLayout());
            JButton normalScore = new JButton("Regular");
            JButton altScore = new JButton("Alternative");
            normalScore.addActionListener(e1 -> {
                    scoringRegular = true;
                    scoreDialog.dispose();
            });
            altScore.addActionListener(e1 -> {
                scoringRegular = false;
                scoreDialog.dispose();
            });
            scoreDialog.add(normalScore);
            scoreDialog.add(altScore);
            scoreDialog.setSize(new Dimension(245,80));
            scoreDialog.setLocationRelativeTo(null);
            scoreDialog.setResizable(false);
            scoreDialog.setVisible(true);
            frame.getContentPane().remove(background);
            new GameBoard(frame, rows, cols, ships, scoringRegular);
        });
    }

    private void setHiscoreButtonFunc(){
        this.hiscoreButton.addActionListener(e -> {
            HighScore highScore = new HighScore();
            highScore.drawHighscores();
        });
    }

    private void setRulesButtonFunc(){
        this.rulesButton.addActionListener(e -> new Rules());
    }

    private void setSettingsButtonFunc() {
        this.settingsButton.addActionListener(e -> {
            Settings settings = new Settings(randomization);
            rows = settings.getRows();
            cols = settings.getCols();
            ships = settings.getShips();
            randomization = settings.isRandomization();
        });
    }

    private void setExitButtonFunc(){
        this.exitButton.addActionListener(e -> System.exit(0));
    }

    private void drawButtons(){
        background.add(this.startButton);
        background.add(this.hiscoreButton);
        background.add(this.rulesButton);
        background.add(this.settingsButton);
        background.add(this.exitButton);
    }
    public void drawMainScreen() throws IOException {
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
