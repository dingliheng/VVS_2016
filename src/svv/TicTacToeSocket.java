package svv;

import java.awt.Point;
import java.util.Map;
import java.util.PrimitiveIterator;

import javax.rmi.CORBA.Tie;

import svv.GameConstant.Winner;

public class TicTacToeSocket implements GameSocket{
	
	private TicTacToeTester tictactoetester;
	
	
	
	public TicTacToeSocket(char players, char _userSymbol, char goFirst) {
		tictactoetester = new TicTacToeTester(players, _userSymbol, goFirst);
	}
	
	
	public TicTacToeSocket(TicTacToeTester _tictactoetester){
		tictactoetester = _tictactoetester;
	}
	
	
	
	public void synboard(){
		char[][] myboard = tictactoetester.tictactoe.board;
		Map matrix = tictactoetester.matrix;
		
		for (int i=0; i<3; i++){
			for (int j=0;j<3;j++){
				char current_char = myboard[i][j];
				if(current_char == ' '){
					matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Empty));
				}else if (current_char == 'x'){
					matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Cross));
				}else {
					matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Circle));
				}
				
				
			}
			
			
		}
		
		
		
		
	}
	
	

	@Override
	public BoardState getBoard() {
		// TODO Auto-generated method stub
		return tictactoetester;
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		tictactoetester.tictactoe.resetBoard();
		synboard();
		}

	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int l = m.update.col;
		
		GameConstant.cellState state = m.update.getState();
		char current_state;
		
		if(state == GameConstant.cellState.Circle) {current_state = 'o';}
		else if (state ==  GameConstant.cellState.Cross){ current_state = 'x';}
		else current_state =' ';
		
		
		tictactoetester.tictactoe.board[r][l] = current_state;
		
		synboard();
		
	}

	@Override
	public Winner getWinner() {
		
		char [][] current_board = tictactoetester.tictactoe.board;
		int turn_in_get_winner = tictactoetester.tictactoe.turn;
		char usersym = tictactoetester.tictactoe.userSymbol;
		char compsym = tictactoetester.tictactoe.compSymbol;
		boolean is_game_won = tictactoetester.tictactoe.isGameWon(current_board, turn_in_get_winner, usersym, compsym);
		
		if(tictactoetester.tictactoe.remainCount==0){
		if(!is_game_won){return Winner.Neither;}
		else if (turn_in_get_winner==0){return Winner.First;}
		else return Winner.Second;
		
		}	else return null;
	
	}

}
