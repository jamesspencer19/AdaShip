package com.adaship;
import java.util.Arrays;
import java.util.Random;

public class randomGenerator {
    public static Random rand = new Random();
    public static int [] randomCoordinates(){
        int length = configReader.getBoardLength();
        int width = configReader.getBoardWidth();
        int [] randCoordinates = new int[2];
        randCoordinates[0] = rand.nextInt(length + 1);
        randCoordinates[1] = rand.nextInt(width + 1);
        return randCoordinates;
    }
    public static char randDirection(){
        char [] directionArray = {'U', 'R', 'D', 'L'};
        return directionArray[rand.nextInt(directionArray.length)];
    }
}
