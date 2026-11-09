package cpsc2150.extendedConnectX;

import java.net.PortUnreachableException;

/**
 * A 2D char array or map.
 * This matrix is bounded by the MAX_ROW and MAX_COL
 * @Initialization ensures the gameboard is empty with ' ' in each index
 * @constraints 0 <= row < setRow
 *              0 <= col < setCol
 */
public interface IGameBoard {

    /**
     * @Invariants MAX_ROW == 100
     *             MAX_COL == 100
     *             MAX_NUM_TO_WIN == 25
     *             MIN_ROW == 3
     *             MIN_COL == 3
     *             MIN_NUM_TO_WIN == 3
     */

    public static final int MAX_ROW = 100;
    public static final int MAX_COL = 100;
    public static final int MAX_NUM_TO_WIN = 25;
    public static final int MIN_ROW = 3;
    public static final int MIN_COL = 3;
    public static final int MIN_NUM_TO_WIN = 3;

    /**
     * @param   c, the integer value of the column
     * @pre     col is an int
     *          0 <= col <= MAX_COL - 1
     * @post    The value of the topmost position is checked to have a token or not
     *          and returns the true if no token is present,
     *          and false if there is a token present
     * @returns boolean true if there is not a token there and boolean false otherwise
     */
    default boolean checkIfFree(int c){
        //boolean freeCheck = false;
        BoardPosition position = new BoardPosition(getNumRows()-1,c);
        //Checks the top of the column
        if(whatsAtPos(position) != ' '){
            return false;
        }

        return true;
    }

    /**
     * @param   c, the integer value of the column
     * @pre     col in an integer
     *          0 <= c <= setCol
     * @post    returns true if the player has won, and false if the player hasn't won
     * @return  boolean true or false depending on if the player has satisfied a win condition
     */
    //public boolean checkForWin(int c);

    default boolean checkForWin(int c){
        int baseCol = c;
        int baseRow = getNumRows()-1;
        int currRow = 0;
        char playerAtPos = ' ';

        for(int i = baseRow; i > -1; i--){
            BoardPosition position = new BoardPosition(i,baseCol);
            if(whatsAtPos(position) != ' '){
                currRow = i;
                break;
            }
        }

        BoardPosition pos = new BoardPosition(currRow,baseCol);
        playerAtPos = whatsAtPos(pos);
        if(checkHorizWin(pos,playerAtPos) || checkDiagWin(pos,playerAtPos) || checkVertWin(pos,playerAtPos)){
            return true;
        }

        return false;
    }

    /**
     * @post    returns the true if the game has ended in a tie, false
     *          if the game hasn't ended with a tie.
     * @return  boolean true if all column's checkIfFree return true
     *          if one returns false, then boolean false
     */
    default boolean checkTie(){
        for(int i = 0; i < getNumColumns(); i++){
            BoardPosition pos = new BoardPosition(getNumRows()-1,i);
            if(whatsAtPos(pos) == ' '){
                return false;
            }
        }
        return true;
    }

    /**
     * @param   p, represents the current player's token
     * @param   c, the column to place the token into
     * @pre     [p = 'X' OR p = 'O' OR p = '' AND
     *          0 <= col <= 8]
     * @post    p is placed at the greatest row within column 'c'
     *          inside of matrix
     */
    public void placeToken(char p, int c);

