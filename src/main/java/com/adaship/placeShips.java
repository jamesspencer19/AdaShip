package com.adaship;

public class placeShips {

    //place ships on the gameboard
    public static void placeShipsArray(int[][] gameboard, int[] coordinates, char direction, String shipname, int shipsize) {
        //validate ship location
        validation.coordValidation(coordinates,direction);
        //get ship names
        String[] shipnames = configReader.getShipnames();
        //get col and row after validation
        int row = validation.getRow();
        int col = validation.getCol();
        //get the index of ship in its array
        int i = shipsize;
        while (i > 0) {
            int index =0;
            for (int s = 0; s < shipnames.length; s++) {
                if (shipnames[s].equals(shipname)) {
                    index = s + 1;
                    break;
                }
            }
            //place this index on the board
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