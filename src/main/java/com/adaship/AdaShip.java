package com.adaship;
import java.io.IOException;


public class AdaShip {
    public static void main(String[] args) throws IOException {
        //Read the config file and extract all data
        configReader.readConfig();
        //Run the menu allowing the user to choose which game mode that they would like to play
        AdaShipMenu.menu();
    }
}
