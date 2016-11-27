package svv;

import svv.GameConstant.Winner;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SudokuSocket implements GameSocket {
	private SudokuTester sudokuTester;

	public SudokuSocket(int emptySlots) {
		sudokuTester = new SudokuTester(emptySlots);
	}

	public SudokuSocket(SudokuTester _sudokuTester) {
		sudokuTester = _sudokuTester ;
	}
	@Override
	public BoardState getBoard() {
		// TODO Auto-generated method stub
		return sudokuTester;
	}

	public void synBoard() {
		JTextField[][] myBoard = sudokuTester.sudoku.tfCells;
		Map matrix=sudokuTester.matrix;
		for (int i=0; i<9 ; i++) {
			for(int j=0; j<9; j++) {
				String content = myBoard[i][j].getText();
				if (content.equals("")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
				}else {
					matrix.put(new Point(i,j),new Cell(i,j, sudokuTester.states[Integer.parseInt(content)-1]));
				}
			}
		}
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		//sudokuTester.
		sudokuTester.sudoku.startNewGame();
		synBoard();
	}

	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int l = m.update.col;
		//int num = 0;
		GameConstant.cellState state = m.update.getState();

		//sudokuTester.matrix.get(new Point(r,l)).setState(state);

		for (int i=0;i<9;i++) {
			if( state == sudokuTester.states[i]) {
				sudokuTester.sudoku.tfCells[r][l].setText(String.valueOf(i+1));
				break;
			}
		}
		synBoard();
	}

	@Override
	public Winner getWinner() {
		// TODO Auto-generated method stub
		int res = sudokuTester.sudoku.getWinner();
		switch (res) {
			case 0: return Winner.Neither;
			case 1: return Winner.First;
			case 2: return Winner.Second;
		}
		return sudokuTester.getWinner();
	}

}