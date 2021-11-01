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
        BufferedReader br = new BufferedReader(new FileReader("config.iml"));
        String line;
        ArrayList<String> shipnameslist = new ArrayList<String>(Arrays.asList(shipnames));
        ArrayList<Integer> shipsizeslist = new ArrayList<Integer>(Arrays.asList(shipsizes));

        while ((line = br.readLine()) != null) {
            String attribute[] = line.split(",");
            if (attribute[0].equals("board")) {
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

            } else if (attribute[0].equals("boat")) {
                shipnameslist.add(attribute[1]);
                shipsizeslist.add(Integer.parseInt(attribute[2]));
            } else {
                System.out.println("Error Reading Config");
            }
        }
        shipnames = shipnameslist.toArray(shipnames);
        shipsizes = shipsizeslist.toArray(shipsizes);

        int shipstotal = 0;

        for (int i =0; i< shipsizes.length;i++){
            shipstotal += shipsizes[i];
        }
        if (shipstotal>(boardWidth*boardLength)){
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
