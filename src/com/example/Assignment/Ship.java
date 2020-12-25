package com.example.Assignment;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private ArrayList<Point> coords = new ArrayList<Point>();


    public Ship(String name){
        this.name = name;
        if (this.name.toLowerCase().equals("carrier")){
            this.size = Constants.CARRIER_SIZE;
        } else {
            if (this.name.toLowerCase().equals("battleship")){
                this.size = Constants.BATTLESHIP_SIZE;
            } else {
                if (this.name.toLowerCase().equals("submarine")){
                    this.size = Constants.SUBMARINE_SIZE;
                } else {
                    if (this.name.toLowerCase().equals("destroyer")){
                        this.size = Constants.DESTROYER_SIZE;
                    } else {
                        this.size = 0;
                    }
                }
            }
        }
    }

    public void setCoords(ArrayList<Point> p){
        this.coords = p;
    }

    public boolean isCoordsEmpty(){
        return this.coords.size() == 0;
    }

    public boolean isValid(){
        if (this.name.toLowerCase().equals("carrier")){
            return this.coords.size() == Constants.CARRIER_SIZE;
        } else {
            if (this.name.toLowerCase().equals("battleship")){
                return this.coords.size() == Constants.BATTLESHIP_SIZE;
            } else {
                if (this.name.toLowerCase().equals("submarine")){
                    return this.coords.size() == Constants.SUBMARINE_SIZE;
                } else {
                    if (this.name.toLowerCase().equals("destroyer")){
                        return this.coords.size() == Constants.DESTROYER_SIZE;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public int getSize(){ return this.size; }
}
