package com.adaship;

import java.util.Scanner;

public class validation {
    public static int row;
    public static int col;

    public static Scanner sc = new Scanner(System.in);

    public static char charValidation() {
        boolean repeat = true;
        char charcol = 0;
        while (repeat){
            charcol = Character.toUpperCase(sc.next().charAt(0));
            if (Character.toString(charcol).matches("[A-Z]")){
                repeat=false;
            }else{
                System.out.println("Input is not (A-Z)");
                repeat = true;
            }
        }
        return charcol;
    }
    public static int intValidation(){
        while (!sc.hasNextInt()) {
            System.out.println("Input is not a number!");
            sc.nextLine();
        }
        return sc.nextInt();
    }
    public static void coordValidation(int[]coordinates,char direction){
        row = coordinates[0];
        col = coordinates[1];
        if (direction == 'U') {
            row = row+1;
        } else if (direction == 'D') {
            row = row - 1;
        } else if (direction == 'L') {
            col = col+1;
        } else if (direction == 'R') {
            col = col-1;
        }
    }

    public static int getRow() {
        return row;
    }

    public static void setRow(int row) {
        validation.row = row;
    }

    public static int getCol() {
        return col;
    }

    public static void setCol(int col) {
        validation.col = col;
    }
}
