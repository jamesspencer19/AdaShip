package com.adaship;

import java.util.Arrays;

public class createBoard {

    public static char [] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char water = configReader.getWater();
    public static char ship = configReader.getShip();
    public static char hit = configReader.getHit();
    public static char miss = configReader.getMiss();

    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";


    public static char [][] createGameBoard(int boardLength, int boardWidth, char water){
        char[][] gameboard = new char[boardLength][boardWidth];
        for(char[] row: gameboard){
            Arrays.fill(row, water);
        }
        return gameboard;
    }

    public static void printGameBoard(char[][] gameboard){
        System.out.println("-----------GAME BOARD------------");
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        System.out.print("    ");
        for(int i=0; i < gameBoardWidth;i++){
            System.out.print(alphabet[i] + "  ");
        }
        System.out.println();
        for(int row = 0; row < gameBoardLength ; row++){
            if(row < 9){
                System.out.print(row + 1 + "   ");
            }else{
                System.out.print(row + 1 + "  ");
            }
            for(int col = 0; col < gameBoardWidth ; col++){
                char position = gameboard[row][col];
                    if (position==ship){
                        System.out.print(GREEN + position + "  " + RESET);
                    }else if(position==hit){
                        System.out.print(RED + position + "  " + RESET);
                    }else if(position==miss){
                        System.out.print(position + "  " + RESET);
                    }
                    else{
                        System.out.print(BLUE + water + "  " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printTargetBoard(char[][] gameboard){
        System.out.println("----------TARGET BOARD-----------");
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        System.out.print("    ");
        for(int i=0; i < gameBoardWidth;i++){
            System.out.print(alphabet[i] + "  ");
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
                if(position==hit){
                    System.out.print(RED + position + "  " + RESET);
                }else if(position==miss){
                    System.out.print(position + "  " + RESET);
                }
                else{
                    System.out.print(BLUE + water + "  " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
