package com.example.Assignment;

import javax.swing.*;
import java.awt.*;

public class Rules {
    private JFrame ruleFrame = new JFrame("Rules");


    public Rules() {
        ruleFrame.setSize(new Dimension(400, 600));
        JLabel ruleTitle = new JLabel("Battleship: The Rules");
        ruleTitle.setHorizontalAlignment(SwingConstants.CENTER);
        ruleFrame.getContentPane().add(ruleTitle, BorderLayout.PAGE_START);
        ruleFrame.setVisible(true);
    }
}
