package com.adaship;
import java.io.IOException;


public class AdaShip {
    public static void main(String[] args) throws IOException {
        configReader.readConfigJSON();
        randomGenerator.randomCoordinates();
        AdaShipMenu.menu();
    }
}
