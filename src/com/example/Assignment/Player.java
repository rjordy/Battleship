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

    /*
     * when a player is initialized they should start with a score of 0
     */
    public Player(int i){
        this.name = "Player " + String.valueOf(i);
        this.score = 0;

        this.lPlayer = new JLabel(name);
        this.lScore = new JLabel("Score: " + String.valueOf(score));
    }

    /*
     * This method modifies the corresponding JLabels to be using a bold font
     */
    public void setBold(){
        lPlayer.setFont(BOLD_FONT);
        lScore.setFont(BOLD_FONT);
    }

    /*
     * This method modifies the corresponding JLabels to be using a regular font
     */
    public void setPlain(){
        lPlayer.setFont(DEFAULT_FONT);
        lScore.setFont(DEFAULT_FONT);
    }

    public String getName(){ return this.name; }

    public void setName(String name){
        this.name = name;
    }

    /*
     * This will return the JLabel containing the player name
     */
    public JLabel getlPlayer(){
        return lPlayer;
    }

    /*
     * This will return the JLabel containing the score of the player
     */
    public JLabel getlScore(){
        return lScore;
    }

    /*
     * This will return the score assigned to the player
     */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /*
     * If the text of a player uses a bold font this indicates that it's his turn
     * this method uses this to return whether this is this player's turn or not
     */
    public boolean getTurn() {
        if (this.lPlayer.getFont() == BOLD_FONT){
            return true;
        } else{
            return false;
        }
    }

    /*
     * This method will change a bold font to a regular font
     * and a regular font to a bold font
     * effectively switching the turn of the player
     */
    public void switchTurn(){
        if (getTurn()){
            this.setPlain();
        } else{
            this.setBold();
        }
    }

    /*
     * This will add to the score of the player
     */
    public void addScore(int i){
        this.score += i;
        this.lScore.setText("Score: " + String.valueOf(score));
    }
}
