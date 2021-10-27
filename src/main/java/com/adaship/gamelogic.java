package com.adaship;

public class gamelogic {

    public static int carrier = configReader.getCarrier();
    public static int battleship = configReader.getBattleship();
    public static int submarine = configReader.getSubmarine();
    public static int destroyer = configReader.getDestroyer();
    public static int patrol = configReader.getPatrol();

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
        int chits = 0;
        int cmiss = 0;
        while (checkGameOver(computergameboard) || !checkGameOver(playergameboard)) {
            if (turn.equals("Player Turn")) {
                boolean repeat = true;
                while (repeat){
                    int [] playercoordinates = player.playerShot();
                    if (validation.validateTorpedo(computergameboard,playercoordinates)){
                        if (guessAgainstTarget(computergameboard, playercoordinates)){
                            phits++;
                        }else {
                            pmiss++;
                        }
                        createBoard.printTargetBoard(computergameboard);
                        sunkShip(computergameboard);
                        System.out.println("Player Hits: " + phits + "\nPlayer Misses: " + pmiss);
                        turn = "Computer Turn";
                        System.out.println(turn);
                        repeat=false;
                    }else{
                        System.out.println("Invalid Torpedo Location");
                        repeat=true;
                    }
                }
            } else if (turn.equals("Computer Turn")) {
                randomGenerator.randomiser();
                if (guessAgainstTarget(playergameboard, randomGenerator.getRandCoordinates())){
                    chits++;
                }else {
                    cmiss++;
                }
                createBoard.printGameBoard(playergameboard);
                sunkShip(playergameboard);
                System.out.println("Computer Hits: " + chits + "\nComputer Misses: " + cmiss);
                turn = "Player Turn";
                System.out.println(turn);
            }
        }
        if (checkGameOver(computergameboard)) {
            System.out.println("PLAYER WINS!");
        } else if (checkGameOver(playergameboard)) {
            System.out.println("COMPUTER WINS!");
        }
    }

    public static void sunkShip(int[][] gameboard) {
        int[] shipsizes = {carrier, battleship, submarine, destroyer, patrol};
        String[] shipnames = {"Carrier", "Battleship", "Submarine", "Destroyer", "Patrol"};
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
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
                System.out.println("Ship: " + shipnames[i-1] + " has been sunk!");
            }
            i--;
        }
    }

    }
