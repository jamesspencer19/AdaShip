package com.adaship;

public class placeShips {

    public static void placeShipsArray(int[][] gameboard, int[] coordinates, char direction, String shipname, int shipsize) {
        validation.coordValidation(coordinates,direction);
        String[] shipnames = configReader.getShipnames();
        int row = validation.getRow();
        int col = validation.getCol();
        int i = shipsize;
        while (i > 0) {
            int index =0;
            for (int s = 0; s < shipnames.length; s++) {
                if (shipnames[s].equals(shipname)) {
                    index = s + 1;
                    break;
                }
            }
            if (direction == 'U') {
                gameboard[row - i][col] = index;
            } else if (direction == 'D') {
                gameboard[row + i][col] = index;
            } else if (direction == 'L') {
                gameboard[row][col - i] = index;
            } else if (direction == 'R') {
                gameboard[row][col + i] = index;
            }
            i--;
        }
    }
}