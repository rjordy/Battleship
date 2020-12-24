package com.example.Assignment;

import javax.swing.*;
import java.awt.*;

public class GameBoard{
    private static final int VALID_BOXES = 14;


    public GameBoard(JFrame theFrame){
        JFrame frame = theFrame;
        frame.getContentPane().removeAll();
//        create JPanel;
//        add player information;
//        add button for exit;
//        add button for hiscore;
//        add button for rules;
//        frame.getContentPane().add(panel);
        frame.getContentPane().add(new Grid());

        frame.revalidate();
        frame.repaint();

    }
}
