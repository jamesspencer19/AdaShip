package com.adaship;

import java.util.Arrays;

public class computer {

    public static int[][] computerPlayer() {
        randomGenerator.randomiser();
        Integer[] shipsizes = configReader.getShipsizes();
        System.out.println(Arrays.toString(shipsizes));
        String[] shipnames = configReader.getShipnames();
        int[][] computerGameboard = createBoard.createGameBoard(configReader.getBoardLength(), configReader.getBoardWidth(), configReader.getWater());
        for (int i = 0; i < shipsizes.length; i++) {
            boolean repeat = true;
            while (repeat) {
                if (validation.validateLocation(computerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipsizes[i])) {
                    placeShips.placeShipsArray(computerGameboard, randomGenerator.getRandCoordinates(), randomGenerator.getRandDirection(), shipnames[i],shipsizes[i]);
                    randomGenerator.randomiser();
                    repeat = false;
                } else {
                    randomGenerator.randomiser();
                    repeat = true;
                }
            }
        }
        return computerGameboard;
    }

    public static void computerTurn(int[][] playergameboard){
        int chits = 0;
        int cmiss = 0;
        randomGenerator.randomiser();
        if (gamelogic.guessAgainstTarget(playergameboard, randomGenerator.getRandCoordinates())) {
            chits++;
        } else {
            cmiss++;
        }
        createBoard.printGameBoard(playergameboard);
        gamelogic.sunkShip(playergameboard);
        System.out.println("Computer Hits: " + chits + "\nComputer Misses: " + cmiss);
    }


}
