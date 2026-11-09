package cpsc2150.extendedConnectX;

import java.util.*;

public class GameScreen {

    /**
     * @param args
     * @post Game has been played
     * @invariants
     * MIN_PLAYERS = 2
     * MAX_PLAYERS = 10
     */

    static final int MIN_PLAYERS = 2;
    static final int MAX_PLAYERS = 10;

    public static void main (String [] args){
        IGameBoard gameMatrix;

        List <Character> playerXO = new ArrayList<>();
        int numPlayer = 0;
        int turns;
        int numCol = 0;
        int numRow = 0;
        int numWin = 0;
        boolean checkWin;

        String playChoice = " ";
        int colChoice = 0;
        Scanner scan = new Scanner(System.in);

        while(!playChoice.equals("n") && !playChoice.equals("N")){
            playerXO.clear();
            System.out.println("How many players?");
            numPlayer = scan.nextInt();

            //get num of Players
            while(numPlayer < MIN_PLAYERS || numPlayer > MAX_PLAYERS){
                if(numPlayer < 2){
                    System.out.println("Must be at least 2 players");
                }else{
                    System.out.println("Must be 10 players or fewer");
                }
                System.out.println("How many players?");
                numPlayer = scan.nextInt();
            }
            scan.nextLine(); //Clears the next line from grabbing int

            //Get Player tokens
            for(int i = 0; i < numPlayer; i++){
                System.out.println("Enter the character to represent player " + (i+1));
                playChoice = scan.nextLine();
                if(!Character.isUpperCase(playChoice.charAt(0))){
                    playChoice = playChoice.toUpperCase();
                }
                System.out.println("playChoice = " + playChoice);
                while(playerXO.contains(playChoice.charAt(0))){
                    System.out.println(playChoice + " is already taken as a player token!");
                    System.out.println("Enter the character to represent player " + (i+1));
                    playChoice = scan.nextLine();
                    if(!Character.isUpperCase(playChoice.charAt(0))){
                        playChoice = playChoice.toUpperCase();
                    }
                }
                playerXO.add(playChoice.charAt(0));
            }

            System.out.println("How many rows should be on the board?");
            numRow = scan.nextInt();
            //Get num row
            while(numRow < GameBoard.MIN_ROW || numRow > GameBoard.MAX_ROW){
                if(numRow < GameBoard.MIN_ROW){
                    System.out.println("Must be at least 3 rows");
                }else{
                    System.out.println("Must be 100 rows or fewer");
                }
                System.out.println("How many rows should be on the board?");
                numRow = scan.nextInt();
            }

            System.out.println("How many columns should be on the board?");
            numCol = scan.nextInt();
            //Get num col
            while(numCol < GameBoard.MIN_COL || numCol > GameBoard.MAX_COL){
                if(numCol < GameBoard.MIN_COL){
                    System.out.println("Must be at least 3 columns");
                }else{
                    System.out.println("Must be 100 columns or fewer");
                }
                System.out.println("How many columns should be on the board?");
                numCol = scan.nextInt();
            }

            //Get num to win
            System.out.println("How many in a row to win?");
            numWin = scan.nextInt();
            while(numWin < GameBoard.MIN_NUM_TO_WIN || numWin > GameBoard.MAX_NUM_TO_WIN ||
                    (numWin > numCol && numWin > numRow)){
                if(numWin < GameBoard.MIN_NUM_TO_WIN){
                    System.out.println("Must be at least 3 to win");
                }else if (numWin > GameBoard.MAX_NUM_TO_WIN){
                    System.out.println("Must be 100 or fewer to win");
                }else{
                    System.out.println("Must be fewer than the highest row or column");
                }
                System.out.println("How many in a row to win?");
                numWin = scan.nextInt();
            }
            //Clear new line
            scan.nextLine();

            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
            playChoice = scan.nextLine();
            while(playChoice.charAt(0) != 'F' && playChoice.charAt(0) != 'f' && playChoice.charAt(0) != 'M'
                    && playChoice.charAt(0) != 'm'){
                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
                playChoice = scan.nextLine();
            }

            if(playChoice.charAt(0) == 'F' || playChoice.charAt(0) == 'f'){
                gameMatrix = new GameBoard(numRow, numCol, numWin);
            }else{
                gameMatrix = new GameBoardMem(numRow,numCol,numWin);
            }

            turns = 0;
            checkWin = false;

            while(!checkWin && !gameMatrix.checkTie()){
                System.out.println(gameMatrix.toString());
                System.out.println("Player " + playerXO.get(turns % numPlayer) + ", " +
                        "What column do you want to place your token?");
                colChoice = scan.nextInt();

                while(colChoice < 0 || colChoice > gameMatrix.getNumColumns() - 1){
                    System.out.println("Please enter column between 0-" + (gameMatrix.getNumColumns()-1));
                    colChoice = scan.nextInt();
                }
                while(!gameMatrix.checkIfFree(colChoice)){
                    System.out.println("Column full, please select another");
                    colChoice = scan.nextInt();
                    while(colChoice < 0 || colChoice > gameMatrix.getNumColumns() - 1){
                        System.out.println("Please enter column between 0-" + (gameMatrix.getNumColumns()-1));
                        colChoice = scan.nextInt();
                    }
                }

                gameMatrix.placeToken(playerXO.get(turns % numPlayer), colChoice);

                checkWin = gameMatrix.checkForWin(colChoice);
                turns++;
            }
            //to go back one if the loop ends
            turns--;

            System.out.println(gameMatrix.toString());

            if(!checkWin){
                System.out.println("Draw! No one wins");
            }else{
                System.out.println("Player " + playerXO.get(turns % numPlayer) + " has won!");
            }

            //Clears new line in scan;
            scan.nextLine();
            System.out.println("Rematch? (Y/N)");
            playChoice = scan.nextLine();
        }
        System.out.println("Thanks for playing!");
    }
}
