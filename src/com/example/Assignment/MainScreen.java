package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainScreen {
    private final JFrame frame;
    private JPanel background;
    private int rows;
    private int cols;
    private boolean randomization;
    private boolean scoringRegular;

    private ImageButton startButton;
    private ImageButton hiscoreButton;
    private ImageButton rulesButton;
    private ImageButton settingsButton;
    private ImageButton exitButton;

    private Ship[] ships;

    /*
     * By default the mainscreen should prepare for the board placement
     * it does this by using a grid size of 8x8
     * and using randomization for the board placement
     */
    public MainScreen(JFrame frame) {
        this.frame = frame;
        this.rows = 8;
        this.cols = 8;
        this.randomization = true;
        this.scoringRegular = true;
        Placement placement = new Placement();
        placement.createRandomBoard(rows, cols);
        this.ships = placement.getShips();
    }


    /*
     * This initializes the buttons that will be used on the main screen
     * with every button there is a call to *ButtonFunc()
     */
    private void initButtons(){
        this.startButton = new ImageButton(Constants.START_ICON, Constants.START_HOVER_ICON);
        this.startButton.setBounds(500, 160,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT );
        setStartButtonFunc();

        this.hiscoreButton = new ImageButton(Constants.HIGHSCORE_ICON, Constants.HIGHSCORE_HOVER_ICON);
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

    /*
     * This adds functionality to the start button
     * whenever the start button is clicked it should ask the user which scoring system they want to use
     * afterwards the background is removed from the frame and replaced by a gameboard
     */
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

    /*
     * When the highscore button is clicked it should open up a new frame
     * This displays the top 10 of previously attained scores
     */
    private void setHiscoreButtonFunc(){
        this.hiscoreButton.addActionListener(e -> {
            HighScore highScore = new HighScore();
            highScore.drawHighscores();
        });
    }

    /*
     * whenever the rules button is clicked it should open a new frame
     * This displays the general rules that belong to the game
     */
    private void setRulesButtonFunc(){
        this.rulesButton.addActionListener(e -> new Rules());
    }

    /*
     * Whenever the settings button is called it should up a new frame displaying the settings
     * by default it will show the settings for randomization, which allow the user to change the grid dimensions
     * by clicking change source the settings convert to a user provided file
     * this allows the user to select a .txt file of their choosing
     * if no file is chosen then it will default to Placement.txt contained in the root folder
     */
    private void setSettingsButtonFunc() {
        this.settingsButton.addActionListener(e -> {
            Settings settings = new Settings(randomization);
            rows = settings.getRows();
            cols = settings.getCols();
            ships = settings.getShips();
            randomization = settings.isRandomization();
        });
    }

    /*
     * Whenever the user clicks on the exit button it should fully stop the program
     */
    private void setExitButtonFunc(){
        this.exitButton.addActionListener(e -> System.exit(0));
    }

    /*
     * This method will add the buttons to the screen
     */
    private void drawButtons(){
        background.add(this.startButton);
        background.add(this.hiscoreButton);
        background.add(this.rulesButton);
        background.add(this.settingsButton);
        background.add(this.exitButton);
    }

    /*
     * This contains the full logic for the creation of the main screen
     */
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
