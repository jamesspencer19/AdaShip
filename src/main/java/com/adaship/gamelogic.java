package com.adaship;

import java.util.Arrays;

public class gamelogic {
    
    //Method to check computer/player guess agaisnt the target
    public static boolean guessAgainstTarget(int[][] gameboard, int[] coordinates) {
        //separate row and column coordinates
        int row = coordinates[0];
        int col = coordinates[1];
        //If a mine is hit return true
        if (checkForMine(gameboard, coordinates)) {
            return true;
        }
        //If a mine is not hit
        else {
            //If the value on the board where the coordinates are is greater than zero
            if (gameboard[row][col] > 0) {
                //A ship is present so change the value to -1 (A hit)
                gameboard[row][col] = -1;
                //Print that a hit has occurred
                System.out.println("HIT");
                return true;
            } else {
                //A miss has occurred
                gameboard[row][col] = -2;
                //Print that a miss has occurred
                System.out.println("MISS");
                return false;
            }
        }
    }
    
    //Method to check if the game is over
    public static boolean checkGameOver(int[][] gameboard) {
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        //Loop through row
        for (int i = 0; i < gameBoardLength; i++) {
            //Loop through column
            for (int j = 0; j < gameBoardWidth; j++) {
                //if the current value is greater than zero there is a ship still present
                if (gameboard[i][j] > 0) {
                    return false;
                }
            }
        }
        //If there are no ships present the opponent has won
        return true;
    }
    
    //Player against computer method
    public static void playerAgainstComputer(int[][] computergameboard, int[][] playergameboard) {
        //record hits and misses
        int phits = 0;
        int pmiss = 0;
        //record hits and misses
        int chits = 0;
        int cmiss = 0;
        //First turn is player
        String turn = "Player Turn";
        //while the game is not over
        while (!checkGameOver(computergameboard) || !checkGameOver(playergameboard)) {
            //If the turn is the players
            if (turn.equals("Player Turn")) {
                //Print the players gameboard
                createBoard.printGameBoard(playergameboard, "game");
                //Repeat in case torpedo location is invalid
                boolean repeat = true;
                while (repeat) {
                    //Run the playershot method to get the coordinates for torpedo
                    int[] playercoordinates = player.playerShot();
                    //If the torpedo location is valid on the opponents board
                    if (validation.validateTorpedo(computergameboard, playercoordinates)) {
                        //Provide the player with the result of their torpedo shot
                        //if coordinates are a hit
                        if (guessAgainstTarget(computergameboard, playercoordinates)) {
                            //incerase hits
                            phits++;
                        }
                        //if coordinates miss
                        else {
                            //increase misses
                            pmiss++;
                        }
                        System.out.println(turn + " Hits: " + phits + "\n" + turn + " Misses: " + pmiss);
                        //Loop to Switch turn to computer on the input of 1
                        //print game and target boards
                        createBoard.printGameBoard(playergameboard, "game");
                        createBoard.printGameBoard(computergameboard, "target");
                        //check if any ships have sunk
                        gamelogic.sunkShip(computergameboard);
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
                    }
                    //If the torpedo location is invalid replay the player shot
                    else {
                        System.out.println("Invalid Torpedo Location");
                        repeat = true;
                    }
                }
            }
            //If the turn is the computers
            else if (turn.equals("Computer Turn")) {
                //Play the computer shot on the player board
                //Generate random values
                randomGenerator.randomiser();
                //If the coordiates are a HIT on a Mine/Ship
                if (gamelogic.guessAgainstTarget(playergameboard, randomGenerator.getRandCoordinates())) {
                    //Increment hits
                    chits++;
                }
                //If the coordiates are a MISS
                else {
                    //Increment misses
                    cmiss++;
                }
                //Check if any ships have been sunk
                gamelogic.sunkShip(playergameboard);
                //Display the computers hits and misses
                System.out.println("Computer Hits: " + chits + "\nComputer Misses: " + cmiss);                //Switch turn
                turn = getTurn(turn);
            }
        }
        //Check for gameover
        if (checkGameOver(computergameboard)) {
            System.out.println("PLAYER WINS!");
        } else if (checkGameOver(playergameboard)) {
            System.out.println("COMPUTER WINS!");
        }
    }

