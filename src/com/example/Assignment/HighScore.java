package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore {
    private ArrayList<Integer> highscores;

    /*
     * Always read the file and sort the values for good measure
     */
    public HighScore(){
        readFile();
        sortHighscores();
    }


    /*
     * The highscores.txt file is read line by line
     * it will try to convert the line to an integer and add it to an ArrayList
     * if it fails to convert then it will skip this line
     */
    public void readFile(){
        try {
            highscores = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("Highscores.txt"));
            String line = br.readLine();
            while (line != null){
                try{
                    highscores.add(Integer.parseInt(line));
                } catch (NumberFormatException e){}
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Whenever the highscores are sorted this should be sorted from highest to lowest
     * collections will sort from lowest to highest by default
     * so the reverse method is called right after
     */
    public void sortHighscores(){
        Collections.sort(highscores);
        Collections.reverse(highscores);
    }

    /*
     * Add whatever integer to ArrayList
     * afterwards it is sorted again and written back to the file
     */
    public void addHighscore(int i){
        highscores.add(i);
        sortHighscores();
        writeFile();
    }

    /*
     * write the ArrayList highscores to highscores.txt
     * but only do this for the top 10
     * if the highscores contain less than 10 numbers it will stop after the last number
     */
    public void writeFile(){
        try{
            FileWriter writer = new FileWriter("Highscores.txt");
            for (int i = 0; i<10; i++){
                if (i >= this.highscores.size()){
                    break;
                }
                writer.write(String.valueOf(this.highscores.get(i)) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(){
        try{
            File file = new File("Highscores.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Draw a new frame displaying the highscores
     * this is formatted in html to make it easier
     */
    public void drawHighscores(){
        JFrame highScoreFrame = new JFrame();
        highScoreFrame.setLayout(new BorderLayout());
        highScoreFrame.setSize(new Dimension(280,400));
        String top = "<h1><u>Highscores</u></h1>";
        String numbers = getNumbers();
        String scoreString = getTop();
        JLabel enumeration = new JLabel("<html>" + numbers + "<html>");
        JLabel scores = new JLabel("<html>" + scoreString + "<html>");
        JLabel title = new JLabel("<html>" + top + "<html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        enumeration.setBorder(BorderFactory.createEmptyBorder(0,85,0,30));
        highScoreFrame.add(title, BorderLayout.PAGE_START);
        highScoreFrame.add(enumeration, BorderLayout.LINE_START);
        highScoreFrame.add(scores, BorderLayout.CENTER);
        highScoreFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        highScoreFrame.setLocationRelativeTo(null);
        highScoreFrame.setVisible(true);

        for (int i = 1 ; i <= 10 ; i++){
            System.out.println(i);
        }
    }

    /*
     * create a string of html code that contains the numbers from 1 to 10
     */
    public String getNumbers(){
        StringBuilder htmlNumbers = new StringBuilder("<h2>");
        for (int i = 1; i<=10; i++){
            htmlNumbers.append(String.valueOf(i) + ".<br>");
        }
        htmlNumbers.append("</h2>");
        return htmlNumbers.toString();
    }

    /*
     * Create a string of html code that contains the scores in descending order
     */
    public String getTop(){
        StringBuilder htmlTop = new StringBuilder("<h2>");
        for (int i = 0; i<10; i++){
            if (i >= this.highscores.size()){
                htmlTop.append(" ----<br>");
            } else {
                htmlTop.append(String.valueOf(this.highscores.get(i))).append("<br>");
            }
        }
        htmlTop.append("</h2>");
        return htmlTop.toString();
    }
}
