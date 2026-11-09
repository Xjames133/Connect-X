package cpsc2150.extendedConnectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Project 4
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */
public class ConnectXController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private ConnectXView screen;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but

    private int numPlayers;
    private boolean gameover;
    private int player;
    private final char [] playTokens = new char[] {'X','O','A','E','I','P','M','N','V','G'};

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
    }

    /**
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        int curRow = 0;
        BoardPosition pos;

        //Starts new game if restarting
        if(gameover){
            this.newGame();
        }else{
            //checks if the column is free
            if(!curGame.checkIfFree(col)){
                screen.setMessage("Column is full, please select another");
                return;
            }
            //Grabs the first row that's empty in the column
            for(int i = 0; i < curGame.getNumRows(); i++){
                pos = new BoardPosition(i,col);
                if(curGame.whatsAtPos(pos) == ' '){
                    curRow = i;
                    break;
                }
            }

            pos = new BoardPosition(curRow,col);

            //Places the token in the column and then shows the represent spot
            //on the gui
            curGame.placeToken(playTokens[player],col);
            screen.setMarker(pos.getRow(),col,playTokens[player]);

            //Checks if the player has won, or the game has tied
            if(curGame.checkForWin(col)){
                screen.setMessage("Player " + playTokens[player] + " has won! Press any button to play again.");
                gameover = true;

            }else if(curGame.checkTie()){
                screen.setMessage("Draw! Press any button to play again.");
                gameover = true;
            //Continues the game if there wasn't a tie nor someone won
            }else{
                player++;
                if(player >= numPlayers){
                    player = 0;
                }
                screen.setMessage("Player " + playTokens[player] + " it is your turn.");
            }
        }

    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}