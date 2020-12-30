package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore {
    private ArrayList<Integer> highscores;

    public HighScore(){
        readFile();
        sortHighscores();
    }

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

    public void sortHighscores(){
        Collections.sort(highscores);
        Collections.reverse(highscores);
    }

    public void addHighscore(int i){
        highscores.add(i);
        sortHighscores();
        writeFile();
    }

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

    public void drawHighscores(){
        JFrame highScoreFrame = new JFrame();
        highScoreFrame.setLayout(new BorderLayout());
        highScoreFrame.setSize(new Dimension(280,400));
        String top = "<h1><u>Highscores</u></h1>";
        String numbers = "<h2>1.<br>2.<br>3.<br>4.<br>5.<br>6.<br>7.<br>8.<br>9.<br>10.</h2>";
        String right = getTop();
        JLabel enumeration = new JLabel("<html>" + numbers + "<html>");
        JLabel scores = new JLabel("<html>" + right + "<html>");
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

    public String getTop(){
        StringBuilder htmlTop = new StringBuilder("<h2>");
        for (int i = 0; i<10; i++){
            if (i >= this.highscores.size()){
                htmlTop.append("---<br>");
            } else {
                htmlTop.append(String.valueOf(this.highscores.get(i))).append("<br>");
            }
        }
        htmlTop.append("</h2>");
        return htmlTop.toString();
    }
}
