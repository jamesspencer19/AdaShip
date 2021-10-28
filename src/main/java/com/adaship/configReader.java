package com.adaship;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class configReader {
    public static int boardLength;
    public static int boardWidth;
    public static char water;
    public static char ship;
    public static char hit;
    public static char miss;
    public static int carrier;
    public static int battleship;
    public static int submarine;
    public static int destroyer;
    public static int patrol;
    public static String[] shipnames;
    public static int[] shipsizes;
    public static char [] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    public static void readConfigJSON() throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("config.json"));
        String line;
        StringBuilder sbuilderObj = new StringBuilder();
        while((line=br.readLine()) !=null){
            sbuilderObj.append(line);
        }

        JsonObject gsonObj = new Gson().fromJson(sbuilderObj.toString(), JsonObject.class);

        boardLength = gsonObj.getAsJsonObject("board").get("length").getAsInt();
        boardWidth = gsonObj.getAsJsonObject("board").get("width").getAsInt();
        water = gsonObj.getAsJsonObject("board").get("water").getAsString().charAt(0);
        ship = gsonObj.getAsJsonObject("board").get("ship").getAsString().charAt(0);
        hit = gsonObj.getAsJsonObject("board").get("hit").getAsString().charAt(0);
        miss = gsonObj.getAsJsonObject("board").get("miss").getAsString().charAt(0);


        JsonArray jsonArray = gsonObj.getAsJsonArray("boats");
        for (int i = 0; i < 1; i++) {
            carrier = jsonArray.get(i).getAsJsonObject().get("carrier").getAsInt();
            battleship = jsonArray.get(i).getAsJsonObject().get("battleship").getAsInt();
            destroyer = jsonArray.get(i).getAsJsonObject().get("destroyer").getAsInt();
            submarine = jsonArray.get(i).getAsJsonObject().get("submarine").getAsInt();
            patrol = jsonArray.get(i).getAsJsonObject().get("patrol").getAsInt();
        }
        shipnames = new String[]{"Carrier", "Battleship", "Submarine", "Destroyer", "Patrol"};
        shipsizes = new int[]{carrier, battleship, submarine, destroyer, patrol};
    }

    public static String[] getShipnames() {
        return shipnames;
    }

    public static int[] getShipsizes() {
        return shipsizes;
    }

    public static char getShip() {
        return ship;
    }

    public static char getHit() {
        return hit;
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
