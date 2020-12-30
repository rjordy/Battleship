package com.example.Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Placement {
    private int rows;
    private int cols;

    private Ship[] ships;

    private ArrayList<Point> gridCoords;
    private ArrayList<Point> allCoords;


    public Placement(){
    }

    public void readPlacementFile(String fileName) throws Exception {
        this.ships = new Ship[]{
                new Ship("undefined"),
                new Ship("undefined"),
                new Ship("undefined"),
                new Ship("undefined")
        };
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        int nLine = 0;
        String st;
        this.allCoords = new ArrayList<>();
        while((st = br.readLine()) != null) {
            if (isNumeric(st)) {
                this.rows = Integer.parseInt(st);
                this.cols = this.rows;
            } else if (nLine > 4) {
                showError("Too many boats!");
            } else {
                String[] line = st.split(";");
                String shipName = line[0];
                ships[nLine].setName(shipName);
                if (!ships[nLine].isValidName()) {
                    showError("Invalid ship name on boat declaration: " + nLine + 1);
                    break;
                } else {
                    ArrayList<Point> coords = new ArrayList<>();
                    for (String part : line) {
                        if (!part.equals(shipName)) {
                            String[] p = part.split("\\*");
                            Point temp = new Point(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
                            coords.add(temp);
                            this.allCoords.add(temp);
                        }
                    }
                    ships[nLine].setCoords(coords);
                }
                nLine++;
            }
            System.out.println(st);
        }
        if (!check_size()){
            showError("Some boat(s) contains too many/few coordinates!");
        }
        if (!unique_loc()){
            showError("Some boats have overlapping coordinates!");
        }
        isOnBoard();
        if (!checkAlignment()){
            showError("Some boats are not in a straight line!");
        }
    }

    public void showError(String errormsg) throws Exception {
        JFrame error = new JFrame("ERROR!");
        error.add(new JLabel("We've encountered an error!"));
        error.add(new JLabel(errormsg));
        error.setSize(new Dimension(300,100));
        error.setVisible(true);
        throw new Exception();
    }

    private boolean isNumeric(String s){
        return s.chars().allMatch(Character::isDigit);
    }

    public boolean unique_loc(){
        for (int i = 0; i<this.allCoords.size() - 1;i++){
            Point temp = allCoords.get(i);
            for (int j = i + 1; j<this.allCoords.size(); j++){
                Point temp2 = allCoords.get(j);
                if (temp.equals(temp2)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check_size(){
        for (Ship s : ships){
            if (!s.isValidSize()){
                return false;
            }
        }
        return true;
    }

    public void isOnBoard() throws Exception {
        for (Point p : this.allCoords){
            int x = (int) p.getX();
            int y = (int) p.getY();
            if (x < 0 || y < 0){
                showError("Some boats have negative coordinates!");
            } else if (x > this.rows || y > this.cols){
                showError("Some boats have coordinates that exceed the maximum grid size!");
            }
        }
    }

    public boolean checkAlignment(){
        for (Ship s : ships){
            if (!checkHorizontal(s.getCoords()) && !checkVertical(s.getCoords())){
                System.out.println(s.getName());
                return false;
            }
        }
        return true;
    }

    public boolean checkHorizontal(ArrayList<Point> pointList){
        int x = (int) pointList.get(0).getX();
        int prev_y = (int) pointList.get(0).getY();
        for (Point p : pointList){
            int p_y = (int) p.getY();
            int diff = p_y - prev_y;
            prev_y = p_y;
            if (x != p.getX()){
                return false;
            } else if (diff > 1 || diff < -1){
                return false;
            }
        }
        return true;
    }

    public boolean checkVertical(ArrayList<Point> pointList){
        int y = (int) pointList.get(0).getY();
        int prev_x = (int) pointList.get(0).getX();
        for (Point p : pointList){
            int p_x = (int) p.getX();
            int diff = p_x - prev_x;
            System.out.println(diff);
            prev_x = p_x;
            if (y != p.getY()){
                return false;
            } else if (diff > 1 || diff < -1){
                return false;
            }
        }
        return true;
    }

    public void createRandomBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.ships = new Ship[]{
                new Ship("Carrier"),
                new Ship("Battleship"),
                new Ship("Submarine"),
                new Ship("Destroyer")
        };

        Point rPoint;
        String orientation;

        this.gridCoords = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                this.gridCoords.add(new Point(j, i));
            }
        }

        for (Ship ship : ships) {
            do {
                rPoint = getRandomPoint();
                orientation = getRandomOrientation(rPoint, ship.getSize());
            } while (!isValidCoord(rPoint) || orientation.equals("Invalid"));

            ArrayList<Point> coords = getPlacementCoords(rPoint, ship.getSize(), orientation);
            ship.setCoords(coords);
            removeCoords(coords);
        }
    }

    private void removeCoords(ArrayList<Point> coords){
        for (Point shipcoord : coords){
            this.gridCoords.removeIf(shipcoord::equals);
        }
    }

    public Point getRandomPoint(){
        Random rand = new Random();
        int x = rand.nextInt(this.cols);
        int y = rand.nextInt(this.rows);
        return new Point(x, y);

    }

    public boolean isValidCoord(Point p){
        for (Point var: this.gridCoords){
            if (p.equals(var)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPlacement(Point startPoint, int size, String orientation){
        int x = (int) startPoint.getX();
        int y = (int) startPoint.getY();
        int vert = 0;
        int horiz = 0;
        for (int i = 1; i <= size; i++) {
            if (orientation.toLowerCase().equals("up")){
                vert = i;
            } else {
                if (orientation.toLowerCase().equals("down")) {
                    vert = -i;
                } else {
                    if (orientation.toLowerCase().equals("left")) {
                        horiz = -i;
                    } else {
                        if (orientation.toLowerCase().equals("right")) {
                            horiz = i;
                        }
                    }
                }
            }
            if (!isValidCoord(new Point(x + horiz, y + vert))){
                return false;
            }
        }
        return true;
    }

    private String getRandomOrientation(Point p, int size){
        ArrayList<String> validOrientations = new ArrayList<>();
        String[] orientations = new String[]{"Up", "Down", "Left", "Right"};
        for (String orientation : orientations) {
            if (isValidPlacement(p, size, orientation)) {
                validOrientations.add(orientation);
            }
        }
        Random rand = new Random();
        if (validOrientations.size() == 0){
            return "Invalid";
        }
        return validOrientations.get(rand.nextInt(validOrientations.size()));
    }

    private ArrayList<Point> getPlacementCoords(Point startPoint, int size, String orientation){
        ArrayList<Point> arr = new ArrayList<>();
        int x = (int) startPoint.getX();
        int y = (int) startPoint.getY();
        int vert = 0;
        int horiz = 0;

        arr.add(startPoint);
        for (int i = 1; i < size; i++) {
            if (orientation.toLowerCase().equals("up")){
                vert = i;
            }
            if (orientation.toLowerCase().equals("down")){
                vert = -i;
            }
            if (orientation.toLowerCase().equals("left")){
                horiz = -i;
            }
            if (orientation.toLowerCase().equals("right")){
                horiz = i;
            }
            arr.add(new Point(x + horiz, y + vert));
        }
        return arr;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Ship[] getShips() {
        return ships;
    }
}