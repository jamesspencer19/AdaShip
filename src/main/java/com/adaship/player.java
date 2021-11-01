package com.adaship;

import java.util.Arrays;
import java.util.Scanner;

public class player {
    public static char[] alphabet = configReader.getAlphabet();
    public static int[][] targetboard;
    public static int[][] playerGameboard;
    public static int[] playercoordinates;
    public static Scanner sc = new Scanner(System.in);


    public static int[][] playerPlaceShips(int playernum) {
        System.out.println("Player: " + playernum + " Place Ships");
        Integer[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        playerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        createBoard.printGameBoard(playerGameboard);
        boolean notcomplete = true;
        boolean[] placedArray = new boolean[shipsizes.length];
        for (int i = 0; i < shipsizes.length; i++) {
            placedArray[i] = false;
        }
        while (notcomplete) {
            System.out.println("0. Automatically place ships");
            for (int i = 0; i < placedArray.length; i++) {
                if (!placedArray[i]) {
                    System.out.println((i + 1) + ". Place ship " + shipnames[i] + ", Size: " + shipsizes[i] + " NOT PLACED");
                } else {
                    System.out.println((i + 1) + ". Place ship " + shipnames[i] + ", Size: " + shipsizes[i] + " PLACED");
                }
            }
            System.out.println("Select a ship to place, or -1 to continue, or -2 to clear shipboard, -3 to quit: ");
            int shipchoice = validation.intValidation();
            boolean repeat = true;
            while (repeat) {
                if (shipchoice == -3) {
                    System.exit(0);
                } else if (shipchoice == -2) {
                    playerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
                    createBoard.printGameBoard(playerGameboard);
                    Arrays.fill(placedArray, false);
                    repeat = false;
                } else if (shipchoice == -1) {
                    repeat = false;
                } else if (shipchoice == 0) {
                    for (int i = 0; i < placedArray.length; i++) {
                        boolean repeat2 = true;
                        while (repeat2) {
                            if (!placedArray[i]) {
                                randomGenerator.randomiser();
                                if (validation.validateLocation(playerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipsizes[i])) {
                                    placeShips.placeShipsArray(playerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipnames[i], shipsizes[i]);
                                    placedArray[i] = true;
                                    repeat2 = false;
                                } else {
                                    randomGenerator.randomiser();
                                    placedArray[i] = false;
                                    repeat2 = true;
                                }
                            } else {
                                repeat2 = false;
                            }
                        }
                    }
                    createBoard.printGameBoard(playerGameboard);
                    repeat = false;
                } else if (shipchoice > 0 && shipchoice < shipsizes.length) {
                    shipchoice = shipchoice - 1;
                    int gameBoardLength = playerGameboard.length;
                    int gameBoardWidth = playerGameboard[0].length;
                    String shipname = shipnames[shipchoice];
                    int index = 0;
                    for (int s = 0; s < shipnames.length; s++) {
                        if (shipnames[s].equals(shipname)) {
                            index = s + 1;
                            break;
                        }
                    }
                    if (placedArray[shipchoice]) {
                        for (int i = 0; i < gameBoardLength; i++) {
                            for (int j = 0; j < gameBoardWidth; j++) {
                                if (playerGameboard[i][j] == index) {
                                    playerGameboard[i][j] = 0;
                                }
                            }
                        }
                    }
                    System.out.println("Place Ship, Size: " + shipsizes[shipchoice] + " Enter Row Co-Ordinates: ");
                    int row = validation.intValidation();
                    row = row - 1;
                    System.out.println("Place Ship, Size: " + shipsizes[shipchoice] + " Enter Column Co-Ordinates (A-Z): ");
                    char charcol = validation.charValidation();
                    int col = 0;
                    for (int b = 0; b < alphabet.length; b++) {
                        if (alphabet[b] == charcol) {
                            col = b;
                        }
                    }
                    int[] coordinates = {row, col};
                    boolean repeat2 =true;
                    while (repeat2){
                        System.out.println("Enter the direction of the ship e.g (U,D,L,R): ");
                        char direction = Character.toUpperCase(sc.next().charAt(0));
                        if (direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R') {
                            if (validation.validateLocation(playerGameboard, coordinates, direction, shipsizes[shipchoice])) {
                                placeShips.placeShipsArray(playerGameboard, coordinates, direction, shipnames[shipchoice], shipsizes[shipchoice]);
                                createBoard.printGameBoard(playerGameboard);
                                placedArray[shipchoice] = true;
                                repeat2 = false;
                                repeat = false;
                            }
                        }else {
                            System.out.println("Invalid Direction. Retry");
                            repeat2=true;
                        }
                    }
                } else {
                    System.out.println("Invalid Choice. Try Again!");
                    repeat = true;
                }
            }
            for (int b = 0; b < placedArray.length; b++) {
                if (!placedArray[b]) {
                    notcomplete = true;
                    break;
                } else if (shipchoice == -1 && placedArray[b]) {
                    notcomplete = false;
                }
            }
        }
        createBoard.printGameBoard(playerGameboard);
        targetboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        createBoard.printTargetBoard(targetboard);
        return playerGameboard;
    }

    public static int[] playerShot() {
        System.out.println("Torpedo Menu: \n1. Manually Fire\n2. Auto Fire\n3. Exit\nEnter Choice: ");
        int torpedomenu = validation.intValidation();
        switch (torpedomenu) {
            case 1: {
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
                return playercoordinates = new int[]{row, col};
            }
            case 2: {
                randomGenerator.randomiser();
                return playercoordinates = randomGenerator.getRandCoordinates();
            }
            case 3: {
                System.exit(0);
            }
        }
        return playercoordinates;
    }

    public static void playerInfo(int[][] attackboard, int[][] currentgameboard,int[] attackcoordinates, String turn){
        int phits = 0;
        int pmiss = 0;
        if (gamelogic.guessAgainstTarget(attackboard, attackcoordinates)) {
            phits++;
        } else {
            pmiss++;
        }
        createBoard.printGameBoard(currentgameboard);
        createBoard.printTargetBoard(attackboard);
        gamelogic.sunkShip(attackboard);
        System.out.println(turn + " Hits: " + phits + "\n" + turn + " Misses: " + pmiss);
    }

    public static void salvoPlayer(int playershipsleft, int[][] attackgameboard){
        int hits = 0;
        int miss = 0;
        for (int sl = 0; sl < playershipsleft; sl++) {
            int[] attackcoordinates = player.playerShot();
            if (validation.validateTorpedo(attackgameboard, attackcoordinates)) {
                if (gamelogic.guessAgainstTarget(attackgameboard, attackcoordinates)) {
                    hits++;
                } else {
                    miss++;
                }
                createBoard.printTargetBoard(attackgameboard);
                gamelogic.sunkShip(attackgameboard);
                System.out.println("Player Hits: " + hits + "\nPlayer Misses: " + miss);
            }
        }
    }

}
