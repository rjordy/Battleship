package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard {
    private int valid_boxes = Constants.VALID_BOXES;
    private int rows;
    private int cols;
    private final boolean scoringRegular;
    private Ship[] ships;

    private final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    private final Font BOLD_FONT = new Font("Dialog", Font.BOLD, 12);

    private final Player player1 = new Player(1);
    private final Player player2 = new Player(2);
    private final JLabel lTurn = new JLabel("It's your turn Player 1!");

    private HighScore highScore;

    public GameBoard(JFrame frame, int rows, int cols, Ship[] ships, Boolean scoring){
        this.rows = rows;
        this.cols = cols;
        this.ships = ships;
        this.scoringRegular = scoring;
        this.highScore = new HighScore();

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        /*
         * Adding player information to the frame. This will always be displayed at the top of the frame
         * Using a GridBagLayout for the first panel with 3 rows and 3 columns.
         */
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,0,0,30);

        JButton brule = new JButton("Rules");
        JButton BHiscore = new JButton("Hiscores");
        JButton BExit = new JButton("Exit");

        brule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Rules();
            }
        });

        BHiscore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                highScore.drawHighscores();
            }
        });


        BExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });


        /*
         * Initialize player information and display the first turn by setting the font of player 1 to bold
         * and setting the font of player 2 to plain
         * Afterwards add all labels and buttons to the top of the frame
         */
        player1.setBold();
        player2.setPlain();

        gbc.gridx = 0;
        gbc.gridy = 0;
        playerInfo.add(player1.getlPlayer(),gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        playerInfo.add(brule, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        playerInfo.add(player2.getlPlayer(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        playerInfo.add(player1.getlScore(),gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        playerInfo.add(BHiscore, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        playerInfo.add(player2.getlScore(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        playerInfo.add(BExit, gbc);
        frame.getContentPane().add(playerInfo, BorderLayout.PAGE_START);

        /*
         * Adding turn indicator, which will be displayed on the bottom of the grid
         */
        JPanel turnIndicator = new JPanel();
        turnIndicator.add(lTurn);
        frame.add(turnIndicator, BorderLayout.PAGE_END);


        /*
         * Add Grid as a panel to the frame
         * Also add a mouseListener to the grid that listens to which panel on the grid was clicked on.
         * After clicking a cell it should get selected and given a color.
         * It should also give a specific amount of points to whichever player whose turn it currently is.
         */
        Grid grid = new Grid(ships);
        grid.setGridSize(this.rows,this.cols);
        grid.drawGrid();
        grid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GridLogic panel = (GridLogic) grid.getComponentAt(e.getPoint());
                if (panel == null) {
                } else {
                    if (panel.isActive()) {
                        String panelName = panel.getName();
                        /*
                         * Each cell has a name resembling cellPane[x,y],
                         * this function aims to read the coordinates of this
                         */
                        String[] panelCoord = panelName.substring(9, panelName.length() - 1).split(", ");
                        Point panelPoint = new Point(Integer.parseInt(panelCoord[0]), Integer.parseInt(panelCoord[1]));
                        for (Ship ship : ships) {
                            if (ship.containsPoint(panelPoint)) {
                                /*
                                 * If there is a ship that contains a coordinate equal to the cellpanel
                                 * then it should tell this ship it was hit
                                 * and assign the correct amount of point to the right player
                                 */
                                ship.addHit();
                                int amtPoints = ship.getScore();
                                if (player1.getTurn()) {
                                    player1.addScore(amtPoints);
                                } else {
                                    if (scoringRegular) {
                                        player2.addScore(amtPoints);
                                    } else {
                                        player2.addScore((int) (amtPoints*1.2));
                                    }
                                }
                                /*
                                 * The number of cells that contain a ship
                                 *  and haven't been selected, has just decreased.
                                 */
                                valid_boxes--;
                                // If there are no more valid cells then the game should end and display the winner
                                if (valid_boxes == 0){
                                    announceWinner();
                                }
                            }
                        }
                        /*
                         * This will color the cell and switch the turns, it should always happen
                         * Independently of hit or miss
                         */
                        panel.showHit();
                        switchTurn();

                    }
                }
            }
        });
        frame.getContentPane().add(grid, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    /*
    This method switches the turns around
     */
    public void switchTurn(){
        player1.switchTurn();
        player2.switchTurn();
        if (player1.getTurn()){
            lTurn.setText("It's your turn " + player1.getName() + "!");
        } else{
            lTurn.setText("It's your turn " + player2.getName() + "!");
        }
    }

    /*
     * Announce a winner when there are no more valid cells
     * the winner is whichever player has the highest score
     * independent of the winner both scores are added to the highscores
     * as only the top 10 gets displayed.
     * After this frame the program should stop
     */
    public void announceWinner(){
        String winnerName;
        int winnerScore;

        if (player1.getScore() > player2.getScore()){
            winnerName = player1.getName();
            winnerScore = player1.getScore();
        } else {
            winnerName = player2.getName();
            winnerScore = player2.getScore();
        }

        highScore.addHighscore(player1.getScore());
        highScore.addHighscore(player2.getScore());
        JFrame winnerFrame = new JFrame(winnerName + " has won!");
        JPanel winnerPanel = new JPanel();
        winnerPanel.add(new JLabel("Congratulations " + winnerName + "!"));
        winnerPanel.add(new JLabel("You won with a score of " + winnerScore + "."));
        winnerFrame.setSize(new Dimension(280,100));
        winnerFrame.add(winnerPanel, BorderLayout.CENTER);
        winnerFrame.setLocationRelativeTo(null);
        winnerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        winnerFrame.setVisible(true);
    }


}
