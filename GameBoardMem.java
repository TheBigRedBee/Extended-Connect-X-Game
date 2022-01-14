package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.IGameBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    private static int row;
    private static int column;
    private static int numToWin;
    private Map<Character, List<BoardPosition>> board;


    public GameBoardMem(int numRow, int numCol, int numWin) {
        row = numRow;
        column = numCol;
        numToWin = numWin;
        board = new HashMap<>();
    }

    public void placeToken(char p, int c) {
        List<BoardPosition> position = new ArrayList<BoardPosition>();
        if(!board.containsKey(p)) {
            board.put(p,position);
        }
        int r = 0;
        boolean found = false;
        //loop until you find the lowest spot in the board with a value and place the player in that spot
        do {
            BoardPosition pos = new BoardPosition(r,c);
            if(whatsAtPos(pos)==empty) {
                found = true;
                board.get(p).add(pos);
            }
            else {
                r++;
            }
        } while ((r < row) && (!found));
    }

    public char whatsAtPos(BoardPosition pos) {
        for(char player : board.keySet()) {
            if (board.get(player).contains(pos)) {
                return player;
            }
        }
        return empty;
    }

    public boolean isPlayerAtPos(BoardPosition pos, char p) {
        if(board.containsKey(p)) {
            return board.get(p).contains(pos);
        }
        else return false;
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
