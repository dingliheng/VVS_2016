package svv;

import java.awt.Frame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.naming.InsufficientResourcesException;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import svv.GameConstant.Player;
import svv.GameConstant.Winner;
import svv.GameConstant.cellState;

public class TicTacToeTester extends BoardState {
	
	public int turn;
	
	
	public TicTacToe_dual tictactoe;
	public char[][] myboard;
	public cellState[] states = {cellState.Circle, cellState.Cross, cellState.Empty};
	
	public TicTacToeTester(char players, char _userSymbol, char goFirst){
		
		tictactoe = new TicTacToe_dual(players, _userSymbol, goFirst);
		myboard = tictactoe.board;
		
		if(goFirst=='y'){turn=0;}
		else turn=1;
	
	for(int i =0; i<3; i++){
		for(int j=0; j<3; j++){
			char current_char = myboard[i][j];
				if (current_char==' '){
					matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Empty));}
					else if (current_char=='x'){
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Cross));
					}else if (current_char=='o'){
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Circle));
					}
		}

	}
	}
	
	

	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		
		int number_of_circle=0;
		int number_of_cross=0;
		
		for (int i=0;i<3;i++){
			for(int j=0; j<3;j++){
				
				if(matrix.get(new Point(i, j)).getState() == GameConstant.cellState.Circle){
					number_of_circle = number_of_circle + 1;
				}
				else if (matrix.get(new Point(i, j)).getState()==GameConstant.cellState.Cross){
					number_of_cross = number_of_cross + 1;
				}
			}
		}
		
		
		if (tictactoe.ans=='y'){
			if(tictactoe.turn==0){
				if(tictactoe.userSymbol=='x'){
					if(number_of_cross != number_of_circle + 1)		return false;
				}
				else if(tictactoe.userSymbol=='o'){
					if(number_of_circle != number_of_cross + 1)		return false;
				}
			}else {
				if(number_of_circle != number_of_cross)			return false;
			}		
		}
		
		else{
			if(tictactoe.turn==0){
				if(number_of_circle != number_of_cross)			return false;
			}else {
				if(tictactoe.userSymbol=='x'){
					if(number_of_cross + 1 != number_of_circle)			return false; 
				}else if(tictactoe.userSymbol=='o') {
					if(number_of_circle + 1 != number_of_cross)			return false;
						
					
				}
				
			}
		}
		
		
		
		
		return true;
	}

	@Override
	public Winner getWinner() {
		// TODO Auto-generated method stub
		
		Winner winner_of_game = Winner.Neither;
		
		boolean someone_wins = false;
		int win=-1;			//at the beginning, we set win is -1 means that no one wins
		char protenial_winner_char;
		int i,j;
		
		cellState protenial_winner_state;
		
		if (tictactoe.turn==0)	protenial_winner_char=tictactoe.userSymbol;
		else  protenial_winner_char = tictactoe.compSymbol;
		
		if(protenial_winner_char=='x')	protenial_winner_state = GameConstant.cellState.Cross;
		else protenial_winner_state = GameConstant.cellState.Circle;
		
		
	//	System.out.println("remaincount" + tictactoe.remainCount);
		
		//for horizontal direction
		for(i=0;i<3 && !someone_wins;i++){
			for(j=0; j<3;j++){
				if(matrix.get(new Point(i,j)).getState() != protenial_winner_state) 	break;		
			}if( j ==3){
				someone_wins=true;
			}
		}
		
		
		//for vertical direction
		for (j = 0; j < 3 && !someone_wins; j++) {
		      for (i = 0; i < 3; i++) {
		        if (matrix.get(new Point(i,j)).getState() != protenial_winner_state)
		          break;
		      }
		      if (i == 3)
		        someone_wins = true;
		    }
		
		
		//for diagonal
		
		if (!someone_wins) {
		      for (i = 0; i < 3; i++) {
		        if (matrix.get(new Point(i,i)).getState() != protenial_winner_state)
		          break;
		      }
		      if (i == 3)
		        someone_wins = true;
		    }

		    // for inverse diagonal
		    if (!someone_wins) {
		      for (i = 0; i < 3; i++) {
		        if (matrix.get(new Point(i,3-1-i)).getState() != protenial_winner_state)
		          break;
		      }
		      if (i == 3)
		        someone_wins = true;
		    }
		
		if(someone_wins){
			if(tictactoe.turn==0){
				winner_of_game = Winner.First;
			}else {
				winner_of_game = Winner.Second;
			}
			
		}
		
		else if(!someone_wins && (tictactoe.remainCount==0)) 	winner_of_game = Winner.Draw;
		
		
		
		
		
		
		return winner_of_game;
	}

	
	
	
	@Override
	public BoardState getInitState() {
		// TODO Auto-generated method stub
		for (int i=0; i <3; i++){
			for(int j=0;j<3;j++){
				matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Empty));
			}
		}
		
		
		
		
		return this;
	}

	@Override
	public BoardState initBoardConstant() {
		// TODO Auto-generated method stub
		rows=3;
		cols=3;
		numOfPlayer=2;				
		statePerCell = 3;
		w = Winner.Neither;
		
		return this;
	}

	
	
	
	@Override
	public List<Move> getPossibleMove() {
		// TODO Auto-generated method stub
		ArrayList<Move>  possible_move = new ArrayList<Move>();
		char current_symbol;
		cellState current_cell_state_symbol;
		Player current_player;			//current player or next player?
		
		if(turn ==1){
			current_symbol = tictactoe.userSymbol;
			current_player = Player.First;
		}else{
			current_symbol = tictactoe.compSymbol;
			current_player = Player.Second;
		} 
		
		if(current_symbol == 'x'){
			current_cell_state_symbol = cellState.Cross;
		}else if(current_symbol == 'o'){
			current_cell_state_symbol = cellState.Circle;
		}else 
			current_cell_state_symbol = cellState.Empty;
		
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(matrix.get(new Point(i,j)).getState() == cellState.Empty){
					//System.out.println("have a empty pot");
					Move current_one_possible_move = new Move(current_player, new Cell(i, j, current_cell_state_symbol));
					possible_move.add(current_one_possible_move);
				}
			}
		}
		
		
		turn = (turn+1)%2;
		
		//System.out.println("possible move size" + possible_move.size());
		
		
		
		
		return possible_move;
	}

}
