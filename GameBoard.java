package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.IGameBoard;

/**
 * Signature:
 * @author Brady Loggins
 * @class CPSC2151
 * @section 001
 * @date February 24 2021.
 */

public class GameBoard extends AbsGameBoard implements IGameBoard {

    private final char[][] board;
    private static int row;
    private static int column;
    private static int numToWin;

    /**
     * this is the constructor for the cpsc2150.extendedConnectX.GameBoard
     * @post cpsc2150.extendedConnectX.GameBoard = [][]
     * @param numRow the number of rows in the game
     * @param numColumn the number of columns in the game
     */
    public GameBoard(int numRow, int numColumn, int numWin) {
        //creates the board, then loops through each value and sets it to empty
        row = numRow;
        column = numColumn;
        numToWin = numWin;
        board = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = empty;
            }
        }
    }

    public void placeToken(char p, int c) {
        int r = 0;
        boolean found = false;
        //loop until you find the lowest spot in the board with a value and place the player in that spot
        do {
            BoardPosition pos = new BoardPosition(r,c);
            if(whatsAtPos(pos)==empty) {
                this.board[r][c] = p;
                found = true;
            }
            else {
                r++;
            }
        } while ((r < row) && (!found));
    }

    public char whatsAtPos(BoardPosition pos) {
        return (this.board[pos.getRow()][pos.getColumn()]);
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char p) {
        return (this.board[pos.getRow()][pos.getColumn()]==p);

    }

    public int getNumRows() {
        return row;
    }
    public int getNumColumns() {
        return column;
    }
    public int getNumToWin() {
        return numToWin;
    }

}
