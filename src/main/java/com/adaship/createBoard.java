package com.adaship;

import java.util.Arrays;

public class createBoard {

    //Set all board attributes
    public static char water = configReader.getWater();
    public static char ship = configReader.getShip();
    public static char hit = configReader.getHit();
    public static char miss = configReader.getMiss();
    public static char mine = configReader.getMine();
    public static char[] alphabet = configReader.getAlphabet();

    //Set all colours
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";

    //Create the gameboard method
    public static int [][] createGameBoard(int boardLength, int boardWidth, char water){
        //Create 2d array with the length and width of the board
        int[][] gameboard = new int[boardLength][boardWidth];
        //for each row in the gameboard fill with water which is represented 0
        for(int[] row: gameboard){
            Arrays.fill(row, 0);
        }
        return gameboard;
    }

    //Print the gameboard method
    public static void printGameBoard(int[][] gameboard, String boardtype){
        //Notify the user which type of board they are seeing: Target or Game
        if (boardtype.equals("game")){
            System.out.println("-----------GAME BOARD------------");
        }else if (boardtype.equals("target")){
            System.out.println("----------TARGET BOARD-----------");
        }
        //Get the gameboard length and width
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        //Print the column index using the alphabet
        System.out.print("    ");
        //Print a char of the alphabet for each index of the boardwidth for column
        for(int i=0; i < gameBoardWidth;i++){
            System.out.print(alphabet[i] + "  ");
        }
        System.out.println();
        //Iterate through each row
        for(int row = 0; row < gameBoardLength ; row++){
            //Print an incrementing number for each index of the boardlength for row
            if(row < 9){
                System.out.print(row + 1 + "   ");
            }
            //Print 1 less space if the index is greater than ten
            else{
                System.out.print(row + 1 + "  ");
            }
            //Iterate through each column
            for(int col = 0; col < gameBoardWidth ; col++){
                //Set the position as the value in the 2d array and that coordinate
                int position = gameboard[row][col];
                //If the board type is a gameboard
                if (boardtype.equals("game")){
                    //Print ship character from the config if the position value is greater than zero
                    if (position>0){
                        System.out.print(GREEN + ship + "  " + RESET);
                    }
                    //If the position is equal to -3 print a mine character from the config that hasn't been hit
                    else if (position==-3){
                        System.out.print(CYAN + mine + "  " + RESET);
                    }
                    //Print all values that appear on a standard board (Target)
                    else standardBoard(position);
                }else if (boardtype.equals("target")){
                    //Print all values that appear on a standard board (Target)
                    standardBoard(position);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void standardBoard(int position) {
        //If the position is equal to -1 print a hit character from the config
        if(position==-1){
            System.out.print(RED + hit + "  " + RESET);
        }
        //If the position is equal to -2 print a miss character from the config
         else if(position==-2){
            System.out.print(miss + "  " + RESET);
        }
        //If the position is equal to -4 print a mine character from the config that has been hit
        else if (position==-4){
            System.out.print(RED + mine + "  " + RESET);
        }
        //Else print the water character from the config
        else{
            System.out.print(BLUE + water + "  " + RESET);
        }
    }

}