package com.adaship;

import java.util.Scanner;

public class placeShips {
    public static void placeShipsOnBoard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Place Carrier Ship, Size: " + configReader.getCarrier() + "Enter Co-Ordinates e.g. 3,6: ");
        String carrierPosition = sc.nextLine();
    }

    private static int processCoOrdinates(String position){
        return 0;
    }
}
