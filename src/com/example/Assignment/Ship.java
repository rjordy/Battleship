package com.example.Assignment;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private int score;
    private int hits;
    private ArrayList<Point> coords = new ArrayList<>();


    /*
     * On initialization of a ship it should be able to receive any name
     * After it receives a name it will use this to check for the default size and score
     * this will return a 0 if it is not a valid name
     * it will also add a hit counter which starts at 0
     */
    public Ship(String name){
        this.name = name;
        setSize();
        setScore();
        this.hits = 0;
    }

    /*
     * this allows a user to give a ship a different name
     * it will then again get the default score and size
     */
    public void setName(String name){
        this.name = name;
        setSize();
        setScore();
    }

    /*
     * This method will use the name of the ship to determine what its size should be
     * if the name is invalid this size will be 0
     */
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

    /*
     * this method will return an arraylist of all the points that are assigned to the ship
     */
    public ArrayList<Point> getCoords(){
        return this.coords;
    }

    public String getName(){
        return this.name;
    }

    /*
     * this method will allow the ship to be assigned an arraylist of coordinates
     */
    public void setCoords(ArrayList<Point> p){
        this.coords = p;
    }

    public boolean isCoordsEmpty(){
        return this.coords.size() == 0;
    }

    /*
     * this method checks whether the amount of coordinates assigned to the ship are equal
     * to the default size that was assigned to this ship according to the ship name
     */
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

    /*
     * this will assign a score to the ship according the ship name
     */
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

    /*
     * this returns the score the ship should be giving on a hit
     * if the hit counter is equal to the default size then this means this was the last hit the ship could receive
     * in this case the players should receive double points
     */
    public int getScore(){
        if (this.hits == this.size){
            return this.score * 2;
        }
        return this.score;
    }

    public int getSize(){ return this.size; }

    /*
     * this method will check whether a given point is part of the ship coordinates
     */
    public boolean containsPoint(Point p){
        for (Point coord : this.coords){
            if (coord.equals(p)){
                return true;
            }
        }
        return false;
    }

    /*
     * this method will check if the given ship name is a valid name or not
     */
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

    public void addHit(){
        this.hits++;
    }
}
