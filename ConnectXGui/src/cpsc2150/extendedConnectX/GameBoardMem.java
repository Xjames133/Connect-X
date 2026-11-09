package cpsc2150.extendedConnectX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoardMem extends AbsGameBoard{

    private int setRow = 0;
    private int setCol = 0;
    private int numWin = 0;
    Map<Character, List<BoardPosition>> gameBoard;

    //Done
    public GameBoardMem (int row, int col, int setWin){
        setRow = row;
        setCol = col;
        numWin = setWin;
        gameBoard = new HashMap<>();
    }

    //Done
    public void placeToken(char p, int c){
        if(!gameBoard.containsKey(p)){
            gameBoard.put(p,new ArrayList<>());
        }
        for(int i = 0; i < setRow; i++){
            BoardPosition position = new BoardPosition(i,c);
            if(whatsAtPos(position) == ' '){
                gameBoard.get(p).add(position);
                break;
            }
        }
    }

    //Done
    public char whatsAtPos(BoardPosition pos){
        char retChar = ' ';
        for(Map.Entry<Character,List<BoardPosition>> entry: gameBoard.entrySet()){
            if(entry.getValue().contains(pos)){
                retChar = entry.getKey();
                //System.out.println("ret Char holds : " + retChar);
                break;
            }
        }
        return retChar;
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
