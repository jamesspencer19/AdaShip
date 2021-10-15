package com.adaship;

import java.util.Scanner;

public class placeShips {
    public static int carrier = configReader.getCarrier();
    public static int battleship = configReader.getBattleship();
    public static int submarine = configReader.getSubmarine();
    public static int destroyer = configReader.getDestroyer();
    public static int patrol = configReader.getPatrol();
    public static char ship = configReader.getShip();
    public static char water = configReader.getWater();

    public static void playerPlaceShips() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Place Carrier Ship, Size: " + configReader.getCarrier() + "Enter Co-Ordinates e.g. 3,6: ");
        String carrierPosition = sc.nextLine();
    }


    public static char[][] placeShipsArray(char[][] gameboard, int[] coordinates, char direction, int shipsize) {
        int row = coordinates[0];
        int col = coordinates[1];
        int i = shipsize;
        System.out.println(direction);
        while (i > 0) {
            System.out.println("Shipsize: " + i);
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
        createBoard.printGameBoard(gameboard, water, ship, configReader.getHit(), configReader.getMiss());
        return gameboard;
    }
}
