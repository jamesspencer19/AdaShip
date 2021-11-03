package com.adaship;

import java.util.Arrays;

public class computer {
    public static int hits = 0;
    public static int miss = 0;
    //for each ship left

    //Computer player method
    public static int[][] computerPlayer() {
        //Run the randomiser method to generate random direction and coordinates
        randomGenerator.randomiser();
        //Get shipnames and sizes from config reader
        Integer[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        //Create the computers gameboard using the board size from the config file
        int[][] computerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        //For each available ship from config file
        for (int i = 0; i < shipsizes.length; i++) {
            //repeat until a valid location is found
            boolean repeat = true;
            while (repeat) {
                //If the random generated direction and coordinates are valid
                if (validation.validateLocation(computerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipsizes[i])) {
                    //Place the ship on the board in the location
                    placeShips.placeShipsArray(computerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipnames[i],shipsizes[i]);
                    //Re-generate random values
                    randomGenerator.randomiser();
                    //Do not repeat as ship has been placed
                    repeat = false;
                }
                //The ship doesnt fit
                else {
                    //Re-generate random values
                    randomGenerator.randomiser();
                    //Repeat as ship hasn't been placed
                    repeat = true;
                }
            }
        }
        //Return the computer gameboard
        return computerGameboard;
    }


    public static void salvoComputer(int computershipsleft, int[][] attackgameboard) {
        for (int sl = 0; sl < computershipsleft; sl++) {
            randomGenerator.randomiser();
            //player makes a torpedo shot
            int[] attackcoordinates = randomGenerator.getRandCoordinates();
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
