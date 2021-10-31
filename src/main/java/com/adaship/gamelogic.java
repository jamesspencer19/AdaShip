package com.adaship;

import java.util.Arrays;
import java.util.Scanner;

public class gamelogic {
    public static Scanner sc = new Scanner(System.in);


    public static boolean guessAgainstTarget(int[][] gameboard, int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        if (gameboard[row][col] > 0) {
            gameboard[row][col] = -1;
            System.out.println("HIT");
            return true;
        } else {
            gameboard[row][col] = -2;
            System.out.println("MISS");
            return false;
        }
    }

    public static boolean checkGameOver(int[][] gameboard) {
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        char val = 'S';
        for (int i = 0; i < gameBoardLength; i++) {
            for (int j = 0; j < gameBoardWidth; j++) {
                if (gameboard[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void playerAgainstComputer(int[][] computergameboard, int[][] playergameboard) {
        String turn = "Player Turn";
        int phits = 0;
        int pmiss = 0;
        while (checkGameOver(computergameboard) || !checkGameOver(playergameboard)) {
            if (turn.equals("Player Turn")) {
                boolean repeat = true;
                while (repeat) {
                    int[] playercoordinates = player.playerShot();
                    if (validation.validateTorpedo(computergameboard, playercoordinates)) {
                        player.playerInfo(computergameboard,playergameboard,playercoordinates,turn);
                        boolean repeat2 = true;
                        while (repeat2) {
                            System.out.println("Enter 1 to switch to Computer Turn");
                            int switchturn = validation.intValidation();
                            if (switchturn == 1) {
                                turn = "Computer Turn";
                                System.out.println(turn);
                                repeat2 = false;
                                repeat = false;
                            } else {
                                System.out.println("Invalid Option");
                                repeat2 = true;
                            }
                        }
                    } else {
                        System.out.println("Invalid Torpedo Location");
                        repeat = true;
                    }
                }
            } else if (turn.equals("Computer Turn")) {
                computer.computerTurn(playergameboard);
                turn = getString(turn);
            }
        }
        if (checkGameOver(computergameboard)) {
            System.out.println("PLAYER WINS!");
        } else if (checkGameOver(playergameboard)) {
            System.out.println("COMPUTER WINS!");
        }
    }

    private static String getString(String turn) {
        boolean repeat2 = true;
        while (repeat2) {
            System.out.println("Enter 1 to switch to Player Turn");
            int switchturn = validation.intValidation();
            if (switchturn == 1) {
                turn = "Player Turn";
                System.out.println(turn);
                repeat2 = false;
            } else {
                System.out.println("Invalid Option");
                repeat2 = true;
            }
        }
        return turn;
    }

    public static void playerAgainstPlayer(int[][] player1gameboard, int[][] player2gameboard) {
        String turn = "Player 1 Turn";
        int p2hits = 0;
        int p2miss = 0;
        while (checkGameOver(player1gameboard) || !checkGameOver(player2gameboard)) {
            if (turn.equals("Player 1 Turn")) {
                System.out.println(turn);
                boolean repeat = true;
                while (repeat) {
                    int[] player1coordinates = player.playerShot();
                    if (validation.validateTorpedo(player2gameboard, player1coordinates)) {
                        player.playerInfo(player2gameboard,player1gameboard,player1coordinates,turn);
                        boolean repeat2 = true;
                        while (repeat2) {
                            System.out.println("Enter 1 to switch to Player 2 Turn");
                            int switchturn = validation.intValidation();
                            if (switchturn == 1) {
                                turn = "Player 2 Turn";
                                System.out.println(turn);
                                repeat2 = false;
                                repeat = false;
                            } else {
                                System.out.println("Invalid Option");
                                repeat2 = true;
                            }
                        }
                    } else {
                        System.out.println("Invalid Torpedo Location");
                        repeat = true;
                    }
                }
            }
            if (turn.equals("Player 2 Turn")) {
                boolean repeat = true;
                while (repeat) {
                    int[] player2coordinates = player.playerShot();
                    System.out.println(Arrays.toString(player2coordinates));
                    if (validation.validateTorpedo(player1gameboard, player2coordinates)) {
                        player.playerInfo(player1gameboard,player2gameboard,player2coordinates,turn);
                        boolean repeat2 = true;
                        while (repeat2) {
                            System.out.println("Enter 1 to switch to Player 1 Turn");
                            int switchturn = validation.intValidation();
                            if (switchturn == 1) {
                                turn = "Player 1 Turn";
                                System.out.println(turn);
                                repeat2 = false;
                                repeat = false;
                            } else {
                                System.out.println("Invalid Option");
                                repeat2 = true;
                            }
                        }
                    } else {
                        System.out.println("Invalid Torpedo Location");
                        repeat = true;
                    }
                }
            }
        }
        if (checkGameOver(player1gameboard)) {
            System.out.println("PLAYER 2 WINS!");
        } else if (checkGameOver(player2gameboard)) {
            System.out.println("PLAYER 1 WINS!");
        }
    }

    public static void salvoPlayerComputer(int[][] computergameboard, int[][] playergameboard) {
        String turn = "Player Turn";
        int phits = 0;
        int pmiss = 0;
        int chits = 0;
        int cmiss = 0;
        while (checkGameOver(computergameboard) || !checkGameOver(playergameboard)) {
            if (turn.equals("Player Turn")) {
                boolean repeat = true;
                int playershipsleft = shipsleft(playergameboard);
                while (repeat) {
                    player.salvoPlayer(playershipsleft, computergameboard);
                    boolean repeat2 = true;
                    while (repeat2) {
                        System.out.println("Enter 1 to switch to Computer Turn");
                        int switchturn = validation.intValidation();
                        if (switchturn == 1) {
                            turn = "Computer Turn";
                            System.out.println(turn);
                            repeat2 = false;
                        } else {
                            System.out.println("Invalid Option");
                            repeat2 = true;
                        }
                    }
                    repeat = false;
                }
            } else if (turn.equals("Computer Turn")) {
                int computershipsleft = shipsleft(computergameboard);
                for (int sl = 0; sl < computershipsleft; sl++) {
                    computer.computerTurn(playergameboard);
                }
                turn = getString(turn);
            }
        }
        if (checkGameOver(computergameboard)) {
            System.out.println("PLAYER WINS!");
        } else if (checkGameOver(playergameboard)) {
            System.out.println("COMPUTER WINS!");
        }
    }

    public static void salvoPlayerPlayer(int[][] player1gameboard, int[][] player2gameboard) {
        String turn = "Player 1 Turn";
        int p1hits = 0;
        int p1miss = 0;
        int p2hits = 0;
        int p2miss = 0;
        while (checkGameOver(player1gameboard) || !checkGameOver(player2gameboard)) {
            if (turn.equals("Player 1 Turn")) {
                System.out.println(turn);
                boolean repeat = true;
                while (repeat) {
                    int player1shipsleft = shipsleft(player1gameboard);
                    player.salvoPlayer(player1shipsleft,player2gameboard);
                    boolean repeat2 = true;
                    while (repeat2) {
                        System.out.println("Enter 1 to switch to Player 2 Turn");
                        int switchturn = validation.intValidation();
                        if (switchturn == 1) {
                            turn = "Player 2 Turn";
                            System.out.println(turn);
                            repeat2 = false;
                        } else {
                            System.out.println("Invalid Option");
                            repeat2 = true;
                        }
                        repeat = false;
                    }
                }
            }
            if (turn.equals("Player 2 Turn")) {
                boolean repeat = true;
                while (repeat) {
                    int player2shipsleft = shipsleft(player2gameboard);
                    player.salvoPlayer(player2shipsleft,player1gameboard);
                    boolean repeat2 = true;
                    while (repeat2) {
                        System.out.println("Enter 1 to switch to Player 1 Turn");
                        int switchturn = validation.intValidation();
                        if (switchturn == 1) {
                            turn = "Player 1 Turn";
                            System.out.println(turn);
                            repeat2 = false;
                        } else {
                            System.out.println("Invalid Option");
                            repeat2 = true;
                        }
                        repeat = false;

                    }
                }
            }
        }
        if (checkGameOver(player1gameboard)) {
            System.out.println("PLAYER 2 WINS!");
        } else if (checkGameOver(player2gameboard)) {
            System.out.println("PLAYER 1 WINS!");
        }
    }

    public static void sunkShip(int[][] gameboard) {
        int[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        int shipsleft = shipsizes.length;
        int i = shipsizes.length;
        while (i > 0) {
            boolean ship = true;
            for (int b = 0; b < gameBoardLength; b++) {
                for (int j = 0; j < gameBoardWidth; j++) {
                    if (gameboard[b][j] == i) {
                        ship = false;
                    }
                }
            }
            if (ship) {
                System.out.println("Ship: " + shipnames[i - 1] + " has been sunk!");
            }
            i--;
        }
    }

    public static int shipsleft(int[][] gameboard) {
        int[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        int shipsleft = shipsizes.length;
        int i = shipsizes.length;
        while (i > 0) {
            boolean ship = true;
            for (int b = 0; b < gameBoardLength; b++) {
                for (int j = 0; j < gameBoardWidth; j++) {
                    if (gameboard[b][j] == i) {
                        ship = false;
                    }
                }
            }
            if (ship) {
                System.out.println("Ship: " + shipnames[i - 1] + " has been sunk!");
                shipsleft--;
            }
            i--;
        }
        return shipsleft;
    }

}
