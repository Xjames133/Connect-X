package cpsc2150.extendedConnectX;

import java.util.*;

/**
 * A class holding coordinates for the gameboard matrix (Array or Map)
 * row is bounded by MAX_ROW in gameboard
 * col is bounded by MAX_COL in gameboard
 * @Initialization ensures that row is equal to a corresponding row in gameboard
 *                 and ensures that col is equal to a corresponding col in gameboard
 * @constraints 0 <= row < MAX_ROW
 *              0 <= col < MAX_COL
 */
public class BoardPosition {

    private int row = 0;
    private int col = 0;

    /**
     *
     * @param row 0 <= row <= MAX_ROW
     * @param col 0 <= col <= MAX_COL
     * @post creates an instance of board position
     */
    public BoardPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     *
     * @return row
     * @post returns the row
     */
    public int getRow(){
        return row;
    }

    /**
     *
     * @return column
     * @post returns the column
     */
    public int getColumn(){
        return col;
    }

    /**
     * @param	obj, the BoardPosition object being compared@
     * @post    returns true if the value of the object sent in, is equal to this.
     *          false if otherwise
     * @return  true if both positions are indeed equal
     *          or false if both are not equal
     */
    @Override
    public boolean equals(Object obj){
        boolean posEqual = false;
        if(obj instanceof BoardPosition) {
            BoardPosition temp = (BoardPosition) obj;
            if (getRow() == temp.getRow()) {
                if (getColumn() == temp.getColumn()) {
                    posEqual = true;
                }
            } else {
                posEqual = false;
            }
        }
        return posEqual;
    }

    /**
     * @pre position pos is valid
     * @post returns a string of the row and column
     * @return string of the row and column position
     */
    @Override
    public String toString(){
        String retString = "<";

        retString = retString + this.getRow();
        retString = retString + ", " + this.getColumn() + ">";

        return retString;
    }
}
