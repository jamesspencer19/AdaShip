package com.adaship;

import java.util.Scanner;

public class AdaShipMenu {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("⛵-------------- AdaShip --------------⛵");
        System.out.println("              |    |    |                 \n" +
                "             )_)  )_)  )_)              \n" +
                "            )___))___))___)\\            \n" +
                "           )____)____)_____)\\\\\n" +
                "         _____|____|____|____\\\\\\__\n" +
                "---------\\                   /---------\n" +
                "  ^^^^^ ^^^^^^^^^^^^^^^^^^^^^\n" +
                "    ^^^^      ^^^^     ^^^    ^^\n" +
                "         ^^^^      ^^^");
        System.out.println("Menu: \n" + "1. Player vs Computer\n2. Player vs Player\n3. Player vs Computer (Salvo)\n4. Quit\n");
        int answer = validation.intValidation();
        switch (answer){
            case 1:{
                gamelogic.playerAgainstComputer(computer.computerPlayer(), player.playerPlaceShips(1));
            }
            break;
            case 2:{
                gamelogic.playerAgainstPlayer(player.playerPlaceShips(1), player.playerPlaceShips(2));
            }
            break;
            case 3:{
                gamelogic.salvoPlayerComputer(computer.computerPlayer(), player.playerPlaceShips(1));
            }
            break;
            case 4: {
                for (int i = 0; i < 25; i++) {
                    System.out.println("\n");
                }
            }
            break;
        }
    }
}
