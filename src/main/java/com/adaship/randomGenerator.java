package com.adaship;

import java.util.Random;

public class randomGenerator {
    public static Random rand = new Random();
    public static int [] randCoordinates = new int[2];
    public static char randDirection;


    public static void randomiser(){
        int length = configReader.getBoardLength();
        int width = configReader.getBoardWidth();
        randCoordinates[0] = rand.nextInt(length);
        randCoordinates[1] = rand.nextInt(width);
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