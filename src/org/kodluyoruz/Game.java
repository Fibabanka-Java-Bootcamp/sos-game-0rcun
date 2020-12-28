package org.kodluyoruz;

import java.util.Random;

public class Game {
    private int size;
    private char[][] gameArea;
    static int moveCounter = 0;
    private Player[] players;

    /**
     * Initializes conditions that are starting game
     * @param size Defines game area
     */
    public Game(int size){
        this.size = size;
        this.moveCounter = this.size*this.size;
        createGame();
    }

    /**
     * Checks that size variable is valid
     * @param size
     * @return
     */
    static boolean checkSize(int size) throws IllegalArgumentException {
        if(size>=3 && size <=7) return true;
        else throw new IllegalArgumentException();
    }

    /**
     * Init player and game table
     */
    void createGame(){
        Random random = new Random();

        //Init game area
        gameArea = new char[this.size][this.size];

        //Create players
        players = new Player[2];
        players[0] = new Player('S');
        players[0].setTurn(true);
        players[1] = new Player('O');

        //Random selections
        if(random.nextInt()%2==0) {
            players[0].setComputer(true);
        } else {
            players[1].setComputer(true);
        }
    }

    /**
     * Shows game table
     */
    void drawGameArea(){
        for (int i = 0; i <= this.size ; i++) {
            //Rows
            for (int j = 0; j <= this.size ; j++) {
                //Columns
                if(i==0 && j==0) {
                    System.out.print("   \t|");
                }else if(i==0){
                    System.out.print(" "+j+"\t|");
                }else if (j==0){
                    System.out.print(" "+i+"\t|");
                } else {
                    System.out.print(" "+this.gameArea[i-1][j-1]+"\t|");
                }
            }
            System.out.println();
        }
    }

    boolean makeMove(Player player, int column, int row){
        if(!(column>size || column<0 || row>size || row<0)){
            if(gameArea[row-1][column-1] == '\u0000'){
                if(player.isComputer())
                    System.out.println("--------Computer playing--------\nColumn: "+column+"\tRow: "+row);
                else
                    System.out.println("--------User playing--------\nColumn: "+column+"\tRow: "+row);
                gameArea[row-1][column-1] = player.getLetter();
                this.moveCounter --;
                return true;
            } else {
                System.out.println("--------Please select correct indexes--------\n\n\n\n");
                return false;
            }
        }
        System.out.println("--------Please select correct indexes--------\n\n\n\n");
        return false;
    }

    /**
     * Monitors and catches game over status
     * @return
     */
    boolean gameStatus(){
        if(Game.moveCounter==0){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n--------Game Over!--------");
            this.drawGameArea();
            return false;
        }else{
            return true;
        }
    }

    /**
     * Returns and manages whose play
     * @return
     */
    Player turn(){
        if(getPlayers()[0].getTurn()){
            getPlayers()[0].setTurn(false);
            getPlayers()[1].setTurn(true);
            return getPlayers()[0];
        }
        else {
            getPlayers()[1].setTurn(false);
            getPlayers()[0].setTurn(true);
            return getPlayers()[1];
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getSize() {
        return size;
    }


}
