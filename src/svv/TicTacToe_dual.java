package svv;
//
// This program plays the game of Tic-Tac-Toe with the user.

import java.util.Scanner;

public class TicTacToe_dual
{
  public static Scanner sc = new Scanner(System.in);
  public int turn;
  public char userSymbol;
  public char compSymbol;
  public static char numberOfplayer;
  public char ans;
  public static int remainCount;
  public static final int SIZE = 3;
  public static char[][] board= new char[SIZE][SIZE];
  
  public TicTacToe_dual(){
	  
		  
	    resetBoard(); // initialize the board (with ' ' for all cells)

	    // First, welcome message and display the board.
	    System.out.println("===== WELCOME TO THE TIC-TAC-TOE GAME!! =====\n");
	    showBoard(board);

	    System.out.println("  how many players in this game?   1 or 2 ?");
	    numberOfplayer = sc.next().toLowerCase().charAt(0);
	    System.out.println("  number of players: " + numberOfplayer);
	    
	    
	    
	    
	    // Then ask the user which symbol (x or o) he/she wants to play.
	    System.out.print("  Which symbol do player1 want to play, \"x\" or \"o\"? ");
	    userSymbol = sc.next().toLowerCase().charAt(0);
	    compSymbol = (userSymbol == 'x') ? 'o' : 'x';
	    
	    

	    // Also ask whether or not the user wants to go first.
	    System.out.println();
	    System.out.print("  Does player1 want to go first (y/n)? ");
	    ans = sc.next().toLowerCase().charAt(0);
	    //int turn;  // 0 -- the user, 1 -- the computer or the second user
  
  };

  public TicTacToe_dual(char players, char _userSymbol, char goFirst){
    numberOfplayer = players;
    userSymbol = _userSymbol;
    ans = goFirst;

  }
  
  
  
  public static void main(String[] args)
  {

    //final int SIZE = 3;
    //char[][] board = new char[SIZE][SIZE]; // game board

	  TicTacToe_dual tictactoe = new TicTacToe_dual();
   
	  remainCount = SIZE * SIZE; // empty cell count

    // THE VERY FIRST MOVE.
    if (tictactoe.ans == 'y') {
      tictactoe.turn = 0;
      if(numberOfplayer=='2') System.out.println("  Player 1's turn");
      userPlay(tictactoe.board, tictactoe.userSymbol); // user puts his/her first tic
    }
    else {
      tictactoe.turn = 1;
      if (numberOfplayer=='2'){
    	  System.out.println("  Player 2's turn");
    	  userPlay(tictactoe.board, tictactoe.compSymbol);
      }	
      else
      compPlay(tictactoe.board, tictactoe.compSymbol); // computer puts its first tic
    }
    // Show the board, and decrement the count of remaining cells.
    showBoard(tictactoe.board);
    remainCount--;

    // Play the game until either one wins.
    boolean done = false;
    int winner = -1;   // 0 -- the user, 1 -- the computer/the player 2, -1 -- draw

    while (!done && remainCount > 0) {
      // If there is a winner at this time, set the winner and the done flag to true.
      done = isGameWon(tictactoe.board, tictactoe.turn, tictactoe.userSymbol, tictactoe.compSymbol); // Did the turn won?

      if (done)
        winner = tictactoe.turn; // the one who made the last move won the game
      else {
        // No winner yet.  Find the next turn and play.
        tictactoe.turn = (tictactoe.turn + 1 ) % 2;

        if (tictactoe.turn == 0){
        	if (numberOfplayer=='2')	System.out.println("  Player 1's turn");
          userPlay(tictactoe.board, tictactoe.userSymbol);
          }
        else{
        	if(numberOfplayer=='2'){
        		System.out.println("  Player 2's turn");
        		userPlay(tictactoe.board, tictactoe.compSymbol);
        		}
        	else 
        		compPlay(tictactoe.board, tictactoe.compSymbol);}
        

        // Show the board after one tic, and decrement the rem count.
        showBoard(tictactoe.board);
        remainCount--;
      }
    }

    // Winner is found.  Declare the winner.
    if (winner == 0)
      System.out.println("\n** YOU WON.  CONGRATULATIONS!! **");
    else if (winner == 1)
      System.out.println("\n** YOU LOST..  Maybe next time :) **");
    else
      System.out.println("\n** DRAW... **");

  }

  public static void resetBoard()
  {
    for (int i = 0; i < board[0].length; i++)
      for (int j = 0; j < board[0].length; j++)
        board[i][j] = ' ';
  }

  public static void showBoard(char[][] brd)
  {
    int numRow = brd.length;
    int numCol = brd[0].length;

    System.out.println();

    // First write the column header
    System.out.print("    ");
    for (int i = 0; i < numCol; i++)
      System.out.print(i + "   ");
    System.out.print('\n');

    System.out.println(); // blank line after the header

    // The write the table
    for (int i = 0; i < numRow; i++) {
      System.out.print(i + "  ");
      for (int j = 0; j < numCol; j++) {
        if (j != 0)
          System.out.print("|");
        System.out.print(" " + brd[i][j] + " ");
      }

      System.out.println();

      if (i != (numRow - 1)) {
        // separator line
        System.out.print("   ");
        for (int j = 0; j < numCol; j++) {
          if (j != 0)
            System.out.print("+");
          System.out.print("---");
        }
        System.out.println();
      }
    }
    System.out.println();
  }

  public static void userPlay(char[][] brd, char usym)
  {
    System.out.print("\nEnter the row and column indices: ");
    int rowIndex = sc.nextInt();
    int colIndex = sc.nextInt();

    while (brd[rowIndex][colIndex] != ' ') {
      System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
      rowIndex = sc.nextInt();
      colIndex = sc.nextInt();
    }

    brd[rowIndex][colIndex] = usym;
  }

  public static void compPlay(char[][] brd, char csym)
  {
    // Find the first empty cell and put a tic there.
    for (int i = 0; i < brd.length; i++) {
      for (int j = 0; j < brd[0].length; j++) {
        if (brd[i][j] == ' ') { // empty cell
          brd[i][j] = csym;
          return;
        }
      }
    }
  }

  public static boolean isGameWon(char[][] brd, int turn, char usym, char csym)
  {
    char sym;
    if (turn == 0)
      sym = usym;
    else
      sym = csym;

    int i, j;
    boolean win = false;

    // Check win by a row
    for (i = 0; i < brd.length && !win; i++) {
      for (j = 0; j < brd[0].length; j++) {
        if (brd[i][j] != sym)
          break;
      }
      if (j == brd[0].length)
        win = true;
    }

    // Check win by a column
    for (j = 0; j < brd[0].length && !win; j++) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][j] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    // Check win by a diagonal (1)
    if (!win) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][i] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    // Check win by a diagonal (2)
    if (!win) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][brd.length - 1 - i] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    // Finally return win
    return win;
  }
  
}