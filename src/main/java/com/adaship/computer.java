package com.adaship;

import java.util.Arrays;

public class computer {
    public static int carrier = configReader.getCarrier();
    public static int battleship = configReader.getBattleship();
    public static int submarine = configReader.getSubmarine();
    public static int destroyer = configReader.getDestroyer();
    public static int patrol = configReader.getPatrol();


    public static int[][] computerPlayer() {
        randomGenerator.randomiser();
        int[] shipsizes = {carrier, battleship, submarine, destroyer, patrol};
        String[] shipnames = {"Carrier", "Battleship", "Submarine", "Destroyer", "Patrol"};
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
}
