package org.kodluyoruz;

import java.util.Random;

public class Player {
    private char letter;
    private int score;
    private boolean turn;   //Represents it that whose will play now
    private boolean isComputer;


    public Player(char letter){
        this.letter = letter;
        this.isComputer = false;
        this.turn = false;
    }

    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setScore(int score) {
        System.out.println(this.getLetter()+"\t gained a point!");
        this.score = score;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }
}
