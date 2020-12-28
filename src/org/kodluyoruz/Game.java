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

    /**
     * Manages game moves.
     * @param player
     * @param column
     * @param row
     * @return
     */
    boolean makeMove(Player player, int column, int row){
        if(!(column>size || column<0 || row>size || row<0)){
            if(gameArea[row-1][column-1] == '\u0000'){
                if(player.isComputer())
                    System.out.println("--------Computer playing--------\nColumn: "+column+"\tRow: "+row);
                else
                    System.out.println("--------User playing--------\nColumn: "+column+"\tRow: "+row);
                gameArea[row-1][column-1] = player.getLetter();
                this.moveCounter --;
                checkScore(player,row-1,column-1);
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
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n--------Game Over!--------\n\n"+"Player "+players[0].getLetter()+": "+players[0].getScore()+"    "
                                                                                   +"Player "+players[1].getLetter()+": "+players[1].getScore()+"\n\n");
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
        if(players[0].getTurn()){
            players[0].setTurn(false);
            players[1].setTurn(true);
            return players[0];
        }
        else {
            players[1].setTurn(false);
            players[0].setTurn(true);
            return players[1];
        }
    }

    /**
     * Manages scores
     * @param player
     * @param row
     * @param column
     */
    public void checkScore(Player player, int row, int column){
        if(player.getLetter() == 'S'){      //'S' inserted
            if(validatedReach(row-1, column-1) && gameArea[row-1][column-1] == 'O'){    // ^<
                if(validatedReach(row-2, column-2) && gameArea[row-2][column-2] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row, column-1) && gameArea[row][column-1] == 'O'){    // ^
                if(validatedReach(row, column-2) && gameArea[row][column-2] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row+1, column-1) && gameArea[row+1][column-1] == 'O'){    // ^>
                if(validatedReach(row+2, column-2) && gameArea[row+2][column-2] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row-1, column) && gameArea[row-1][column] == 'O'){    // <
                if(validatedReach(row-2, column) && gameArea[row-2][column] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row-1, column+1) && gameArea[row-1][column+1] == 'O'){    // v<
                if(validatedReach(row-2, column+2) && gameArea[row-2][column+2] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row, column+1) && gameArea[row][column+1] == 'O'){    // v
                if(validatedReach(row, column+2) && gameArea[row][column+2] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row+1, column+1) && gameArea[row+1][column+1] == 'O'){    // v>
                if(validatedReach(row+2, column+2) && gameArea[row+2][column+2] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row+1, column) && gameArea[row+1][column] == 'O'){    // >
                if(validatedReach(row+2, column) && gameArea[row+2][column] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
        } else { // 'O' inserted
            if(validatedReach(row-1, column-1) && gameArea[row-1][column-1] == 'S'){    // ^<
                if(validatedReach(row+1, column+1) && gameArea[row+1][column+1] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row, column-1) && gameArea[row][column-1] == 'S'){    // ^
                if(validatedReach(row, column+1) && gameArea[row][column+1] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row+1, column-1) && gameArea[row+1][column-1] == 'S'){    // ^>
                if(validatedReach(row-1, column+1) && gameArea[row-1][column+1] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row-1, column) && gameArea[row-1][column] == 'S'){    // <
                if(validatedReach(row+1, column) && gameArea[row+1][column] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row-1, column+1) && gameArea[row-1][column+1] == 'S'){    // v<
                if(validatedReach(row+1, column-1) && gameArea[row+1][column-1] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row, column+1) && gameArea[row][column+1] == 'S'){    // v
                if(validatedReach(row, column-1) && gameArea[row][column-1] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row+1, column+1) && gameArea[row+1][column+1] == 'S'){    // v>
                if(validatedReach(row-1, column-1) && gameArea[row-1][column-1] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
            if(validatedReach(row+1, column) && gameArea[row+1][column] == 'S'){    // >
                if(validatedReach(row-1, column) && gameArea[row-1][column] == 'S'){
                    player.setScore(player.getScore()+1);
                }
            }
        }
    }

    /**
     * Banned to reach out of  the array bounds
     * @param column
     * @param row
     * @return
     */
    public boolean validatedReach(int column, int row){
        if(column>=size || row >= size || column <0 || row <0) return false;
        else return true;
    }

    public int getSize() {
        return size;
    }

}
