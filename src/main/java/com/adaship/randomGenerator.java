package com.adaship;
import java.util.Arrays;
import java.util.Random;

public class randomGenerator {
    public static void randomCoordinates(){
        Random rand = new Random();
        int length = configReader.getBoardLength();
        int width = configReader.getBoardWidth();
        char [] directionArray = {'U', 'R', 'D', 'L'};
        int [] randCoordinates = new int[2];
        randCoordinates[0] = rand.nextInt(length + 1);
        randCoordinates[1] = rand.nextInt(width + 1);
        char direction = directionArray[rand.nextInt(directionArray.length)];
        System.out.println(direction);
        System.out.println(Arrays.toString(randCoordinates));
    }
}
