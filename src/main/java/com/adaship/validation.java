package com.adaship;

import java.util.Scanner;

public class validation {
    public static int row;
    public static int col;

    public static Scanner sc = new Scanner(System.in);

    //check that a char has been entered by user
    public static char charValidation() {
        boolean repeat = true;
        char charcol = 0;
        while (repeat) {
            charcol = Character.toUpperCase(sc.next().charAt(0));
            if (Character.toString(charcol).matches("[A-Z]")) {
                repeat = false;
            } else {
                System.out.println("Input is not (A-Z)");
                repeat = true;
            }
        }
        return charcol;
    }

    //check that an integer has been entered by user
    public static int intValidation() {
        while (!sc.hasNextInt()) {
            System.out.println("Input is not a number!");
            sc.nextLine();
        }
        return sc.nextInt();
    }

    //change location to calibrate direction
    public static void coordValidation(int[] coordinates, char direction) {
        row = coordinates[0];
        col = coordinates[1];
        if (direction == 'U') {
            row = row + 1;
        } else if (direction == 'D') {
            row = row - 1;
        } else if (direction == 'L') {
            col = col + 1;
        } else if (direction == 'R') {
            col = col - 1;
        }
    }

    //validate that the user is placing the ship on the board
    public static boolean validateLocation(int[][] gameboard, int[] coordinates, char direction, int shipsize) {
        validation.coordValidation(coordinates, direction);
        int row = validation.getRow();
        int col = validation.getCol();
        int i = shipsize;
        //flag false if the ship goes off the board as it is not placed on water
        boolean flag = true;
        try {
            while (i > 0) {
                if (direction == 'U') {
                    if (gameboard[row - i][col] != 0) {
                        flag = false;
                        break;
                    }
                } else if (direction == 'D') {
                    if (gameboard[row + i][col] != 0) {
                        flag = false;
                        break;
                    }
                } else if (direction == 'L') {
                    if (gameboard[row][col - i] != 0) {
                        flag = false;
                        break;
                    }
                } else if (direction == 'R') {
                    if (gameboard[row][col + i] != 0) {
                        flag = false;
                        break;
                    }
                }
                i--;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            flag = false;
        }
        return flag;
    }
    //check if torpedo is on water, ship or mine not a previous hit/miss
    public static boolean validateTorpedo(int[][] gameboard, int[] coordinates) {
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        row = coordinates[0];
        col = coordinates[1];
        int val = gameboard[row][col];
        if ((val >= 0 || val == -3)&& row < gameBoardLength && col < gameBoardWidth) {
            return true;
        } else {
            return false;
        }
    }

    //validate that a mine is only placed on water
    public static boolean validateMine(int[][] gameboard, int[] coordinates) {
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        row = coordinates[0];
        col = coordinates[1];
        int val = gameboard[row][col];
        if (val == 0 && row < gameBoardLength && col < gameBoardWidth) {
            return true;
        } else {
            return false;
        }
    }

    public static int getRow() {
        return row;
    }

    public static int getCol() {
        return col;
    }

}
