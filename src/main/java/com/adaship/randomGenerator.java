package com.adaship;
import java.util.Arrays;
import java.util.Random;

public class randomGenerator {
    public static Random rand = new Random();
    public static int [] randCoordinates = new int[2];
    public static char randDirection;
    public static char water = configReader.getWater();


    public static void randomiser(){
        int length = configReader.getBoardLength();
        int width = configReader.getBoardWidth();
        randCoordinates[0] = rand.nextInt(length);
        randCoordinates[1] = rand.nextInt(width);
        char [] directionArray = {'U', 'R', 'D', 'L'};
        randDirection = directionArray[rand.nextInt(directionArray.length)];
    }

    public static boolean validateLocation(char[][] gameboard, int[] coordinates, char direction, int shipsize){
        validation.coordValidation(coordinates,direction);
        int row = validation.getRow();
        int col = validation.getCol();
        int i = shipsize;
        boolean flag = true;
        try {
            while (i > 0) {
                if (direction == 'U') {
                    if (gameboard[row - i][col] != water) {
                        flag = false;
                        break;
                    }
                } else if (direction == 'D') {
                    if (gameboard[row + i][col] != water) {
                        flag = false;
                        break;
                    }
                } else if (direction == 'L') {
                    if (gameboard[row][col - i] != water) {
                        flag = false;
                        break;
                    }
                } else if (direction == 'R') {
                    if (gameboard[row][col + i] != water) {
                        flag = false;
                        break;
                    }
                }
                i--;
            }
        }catch (ArrayIndexOutOfBoundsException exception){
            flag = false;
        }
        return flag;
    }

    public static int[] getRandCoordinates() {
        return randCoordinates;
    }

    public static void setRandCoordinates(int[] randCoordinates) {
        randomGenerator.randCoordinates = randCoordinates;
    }

    public static char getRandDirection() {
        return randDirection;
    }

    public static void setRandDirection(char randDirection) {
        randomGenerator.randDirection = randDirection;
    }
}