package com.adaship;

import java.util.Arrays;
import java.util.Scanner;

public class placeShips {

    public static char ship = configReader.getShip();

    public static char[][] placeShipsArray(char[][] gameboard, int[] coordinates, char direction, int shipsize) {
        int row = coordinates[0];
        int col = coordinates[1];
        if (direction == 'U') {
            row = row+1;
        } else if (direction == 'D') {
            row = row - 1;
        } else if (direction == 'L') {
            col = col+1;
        } else if (direction == 'R') {
            col = col-1;
        }
        int i = shipsize;
        while (i > 0) {
            if (direction == 'U') {
                gameboard[row - i][col] = ship;
            } else if (direction == 'D') {
                gameboard[row + i][col] = ship;
            } else if (direction == 'L') {
                gameboard[row][col - i] = ship;
            } else if (direction == 'R') {
                gameboard[row][col + i] = ship;
            }
            i--;
        }
        return gameboard;
    }
}
