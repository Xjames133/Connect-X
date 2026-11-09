package cpsc2150.extendedConnectX;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * @post returns a string representation of the gameboard matrix
     * @return a string representation of the gameboard matrix
     */
    @Override
    public String toString(){
        String board = "";
        BoardPosition [] boardPos = new BoardPosition[this.getNumRows() * this.getNumColumns()];
        int counter = 0;

        //This shows all Column Numbers
        for(int i = 0; i < this.getNumColumns(); i++){
            if(i < 10){
                board = board + "| " + i;
            }else{
                board += "|" + i;
            }
            //board = board + (i);
        }
        board = board + "|\n";

        for(int j = this.getNumRows()-1; j > -1; j--){
            for(int k = 0; k < this.getNumColumns(); k++){
                boardPos[counter] = new BoardPosition(j,k);
                board = board + "|" + this.whatsAtPos(boardPos[counter]) + " ";
                counter++;
            }
            board = board + "|\n";
        }

        return board;

    }

}
