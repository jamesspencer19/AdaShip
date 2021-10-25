package com.adaship;

public class gamelogic {
    public static void guessAgainstTarget(char [][] gameboard,int[] coordinates){
        int row = coordinates[0];
        int col = coordinates[1];
        if (gameboard[row][col] == 'S'){
            gameboard[row][col] = configReader.getHit();
            System.out.println("HIT");
        }else {
            gameboard[row][col] = configReader.getMiss();
            System.out.println("MISS");
        }
    }

    public static boolean checkGameOver(char[][] gameboard){
        int gameBoardLength = gameboard.length;
        int gameBoardWidth = gameboard[0].length;
        char val = 'S';
        for(int i = 0; i < gameBoardLength; i++){
            for(int j = 0; j < gameBoardWidth; j++){
                if(gameboard[i][j] == val)
                    return true;
            }
        }
        return false;
    }

    public static void playerAgainstComputer(char [][] computergameboard, char[][]playergameboard){
        String turn = "Player Turn";
        while(checkGameOver(computergameboard) || !checkGameOver(playergameboard)){
            if (turn.equals("Player Turn")){
                player.playerShot();
                guessAgainstTarget(computergameboard,player.getPlayercoordinates());
                createBoard.printTargetBoard(computergameboard);
                turn = "Computer Turn";
                System.out.println(turn);
            }else if(turn.equals("Computer Turn")){
                randomGenerator.randomiser();
                guessAgainstTarget(playergameboard,randomGenerator.getRandCoordinates());
                createBoard.printGameBoard(playergameboard);
                turn = "Player Turn";
                System.out.println(turn);
            }
        }
        if(!checkGameOver(computergameboard)){
            System.out.println("PLAYER WINS!");
        }else if (!checkGameOver(playergameboard)){
            System.out.println("COMPUTER WINS!");
        }
    }


}
