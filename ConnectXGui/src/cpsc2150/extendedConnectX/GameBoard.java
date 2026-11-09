package cpsc2150.extendedConnectX;
import java.util.*;

public class GameBoard extends AbsGameBoard{

    private char gameBoard [][];
    private int setRow = 0;
    private int setCol = 0;
    private int numWin = 0;
    /**
     * @post Initializes the gameboard matrix, each index has ' ' and board position
     */
    public GameBoard(int row, int col, int setWin){
        setRow = row;
        setCol = col;
        numWin = setWin;
        gameBoard = new char [setRow][setCol];

        for(int i = 0; i < setRow; i++){
            for (int j = 0; j < setCol; j++){
                gameBoard[i][j] = ' ';
            }
        }
    }

    public void placeToken(char p, int c){
        int baseCol = c;

        for(int i = 0; i < setRow; i++){
            if(gameBoard[i][baseCol] == ' '){
                gameBoard[i][baseCol] = p;
                break;
            }
        }

    }

    public char whatsAtPos(BoardPosition pos){
        return gameBoard[pos.getRow()][pos.getColumn()];
    }

    public int getNumRows(){
        return setRow;
    }

    public int getNumColumns(){
        return setCol;
    }

    public int getNumToWin(){
        return numWin;
    }

}