    /**
     * @param   pos, a single cell on the BoardPosition
     * @param   p, the char representing the current player's token
     * @pre     [0 <= pos row <= MAX_ROW-1
     *          p = 'X' or p = 'O']
     * @post    returns true if the player has won horizontally, or false if they haven't
     * @return  boolean true or false depending on if the player has 4 in a row horizontally
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int baseCol = pos.getColumn();
        int baseRow = pos.getRow();
        int counter = 0;

        //Check right
        if(baseCol < getNumColumns()-1){
            for(int i = baseCol + 1; i < getNumColumns(); i++){
                BoardPosition newPos = new BoardPosition(baseRow,i);
                if(isPlayerAtPos(newPos,p)){
                    counter++;
                }else{
                    break;
                }
            }
        }

        if(counter >= getNumToWin()-1){
            return true;
        }

        //Check left
        if(baseCol-1 > -1){
            for(int i = baseCol-1; i >= 0; i--){
                BoardPosition newPos2 = new BoardPosition(baseRow,i);
                if(isPlayerAtPos(newPos2,p)){
                    counter++;
                }else{
                    break;
                }
            }
        }

        if(counter >= getNumToWin()-1){
            return true;
        }

        return false;
    }

    /**
     * @param   pos, a single cell on the BoardPosition
     * @param   p, the char representing the current player's token
     * @pre     pos has an int row and column AND
     *          p = 'X' or p = 'O'
     * @post    returns true if the player has won vertically, or false if they haven't
     * @return  boolean true or false depending on if the player has 4 in a row vertically
     */
    default boolean checkVertWin(BoardPosition pos, char p){
        int baseCol = pos.getColumn();
        int baseRow = pos.getRow();
        int counter = 0;

        //Check the height to see if it's possible to even have 5 tokens
        if(baseRow + 1 < getNumToWin()){
            return false;
        }else {
            for (int i = baseRow; i > -1; i--) {
                BoardPosition newPos = new BoardPosition(i, baseCol);
                if (isPlayerAtPos(newPos, p)) {
                    counter++;
                } else {
                    break;
                }
            }

            if (counter >= getNumToWin()) {
                return true;
            }

        }
        return false;
    }

    /**
     * @param   pos, a single cell on the board position
     * @param   p, the char representing the current player's token
     * @pre     p = 'X' or p = 'O'
     * @post    returns true if the player has won diagonally, or false if they haven't
     * @return  boolean true or false depending on if the player has 4 in a row diagonally
     */
    default boolean checkDiagWin(BoardPosition pos, char p){
        int baseCol = pos.getColumn();
        int baseRow = pos.getRow();
        int counter = 0;
        int i = baseRow;
        int j = baseCol;

        //moves to the next chip
        i--;
        j++;
        //checking to bottom right (-,+)
        while(i != -1 && j != getNumColumns()){
            BoardPosition newPos = new BoardPosition(i,j);
            if(isPlayerAtPos(newPos, p)){
                counter++;
            }else{
                break;
            }
            i--;
            j++;
        }

        //check if player won
        if(counter >= getNumToWin()-1){
            return true;
        }

        i = baseRow;
        j = baseCol;

        //moves to the next chip;
        i++;
        j--;
        //checking to the top left (+,-)
        while(i < getNumRows() && j > -1){
            BoardPosition newPos2 = new BoardPosition(i,j);
            if(isPlayerAtPos(newPos2, p)){
                counter++;
            }else{
                break;
            }
            i++;
            j--;
        }

        //check if player won
        if(counter >= getNumToWin()-1){
            return true;
        }

        //reset counter for the next diagonal
        counter = 0;
        i = baseRow;
        j = baseCol;
        i--;
        j--;

        //checking to the bottom left (-,-)
        while(i != -1 && j != -1){
            BoardPosition newPos3 = new BoardPosition(i,j);
            if(isPlayerAtPos(newPos3, p)){
                counter++;
            }else{
                break;
            }
            i--;
            j--;
        }

        if(counter >= getNumToWin()-1){
            return true;
        }

        i = baseRow;
        j = baseCol;
        i++;
        j++;

        //Checking top right (+,+)
        while (i != getNumRows() && j != getNumColumns()){
            BoardPosition newPos4 = new BoardPosition(i,j);
            if(isPlayerAtPos(newPos4,p)){
                counter++;
            }else{
                break;
            }
            i++;
            j++;
        }

        if(counter >= getNumToWin()-1){
            return true;
        }

        return false;
    }

    /**
     * @param   pos, represents the position of a single cell in the board
     * @pre     0 <= pos row <= 5 and 0 <= pos col <= 8
     * @post    returns the character at the position given
     * @return  char, the character at that position of the pos
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @param   pos, a single cell on the boardposition
     * @param   player, the character representing the current player's token
     * @pre     player is a character AND
     *          player = 'X' OR playerTok = 'O' or playerTok = ''
     * @post    returns true if player has a token at position,
     *          false if a token isn't present
     * @return  boolean true if the player token is at the desired position, false otherwise
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player){
        if(whatsAtPos(pos) == player){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @return the number of rows in the matrix
     */
    public int getNumRows();

    /**
     *
     * @return the number of columns in the matrix
     */
    public int getNumColumns();

    /**
     *
     * @return the number of tokens needed to win
     */
    public int getNumToWin();
}
