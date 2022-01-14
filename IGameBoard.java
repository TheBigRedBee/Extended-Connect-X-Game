package cpsc2150.extendedConnectX;/*
  Signature:
  @author Brady Loggins
 * @class CPSC2151
 * @section 001
 * @date February 24 2021.
 */
/**
 * Invariants:
 * 0 <= c < 7
 * p = X || p = O
 * Initialization ensure: [empty gameBoard]
 * [Pieces should fall to bottom]
 */
public interface IGameBoard {
    char empty = ' ';
    /**
     * @Description: returns true if column is able to accept another token, false otherwise
     *
     * @param c is the column being checked
     * @return true or false
     * @pre 0 <= c < 7
     * @post charAtPos || empty
     */
    default boolean checkIfFree(int c) {
        //check the total num row - 1
        int r = this.getNumRows() - 1;
        //create cpsc2150.extendedConnectX.BoardPosition at top of column
        BoardPosition pos = new BoardPosition(r,c);
        return whatsAtPos(pos) == empty;
    }

    /**
     * @Description:  true if the last token placed (which was placed in column c) resulted in the player winning the game.
     * Otherwise, it returns false.
     * Note: this is not checking the entire board for a win,
     * it is just checking if the last token placed results in a win
     *
     * @param c is the column being checked
     * @return true or false
     * @post true or false
     */
    default boolean checkForWin(int c) {
        int r = 0;
        char p = empty;
        //find out what row the last move was made in that column was
        //and what player made that move
        for(int i = this.getNumRows()-1; i >=0; i--) {
            BoardPosition pos = new BoardPosition(i,c);
            p = whatsAtPos(pos);
            if(p!=empty) {
                r = i;
                i = 0;
            }
        }
        //create cpsc2150.extendedConnectX.BoardPosition to check for wins and all ways that can be won
        BoardPosition check = new BoardPosition(r,c);
        if(checkHorizWin(check,p)) {
            return true;
        }
        else if (checkVertWin(check, p)) {
            return true;
        }
        else {
            return checkDiagWin(check, p);
        }
    }

    /**
     * @Description:  a token p in column c on the game board. The token will be placed in the lowest available row in column c.
     *
     * @param p is the player
     * @param c is the column to place in
     * @pre 0 <= c < 7
     * @post cpsc2150.extendedConnectX.GameBoard contains player p in column c
     */
    void placeToken(char p, int c);

