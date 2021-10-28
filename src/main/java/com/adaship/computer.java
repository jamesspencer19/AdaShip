package com.adaship;

public class computer {

    public static int[][] computerPlayer() {
        randomGenerator.randomiser();
        int[] shipsizes = configReader.getShipsizes();
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
}
