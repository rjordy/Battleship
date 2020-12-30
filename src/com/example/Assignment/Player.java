package com.example.Assignment;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;

public class Player {
    private String name;
    private int score;
    private JLabel lPlayer;
    private JLabel lScore;

    private Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    private Font BOLD_FONT = new Font("Dialog", Font.BOLD, 12);

    public Player(int i){
        this.name = "Player " + String.valueOf(i);
        this.score = 0;

        this.lPlayer = new JLabel(name);
        this.lScore = new JLabel("Score: " + String.valueOf(score));
    }

    public void setBold(){
        lPlayer.setFont(BOLD_FONT);
        lScore.setFont(BOLD_FONT);
    }

    public void setPlain(){
        lPlayer.setFont(DEFAULT_FONT);
        lScore.setFont(DEFAULT_FONT);
    }

    /**
    * Gets name of the player
     *
     * @return name
     */
    public String getName(){ return this.name; }

    public void setName(String name){
        this.name = name;
    }

    public JLabel getlPlayer(){
        return lPlayer;
    }

    public JLabel getlScore(){
        return lScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getTurn() {
        if (this.lPlayer.getFont() == BOLD_FONT){
            return true;
        } else{
            return false;
        }
    }

    public void switchTurn(){
        if (getTurn()){
            this.setPlain();
        } else{
            this.setBold();
        }
    }

    public void addScore(int i){
        this.score += i;
        this.lScore.setText("Score: " + String.valueOf(score));
    }
}
