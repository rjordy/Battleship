package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid extends JPanel {
    public Grid(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                int TOP_BORDER = 1;
                int LEFT_BORDER = 1;
                int BOTT_BORDER = 0;
                int RIGHT_BORDER = 0;

                GridLogic cellPane = new GridLogic();
                Border border = null;

                if (row == 0){
                    TOP_BORDER++;
                } else if (row == 7){
                    BOTT_BORDER += 2;
                }

                if (col == 0){
                    LEFT_BORDER++;
                } else if (col == 7){
                    RIGHT_BORDER += 2;
                }

                border = new MatteBorder(TOP_BORDER, LEFT_BORDER, BOTT_BORDER, RIGHT_BORDER, Color.GRAY);
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }
}
