package com.adaship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class configReader {
    public static int boardLength;
    public static int boardWidth;
    public static char water;
    public static char ship;
    public static char hit;
    public static char miss;
    public static char mine;
    public static String[] shipnames = new String[0];
    public static Integer[] shipsizes = new Integer[0];
    public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    public static void readConfigJSON() throws IOException {
        //Create a new buffered reader to read the file as a data stream
        BufferedReader br = new BufferedReader(new FileReader("config.iml"));
        //String for each line
        String line;
        //Arraylist for ship names to create the array
        ArrayList<String> shipnameslist = new ArrayList<String>(Arrays.asList(shipnames));
        //Arraylist for ship sizes to create the array
        ArrayList<Integer> shipsizeslist = new ArrayList<Integer>(Arrays.asList(shipsizes));

        //Loop through each line until there is not any more data
        while ((line = br.readLine()) != null) {
            //Split each word into an array by comma using regex
            String attribute[] = line.split(",");
            //If the first value is board
            if (attribute[0].equals("board")) {
                //Extract all board attributes
                if (attribute[1].equals("length")) {
                    boardLength = Integer.parseInt(attribute[2]);
                } else if (attribute[1].equals("width")) {
                    boardWidth = Integer.parseInt(attribute[2]);
                } else if (attribute[1].equals("water")) {
                    String char0 = attribute[2];
                    water = char0.charAt(0);
                } else if (attribute[1].equals("ship")) {
                    String char0 = attribute[2];
                    ship = char0.charAt(0);
                } else if (attribute[1].equals("hit")) {
                    String char0 = attribute[2];
                    hit = char0.charAt(0);
                } else if (attribute[1].equals("miss")) {
                    String char0 = attribute[2];
                    miss = char0.charAt(0);
                } else if (attribute[1].equals("mine")) {
                    String char0 = attribute[2];
                    mine = char0.charAt(0);
                }

            }//If the first word is boat
            else if (attribute[0].equals("boat")) {
                //Extract boat names and sizes
                shipnameslist.add(attribute[1]);
                shipsizeslist.add(Integer.parseInt(attribute[2]));
            } else {
                //If the config cannot be read provide error
                System.err.println("Error Reading Config");
            }
        }

        //create shipname and shipsizes array from the corresponding array lists
        shipnames = shipnameslist.toArray(shipnames);
        shipsizes = shipsizeslist.toArray(shipsizes);

        //Method to check the the ships in the config file fit on the board
        int shipstotal = 0;
        for (int i =0; i< shipsizes.length;i++){
            //Get the sum of all ship sizes
            shipstotal += shipsizes[i];
        }
        //If the sum of all ships is greater than the board size
        if (shipstotal>(boardWidth*boardLength)){
            //The config file contains too many ships for the given board size
            System.out.println("Too many ships for board");
            System.exit(0);
        }
    }


    public static String[] getShipnames() {
        return shipnames;
    }

    public static Integer[] getShipsizes() {
        return shipsizes;
    }

    public static char getShip() {
        return ship;
    }

    public static char getHit() {
        return hit;
    }

    public static char getMine() {
        return mine;
    }

    public static char getMiss() {
        return miss;
    }

    public static int getBoardLength() {
        return boardLength;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    public static char getWater() {
        return water;
    }

    public static char[] getAlphabet() {
        return alphabet;
    }
}
