package com.example.Assignment;

import javax.swing.*;
import java.awt.*;

public class Constants {

    private Constants() {}

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;
    public static final Dimension FRAME_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public static final int BUTTON_WIDTH = 267;
    public static final int BUTTON_HEIGHT = 50;

    public static final ImageIcon START_ICON = new ImageIcon("images/Start.png");
    public static final ImageIcon START_HOVER_ICON = new ImageIcon("images/StartHover.png");
    public static final ImageIcon HISCORE_ICON = new ImageIcon("images/Hiscores.png");
    public static final ImageIcon HISCORE_HOVER_ICON = new ImageIcon("images/HiscoresHover.png");
    public static final ImageIcon SETTINGS_ICON = new ImageIcon("images/Settings.png");
    public static final ImageIcon SETTINGS_HOVER_ICON = new ImageIcon("images/SettingsHover.png");
    public static final ImageIcon SHIPS_ICON = new ImageIcon("images/Ships.png");
    public static final ImageIcon SHIPS_HOVER_ICON = new ImageIcon("images/ShipsHover.png");
    public static final ImageIcon RULES_ICON = new ImageIcon("images/Rules.png");
    public static final ImageIcon RULES_HOVER_ICON = new ImageIcon("images/RulesHover.png");
    public static final ImageIcon EXIT_ICON = new ImageIcon("images/Exit.png");
    public static final ImageIcon EXIT_HOVER_ICON = new ImageIcon("images/ExitHover.png");

    public static final int CARRIER_SIZE = 5;
    public static final int BATTLESHIP_SIZE = 4;
    public static final int SUBMARINE_SIZE = 3;
    public static final int DESTROYER_SIZE = 2;
}
