package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    private int rows;
    private int cols;
    private boolean randomization = true;

    private final JDialog d;
    private JComboBox amtCol;
    private JComboBox amtRow;
    private JPanel settings;
    private JPanel fileSettings;
    private JPanel buttons;
    private JPanel boardSource;
    private JLabel filePath;

    private Placement placement;
    private Ship[] ships;

    private final Integer[] SIZES = new Integer[]{5,6,7,8,9,10,11,12,13,14,15};

    public Settings(boolean randomization){
        this.randomization = randomization;
        JFrame gridSize = new JFrame();
        this.d = new JDialog(gridSize, "Settings", true);
        this.d.setLayout(new BorderLayout());
        this.d.setSize(new Dimension(400,250));

        addBoardSource(true);
        addRandomSettings();
        addFileSettings();
        addButtons();
        this.d.add(boardSource, BorderLayout.PAGE_START);
        this.d.add(settings, BorderLayout.CENTER);
        this.d.add(buttons, BorderLayout.PAGE_END);
        this.d.setVisible(true);
    }

    private void addBoardSource(Boolean r){
        this.boardSource = new JPanel();
        boardSource.setLayout(new BoxLayout(boardSource, BoxLayout.PAGE_AXIS));
        JLabel BOARD = new JLabel("Current board setting:");
        BOARD.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel source;
        if (r) {
            source = new JLabel("Randomization");
        } else {
            source = new JLabel("User provided file");
        }
        source.setFont(new Font("Dialog", Font.PLAIN, 12));
        source.setAlignmentX(Component.CENTER_ALIGNMENT);
        boardSource.add(BOARD);
        boardSource.add(source);
    }

    private void addButtons(){
        this.buttons = new JPanel();
        this.buttons.setLayout(new FlowLayout());
        JButton changeButton = new JButton("Change source");
        changeButton.addActionListener(e -> {
            if (randomization){
                System.out.println("first check: " + randomization);
                d.remove(settings);
                d.remove(boardSource);
                addFileSettings();
                addBoardSource(!randomization);
                d.add(boardSource, BorderLayout.PAGE_START);
                d.add(fileSettings, BorderLayout.CENTER);
                d.revalidate();
                randomization = !randomization;
                System.out.println("second check: " + randomization);
            } else {
                System.out.println("third check: " + randomization);
                d.remove(fileSettings);
                d.remove(boardSource);
                addRandomSettings();
                addBoardSource(!randomization);
                d.add(boardSource, BorderLayout.PAGE_START);
                d.add(settings, BorderLayout.CENTER);
                d.revalidate();
                randomization = !randomization;
                System.out.println("last check: " + randomization);
            }
        });
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
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
                    } else {
                        placement.readPlacementFile(filePath.getText());
                    }
                    setRows(placement.getRows());
                    setCols(placement.getCols());
                    ships = placement.getShips();
                } catch (Exception exception) {
                    randomization = true;
                    setRows(8);
                    setCols(8);
                    placement.createRandomBoard(rows, cols);
                    ships = placement.getShips();
                }
            }
            d.dispose();
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
        this.amtCol = new JComboBox<>(this.SIZES);
        this.amtCol.setSelectedItem(8);
        this.settings.add(amtCol, gbc);
        gbc.gridx = 0;
        this.amtRow = new JComboBox<>(this.SIZES);
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
        open.addActionListener(e -> {
            int r = fc.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                filePath.setText(fc.getSelectedFile().getAbsolutePath());
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
