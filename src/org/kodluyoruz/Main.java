package org.kodluyoruz;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean errorFlag = false;
        Scanner scanner = new Scanner(System.in);
        int size;
        Game game;

        System.out.println("Hello! Game area must to be AxA format");
        System.out.print("Please tell me A value : ");

        try{
            size = scanner.nextInt();
            Game.checkSize(size);
        } catch (IllegalArgumentException exception){
            System.out.println("It is not valid value.");
            System.out.println("Game created minimum value: 3");
            size = 3;
        } catch (InputMismatchException exception) {
            System.out.println("It is not valid value.");
            System.out.println("Game created minimum value: 3");
            size = 3;
            scanner = new Scanner(System.in);   //Refresh scanner object
        }
        game = new Game(size);

        Player currentPlayer;
        while (game.gameStatus()){
            int column,row;
            Random random = new Random();
             currentPlayer = game.turn();
            do{
                if(currentPlayer.isComputer()){
                    column = random.nextInt(game.getSize())+1;
                    row = random.nextInt(game.getSize())+1;
                }else {
                    System.out.print("Please insert column:");
                    column = scanner.nextInt();
                    System.out.print("Please insert row:");
                    row = scanner.nextInt();
                }
            }while (!game.makeMove(currentPlayer, column,row));
            game.drawGameArea();
        }
    }


}
