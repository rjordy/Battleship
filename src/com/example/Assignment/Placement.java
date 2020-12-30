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


    public Placement(){}

    /*
     * this method will read the placement file which is passed with the parameter
     * It will will read the file line-by-line
     * if it encounters any errors it will display a new frame displaying an error
     * afterwards it will default back to creating a random board
     */
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
        //this arraylist will be used further down in the program as easy access to all of the coordinates that were used
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
        }
        br.close();
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

    /*
     * If an error is encounter it should display a new frame
     * This frame will display the error that was encountered
     */
    public void showError(String errormsg) throws Exception {
        JFrame error = new JFrame("ERROR!");
        error.add(new JLabel(errormsg));
        error.setSize(new Dimension(300,100));
        error.setVisible(true);
        throw new Exception();
    }

    /*
     * This methods checks if every character of a string is numerical otherwise it will return false
     */
    private boolean isNumeric(String s){
        return s.chars().allMatch(Character::isDigit);
    }

    /*
     * This method checks to make sure that every ship has a unique location
     * and none of them are overlapping
     *
     */
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

    /*
     * Each ship gets their default size assigned on initialization in the Ship class
     * this method will check whether the length of the coordinates it has received
     * match the default size of the ship
     * if it doesn't then there could be either too few or too many coordinates given for this ship
     */
    public boolean check_size(){
        for (Ship s : ships){
            if (!s.isValidSize()){
                return false;
            }
        }
        return true;
    }

    /*
     * This method checks whether every coordinate of every ship is contained within the grid
     * It should not be less than  and should not exceed the maximum grid dimension
     */
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

    /*
     * This will check if a ship is well aligned
     * this means that if it's not horizontally aligned
     * and it's not vertically aligned
     * this should return false
     * if any of these alignments are true then the ship is well-aligned
     */
    public boolean checkAlignment(){
        for (Ship s : ships){
            if (!checkHorizontal(s.getCoords()) && !checkVertical(s.getCoords())){
                return false;
            }
        }
        return true;
    }

    /*
     * This checks the horizontal alignment of a ship
     * it takes every coordinate for the ship
     * if every coordinate is on the same row then this means that every x coordinate should be equal
     * furthermore there should also be a check for any gaps within on the ship
     * as this is still an error but could happen on the same row
     * this is done by checking every y coordinate and checking if it differs more than 1 with the previous coordinate
     */
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

    /*
     * This checks the vertical alignment of a ship
     * it takes every coordinate for the ship
     * if every coordinate is on the same column then this means that every y coordinate should be equal
     * furthermore there should also be a check for any gaps within on the ship
     * as this is still an error but could happen on the same row
     * this is done by checking every x coordinate and checking if it differs more than 1 with the previous coordinate
     */
    public boolean checkVertical(ArrayList<Point> pointList){
        int y = (int) pointList.get(0).getY();
        int prev_x = (int) pointList.get(0).getX();
        for (Point p : pointList){
            int p_x = (int) p.getX();
            int diff = p_x - prev_x;
            prev_x = p_x;
            if (y != p.getY()){
                return false;
            } else if (diff > 1 || diff < -1){
                return false;
            }
        }
        return true;
    }

    /*
     * This method creates a random placement for every ship in the game.
     */
    public void createRandomBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        //first the ships should be initialized
        this.ships = new Ship[]{
                new Ship("Carrier"),
                new Ship("Battleship"),
                new Ship("Submarine"),
                new Ship("Destroyer")
        };

        Point rPoint;
        String orientation;

        //Create an arraylist containing all the possible coordinates that are available on the grid
        this.gridCoords = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                this.gridCoords.add(new Point(j, i));
            }
        }

        /*
         * for every ship take a random point on the grid
         * if it's a valid point then take a random valid orientation to place the ship in
         * afterwards assign these coordinates to the ship class
         * and remove these same coordinates from the list of valid coordinates
         */
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

    /*
     * This method loops over every point of a given arraylist and removes them from the valid coordinates
     * but only if they are equal
     */
    private void removeCoords(ArrayList<Point> coords){
        for (Point shipcoord : coords){
            this.gridCoords.removeIf(shipcoord::equals);
        }
    }

    /*
     * This method creates a random point which is on the grid, but not necessarily valid
     */
    public Point getRandomPoint(){
        Random rand = new Random();
        int x = rand.nextInt(this.cols);
        int y = rand.nextInt(this.rows);
        return new Point(x, y);

    }

    /*
     * this method checks whether a given point appears in the arraylist containing valid coordinates
     */
    public boolean isValidCoord(Point p){
        for (Point var: this.gridCoords){
            if (p.equals(var)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method checks whether a ship of a certain size can be put in specific orientation given a starting point
     */
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

    /*
     * This methods will use a starting point and ship size and call upon validplacement
     * for every orientation that returns true in other words the ship can be placed in this orientation
     * it will add this orientation to an arraylist
     * at the end a random orientation is chosen from this arraylist
     * if there are no valid orientation it will return invalid
     */
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

    /*
     * When given a starting point, ship size and orientation
     * this method will generate all the coordinates that will be occupied by the ship if placed
     */
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