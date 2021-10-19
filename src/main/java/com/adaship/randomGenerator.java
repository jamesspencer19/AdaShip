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
        randCoordinates[0] = rand.nextInt(length -1);
        randCoordinates[1] = rand.nextInt(width -1);
        char [] directionArray = {'U', 'R', 'D', 'L'};
        randDirection = directionArray[rand.nextInt(directionArray.length)];
    }

    public static boolean validateLocation(char[][] gameboard, int[] coordinates, char direction, int shipsize){
        int row = coordinates[0];
        int col = coordinates[1];
        if (direction == 'U') {
            col = col-1;
        } else if (direction == 'D') {
            col = col-2;
            row = row-1;
        } else if (direction == 'L') {
            row = row-1;
        } else if (direction == 'R') {
            col = col-2;
            row = row-1;
        }
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
