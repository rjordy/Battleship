package com.example.Assignment;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private int score;
    private ArrayList<Point> coords = new ArrayList<>();


    public Ship(String name){
        this.name = name;
        setSize();
        setScore();
    }

    public void setName(String name){
        this.name = name;
        setSize();
        setScore();
    }

    public void setSize(){
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

    public ArrayList<Point> getCoords(){
        return this.coords;
    }

    public String getName(){
        return this.name;
    }

    public void setCoords(ArrayList<Point> p){
        this.coords = p;
    }

    public boolean isCoordsEmpty(){
        return this.coords.size() == 0;
    }

    public boolean isValidSize(){
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

    public void setScore(){
        if (this.name.toLowerCase().equals("carrier")){
            this.score = 10;
        } else {
            if (this.name.toLowerCase().equals("battleship")){
                this.score = 20;
            } else {
                if (this.name.toLowerCase().equals("submarine")){
                    this.score = 25;
                } else {
                    if (this.name.toLowerCase().equals("destroyer")){
                        this.score = 50;
                    } else {
                        this.score = 0;
                    }
                }
            }
        }
    }

    public int getScore(){
        return this.score;
    }

    public int getSize(){ return this.size; }

    public boolean containsPoint(Point p){
        for (Point coord : this.coords){
            if (coord.equals(p)){
                return true;
            }
        }
        return false;
    }

    public boolean isValidName(){
        if (this.name.toLowerCase().equals("carrier")){
            return true;
        } else {
            if (this.name.toLowerCase().equals("battleship")){
                return true;
            } else {
                if (this.name.toLowerCase().equals("submarine")){
                    return true;
                } else {
                    return this.name.toLowerCase().equals("destroyer");
                }
            }
        }
    }
}