    //Method to switch turn from computer to player
    private static String getTurn(String turn) {
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

    //Player vs Player method
    public static void playerAgainstPlayer(int[][] player1gameboard, int[][] player2gameboard) {
        //record hits and misses
        int p1hits = 0;
        int p1miss = 0;
        //record hits and misses
        int p2hits = 0;
        int p2miss = 0;
        //First turn is player
        String turn = "Player 1 Turn";
        //while the game is not over
        while (!checkGameOver(player1gameboard) || !checkGameOver(player2gameboard)) {
            //If turn is player 1
            if (turn.equals("Player 1 Turn")) {
                //Print it is player 1 turn
                System.out.println(turn);
                //Print the current players gamebaord
                createBoard.printGameBoard(player1gameboard, "game");
                boolean repeat = true;
                while (repeat) {
                    //Run the playershot method to get the coordinates for torpedo
                    int[] player1coordinates = player.playerShot();
                    //If the torpedo location is valid on the opponents board
                    if (validation.validateTorpedo(player2gameboard, player1coordinates)) {
                        //Provide the player with the result of their torpedo shot
                        //if coordinates are a hit
                        if (guessAgainstTarget(player2gameboard, player1coordinates)) {
                            //incerase hits
                            p1hits++;
                        }
                        //if coordinates miss
                        else {
                            //increase misses
                            p1miss++;
                        }
                        System.out.println(turn + " Hits: " + p1hits + "\n" + turn + " Misses: " + p1miss);
                        //Loop to Switch turn to computer on the input of 1
                        //print game and target boards
                        createBoard.printGameBoard(player1gameboard, "game");
                        createBoard.printGameBoard(player2gameboard, "target");
                        //check if any ships have sunk
                        gamelogic.sunkShip(player2gameboard);
                        boolean repeat2 = true;
                        //Loop to Switch turn to player 2 on the input of 1
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
            //If turn is player 2
            if (turn.equals("Player 2 Turn")) {
                //Print it is player 2 turn
                System.out.println(turn);
                //Print the current players gamebaord
                createBoard.printGameBoard(player2gameboard, "game");
                boolean repeat = true;
                while (repeat) {
                    //Run the playershot method to get the coordinates for torpedo
                    int[] player2coordinates = player.playerShot();
                    //If the torpedo location is valid on the opponents board
                    if (validation.validateTorpedo(player1gameboard, player2coordinates)) {
                        //Provide the player with the result of their torpedo shot
                        //if coordinates are a hit
                        if (guessAgainstTarget(player1gameboard, player2coordinates)) {
                            //incerase hits
                            p2hits++;
                        }
                        //if coordinates miss
                        else {
                            //increase misses
                            p2miss++;
                        }
                        System.out.println(turn + " Hits: " + p2hits + "\n" + turn + " Misses: " + p2miss);
                        //Loop to Switch turn to computer on the input of 1
                        //print game and target boards
                        createBoard.printGameBoard(player2gameboard, "game");
                        createBoard.printGameBoard(player1gameboard, "target");
                        //check if any ships have sunk
                        gamelogic.sunkShip(player1gameboard);
                        //Loop to Switch turn to player 2 on the input of 1
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
        //Check to see which player has won
        if (checkGameOver(player1gameboard)) {
            System.out.println("PLAYER 2 WINS!");
        } else if (checkGameOver(player2gameboard)) {
            System.out.println("PLAYER 1 WINS!");
        }
    }

    //Salvo PLayer vs Computer Method
    public static void salvoPlayerComputer(int[][] computergameboard, int[][] playergameboard) {
        //record hits and misses
        int p1hits = 0;
        int p1miss = 0;
        //record hits and misses
        //record hits and misses
        int chits = 0;
        int cmiss = 0;
        //First turn is the player
        String turn = "Player Turn";
        //while the game is not over
        while (checkGameOver(computergameboard) || !checkGameOver(playergameboard)) {
            if (turn.equals("Player Turn")) {
                //Print the current players gamebaord
                createBoard.printGameBoard(playergameboard, "game");
                //Get the amount of ships left on the gameboard
                int playershipsleft = shipsleft(playergameboard);
                boolean repeat = true;
                while (repeat) {
                    //Play the salvo game mode
                    player.salvoPlayer(playershipsleft, computergameboard);
                    p1hits = player.getHits() + p1hits;
                    p1miss = player.getMiss() + p1miss;
                    System.out.println(turn + " Overall hits: " + p1hits + "Overall Misses: " + p1miss);
                    //Loop to Switch turn to computer on the input of 1
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
            }
            //If it is computer turn
            else if (turn.equals("Computer Turn")) {
                //Get the amount of ships left
                int computershipsleft = shipsleft(computergameboard);
                //fire a torpedo for each ship left
                computer.salvoComputer(computershipsleft, playergameboard);
                chits = computer.getHits() + chits;
                cmiss = computer.getMiss() + chits;
                System.out.println(turn + " Overall hits: " + chits + "Overall Misses: " + chits);
                //switch turn
                turn = getTurn(turn);
            }
        }
        //Check if game is over
        if (checkGameOver(computergameboard)) {
            System.out.println("PLAYER WINS!");
        } else if (checkGameOver(playergameboard)) {
            System.out.println("COMPUTER WINS!");
        }
    }

    //Salvo Player vs Player
    public static void salvoPlayerPlayer(int[][] player1gameboard, int[][] player2gameboard) {
        //record hits and misses
        int p1hits = 0;
        int p1miss = 0;
        //record hits and misses
        int p2hits = 0;
        int p2miss = 0;
        //First turn is player
        String turn = "Player 1 Turn";
        while (checkGameOver(player1gameboard) || !checkGameOver(player2gameboard)) {
            if (turn.equals("Player 1 Turn")) {
                System.out.println(turn);
                createBoard.printGameBoard(player1gameboard, "game");
                boolean repeat = true;
                while (repeat) {
                    int player1shipsleft = shipsleft(player1gameboard);
                    //Run the salvo player
                    player.salvoPlayer(player1shipsleft, player2gameboard);
                    p1hits = player.getHits() + p1hits;
                    p1miss = player.getMiss() + p1miss;
                    System.out.println(turn + " Overall hits: " + p1hits + "Overall Misses: " + p1miss);
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
                System.out.println(turn);
                createBoard.printGameBoard(player2gameboard, "game");
                boolean repeat = true;
                while (repeat) {
                    int player2shipsleft = shipsleft(player2gameboard);
                    //Run the salvo player
                    player.salvoPlayer(player2shipsleft, player1gameboard);
                    p2hits = player.getHits() + p2hits;
                    p2miss = player.getMiss() + p2miss;
                    System.out.println(turn + " Overall hits: " + p2hits + "Overall Misses: " + p2miss);
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

    //Mines computer vs player
    public static void minesComputerPlayer(int[][] computergameboard, int[][] playergameboard) {
        //Place mines on each board
        placeMines(computergameboard);
        placeMines(playergameboard);
        //Play against computer method used
        playerAgainstComputer(computergameboard, playergameboard);
    }

    //Mines player vs Player
    public static void minesPlayerPlayer(int[][] player1gameboard, int[][] player2gameboard) {
        //Place mines on each board
        placeMines(player1gameboard);
        placeMines(player1gameboard);
        //PLay against player method used
        playerAgainstPlayer(player1gameboard, player2gameboard);
    }

    //Computer vs Computer
    public static void minesComputerComputer(int[][] computer1gameboard, int[][] computer2gameboard) {
        //record hits and misses
        int c1hits = 0;
        int c1miss = 0;
        //record hits and misses
        int c2hits = 0;
        int c2miss = 0;
        //First turn is player
        String turn = "Computer 1 Turn";
        //place mines on each board
        placeMines(computer1gameboard);
        placeMines(computer2gameboard);
        while (checkGameOver(computer1gameboard) || !checkGameOver(computer2gameboard)) {
            if (turn.equals("Computer 1 Turn")) {
                createBoard.printGameBoard(computer1gameboard, "game");
                //Generate random values
                randomGenerator.randomiser();
                //If the coordiates are a HIT on a Mine/Ship
                if (gamelogic.guessAgainstTarget(computer2gameboard, randomGenerator.getRandCoordinates())) {
                    //Increment hits
                    c1hits++;
                }
                //If the coordiates are a MISS
                else {
                    //Increment misses
                    c1miss++;
                }
                //Check if any ships have been sunk
                gamelogic.sunkShip(computer2gameboard);
                //Display the computers hits and misses
                System.out.println("Computer Hits: " + c1hits + "\nComputer Misses: " + c1miss);
                createBoard.printGameBoard(computer2gameboard, "target");
                boolean repeat2 = true;
                while (repeat2) {
                    System.out.println("Enter 1 to switch to Player 2 Turn");
                    int switchturn = validation.intValidation();
                    if (switchturn == 1) {
                        turn = "Computer 2 Turn";
                        System.out.println(turn);
                        repeat2 = false;
                    } else {
                        System.out.println("Invalid Option");
                        repeat2 = true;
                    }
                }
            } else if (turn.equals("Computer 2 Turn")) {
                createBoard.printGameBoard(computer2gameboard, "game");
                //Generate random values
                randomGenerator.randomiser();
                //If the coordiates are a HIT on a Mine/Ship
                if (gamelogic.guessAgainstTarget(computer1gameboard, randomGenerator.getRandCoordinates())) {
                    //Increment hits
                    c2hits++;
                }
                //If the coordiates are a MISS
                else {
                    //Increment misses
                    c2miss++;
                }
                //Check if any ships have been sunk
                gamelogic.sunkShip(computer1gameboard);
                //Display the computers hits and misses
                System.out.println("Computer Hits: " + c2hits + "\nComputer Misses: " + c2miss);
                createBoard.printGameBoard(computer1gameboard, "target");
                boolean repeat2 = true;
                while (repeat2) {
                    System.out.println("Enter 1 to switch to Player 2 Turn");
                    int switchturn = validation.intValidation();
                    if (switchturn == 1) {
                        turn = "Computer 1 Turn";
                        System.out.println(turn);
                        repeat2 = false;
                    } else {
                        System.out.println("Invalid Option");
                        repeat2 = true;
                    }
                }
            }
        }
        if (checkGameOver(computer1gameboard)) {
            System.out.println("COMPUTER 1 WINS!");
        } else if (checkGameOver(computer2gameboard)) {
            System.out.println("COMPUTER 2 WINS!");
        }
    }


    public static void sunkShip(int[][] gameboard) {
        //get ship names and sizes
        Integer[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        //get board width and length
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        int i = shipsizes.length;
        //while to loop through each ship
        while (i > 0) {
            boolean ship = true;
            //iterate through row and col to look for a ship
            for (int b = 0; b < gameBoardLength; b++) {
                for (int j = 0; j < gameBoardWidth; j++) {
                    if (gameboard[b][j] == i) {
                        ship = false;
                    }
                }
            }
            //If a ship does not appear in array
            if (ship) {
                //print that this ship has sunk
                System.out.println("Ship: " + shipnames[i - 1] + " has been sunk!");
            }
            //decrease the ship
            i--;
        }
    }

    //check how many ships are left
    public static int shipsleft(int[][] gameboard) {
        //get ship names and sizes
        Integer[] shipsizes = configReader.getShipsizes();
        String[] shipnames = configReader.getShipnames();
        //get board width and length
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        int shipsleft = shipsizes.length;
        int i = shipsizes.length;
        while (i > 0) {
            boolean ship = true;
            //iterate through array and search for ships
            for (int b = 0; b < gameBoardLength; b++) {
                for (int j = 0; j < gameBoardWidth; j++) {
                    if (gameboard[b][j] == i) {
                        ship = false;
                    }
                }
            }
            if (ship) {
                //record how many ships are left
                shipsleft--;
            }
            i--;
        }
        return shipsleft;
    }

    public static void placeMines(int[][] gameboard) {
        //for each mine of 5
        for (int i = 0; i < 5; i++) {
            boolean repeat = true;
            while (repeat) {
                //generate random coordinates
                randomGenerator.randomiser();
                int[] coordinates = randomGenerator.getRandCoordinates();
                int row = coordinates[0];
                int col = coordinates[1];
                //if the mine location is valid
                if (validation.validateMine(gameboard, coordinates)) {
                    //place mine
                    gameboard[row][col] = -3;
                    repeat = false;
                } else {
                    //if location is not valid repeat process
                    repeat = true;
                }
            }
        }
    }

    //check if player hit a mine
    public static boolean checkForMine(int[][] gameboard, int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        try {
            //if the value in the array for the coordinates = -3
            if (gameboard[row][col] == -3) {
                //a mine has been hit
                System.out.println("MINE HIT");
                //check for ships around the mine and if present change to a hit
                if (gameboard[row - 1][col] > 0) {
                    gameboard[row - 1][col] = -1;
                } else if (gameboard[row - 1][col + 1] > 0) {
                    gameboard[row - 1][col + 1] = -1;
                } else if (gameboard[row][col + 1] > 0) {
                    gameboard[row][col + 1] = -1;
                } else if (gameboard[row + 1][col + 1] > 0) {
                    gameboard[row + 1][col + 1] = -1;
                } else if (gameboard[row + 1][col] > 0) {
                    gameboard[row + 1][col] = -1;
                } else if (gameboard[row + 1][col - 1] > 0) {
                    gameboard[row - 1][col + 1] = -1;
                } else if (gameboard[row][col - 1] > 0) {
                    gameboard[row][col - 1] = -1;
                } else if (gameboard[row - 1][col - 1] > 0) {
                    gameboard[row - 1][col - 1] = -1;
                }
                gameboard[row][col] = -4;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return false;
    }

}
