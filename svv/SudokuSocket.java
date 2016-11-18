package svv;

import svv.GameConstant.Winner;

import java.awt.*;
import java.util.List;

public class SudokuSocket implements GameSocket {
	private SudokuTester sudokuTester;

	public SudokuSocket(SudokuTester _sudokuTester) {
		sudokuTester = _sudokuTester ;
	}
	@Override
	public BoardState getBoard() {
		// TODO Auto-generated method stub
		return sudokuTester;
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		//sudokuTester.
		sudokuTester.sudoku.startNewGame();
	}

	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int l = m.update.col;
		int num = 0;
		GameConstant.cellState state = m.update.getState();
		sudokuTester.matrix.get(new Point(r,l)).setState(state);
//		for (int i=1;i<=9;i++) {
//			if( state == sudokuTester.states[i-1]) {
//				num = i;
//				break;
//			}
//		}
		//sudokuTester.sudoku.tfCells[r][l].setText(String.valueOf(num));

	}

	@Override
	public Winner getWinner() {
		// TODO Auto-generated method stub
		return sudokuTester.getWinner();
	}

}
