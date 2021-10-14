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
    public static int shiptotal;


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
        shiptotal = jsonArray.size();
        for (int i = 0; i < jsonArray.size(); i++) {
            carrier = jsonArray.get(i).getAsJsonObject().get("carrier").getAsInt();
            battleship = jsonArray.get(i).getAsJsonObject().get("battleship").getAsInt();
            destroyer = jsonArray.get(i).getAsJsonObject().get("destroyer").getAsInt();
            submarine = jsonArray.get(i).getAsJsonObject().get("submarine").getAsInt();
            patrol = jsonArray.get(i).getAsJsonObject().get("patrol").getAsInt();
        }
    }

    public static int getCarrier() {
        return carrier;
    }

    public static void setCarrier(int carrier) {
        configReader.carrier = carrier;
    }

    public static int getBattleship() {
        return battleship;
    }

    public static void setBattleship(int battleship) {
        configReader.battleship = battleship;
    }

    public static int getSubmarine() {
        return submarine;
    }

    public static void setSubmarine(int submarine) {
        configReader.submarine = submarine;
    }

    public static int getDestroyer() {
        return destroyer;
    }

    public static void setDestroyer(int destroyer) {
        configReader.destroyer = destroyer;
    }

    public static int getPatrol() {
        return patrol;
    }

    public static void setPatrol(int patrol) {
        configReader.patrol = patrol;
    }
    public static char getShip() {
        return ship;
    }

    public static void setShip(char ship) {
        configReader.ship = ship;
    }

    public static char getHit() {
        return hit;
    }

    public static void setHit(char hit) {
        configReader.hit = hit;
    }

    public static char getMiss() {
        return miss;
    }

    public static void setMiss(char miss) {
        configReader.miss = miss;
    }

    public static int getBoardLength() {
        return boardLength;
    }

    public static void setBoardLength(int boardLength) {
        configReader.boardLength = boardLength;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    public static void setBoardWidth(int boardWidth) {
        configReader.boardWidth = boardWidth;
    }

    public static char getWater() {
        return water;
    }

    public static void setWater(char water) {
        configReader.water = water;
    }
}
