package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean errorFlag = false;
        Scanner scanner = new Scanner(System.in);
        int size;
        System.out.println("Hello! Game area must to be AxA format");
        System.out.print("Please tell me A value : ");
        size = scanner.nextInt();

        Game game = new Game();
        Player currentPlayer;
        while (game.gameStatus()){
                int column,row;
                Random random = new Random();
                 currentPlayer = game.turn();
                do{
                    game.drawGameArea();
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
        }


    }


}
