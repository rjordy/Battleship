package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Settings {
    private int rows;
    private int cols;
    private boolean randomization = true;
    private File filename;

    private final JDialog d;
    private JComboBox amtCol;
    private JComboBox amtRow;
    private JPanel settings;
    private JPanel fileSettings;
    private JPanel buttons;
    private JLabel filePath;

    private Placement placement;
    private Ship[] ships;

    private Integer[] SIZES = new Integer[]{5,6,7,8,9,10,11,12,13,14,15};

    public Settings(){
        JFrame gridSize = new JFrame();
        this.d = new JDialog(gridSize, "Settings", true);
        this.d.setLayout(new BorderLayout());
        this.d.setSize(new Dimension(400,250));

        addRandomSettings();
        addFileSettings();
        addButtons();
        this.d.add(settings, BorderLayout.CENTER);
        this.d.add(buttons, BorderLayout.PAGE_END);
        this.d.setVisible(true);
    }

    private void addButtons(){
        this.buttons = new JPanel();
        this.buttons.setLayout(new FlowLayout());
        JButton changeButton = new JButton("Change source");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (randomization){
                    System.out.println("first check: " + randomization);
                    d.remove(settings);
                    addFileSettings();
                    d.add(fileSettings);
                    d.revalidate();
                    randomization = !randomization;
                    System.out.println("second check: " + randomization);
                } else {
                    System.out.println("third check: " + randomization);
                    d.remove(fileSettings);
                    addRandomSettings();
                    d.add(settings);
                    d.revalidate();
                    randomization = !randomization;
                    System.out.println("last check: " + randomization);
                }
            }
        });
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placement = new Placement();
                if (randomization) {
                    setRows((int)amtRow.getSelectedItem());
                    setCols((int)amtCol.getSelectedItem());
                    placement.createRandomBoard(rows, cols);
                    ships = placement.getShips();
                } else {
                    try {
                        if (filePath.getText().equals("no file selected")) {
                            placement.readPlacementFile("Placement.txt");
                            setRows(placement.getRows());
                            setCols(placement.getCols());
                            ships = placement.getShips();
                        } else {
                            placement.readPlacementFile(filePath.getText());
                            setRows(placement.getRows());
                            setCols(placement.getCols());
                            ships = placement.getShips();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                d.dispose();
                placement.getAllShipCoords();
            }
        });
        this.buttons.add(changeButton);
        this.buttons.add(okButton);
    }

    private void addRandomSettings(){
        this.settings = new JPanel();
        this.settings.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,0,10);
        this.settings.add(new JLabel("Rows:"), gbc);
        gbc.gridx = 2;
        this.settings.add(new JLabel("Cols :"), gbc);
        gbc.gridy = 1;
        this.amtCol = new JComboBox<Integer>(this.SIZES);
        this.amtCol.setSelectedItem(8);
        this.settings.add(amtCol, gbc);
        gbc.gridx = 0;
        this.amtRow = new JComboBox<Integer>(this.SIZES);
        this.amtRow.setSelectedItem(8);
        this.settings.add(amtRow, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(10,0,0,0);
        this.settings.add(new JLabel("x"), gbc);
    }

    private void addFileSettings(){
        this.fileSettings = new JPanel();
        fileSettings.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,0,10);
        JFileChooser fc = new JFileChooser();
        JButton open = new JButton("Open");
        this.filePath = new JLabel("no file selected");
        filePath.setBorder(new MatteBorder(1,1,1,1, Color.GRAY));
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = fc.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION){
                    filePath.setText(fc.getSelectedFile().getAbsolutePath());
                }
            }
        });
        fileSettings.add(open, gbc);
        gbc.gridx=1;
        gbc.ipady=10;
        gbc.ipadx=30;
        fileSettings.add(filePath, gbc);
    }

    private void setRows(int rows){
        this.rows = rows;
    }

    private void setCols(int cols){
        this.cols = cols;
    }

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }

    public boolean isRandomization() {
        return randomization;
    }

    public Placement getPlacement() {
        return placement;
    }

    public Ship[] getShips() {
        return ships;
    }
}
