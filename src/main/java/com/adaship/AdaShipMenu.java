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
        System.out.println("Menu: \n" + "1. Player vs Computer\n2. Player vs Player\n3. Player vs Computer (Salvo)\n4. Player vs Player (Salvo)\n" +
                "5. Player vs Computer (Mines)\n6. Player vs Player (Mines)\n7. Copmuter vs Computer (Mines)\n8. Quit\n");
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
            case 4:{
                gamelogic.salvoPlayerPlayer(player.playerPlaceShips(1), player.playerPlaceShips(2));
            }
            break;
            case 5:{
                gamelogic.minesComputerPlayer(computer.computerPlayer(), player.playerPlaceShips(1));
            }
            break;
            case 6:{
                gamelogic.minesPlayerPlayer(player.playerPlaceShips(1),player.playerPlaceShips(2));
            }
            break;
            case 7:{
                gamelogic.minesComputerComputer(computer.computerPlayer(),computer.computerPlayer());
            }
            break;
            case 8: {
                for (int i = 0; i < 25; i++) {
                    System.out.println("\n");
                }
            }
            break;
        }
    }
}
