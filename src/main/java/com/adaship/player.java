package com.adaship;

import java.util.Scanner;

public class player {
    public static int carrier = configReader.getCarrier();
    public static int battleship = configReader.getBattleship();
    public static int submarine = configReader.getSubmarine();
    public static int destroyer = configReader.getDestroyer();
    public static int patrol = configReader.getPatrol();
    public static char[][] targetboard;
    public static char[][] playerGameboard;
    public static int[] playercoordinates;
    public static Scanner sc = new Scanner(System.in);


    public static char[][] playerPlaceShips() {
        int[] shipsizes = {carrier, battleship, submarine, destroyer, patrol};
        playerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        for (int i = 0; i < shipsizes.length; i++) {
            boolean repeat = true;
            while (repeat) {
                System.out.println("Place Ship, Size: " + shipsizes[i] + " Enter Row Co-Ordinates: ");
                int row = sc.nextInt() - 2;
                System.out.println("Place Ship, Size: " + shipsizes[i] + " Enter Column Co-Ordinates: ");
                int col = sc.nextInt() - 1;
                int[] coordinates = {row, col};
                System.out.println("Enter the direction of the ship e.g (U,D,L,R): ");
                char direction = Character.toUpperCase(sc.next().charAt(0));
                if (direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R') {
                    if (randomGenerator.validateLocation(playerGameboard, coordinates, direction, shipsizes[i])) {
                        placeShips.placeShipsArray(playerGameboard, coordinates, direction, shipsizes[i]);
                        createBoard.printGameBoard(playerGameboard);
                        repeat = false;
                    }
                } else {
                    System.out.println("Invalid Choice. Try Again!");
                    repeat = true;
                }
            }
        }
        targetboard = createBoard.createGameBoard(configReader.getBoardLength(),configReader.getBoardWidth(),configReader.getWater());
        return playerGameboard;
    }

    public static void playerShot(){
        System.out.println("Enter Row Co-Ordinates for Torpedo Shot: ");
        int row = sc.nextInt() - 1;
        System.out.println("Enter Column Co-Ordinates for Torpedo Shot: ");
        int col = sc.nextInt() - 1;
        playercoordinates = new int[]{row, col};
    }

    public static int[] getPlayercoordinates() {
        return playercoordinates;
    }

    public static void setPlayercoordinates(int[] playercoordinates) {
        player.playercoordinates = playercoordinates;
    }
}

