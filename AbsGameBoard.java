package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.BoardPosition;
import cpsc2150.extendedConnectX.IGameBoard;

/**
 * Signature:
 * @author Brady Loggins
 * @class CPSC2151
 * @section 001
 * @date February 24 2021.
 */
public abstract class AbsGameBoard implements IGameBoard {
    public String toString() {
        int row = this.getNumRows();
        int column = this.getNumColumns();
        StringBuilder RET = new StringBuilder();
        BoardPosition pos;
        //loop from the top of the board to the bottom
        for(int i = row; i >=0; i--) {
            //from left to right
            RET.append("|");
            for(int j = 0; j < column; j++) {
                if(i == row) {
                    if (j < 10) RET.append(" ").append(j).append("|");
                    else RET.append(j).append("|");
                }
                else {
                    pos = new BoardPosition(i, j);
                    RET.append(this.whatsAtPos(pos)).append(" |");
                }
            }
            RET.append(System.lineSeparator());
        }
        return RET.toString();
    }

}

