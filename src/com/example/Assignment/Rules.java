package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules {
    private JFrame ruleFrame = new JFrame("Rules");


    public Rules() {
        ruleFrame.setSize(new Dimension(550, 910));
        ruleFrame.setLayout(new BorderLayout());
        ruleFrame.getContentPane().add(ruleBody(), BorderLayout.CENTER);
        ruleFrame.setVisible(true);
    }

    public JPanel ruleBody(){
        JPanel ruleBody = new JPanel();
        ruleBody.setLayout(new BorderLayout());
        String bodyString = "<center><h2><u>Battleship: The Rules</u></h2></center>" +
                "<h3> Game mechanics</h3>" +
                "<p>Each player takes turns selecting a cell from within the grid.<br>" +
                "Whenever a cell has been selected it will be colored according to what is behind the cell.<br>" +
                "Only cells that are still greyed out are available to be selected.<br><br>" +
                " - Carriers: Red<br>" +
                " - Battleships: Green<br>" +
                " - Submarine: Yellow<br>" +
                " - Destroyer: Black<br>" +
                " - Miss: Blue</p>" +
                "<h3> Objective </h3>" +
                "<p>The objective of this game is simple, be the player with the highest score when the last boat is sunk. " +
                "As a player this means you will have to hit as many boats as possible. " +
                "Hitting smaller boats will return the highest amount of points.</p>" +
                "<h3> Earning points</h3>" +
                "<p>Points are received when part of a boat is hit. " +
                "They are awarded according to the scoring system you chose at the beginning of the game.<br>" +
                "There are 2 different scoring systems: regular and alternative.<br>" +
                "If you are playing with the regular scoring system then each player is awarded the same amount of points when hitting a specific ship<br>" +
                "If you are playing with the alternative scoring system, then this means that player 2 will receive a higher amount of points per hit. " +
                "This is allows player 2 to compensate for having a later start in the game.<br><br>" +
                "The points received will be as following:<br>" +
                " - Carrier&emsp&emsp: Regular: 10 &emsp   Alternative: 12<br>" +
                " - Battleship&ensp: Regular: 20 &emsp   Alternative: 24<br>" +
                " - Submarine: Regular: 25 &emsp   Alternative: 30<br>" +
                " - Destroyer&ensp : Regular: 50 &emsp   Alternative: 60</p>" +
                "<h3>Ship placement</h3>" +
                "<p>Ships can be placed on the grid in 2 different ways. " +
                "Either you specify a text file containing the positions of every boat. " +
                "Another possibility is to generate a random board. <br><br>" +
                "Whichever you choose they will adhere to the same rules. <br>" +
                "Every boat has unique length that has to be respected when placing them on the grid. <br>" +
                "Furthermore the boats have to be placed in a straight line, this can be horizontal or vertical. <br>" +
                "The 4 standard boats are always placed on the board.  " +
                "This means that every game you play, no matter the grid size, will have a total of 14 cells that will award points.<br><br>" +
                "Ship sizes<br>" +
                "- Carrier: 5<br>" +
                "- Battleship: 4<br>" +
                "- Submarine: 3<br>" +
                "- Destroyer: 2</p>";
        JLabel body = new JLabel("<html>"+ bodyString +"</html>");
        body.setFont(new Font("Dialog", Font.PLAIN, 12));
        ruleBody.add(body, BorderLayout.CENTER);
        ruleBody.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        return ruleBody;
    }
}
