package com.example.Assignment;

import com.example.BattleshipExample.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Placement {
    private int rows;
    private int cols;

    private ArrayList<Point> gridCoords;


    public Placement(){
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

    public void createRandomBoard(int rows, int cols){
        Ship[] ships = new Ship[]{
                new Ship("Carrier"),
                new Ship("Battleship"),
                new Ship("Submarine"),
                new Ship("Destroyer")
        };

        
        this.gridCoords = new ArrayList<Point>();
        for (int i = 1; i<=rows; i++){
            for (int j = 1; j<=cols;j++){
                this.gridCoords.add(new Point(i, j));
            }
        }
        Point rPoint = getRandomPoint();

        if(isValidCoord(rPoint)){
            getRandomOrientation(rPoint, 5);

        }
//        set array of valid coords;
//        take 2 random numbers within grid size;
//        check if numbers are in valid coords else retake;
//        check amt space to left/right/up/down create array with poss boats/orientation;
//        pick random orientation where length != 0;
//        pick random boat in orientation array;
//        repeat for every boat;
    }

    public Point getRandomPoint(){
        Random rand = new Random();
        int x = rand.nextInt(this.cols);
        int y = rand.nextInt(this.rows);
        Point p = new Point(x, y);
        return p;

    }

    public boolean isValidCoord(Point p){
        boolean valid_coord = false;
        for (Point var: this.gridCoords){
            if (p.equals(var)) {
                valid_coord = true;
                break;
            }
        }
        return valid_coord;
    }

    private boolean isValidUpPlacement(Point startPoint, int size){
        int x = (int) startPoint.getX();
        int y = (int) startPoint.getY();
        for (int i = 1; i <= size; i++) {
            if (!isValidCoord(new Point(x, y + i))){
                return false;
            };
        }
        return true;
    }

    private boolean isValidDownPlacement(Point startPoint, int size){
        int x = (int) startPoint.getX();
        int y = (int) startPoint.getY();
        for (int i = 1; i <= size; i++) {
            if (!isValidCoord(new Point(x, y - i))){
                return false;
            };
        }
        return true;
    }

    private boolean isValidRightPlacement(Point startPoint, int size){
        int x = (int) startPoint.getX();
        int y = (int) startPoint.getY();
        for (int i = 1; i <= size; i++) {
            if (!isValidCoord(new Point(x + i, y))){
                return false;
            };
        }
        return true;
    }

    private boolean isValidLeftPlacement(Point startPoint, int size){
        int x = (int) startPoint.getX();
        int y = (int) startPoint.getY();
        for (int i = 1; i <= size; i++) {
            if (!isValidCoord(new Point(x - i, y))){
                return false;
            };
        }
        return true;
    }

    private String getRandomOrientation(Point p, int size){
        ArrayList<String> validOrientations = new ArrayList<String>();
        if (isValidUpPlacement(p, 5)){
            validOrientations.add("Up");
        }
        if (isValidDownPlacement(p, 5)){
            validOrientations.add("Down");
        }
        if (isValidLeftPlacement(p, 5)){
            validOrientations.add("Left");
        }
        if (isValidRightPlacement(p, 5)){
            validOrientations.add("Right");
        }
        Random rand = new Random();
        return validOrientations.get(rand.nextInt(validOrientations.size()));
    }
}
