package cpsc2150.extendedConnectX;/*
  Signature:
  @author Brady Loggins
 * @class CPSC2151
 * @section 001
 * @date February 24 2021.
 */
/**
 * Invariants:
 * Row >= MinRows
 * Row < MaxRows
 * Column >= MinColumns
 * Column < MaxColumns
 */
public class BoardPosition {
    public int boardRow;
    public int boardCol;

    /**
     * @Description: is the constructor for cpsc2150.extendedConnectX.BoardPosition
     * @post each position is on the cpsc2150.extendedConnectX.GameBoard is initialized to a space
     */

    public BoardPosition(int row, int col) {
        boardRow = row;
        boardCol = col;
    }

    /**
     * @Description: the row value
     * @post row = #row
     * @return the row
     */
    public int getRow() {
        return boardRow;
    }

    /**
     * @Description: the column value
     * @post column = #column
     * @return the column
     */
    public int getColumn() {
        return boardCol;
    }

    /**
     * @Description: the equals function
     * determines if two Board Positions are equal
     * @returns: row or column
     */
    public boolean equals(Object pos2) {
        if(!(pos2 instanceof BoardPosition)) return false;
        BoardPosition test = (BoardPosition) pos2;
        if(this.getRow() == test.getRow()) {
            return this.getColumn() == test.getColumn();
        }
        else return false;
    }

    /**
     * @Description: the toString function
     * @post string formatted as "r,c"
     * @return string of board position
     */
    public String toString(){
        int r = this.getRow();
        int c = this.getColumn();
        return r + "," + c;

    }

}
