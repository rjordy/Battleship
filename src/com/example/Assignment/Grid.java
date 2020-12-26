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

    private JPanel selectedPanel = null;

    private static final int GRID_DIMENSION = 400;

    private Ship[] ships;

    public Grid(Ship[] ships) {
        this.ships = ships;
        setLayout(new GridBagLayout());
        Placement placement = new Placement();
        placement.createRandomBoard(GRID_ROWS, GRID_COLS, ships);
        for (Ship ship : ships){
            System.out.println("Coordinates for: " + ship.getName());
            for (Point p : ship.getCoords()){
                System.out.println("X: " + String.valueOf(p.getX()) + "Y: " + String.valueOf(p.getY()));
            }
        }
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
                } else if (row == (GRID_ROWS - 1)){
                    BOTT_BORDER += 2;
                }

                if (col == 0){
                    LEFT_BORDER++;
                } else if (col == (GRID_COLS - 1)){
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
