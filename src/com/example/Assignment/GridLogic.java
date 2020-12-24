package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridLogic extends JPanel {

//    player[1].turn = true;
//    player[2].turn = false;

    private Color originalBackground;
    private static final Color DEFAULTBACKGROUND = new Color(238, 238, 238);

    public GridLogic() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                originalBackground = getBackground();
//                if originalbackground != default {} else {
//                if cell is in carrier:
//                    color red;
//                    else if in destroyer color red ...
//                else:
                setBackground(Color.BLUE);
//                switchplayer;
//                setfont of active player to bold;
//            }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (getBackground() != Color.BLUE) {
                    originalBackground = getBackground();
                    System.out.println(originalBackground);
                    System.out.println(DEFAULTBACKGROUND);
                    setBackground(Color.GRAY);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (getBackground() != Color.BLUE) {
                    setBackground(originalBackground);
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
}
