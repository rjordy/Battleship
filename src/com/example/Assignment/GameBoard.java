package com.example.Assignment;

import sun.text.bidi.BidiRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameBoard {
    private int valid_boxes = 14;
    private int rows;
    private int cols;
    private Ship[] ships;

    private final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    private final Font BOLD_FONT = new Font("Dialog", Font.BOLD, 12);

    private final Player player1 = new Player(1);
    private final Player player2 = new Player(2);

    private final JLabel lTurn = new JLabel("It's your turn Player 1!");

    public GameBoard(JFrame frame, int rows, int cols, Ship[] ships){
        this.rows = rows;
        this.cols = cols;
        this.ships = ships;
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout(0,0));

        /*
        Adding player information to the frame. This will always be displayed at the top of the frame
        Using a GridBagLayout for the first panel with 3 rows and 3 columns.
         */
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,0,0,30);


        JButton brule = new JButton("Rules");
        brule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Rules();
            }
        });

        JButton BHiscore = new JButton("Hiscores");
        BHiscore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switchTurn();
            }
        });

        JButton BExit = new JButton("Exit");
        BExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });


        /**
         * Initiliaze player information and display the first turn by setting the font of player 1 to bold
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

        /**
        * Adding turn indicator, which will be displayed on the bottom of the grid
         */
        JPanel turnIndicator = new JPanel();
        turnIndicator.add(lTurn);
        frame.add(turnIndicator, BorderLayout.PAGE_END);


        /**
        * Add Grid as a panel to the frame
         */
        Grid grid = new Grid(ships);
        grid.setGridSize(this.rows,this.cols);
        grid.drawGrid();
        grid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GridLogic panel = (GridLogic) grid.getComponentAt(e.getPoint());
                if (panel == null) {
                    return;
                } else {
                    if (panel.isActive()) {
                        String panelName = panel.getName();
                        String[] panelCoord = panelName.substring(9, panelName.length() - 1).split(", ");
                        Point panelPoint = new Point(Integer.parseInt(panelCoord[0]), Integer.parseInt(panelCoord[1]));
                        for (Ship ship : ships) {
                            if (ship.containsPoint(panelPoint)) {
                                if (player1.getTurn()) {
                                    player1.addScore(ship.getScore());
                                } else {
                                    player2.addScore(ship.getScore());
                                }
                                valid_boxes--;
                                System.out.println("Boxes left: " + valid_boxes);
                                if (valid_boxes == 0){
                                    announceWinner();
                                }
                            }
                        }
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

    public void switchTurn(){
        player1.switchTurn();
        player2.switchTurn();
        if (player1.getTurn()){
            lTurn.setText("It's your turn " + player1.getName() + "!");
        } else{
            lTurn.setText("It's your turn " + player2.getName() + "!");
        }
    }

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

        JFrame winnerFrame = new JFrame(winnerName + " has won!");
        JPanel winnerPanel = new JPanel();
//        winnerPanel.setLayout(new BoxLayout());
        winnerPanel.add(new JLabel("Congratulations " + winnerName + "!"));
        winnerPanel.add(new JLabel("You won with a score of " + winnerScore + "."));
        winnerFrame.setSize(new Dimension(250,150));
        winnerFrame.add(winnerPanel);
        winnerFrame.setVisible(true);
    }


}
