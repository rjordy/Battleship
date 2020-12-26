package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class GridSize {
    private int rows = 8;
    private int cols = 8;


    private JFrame frame;
    private static JDialog d;


    public GridSize(){
        Integer[] sizes = new Integer[]{5,6,7,8,9,10,11,12,13,14,15};

        frame = new JFrame();
        d = new JDialog(frame , "Select a grid Size", true);
        d.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,0,20);
        d.add(new JLabel("Rows:"), gbc);
        gbc.gridx = 2;
        d.add(new JLabel("Cols :"), gbc);
        gbc.gridy = 1;
        JComboBox amtCol = new JComboBox<Integer>(sizes);
        amtCol.setSelectedItem(8);
        d.add(amtCol, gbc);
        gbc.gridx = 0;
        JComboBox amtRow = new JComboBox<Integer>(sizes);
        amtRow.setSelectedItem(8);
        d.add(amtRow, gbc);
        gbc.gridx = 1;
        d.add(new JLabel("x"), gbc);
        d.setSize(new Dimension(300,150));

        gbc.gridy = 2;
        JButton okBut = new JButton("Ok");
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRows((int)amtRow.getSelectedItem());
                setCols((int)amtCol.getSelectedItem());
                d.dispose();
            }
        });

        d.add(okBut, gbc);
        d.setVisible(true);
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    public void setCols(int cols){
        this.cols = cols;
    }

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }
}
