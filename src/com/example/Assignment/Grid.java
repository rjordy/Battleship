package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Grid extends JPanel {
    private int rows = 8;
    private int cols = 8;

    private JPanel selectedPanel = null;

    private static final int GRID_DIMENSION = 400;

    private Ship[] ships;

    public Grid(Ship[] ships) {
        this.ships = ships;
        setLayout(new GridBagLayout());
    }

    public void drawGrid(){
        int cellDimension = GRID_DIMENSION / rows;

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                int TOP_BORDER = 1;
                int LEFT_BORDER = 1;
                int BOTT_BORDER = 0;
                int RIGHT_BORDER = 0;

                GridLogic cellPane = new GridLogic();
                Border border = null;

                for (Ship ship : this.ships){
                    for (Point p : ship.getCoords()){
                        if (p.equals(new Point(col + 1, row + 1))){
                            cellPane.setHitIndicator(ship.getName());
                        }
                    }
                }

                if (cellPane.getHitIndicator() == null){
                    cellPane.setHitIndicator("Miss");
                }

                if (row == 0){
                    TOP_BORDER++;
                } else if (row == (rows - 1)){
                    BOTT_BORDER += 2;
                }

                if (col == 0){
                    LEFT_BORDER++;
                } else if (col == (cols - 1)){
                    RIGHT_BORDER += 2;
                }

                String name = String.format("cellPane[%d, %d]",
                        col + 1, row + 1);
                cellPane.setName(name);
                border = new MatteBorder(TOP_BORDER, LEFT_BORDER, BOTT_BORDER, RIGHT_BORDER, Color.GRAY);
                cellPane.setBorder(border);

                //set cell size so the total grid will occupy a 400x400 dimension.
                cellPane.setPreferredSize(new Dimension(cellDimension,cellDimension));
                add(cellPane, gbc);
            }
        }
    }


    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    public void setCols(int cols){
        this.cols = cols;
    }

    public void setGridSize(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }
}
