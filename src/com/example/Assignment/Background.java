package com.example.Assignment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background extends JPanel {
/*
* This class allows a user to paint a background on a JPanel
 */
    private Image background;

    public Background(String fileName) throws IOException {
        background = ImageIO.read(new File(fileName));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}
