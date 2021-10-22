package com.adaship;

import java.util.Arrays;
import java.util.Scanner;

public class player {
    public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
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
        String[] shipnames = {"Carrier", "Battleships", "Submarine", "Destroyer", "Patrol"};
        playerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        boolean notcomplete = true;
        int[] shipsizeCOPY = shipsizes;
        boolean[] placedArray = new boolean[shipsizeCOPY.length];
        for (int i = 0; i < shipsizeCOPY.length; i++) {
            placedArray[i] = false;
        }
        while (notcomplete) {
            System.out.println("0. Automatically place ships");
            for (int i = 0; i < placedArray.length; i++) {
                if (!placedArray[i]) {
                    System.out.println((i + 1) + ". Ship size: " + shipsizeCOPY[i]);
                }
            }
            System.out.println("Select a ship to place: ");
            int shipchoice = sc.nextInt();
            boolean repeat = true;
            while (repeat) {
                if (shipchoice == 0) {
                    for (int i = 0; i < placedArray.length; i++) {
                        boolean repeat2 = true;
                        while (repeat2) {
                            if (!placedArray[i]) {
                                if (randomGenerator.validateLocation(playerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipsizeCOPY[i])) {
                                    placeShips.placeShipsArray(playerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipsizeCOPY[i]);
                                    randomGenerator.randomiser();
                                    placedArray[i] = true;
                                    repeat2 = false;
                                } else {
                                    randomGenerator.randomiser();
                                    placedArray[i] = false;
                                    repeat2 = true;
                                }
                            }else{
                                repeat2=false;
                            }
                        }
                    }
                    repeat=false;
                }
                else if (shipchoice > 0) {
                    shipchoice = shipchoice - 1;
                    System.out.println("Place Ship, Size: " + shipsizeCOPY[shipchoice] + " Enter Row Co-Ordinates: ");
                    int row = sc.nextInt();
                    row = row - 1;
                    System.out.println("Place Ship, Size: " + shipsizeCOPY[shipchoice] + " Enter Column Co-Ordinates (A-Z): ");
                    char charcol = Character.toUpperCase(sc.next().charAt(0));
                    int col = 0;
                    for (int b = 0; b < alphabet.length; b++) {
                        if (alphabet[b] == charcol) {
                            col = b;
                        }
                    }
                    int[] coordinates = {row, col};
                    System.out.println("Enter the direction of the ship e.g (U,D,L,R): ");
                    char direction = Character.toUpperCase(sc.next().charAt(0));
                    if (direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R') {
                        if (randomGenerator.validateLocation(playerGameboard, coordinates, direction, shipsizeCOPY[shipchoice])) {
                            placeShips.placeShipsArray(playerGameboard, coordinates, direction, shipsizeCOPY[shipchoice]);
                            createBoard.printGameBoard(playerGameboard);
                            placedArray[shipchoice] = true;
                            repeat = false;
                        }
                    }
                } else {
                    System.out.println("Invalid Choice. Try Again!");
                    repeat = true;
                }
            }
            notcomplete = false;
            for (int i = 0; i < placedArray.length; i++) {
                if (!placedArray[i]) {
                    notcomplete = true;
                }
            }
        }
        createBoard.printGameBoard(playerGameboard);
        targetboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        createBoard.printTargetBoard(targetboard);
        return playerGameboard;
    }

    public static void playerShot() {
        System.out.println("Enter Row Co-Ordinates for Torpedo Shot: ");
        int row = sc.nextInt() - 1;
        System.out.println("Enter Column Co-Ordinates for Torpedo Shot (A-Z): ");
        char charcol = Character.toUpperCase(sc.next().charAt(0));
        int col = 0;
        for (int b = 0; b < alphabet.length; b++) {
            if (alphabet[b] == charcol) {
                col = b;
            }
        }
        playercoordinates = new int[]{row, col};
    }

    public static int[] getPlayercoordinates() {
        return playercoordinates;
    }

    public static void setPlayercoordinates(int[] playercoordinates) {
        player.playercoordinates = playercoordinates;
    }
}
