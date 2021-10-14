package com.adaship;
import java.io.IOException;


public class AdaShip {
    public static void main(String[] args) throws IOException {
        configReader.readConfigJSON();
        char[][] gameboard = createBoard.createGameBoard(configReader.getBoardLength(),configReader.getBoardWidth(),configReader.getWater());
        createBoard.printGameBoard(gameboard, configReader.getWater(), configReader.getShip(),configReader.getHit(),configReader.getMiss());
    }
}
