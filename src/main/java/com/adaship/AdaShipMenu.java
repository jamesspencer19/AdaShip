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
        //while loop to repeat the process of asking the user to choose a menu option until their input is valid
        boolean repeat = true;
        while (repeat) {
            //Provide all game mode options to the user
            System.out.println("Menu: \n" + "1. Player vs Computer\n2. Player vs Player\n3. Player vs Computer (Salvo)\n4. Player vs Player (Salvo)\n" +
                    "5. Player vs Computer (Mines)\n6. Player vs Player (Mines)\n7. Computer vs Computer (Mines)\n8. Quit\n");
            //Validate that the user has entered a valid integer
            int answer = validation.intValidation();
            //Dependant on the user input the gamemode that the have selected is run
            if (answer == 1) {
                gamelogic.playerAgainstComputer(computer.computerPlayer(), player.playerPlaceShips(1));
                System.out.println("Player vs Computer");
                repeat = false;
            }
            else if (answer == 2) {
                gamelogic.playerAgainstPlayer(player.playerPlaceShips(1), player.playerPlaceShips(2));
                System.out.println("Player vs Player");
                repeat = false;
            }
            else if (answer == 3) {
                gamelogic.salvoPlayerComputer(computer.computerPlayer(), player.playerPlaceShips(1));
                System.out.println("Player vs Computer (Salvo)");
                repeat = false;
            }
            else if (answer == 4) {
                gamelogic.salvoPlayerPlayer(player.playerPlaceShips(1), player.playerPlaceShips(2));
                System.out.println("Player vs Player (Salvo)");
                repeat = false;
            }
            else if (answer == 5) {
                gamelogic.minesComputerPlayer(computer.computerPlayer(), player.playerPlaceShips(1));
                System.out.println("Player vs Computer (Mines)");
                repeat = false;
            }
            else if (answer == 6) {
                gamelogic.minesPlayerPlayer(player.playerPlaceShips(1), player.playerPlaceShips(2));
                System.out.println("Player vs Player (Mines)");
                repeat = false;
            }
            else if (answer == 7) {
                gamelogic.minesComputerComputer(computer.computerPlayer(), computer.computerPlayer());
                System.out.println("Copmuter vs Computer (Mines)");
                repeat = false;
            }
            //Allow the user to quit
            else if (answer == 8) {
                System.exit(0);
            }
            //If the option isn't available loop to the start and ask again
            else {
                System.err.println("Invalid option selected");
                repeat = true;
            }
        }
    }
}
