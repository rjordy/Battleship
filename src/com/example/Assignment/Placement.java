package com.example.Assignment;

import com.example.BattleshipExample.Constants;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Placement {
    private int rows;
    private int cols;

    private ArrayList<Point> gridCoords;


    public Placement(){

    }

    public void readPlacementFile(){
//        read file;
//        first line is grid size (dxd);
//        boat_name + coords;
//        for every line first word is boat_name, add subsequent as coords;
//        check bools;
//        if all are true then valid else invalid;
    }

    public boolean unique_loc(){
//        merge all arrays and find duplicates
        return true;
    }

    public boolean check_size(){
//        if carrier then array length == 5;
//        ...
//        if any of boat_names array == 0 or invalid boat_name then error!;
        return true;
    }

    public boolean isOnBoard(){
//        no rows/cols that exceed grid size
        return true;
    }

    public boolean checkAlignment(){
        return true;

    }

    public boolean checkHorizontal(){
        return true;

    }

    public boolean checkVertical(){
        return true;

    }

    public void createRandomBoard(int rows, int cols, Ship[] ships) {
        this.rows = rows;
        this.cols = cols;

        Point rPoint;
        String orientation;

        this.gridCoords = new ArrayList<Point>();
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

            //        set array of valid coords;
            //        take 2 random numbers within grid size;
            //        check if numbers are in valid coords else retake;
            //        check amt space to left/right/up/down create array with poss boats/orientation;
            //        pick random orientation where length != 0;
            //        pick random boat in orientation array;
            //        repeat for every boat;
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
        Point p = new Point(x, y);
        return p;

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
        ArrayList<String> validOrientations = new ArrayList<String>();
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
        ArrayList<Point> arr = new ArrayList<Point>();
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

}