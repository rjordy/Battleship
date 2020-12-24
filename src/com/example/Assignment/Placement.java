package com.example.Assignment;

import java.util.ArrayList;
import java.util.Random;

public class Placement {
    private int rows;
    private int cols;

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
        int[][] gridCoords = new int[rows*cols][2];
        int k = 0;
        for (int i = 1; i<=rows; i++){
            for (int j = 1; j<=cols;j++){
                gridCoords[k][0] = i;
                gridCoords[k][1] = j;
                k++;
            }
        }
        Random rand = new Random();
        boolean valid_coord = false;
        int x = rand.nextInt(cols);
        int y = rand.nextInt(rows);
        for (int[] var : gridCoords){
            if (var[0] == x && var[1] == y) {
                valid_coord = true;
                break;
            }
        }

        if(valid_coord){

        }
//        set array of valid coords;
//        take 2 random numbers within grid size;
//        check if numbers are in valid coords else retake;
//        check amt space to left/right/up/down create array with poss boats/orientation;
//        pick random orientation where length != 0;
//        pick random boat in orientation array;
//        repeat for every boat;
    }
}
