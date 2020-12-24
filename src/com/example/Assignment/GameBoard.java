package com.example.Assignment;

import sun.text.bidi.BidiRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameBoard {
    private static final int VALID_BOXES = 14;
    private JLabel lPlayer1 = new JLabel("Player 1");
    private JLabel lPlayer2 = new JLabel("Player 2");
    private JLabel lScore1 = new JLabel("Score: 0");
    private JLabel lScore2 = new JLabel("Score: 900");
    private JLabel lTurn = new JLabel("It's your turn Player 1!");

    private JButton Brule = new JButton("Rules");
    private JButton BHiscore = new JButton("Hiscores");
    private JButton BExit = new JButton("Exit");

    private Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    private Font BOLD_FONT = new Font("Dialog", Font.BOLD, 12);

    public GameBoard(JFrame frame){
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout(0,0));
//        GridBagConstraints gbc = new GridBagConstraints();

        /*
        Adding player information to the frame. This will always be displayed at the top of the frame
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
                if (lPlayer1.getFont() == DEFAULT_FONT){
                    setTurnP1();
                } else {
                    setTurnP2();
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

        lPlayer2.setFont(new Font("Dialog", Font.PLAIN, 12));
        lScore2.setFont(new Font("Dialog", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 0;
        playerInfo.add(lPlayer1,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        playerInfo.add(Brule, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        playerInfo.add(lPlayer2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        playerInfo.add(lScore1,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        playerInfo.add(BHiscore, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        playerInfo.add(lScore2, gbc);
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

    public void setTurnP1(){
        lPlayer1.setFont(BOLD_FONT);
        lPlayer2.setFont(DEFAULT_FONT);
        lScore1.setFont(BOLD_FONT);
        lScore2.setFont(DEFAULT_FONT);
        lTurn.setText("It's your turn Player 1!");
    }

    public void setTurnP2(){
        lPlayer1.setFont(DEFAULT_FONT);
        lPlayer2.setFont(BOLD_FONT);
        lScore1.setFont(DEFAULT_FONT);
        lScore2.setFont(BOLD_FONT);
        lTurn.setText("It's your turn Player 2!");

    }
}
