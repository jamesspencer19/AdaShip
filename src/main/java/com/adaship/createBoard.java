package com.adaship;

import java.util.Arrays;

public class createBoard {


    public static char water = configReader.getWater();
    public static char ship = configReader.getShip();
    public static char hit = configReader.getHit();
    public static char miss = configReader.getMiss();
    public static char mine = configReader.getMine();
    public static char[] alphabet = configReader.getAlphabet();

    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";


    public static int [][] createGameBoard(int boardLength, int boardWidth, char water){
        int[][] gameboard = new int[boardLength][boardWidth];
        for(int[] row: gameboard){
            Arrays.fill(row, 0);
        }
        return gameboard;
    }

    public static void printGameBoard(int[][] gameboard, String boardtype){
        if (boardtype.equals("game")){
            System.out.println("-----------GAME BOARD------------");
        }else if (boardtype.equals("target")){
            System.out.println("----------TARGET BOARD-----------");
        }
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
                int position = gameboard[row][col];
                if (boardtype.equals("game")){
                    if (position>0){
                        System.out.print(GREEN + ship + "  " + RESET);
                    }else if (position==-3){
                        System.out.print(CYAN + mine + "  " + RESET);
                    }
                    else standardBoard(position);
                }else if (boardtype.equals("target")){
                    standardBoard(position);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void standardBoard(int position) {
        if(position==-1){
            System.out.print(RED + hit + "  " + RESET);
        }else if(position==-2){
            System.out.print(miss + "  " + RESET);
        }else if (position==-4){
            System.out.print(RED + mine + "  " + RESET);
        }
        else{
            System.out.print(BLUE + water + "  " + RESET);
        }
    }

}