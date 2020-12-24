package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid extends JPanel {
    public int GRID_ROWS = 8;
    public int GRID_COLS = 8;

    private static final int GRID_DIMENSION = 400;

    public Grid() {
        setLayout(new GridBagLayout());
    }

    public void drawGrid(){
        int cellDimension = GRID_DIMENSION / GRID_ROWS;

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < GRID_ROWS; row++){
            for (int col = 0; col < GRID_COLS; col++) {
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
                } else if (row == (GRID_ROWS - 1)){
                    BOTT_BORDER += 2;
                }

                if (col == 0){
                    LEFT_BORDER++;
                } else if (col == (GRID_COLS - 1)){
                    RIGHT_BORDER += 2;
                }

                border = new MatteBorder(TOP_BORDER, LEFT_BORDER, BOTT_BORDER, RIGHT_BORDER, Color.GRAY);
                cellPane.setBorder(border);

                //set cell size so the total grid will occupy a 400x400 dimension.
                cellPane.setPreferredSize(new Dimension(cellDimension,cellDimension));
                add(cellPane, gbc);
            }
        }
    }


    public int getGRID_ROWS(){
        return GRID_ROWS;
    }

    public int getGRID_COLS(){
        return GRID_COLS;
    }

    public void setGRID_ROWS(int rows){
        GRID_ROWS = rows;
    }

    public void setGRID_COLS(int cols){
        GRID_COLS = cols;
    }

    public void setGridSize(int rows, int cols){
        GRID_ROWS = rows;
        GRID_COLS = cols;
    }
}
