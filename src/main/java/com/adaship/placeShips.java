package com.adaship;

import java.util.Arrays;
import java.util.Scanner;

public class placeShips {

    public static char ship = configReader.getShip();

    public static char[][] placeShipsArray(char[][] gameboard, int[] coordinates, char direction, int shipsize) {
        validation.coordValidation(coordinates,direction);
        int row = validation.getRow();
        int col = validation.getCol();
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