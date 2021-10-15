package com.adaship;

import java.util.Arrays;

public class createBoard {

    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\u001B[31m";


    public static char [][] createGameBoard(int boardLength, int boardWidth, char water){
        char[][] gameboard = new char[boardLength][boardWidth];
        for(char[] row: gameboard){
            Arrays.fill(row, water);
        }
        return gameboard;
    }

    public static void printGameBoard(char[][] gameboard, char water, char ship, char hit, char miss){
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        System.out.print("    ");
        for(int i=0; i < gameBoardWidth;i++){
            System.out.print(i + 1 + "  ");
        }
        System.out.println();
        for(int row = 0; row < gameBoardLength; row++){
            if(row < 9){
                System.out.print(row + 1 + "   ");
            }else{
                System.out.print(row + 1 + "  ");
            }
            for(int col = 0; col < gameBoardWidth; col++){
                char position = gameboard[row][col];
                    if (position==ship){
                        System.out.print(RED + position + "  " + RESET);
                    }else{
                        System.out.print(BLUE + water + "  " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
