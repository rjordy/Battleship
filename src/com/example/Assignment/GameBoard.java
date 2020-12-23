package com.example.Assignment;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    public GameBoard(){
        super();

        setLayout(new BorderLayout());
        JButton test = new JButton("Test");
        test.setBounds(50,50,50,50);
        add(test);
    }
}
