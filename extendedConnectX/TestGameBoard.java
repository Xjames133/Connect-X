package cpsc2150.extendedConnectX;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {
    private IGameBoard gb(int row, int col, int xWin){return new GameBoard(row,col,xWin);}
    private String BoardString(char [][] board){
        String s = "";
        //prints the board
        for(int i = 0; i < board[0].length; i++){
            //printing for single digit
            if(i< 10){
                s += "| " + i;
            }
                //printing for double digit
            else{
                s += "|" + i;
            }
        }
        s += "|";
        s += "\n";
        for(int i =board.length - 1; i >= 0; i--){
            for(int j = 0; j < board[0].length; j++){
                BoardPosition pos = new BoardPosition(i,j);
                s = s + "|" + board[i][j] + " ";
            }
            s+= "|\n";
        }
        return s;
    }

    @Test
    public void testConstructor_min_values() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testConstructor_max_values() {
        int row = 100;
        int col = 100;
        int num_to_win = 25;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testConstructor_rectangle_shape() {
        int row = 3;
        int col = 4;
        int num_to_win = 3;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        assertEquals(gBoard.toString(), (BoardString(board)));
        assertEquals(gBoard.getNumToWin(), num_to_win);
    }

    @Test
    public void testplaceToken_in_empty_column() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 1);
        board[0][1] = 'X';
        BoardPosition pos = new BoardPosition(0,1);
        assertEquals(gBoard.whatsAtPos(pos), 'X');
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testplaceToken_in_near_full_column() {
        int row = 100;
        int col = 100;
        int num_to_win = 25;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        for (int i = 0; i < row - 1; i++) {
            if (i % 2 == 0) {
                gBoard.placeToken('X', 99);
                board[i][99] = 'X';
            } else {
                gBoard.placeToken('O', 99);
                board[i][99] = 'O';
            }
        }
        gBoard.placeToken('O', 99);
        board[99][99] = 'O';
        BoardPosition pos = new BoardPosition(99,99);
        assertEquals(gBoard.whatsAtPos(pos), 'O');
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testplaceToken_bottom_right() {
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 4);
        board[0][4] = 'X';
        BoardPosition pos = new BoardPosition(0,4);
        assertEquals(gBoard.whatsAtPos(pos), 'X');
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testplaceToken_center_of_board() {
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        for (int i = 0; i < 2 ; i++) {
            if (i % 2 == 0) {
                gBoard.placeToken('X', 2);
                board[i][2] = 'X';
            } else {
                gBoard.placeToken('O', 2);
                board[i][2] = 'O';
            }
        }
        gBoard.placeToken('X', 2);
        board[2][2] = 'X';
        BoardPosition pos = new BoardPosition(2,2);
        assertEquals(gBoard.whatsAtPos(pos), 'X');
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testPlaceToken_less_than_half_full() {
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        board[0][0] = 'X';
        gBoard.placeToken('X', 0);
        board[1][0] = 'X';
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals(gBoard.whatsAtPos(pos), 'X');
        assertEquals(gBoard.toString(), (BoardString(board)));
    }

    @Test
    public void testcheckIfFree_full_column() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 2);
        assertEquals(gBoard.checkIfFree(2), false);
    }

    @Test
    public void testcheckIfFree_column_available() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        assertEquals(gBoard.checkIfFree(0), true);
    }

    @Test
    public void testcheckIfFree_last_column_only_available() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 2);
        assertEquals(gBoard.checkIfFree(2), true);
    }

    @Test
    public void testCheckHorizWin_no_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard= gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(0, 1);
        assertEquals(gBoard.checkHorizWin(pos, 'X'), false);
    }

    @Test
    public void testcheckHorizWin__bottom_right_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard= gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gBoard.checkHorizWin(pos, 'X'), true);
    }

    @Test
    public void testcheckHorizWin_lower_left_corner_placement(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard= gb(row, col, num_to_win);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(gBoard.checkHorizWin(pos, 'X'), false);
    }

    @Test
    public void testcheckHorizWin_between_pieces(){
        int row = 5;
        int col = 5;
        int num_to_win = 5;
        IGameBoard gBoard= gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 4);
        gBoard.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(0, 1);
        assertEquals(gBoard.checkHorizWin(pos, 'X'), true);
    }

    @Test
    public void testcheckVertWin_no_win_below(){
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('O', 1);
        BoardPosition pos = new BoardPosition(2, 1);
        assertEquals(gBoard.checkHorizWin(pos, 'O'), false);
    }

    @Test
    public void testcheckVertWin_top_row_add() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(2, 0);
        assertEquals(gBoard.checkVertWin(pos, 'X'), true);
    }

    @Test
    public void testcheckVertWin_middle_of_board_win(){
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('O', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        BoardPosition pos = new BoardPosition(3, 3);
        assertEquals(gBoard.checkVertWin(pos, 'X'), true);
    }
    
    @Test
    public void testcheckVertWin_right_number_nonconsecutive_token(){
        int row = 6;
        int col = 7;
        int num_to_win = 4;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('O', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        BoardPosition pos = new BoardPosition(4, 3);
        assertEquals(gBoard.checkVertWin(pos, 'X'), false);
    }

    @Test
    public void testcheckDiagWin_bottom_left_placement_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testCheckDiagWin_upper_right_placement_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_placed_diagonal_to_opponent_win(){
        int row = 4;
        int col = 4;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('O', 3);
        gBoard.placeToken('O', 3);
        gBoard.placeToken('O', 3);
        gBoard.placeToken('O', 3);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), true);
    }
    @Test
    public void testDiagWin_win_possible_from_all_directions() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(1, 1);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_bottom_right_placement(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_upper_left_placement(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(2, 0);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_tally_reset_in_all_directions(){
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gBoard.checkDiagWin(pos, 'X'), false);
    }

    @Test
    public void testcheckTie_one_piece_on_board() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        assertEquals(gBoard.checkTie(), false);
    }

    @Test
    public void testcheckTie_full_board() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gBoard.placeToken('X', j);
            }
        }
        assertEquals(gBoard.checkTie(), true);
    }

    @Test
    public void testcheckTie_first_column_full(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('O', 0);
        gBoard.placeToken('X', 0);

        assertEquals(gBoard.checkTie(), false);
    }

    @Test
    public void testcheckTie_all_but_one_top_space(){
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        IGameBoard gBoard = gb(row, col, num_to_win);
        for (int i = 0; i < row -1; i++) {
            for (int j = 0; j < col; j++) {
                gBoard.placeToken('X', j);
            }
        }
        assertEquals(gBoard.checkTie(), false);
    }

    @Test
    public void testIsPlayerAtPos_wrong_char(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gBoard.isPlayerAtPos(pos, 'O'), false);
    }

    @Test
    public void testIsPlayerAtPos_bottom_left_pos() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(gBoard.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testIsPlayerAtPos_between_tokens(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 1);
        assertEquals(gBoard.isPlayerAtPos(pos, 'O'), true);
    }

    @Test
    public void testIsPlayerAtPos_upper_right_pos(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gBoard.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testIsPlayerAtPos_bottom_right(){
        int row = 4;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gBoard.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testwhatsAtPos_empty_board() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        BoardPosition pos = new BoardPosition(2, 1);
        assertEquals(gBoard.whatsAtPos(pos), ' ');
    }

    @Test
    public void testwhatsAtPos_middle_row(){
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 3);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(1, 1);
        assertEquals(gBoard.whatsAtPos(pos), 'O');
    }

    @Test
    public void testwhatsAtPos_next_to_another_token(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('O', 2);
        BoardPosition pos = new BoardPosition(0, 1);
        assertEquals(gBoard.whatsAtPos(pos), 'X');
    }

    @Test
    public void testwhatsAtPos_upper_right_bound(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 2);
        gBoard.placeToken('O', 1);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('O', 2);
        gBoard.placeToken('X', 1);
        gBoard.placeToken('O', 2);
        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gBoard.whatsAtPos(pos), 'O');
    }

    @Test
    public void testwhatsAtPos_lower_left_bound(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gBoard = gb(row, col, num_to_win);
        gBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(gBoard.whatsAtPos(pos),'X');
    }

}
