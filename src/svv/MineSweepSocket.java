package svv;

import java.awt.Point;

import svv.GameConstant.Winner;

public class MineSweepSocket implements GameSocket,Configs{

	private MineSweepTester mineSweepTester;

	public MineSweepSocket(){
		mineSweepTester = new MineSweepTester();
	}

	public MineSweepSocket(MineSweepTester _mineSweeperTest){
		mineSweepTester = _mineSweeperTest;
	}

	@Override
	public BoardState getBoard() {
		// TODO Auto-generated method stub
		return mineSweepTester;
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		MineSweepTester.mineSweeper.init();
	}

	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int l = m.update.col;

		GameConstant.cellState state = m.update.getState();
		mineSweepTester.matrix.get(new Point(r,l)).setState(state);

		for (int i=0;i<=12;i++) {
			if( state == mineSweepTester.states[i]) {
				MineSweepTester.mineSweeper.jb[r][l].setText(String.valueOf(i));
				break;
			}
		}
	}

	@Override
	public Winner getWinner() {
		// TODO Auto-generated method stub
		int res = MineSweepTester.mineSweeper.getWinner();
		switch (res) {
			case 0: return Winner.Neither;
			case 1: return Winner.First;
			case 2: return Winner.Second;
		}
		return mineSweepTester.getWinner();

	}

}