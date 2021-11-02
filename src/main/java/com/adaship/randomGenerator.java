package com.adaship;

import java.util.Random;

public class randomGenerator {
    public static Random rand = new Random();
    public static int [] randCoordinates = new int[2];
    public static char randDirection;

    //randomiser method
    public static void randomiser(){
        //get the length and width of board
        int length = configReader.getBoardLength();
        int width = configReader.getBoardWidth();
        //generate random coordinates
        randCoordinates[0] = rand.nextInt(length);
        randCoordinates[1] = rand.nextInt(width);
        //generate random direction
        char [] directionArray = {'U', 'R', 'D', 'L'};
        randDirection = directionArray[rand.nextInt(directionArray.length)];
    }


    public static int[] getRandCoordinates() {
        return randCoordinates;
    }

    public static char getRandDirection() {
        return randDirection;
    }

}