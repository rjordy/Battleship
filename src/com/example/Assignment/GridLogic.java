package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class GridLogic extends JPanel {
    private Color originalBackground;
    private Color hitBackground;
    private static final Color DEFAULTBACKGROUND = new Color(238, 238, 238);
    private final Color[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.RED};
    private String hitIndicator;

    public GridLogic() {}

    /*
     * when the cell is selected show whichever background
     * was assigned when the cell was added to the grid
     */
    public void showHit(){
        originalBackground = getBackground();
        setBackground(hitBackground);
    }

    /*
     * This sets the background when the cell gets selected
     * the color depends on the shipname
     */
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

    /*
     * This function checks the current background of the cell
     * if it is not blue, red, green, yellow or black
     * then this means the cell hasn't been selected yet and it should return true
     * if it does have a color then the cell is no longer active
     */
    public boolean isActive(){
        if (Arrays.stream(colors).anyMatch(getBackground()::equals)){
            return false;
        } else {
            return true;
        }
    }

}
