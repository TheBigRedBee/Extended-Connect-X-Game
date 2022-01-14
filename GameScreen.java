package cpsc2150.extendedConnectX;
/*
  Signature:
  @author Brady Loggins
 * @class CPSC2151
 * @section 001
 * @date February 24 2021.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameScreen {
    private static final int MIN_ROW = 3;
    private static final int MIN_COL = 3;
    private static final int MIN_WIN = 3;
    private static final int MAX_ROW = 100;
    private static final int MAX_COL = 100;
    private static final int MAX_WIN = 25;

    /**
     * This is the main function that will control the connect4 game
     *
     * Correspondences:
     * playerX: character that refers to player X
     * playerO: character that refers to player O
     * column: the column the player pics
     * continueGame: decides whether or not user wants to play another game
     */
    public static void main(String[] args) {
        int numRow;
        int numCol;
        int numWin;
        char currP;
        int column;
        int cont;
        int numPlayers;

        boolean continueGame = true;
        boolean gameOver;
        boolean valid;
        Scanner scanner = new Scanner(System.in);


        while(continueGame) {
            //getting number of players
            do {
                System.out.println("How many players?");
                numPlayers = Integer.parseInt(scanner.nextLine());
                if(numPlayers < 2) {
                    System.out.println("Must be at least 2 players");
                }
                else if(numPlayers > 10) {
                    System.out.println("Must be 10 players or fewer");
                }
            } while((numPlayers < 2) || (numPlayers > 10));
            //create array of characters for the players
            List<Character> playerList = new ArrayList<>();

            //assign the players to a character
            int i = 1;
            while (i <= numPlayers) {
                do {
                    System.out.println("Enter the character to represent player " + i);
                    currP = scanner.nextLine().charAt(0);
                    currP = Character.toUpperCase(currP);
                    if (playerList.contains(currP)) System.out.println(currP + " is already taken as a player token!");
                    else if (currP == ' ') System.out.println("Choose a character!");
                } while(playerList.contains(currP) || currP == ' ');
                playerList.add(currP);
                i++;
            }

            //get the number of rows
            do {
                System.out.println("How many rows should be on the board?");
                numRow = Integer.parseInt(scanner.nextLine());
            } while(numRow < MIN_ROW || numRow > MAX_ROW);

            //get the number of columns
            do {
                System.out.println("How many columns should be on the board?");
                numCol = Integer.parseInt(scanner.nextLine());
            } while(numCol < MIN_COL || numCol > MAX_COL);

            //get the number to win
            do {
                System.out.println("How many in a row to win?");
                numWin = Integer.parseInt(scanner.nextLine());
            } while((numWin < MIN_WIN || numWin > MAX_WIN) || (numWin > numRow || numWin > numCol));

            //create the gameboard, store the data through the constructor
            gameOver = false;
            IGameBoard game = null;
            do {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                String test = scanner.nextLine();
                if (test.equals("F") || (test.equals("f"))) {
                    game = new GameBoard(numRow, numCol, numWin);
                    gameOver = true;
                } else if (test.equals("M") || test.equals("m")) {
                    game = new GameBoardMem(numRow, numCol, numWin);
                    gameOver = true;
                } else {
                    System.out.println("Please chose a valid game type");
                }
            } while(!gameOver);

            i = 0;
            gameOver = false;
            while(!gameOver) {

                //***********
                //get players move and place token in position
                currP = playerList.get(i);
                do {
                    System.out.println("Player " + currP + ", What column would you like to play?");
                    column = Integer.parseInt(scanner.nextLine());
                    if (column >= numCol) {
                        System.out.println("Column cannot be greater than " + (numCol-1));
                        valid = false;
                    }
                    else if (column < 0) {
                        System.out.println("Column cannot be less than 0");
                        valid = false;
                    }
                    else {
                        valid = game.checkIfFree(column);
                    }
                } while (!valid);
                game.placeToken(currP, column);
                //************

                //************
                //check for win or tie
                if (game.checkForWin(column)) {
                    gameOver = true;
                    System.out.println("Congrats Player " + currP + "! You won!");
                    System.out.println(game.toString());
                }
                else if(game.checkTie()) {
                    gameOver = true;
                    System.out.println("The game ended in a tie.");
                }
                //************

                //************
                //print board and switch players
                else {
                    System.out.println(game.toString());
                    i++;
                    if (i >= numPlayers) i = 0;

                }
                //**********

            }


            //*******************************************
            //find out if user wants to play again
            do {
                System.out.println("Enter 1 to play again, 0 to quit.");
                cont = Integer.parseInt(scanner.nextLine());
                if ((cont == 1) || (cont == 0))   {
                    gameOver = false;
                }
            } while(gameOver);
            if(cont==0) {
                continueGame = false;
            }
            //********************************************


        }
    }
}
