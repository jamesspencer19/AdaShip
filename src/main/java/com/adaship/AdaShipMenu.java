package com.adaship;

import java.io.IOException;
import java.util.Scanner;

public class AdaShipMenu {
    public static void menu() throws IOException {
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
        System.out.println("Menu: \n" + "1. One player v computer game\n" + "2. Quit\n");
        int answer = sc.nextInt();
        switch (answer){
            case 1:{
                gamelogic.playerAgainstComputer(computer.computerPlayer(), player.playerPlaceShips());
            }
            break;
            case 2: {
                for (int i = 0; i < 25; i++) {
                    System.out.println("\n");
                }
            }
            break;
        }
    }
}
