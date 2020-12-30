package com.example.Assignment;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Settings {
    private int rows = 8;
    private int cols = 8;
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

    /*
     * When the settings are called it should open a new frame which by default shows the randomization options
     * this allows the user to edit the amount of rows and columns the grid will have
     * if a user doesn't want to play with randomization they can press the change source button
     * this will remove the randomization settings from the frame and add the file settings on to the frame instead
     */
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
        this.d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.d.setVisible(true);
    }

    /*
     * This method will add JLabels to the frame denoting the currently used source
     */
    private void addBoardSource(Boolean randomized){
        this.boardSource = new JPanel();
        boardSource.setLayout(new BoxLayout(boardSource, BoxLayout.PAGE_AXIS));
        JLabel board = new JLabel("Current board setting:");
        board.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel source;
        if (randomized) {
            source = new JLabel("Randomization");
        } else {
            source = new JLabel("User provided file");
        }
        source.setFont(new Font("Dialog", Font.PLAIN, 12));
        source.setAlignmentX(Component.CENTER_ALIGNMENT);
        boardSource.add(board);
        boardSource.add(source);
    }

    /*
     * This will add 2 buttons (change source and ok) to the frame
     * along with the functionality
     */
    private void addButtons(){
        this.buttons = new JPanel();
        this.buttons.setLayout(new FlowLayout());
        JButton changeButton = new JButton("Change source");
        changeButton.addActionListener(e -> {
            if (randomization){
                d.remove(settings);
                d.remove(boardSource);
                addFileSettings();
                addBoardSource(!randomization);
                d.add(boardSource, BorderLayout.PAGE_START);
                d.add(fileSettings, BorderLayout.CENTER);
                d.revalidate();
                randomization = !randomization;
            } else {
                d.remove(fileSettings);
                d.remove(boardSource);
                addRandomSettings();
                addBoardSource(!randomization);
                d.add(boardSource, BorderLayout.PAGE_START);
                d.add(settings, BorderLayout.CENTER);
                d.revalidate();
                randomization = !randomization;
            }
        });
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            placement = new Placement();
            if (randomization) {
                if (amtRow.getSelectedItem() != null) {
                    setRows((int) amtRow.getSelectedItem());
                }
                if (amtCol.getSelectedItem() != null) {
                    setCols((int) amtCol.getSelectedItem());
                }
                placement.createRandomBoard(this.rows, this.cols);
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

    /*
     * This method will add the settings pertaining to the randomization of the board
     */
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

    /*
     * This method will add the ability to select a file for placement
     * if no file is selected it should use the default file Placement.txt
     */
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
        if (this.rows == 0){
            return 8;
        }
        return this.rows;
    }

    public int getCols(){
        if (this.cols == 0){
            return 8;
        }
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
