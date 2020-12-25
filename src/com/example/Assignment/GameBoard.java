package com.example.Assignment;

import sun.text.bidi.BidiRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameBoard {
    private static final int VALID_BOXES = 14;

    private JButton Brule = new JButton("Rules");
    private JButton BHiscore = new JButton("Hiscores");
    private JButton BExit = new JButton("Exit");

    private Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    private Font BOLD_FONT = new Font("Dialog", Font.BOLD, 12);

    private Player player1 = new Player(1);
    private Player player2 = new Player(2);

    private JLabel lTurn = new JLabel("It's your turn Player 1!");

    public GameBoard(JFrame frame){
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout(0,0));

        /*
        Adding 2 players to the game
         */


        /*
        Adding player information to the frame. This will always be displayed at the top of the frame
        Using a GridBagLayout for the first panel with 3 rows and 3 columns.
         */
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,0,0,30);


        Brule.addMouseListener(new MouseAdapter() {
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
                player1.switchTurn();
                player2.switchTurn();
                if (player1.getTurn()){
                    lTurn.setText("It's your turn " + player1.getName() + "!");
                } else{
                    lTurn.setText("It's your turn " + player2.getName() + "!");
                }
            }
        });

        BExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        player1.setBold();
        player2.setPlain();

        gbc.gridx = 0;
        gbc.gridy = 0;
        playerInfo.add(player1.getlPlayer(),gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        playerInfo.add(Brule, gbc);
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
        Adding turn indicator, which will be displayed to the left of the grid
         */
        JPanel turnIndicator = new JPanel();
        turnIndicator.add(lTurn);
        frame.add(turnIndicator, BorderLayout.PAGE_END);


        /*
        Add Grid as a panel to the frame
         */
        Grid grid = new Grid();
        grid.setGridSize(8,8);
        grid.drawGrid();
        frame.getContentPane().add(grid, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }
}
