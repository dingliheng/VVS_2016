package svv;

import java.awt.Point;
import java.util.Map;
import java.util.PrimitiveIterator;

import javax.rmi.CORBA.Tie;

import svv.GameConstant.Winner;

public class TicTacToeSocket implements GameSocket{
	
	public TicTacToeTester tictactoetester;
	
	
	
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
		
		tictactoetester.tictactoe.remainCount = tictactoetester.tictactoe.remainCount - 1 ;
		//tictactoetester.tictactoe.turn = (tictactoetester.tictactoe.turn + 1) % 2 ;
		
		
		
		
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
		
		tictactoetester.tictactoe.turn = (tictactoetester.tictactoe.turn + 1) % 2 ;
		
		synboard();
		
	}

	@Override
	public Winner getWinner() {
		
		
		
		
		
		
		
		
		
		
		
		char [][] current_board = tictactoetester.tictactoe.board;
		int turn_in_get_winner = (tictactoetester.turn + 1) % 2;
		char usersym = 'x';
		char compsym = 'o';
		boolean is_game_won = tictactoetester.tictactoe.isGameWon(current_board, turn_in_get_winner, 'x', 'o');
		
		
		
		if(is_game_won){
			if(turn_in_get_winner==3)	return Winner.First;
			else if(turn_in_get_winner ==4)		return Winner.Second;
		}
		return tictactoetester.getWinner();
	//	else if(tictactoetester.tictactoe.remainCount!=0)	return Winner.Neither;
	//	else return Winner.Draw;
		//if((!is_game_won) && (tictactoetester.tictactoe.remainCount!=0)){return Winner.Neither;}
		//else if((!is_game_won) && (tictactoetester.tictactoe.remainCount==0)) return Winner.Draw;
		//else if (turn_in_get_winner==0){return Winner.First;}
		//else  return Winner.Second;
		//else return Winner.Draw;
//		return null;
		
		
	
	}

}
