package com.example.Assignment;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImgButton extends JButton {

    public ImgButton(ImageIcon img, ImageIcon img_on_hover) {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setIcon(img);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImgButton.super.setIcon(img_on_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImgButton.super.setIcon(img);
            }
        });
    }
}
