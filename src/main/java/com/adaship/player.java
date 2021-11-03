package com.adaship;

import java.util.Arrays;
import java.util.Scanner;

public class player {
    public static char[] alphabet = configReader.getAlphabet();
    public static int[][] targetboard;
    public static int[][] playerGameboard;
    public static int[] playercoordinates;
    public static Scanner sc = new Scanner(System.in);
    public static int hits = 0;
    public static int miss = 0;

    //player placing ships method
    public static int[][] playerPlaceShips(int playernum) {
        //tell player to place ships
        System.out.println("Player: " + playernum + " Place Ships");
        //get shipnames and sizes
        Integer[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        //create the player board
        playerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        //print the player gameboard
        createBoard.printGameBoard(playerGameboard, "game");
        //while all ships have not been placed
        boolean notcomplete = true;
        //create an array to see if each ship has been placed
        boolean[] placedArray = new boolean[shipsizes.length];
        for (int i = 0; i < shipsizes.length; i++) {
            placedArray[i] = false;
        }
        while (notcomplete) {
            //ask user if they want to auto place all ships
            System.out.println("0. Automatically place ships");
            //display all ships that need/are placed
            for (int i = 0; i < placedArray.length; i++) {
                if (!placedArray[i]) {
                    System.out.println((i + 1) + ". Place ship " + shipnames[i] + ", Size: " + shipsizes[i] + " NOT PLACED");
                } else {
                    System.out.println((i + 1) + ". Place ship " + shipnames[i] + ", Size: " + shipsizes[i] + " PLACED");
                }
            }
            //provide the user with individual placing options
            System.out.println("Select a ship to place, or -1 to continue, or -2 to clear shipboard, -3 to quit: ");
            int shipchoice = validation.intValidation();
            boolean repeat = true;
            while (repeat) {
                //allow the user to exit
                if (shipchoice == -3) {
                    System.exit(0);
                }
                //clear the shipboard by creating a new one
                else if (shipchoice == -2) {
                    playerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
                    createBoard.printGameBoard(playerGameboard, "game");
                    Arrays.fill(placedArray, false);
                    repeat = false;
                }
                //continue to gameplay
                else if (shipchoice == -1) {
                    repeat = false;
                }
                //auto place ships
                else if (shipchoice == 0) {
                    //iterate through unplaced ships
                    for (int i = 0; i < placedArray.length; i++) {
                        boolean repeat2 = true;
                        while (repeat2) {
                            if (!placedArray[i]) {
                                //generate random coordinates
                                randomGenerator.randomiser();
                                //validate location
                                if (validation.validateLocation(playerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipsizes[i])) {
                                    placeShips.placeShipsArray(playerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipnames[i], shipsizes[i]);
                                    placedArray[i] = true;
                                    repeat2 = false;
                                }
                                //if location is invalid retry with new coordinates
                                else {
                                    randomGenerator.randomiser();
                                    placedArray[i] = false;
                                    repeat2 = true;
                                }
                            } else {
                                repeat2 = false;
                            }
                        }
                    }
                    //print the gameboard after ships are placed
                    createBoard.printGameBoard(playerGameboard, "game");
                    repeat = false;
                }
                //manually placing ships
                else if (shipchoice > 0 && shipchoice < shipsizes.length) {
                    shipchoice = shipchoice - 1;
                    int gameBoardLength = playerGameboard.length;
                    int gameBoardWidth = playerGameboard[0].length;
                    //get ships name
                    String shipname = shipnames[shipchoice];
                    //get the index of the chosen ship in the array
                    int index = 0;
                    for (int s = 0; s < shipnames.length; s++) {
                        if (shipnames[s].equals(shipname)) {
                            index = s + 1;
                            break;
                        }
                    }
                    //if the ship is already placed, remove it
                    if (placedArray[shipchoice]) {
                        for (int i = 0; i < gameBoardLength; i++) {
                            for (int j = 0; j < gameBoardWidth; j++) {
                                if (playerGameboard[i][j] == index) {
                                    playerGameboard[i][j] = 0;
                                }
                            }
                        }
                    }
                    //get row coordinate
                    System.out.println("Place Ship, Size: " + shipsizes[shipchoice] + " Enter Row Co-Ordinates: ");
                    //validate int
                    int row = validation.intValidation();
                    row = row - 1;
                    //get col coordinate
                    System.out.println("Place Ship, Size: " + shipsizes[shipchoice] + " Enter Column Co-Ordinates (A-Z): ");
                    //validate char
                    char charcol = validation.charValidation();
                    //convert alphabet col to int
                    int col = 0;
                    for (int b = 0; b < alphabet.length; b++) {
                        if (alphabet[b] == charcol) {
                            col = b;
                        }
                    }
                    //join coordinates
                    int[] coordinates = {row, col};
                    boolean repeat2 = true;
                    while (repeat2) {
                        //get direction of ship
                        System.out.println("Enter the direction of the ship e.g (U,D,L,R): ");
                        char direction = Character.toUpperCase(sc.next().charAt(0));
                        if (direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R') {
                            //if the ship is placed in a valid location
                            if (validation.validateLocation(playerGameboard, coordinates, direction, shipsizes[shipchoice])) {
                                //place on gameboard
                                placeShips.placeShipsArray(playerGameboard, coordinates, direction, shipnames[shipchoice], shipsizes[shipchoice]);
                                createBoard.printGameBoard(playerGameboard, "game");
                                //change the value of the ship in placed array to true
                                placedArray[shipchoice] = true;
                                repeat2 = false;
                                repeat = false;
                            }
                        } else {
                            System.out.println("Invalid Direction. Retry");
                            repeat2 = true;
                        }
                    }
                } else {
                    System.out.println("Invalid Choice. Try Again!");
                    repeat = true;
                }
            }
            //check if all ships are placed
            for (int b = 0; b < placedArray.length; b++) {
                if (!placedArray[b]) {
                    notcomplete = true;
                    break;
                } else if (shipchoice == -1 && placedArray[b]) {
                    notcomplete = false;
                }
            }
        }
        //print complete gameboard
        createBoard.printGameBoard(playerGameboard, "game");
        //create a targetboard
        targetboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        //print targetboard
        createBoard.printGameBoard(targetboard, "target");
        return playerGameboard;
    }

    //players torpedo shot
    public static int[] playerShot() {
        boolean repeat = true;
        while (repeat) {
            //display torpedo options
            System.out.println("Torpedo Menu: \n1. Manually Fire\n2. Auto Fire\n3. Exit\nEnter Choice: ");
            int torpedomenu = validation.intValidation();
            //if the user wants to manually fire
            if (torpedomenu == 1) {
                //get coordinates
                System.out.println("Enter Row Co-Ordinates for Torpedo Shot: ");
                int row = validation.intValidation() - 1;
                int col = 0;
                System.out.println("Enter Column Co-Ordinates for Torpedo Shot (A-Z): ");
                char charcol = validation.charValidation();
                for (int b = 0; b < alphabet.length; b++) {
                    if (alphabet[b] == charcol) {
                        col = b;
                    }
                }
                repeat = false;
                playercoordinates = new int[]{row, col};
            }
            //if an auto shot is chosen
            else if (torpedomenu == 2) {
                //generate random coordinates
                randomGenerator.randomiser();
                repeat = false;
                playercoordinates = randomGenerator.getRandCoordinates();
            }
            //exit
            else if (torpedomenu == 3) {
                System.exit(0);
            }
            //invalid option
            else {
                System.err.println("Invalid option selected");
                repeat=true;
            }
        }
        //return the coordinates of the torpedo shots
        return playercoordinates;
    }

    //salvo game mode
    public static void salvoPlayer(int playershipsleft, int[][] attackgameboard) {
        //for each ship left
        for (int sl = 0; sl < playershipsleft; sl++) {
            //player makes a torpedo shot
            int[] attackcoordinates = player.playerShot();
            //validate torpedo location
            if (validation.validateTorpedo(attackgameboard, attackcoordinates)) {
                if (gamelogic.guessAgainstTarget(attackgameboard, attackcoordinates)) {
                    hits++;
                } else {
                    miss++;
                }
                createBoard.printGameBoard(attackgameboard, "target");
                gamelogic.sunkShip(attackgameboard);
                System.out.println("Player Hits: " + hits + "\nPlayer Misses: " + miss);
            }
        }
    }

    public static int getHits() {
        return hits;
    }

    public static int getMiss() {
        return miss;
    }
}
