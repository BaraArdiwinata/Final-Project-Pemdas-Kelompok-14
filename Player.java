import java.awt.*;

/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 14
 * Members  :
 * 1. 5026231 - Jonathan Berlianto
 * 2. 5026231229 - Lailatul Fitaliqoh
 * 3. 5026231232 - Bara Ardiwinata
 * ------------------------------------------------------
 */

public class Player {
    private String name;
    private int position;
    private int points;

    Player(String name){
        this.name=name;
        this.position=0;
        this.points=0;
    }

    public void setName(String name){

        this.name = name;
    }

    public void setPosition(int position){

        this.position = position;
    }

    public String getName(){

        return this.name;
    }
    public int getPosition(){

        return this.position;
    }

    public int rollDice() {

        return (int)(Math.random()*6+1);
    }

    public void addPoint() {
        this.points++;
    }

    public void moveAround(int y, int boardSize) {
        if(this.position + y>boardSize) {
            this.position = boardSize-(this.position + y)%boardSize;
        } else {
            this.position = this.position + y;
        }

    }
}