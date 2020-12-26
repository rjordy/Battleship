package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class GridLogic extends JPanel {

//    player[1].turn = true;
//    player[2].turn = false;

    private Color originalBackground;
    private Color hitBackground;
    private static final Color DEFAULTBACKGROUND = new Color(238, 238, 238);
    private final Color[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.RED};
    private String hitIndicator;

    public GridLogic() {
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                originalBackground = getBackground();
//                setBackground(hitBackground);
////                switchplayer;
////                setfont of active player to bold;
////            }
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                if (!Arrays.stream(colors).anyMatch(getBackground()::equals)) {
//                    originalBackground = getBackground();
//                    setBackground(Color.GRAY);
//                }
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                if (!Arrays.stream(colors).anyMatch(getBackground()::equals)) {
//                    setBackground(originalBackground);
//                }
//            }
//        });
    }

    public void showHit(){
        originalBackground = getBackground();
        setBackground(hitBackground);
    }

    public void setHitIndicator(String s){
        this.hitIndicator = s;
        if (this.hitIndicator.toLowerCase().equals("carrier")){
            this.hitBackground = Color.RED;
        } else {
            if (this.hitIndicator.toLowerCase().equals("battleship")){
                this.hitBackground = Color.GREEN;
            } else {
                if (this.hitIndicator.toLowerCase().equals("submarine")){
                    this.hitBackground = Color.YELLOW;
                } else {
                    if (this.hitIndicator.toLowerCase().equals("destroyer")){
                        this.hitBackground = Color.BLACK;
                    } else {
                        if (this.hitIndicator.toLowerCase().equals("miss")){
                            this.hitBackground = Color.BLUE;
                        } else {
                            this.hitBackground = Color.BLUE;
                        }
                    }
                }
            }
        }
    }

    public String getHitIndicator(){
        return this.hitIndicator;
    }

    public boolean isActive(){
        if (Arrays.stream(colors).anyMatch(getBackground()::equals)){
            return false;
        } else {
            return true;
        }
    }

}
