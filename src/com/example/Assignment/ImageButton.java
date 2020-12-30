package com.example.Assignment;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageButton extends JButton {

    /*
     * This creates a JButton which uses an image as a button
     * when a user hovers their mouse over the button it should change
     * this is the second argument img_on_hover
     */
    public ImageButton(ImageIcon img, ImageIcon img_on_hover) {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setIcon(img);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageButton.super.setIcon(img_on_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageButton.super.setIcon(img);
            }
        });
    }
}