    /**
     * @Description:  true if the last token placed (which was placed in position pos and player p)
     * resulted in the player winning by getting 4 in a row horizontally. Otherwise, it returns false.
     *
     * @param pos is the cpsc2150.extendedConnectX.BoardPosition being checked
     * @param p   is the player being checked for
     * @return true or false
     * @post true or false
     */
    default boolean checkHorizWin(BoardPosition pos, char p) {
        //get initial starter values and hold onto them
        int r = pos.getRow();
        int c = pos.getColumn();
        //checking columns behind in same row
        int checkC = c-1;
        int count = 1;
        boolean cont = true;
        while ((checkC >=0) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(r,checkC);
            //if the player is at that position, they have +1 to their total count in a row
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkC--;
            }
            //otherwise they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //checking columns in front in same row
        checkC = c+1;
        cont = true;
        while ((checkC < this.getNumColumns()) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(r,checkC);
            //if the player is at that position, they have +1 to their total count in a row
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkC++;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //they did not win horizontally
        return false;
    }

    /**
     * @Description:  true if the last token placed (which was placed in position pos and player p)
     * resulted in the player getting 4 in a row vertically. Otherwise, it returns false.
     *
     * @param pos is the cpsc2150.extendedConnectX.BoardPosition being checked
     * @param p   is the player being checked for
     * @return true or false
     * @post true or false
     */
    default boolean checkVertWin(BoardPosition pos, char p) {
        //get initial starter values and hold onto them
        int r = pos.getRow();
        int c = pos.getColumn();
        //checking rows below in same column
        int checkR = r-1;
        int count = 1;
        boolean cont = true;
        while ((checkR >=0) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(checkR,c);
            //if the player is at that position, they have +1 to their total count in a column
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkR--;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //checking rows above in same column
        checkR = r+1;
        cont = true;
        while ((checkR < this.getNumRows()) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(checkR,c);
            //if the player is at that position, they have +1 to their total count in a column
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkR++;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //they did not win vertically
        return false;
    }

    /**
     * @Description:  true if the last token placed (which was placed in position pos and player p)
     * resulted in the player getting 4 in a row diagonally. Otherwise, it returns false.
     *
     * @param pos is the cpsc2150.extendedConnectX.BoardPosition being checked
     * @param p   is the player being checked for
     * @return true or false
     * @post true or false
     */
    default boolean checkDiagWin(BoardPosition pos, char p) {
        //get initial starter values and hold onto them
        int r = pos.getRow();
        int c = pos.getColumn();
        //checking down one row and to the right
        int checkR = r-1;
        int checkC = c+1;
        int count = 1;
        boolean cont = true;

        while ((checkR >= 0) && (checkC < this.getNumColumns()) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(checkR,checkC);
            //if the player is at that position, they have +1 to their total count in a diagonal
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkR--;
                checkC++;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //checking up one row and to the left
        checkR = r+1;
        checkC = c-1;
        cont = true;
        while ((checkR < this.getNumRows()) && (checkC >=0 )&& (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(checkR,checkC);
            //if the player is at that position, they have +1 to their total count in a diagonal
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkR++;
                checkC--;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //reset counter for other diagonal, checking down one row and to the left
        checkR = r-1;
        checkC = c-1;
        cont = true;
        count = 1;
        while ((checkR >= 0) && (checkC >= 0) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(checkR,checkC);
            //if the player is at that position, they have +1 to their total count in a diagonal
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkR--;
                checkC--;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //checking up one row and to the right
        checkR = r+1;
        checkC = c+1;
        cont = true;
        while ((checkR < this.getNumRows()) && (checkC < this.getNumColumns()) && (cont)) {
            //create the position
            BoardPosition checkPos = new BoardPosition(checkR,checkC);
            //if the player is at that position, they have +1 to their total count in a diagonal
            if(isPlayerAtPos(checkPos,p)) {
                count++;
                checkR++;
                checkC++;
            }
            //otherwise, they do not
            else cont = false;
            //check if they have enough to win
            if(count == this.getNumToWin()) {
                return true;
            }
        }
        //they did not win diagonally
        return false;
    }

    /**
     * @Description:  the char that is in position pos of the game board.
     * If there is no token at the spot it should return a blank space character “ “.
     *
     * @param pos is the cpsc2150.extendedConnectX.BoardPosition being checked
     * @return character in position pos
     * @post character in position pos
     */
    char whatsAtPos(BoardPosition pos);

    /**
     * @Description:  true if the player is at that position
     *
     * @param pos is the cpsc2150.extendedConnectX.BoardPosition being checked
     * @param p   is the player being checked for
     * @return true or false
     * @post true or false
     */
    default boolean isPlayerAtPos(BoardPosition pos, char p) {
        return false;
    }

    /**
     * @Description:  this function should be overriding the method inherited from the Object class.
     * Returns a fully formatted (see example output) string that displays the current game board.
     * This does not print to the screen, it returns a string that represents the cpsc2150.extendedConnectX.GameBoard
     *
     * @return string value for current cpsc2150.extendedConnectX.GameBoard
     */
    String toString();

    /**
     * @Description:  true if the game board results in a tie game, otherwise it returns false.
     *
     * @return true or false
     * @pre checkIfFree(0) = false
     * @pre checkIfFree(1) = false
     * @pre checkIfFree(2) = false
     * @pre checkIfFree(3) = false
     * @pre checkIfFree(4) = false
     * @pre checkIfFree(5) = false
     * @pre checkIfFree(6) = false
     * @post true or false
     */
    default boolean checkTie() {
        int c = 0;
        //loop all columns until you find out if the board is full
        do {
            if(checkIfFree(c)) {
                return false;
            }
            else c++;
        } while (c < this.getNumColumns());
        return true;
    }

    /**
     * @Description: returns the number of rows on the board
     * @return the number of rows in the board
     */
    int getNumRows();

    /**
     * @Description: returns the number of columns on the board
     * @return the number of columns in the board
     */
    int getNumColumns();

    /**
     * @Description: returns the number of tokens required to win
     * @return the number of tokens in a row needed to win
     */
    int getNumToWin();
}
