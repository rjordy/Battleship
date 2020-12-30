package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Grid extends JPanel {
    private int rows = 8;
    private int cols = 8;

    //This will be the dimension of the total JPanel containing all cells
    private static final int GRID_DIMENSION = 400;

    private Ship[] ships;

    public Grid(Ship[] ships) {
        this.ships = ships;
        setLayout(new GridBagLayout());
    }

    /*
     * for every row and column:
     * create a cell and if the row and column match that of a ship
     * use the shipname to define the cell background after being hit
     * if it doesn't match then this means the cell would be a miss
     */
    public void drawGrid(){
        int cellDimension = GRID_DIMENSION / rows;

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                /*
                 * These are the standard borders
                 */
                int TOP_BORDER = 1;
                int LEFT_BORDER = 1;
                int BOTT_BORDER = 0;
                int RIGHT_BORDER = 0;

                GridLogic cellPane = new GridLogic();

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

                /*
                 * if the cell is at the top (row = 0) then add extra weight to the top border
                 * this repeats for every border (top, down, left, right) in order
                 */
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

                //give every panel a distinct name which corresponds to the coordinates
                String name = String.format("cellPane[%d, %d]",
                        col + 1, row + 1);
                cellPane.setName(name);
                Border border = new MatteBorder(TOP_BORDER, LEFT_BORDER, BOTT_BORDER, RIGHT_BORDER, Color.GRAY);
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
