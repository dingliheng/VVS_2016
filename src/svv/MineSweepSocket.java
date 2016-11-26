package svv;

import java.awt.Point;

import svv.GameConstant.Winner;

public class MineSweepSocket implements GameSocket,Configs{

	private MineSweepTester mineSweepTester;

	public MineSweepSocket(MineSweeper _mineSweeper){
		mineSweepTester = new MineSweepTester(_mineSweeper);
	}

//	public MineSweepSocket(MineSweepTester _mineSweeperTest){
//		mineSweepTester = _mineSweeperTest;
//	}

	@Override
	public BoardState getBoard() {
		// TODO Auto-generated method stub
		return mineSweepTester;
	}

	public void synBoard() {

	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		MineSweepTester.mineSweeper.init();
		synBoard();
	}

	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int c = m.update.col;

		GameConstant.cellState state = m.update.getState();
		mineSweepTester.matrix.get(new Point(r,c)).setState(state);


		for (int i=0;i<3;i++) {
			if( state == mineSweepTester.states[i]) {
				if(state == GameConstant.cellState.Zero){//button unchecked
					MineSweepTester.mineSweeper.map[r][c] = 0;
					break;
				}else if(state == GameConstant.cellState.One){//there is a mine
					MineSweepTester.mineSweeper.map[r][c] = 1;
					break;
				}else if(state == GameConstant.cellState.Two){//button checked
					MineSweepTester.mineSweeper.map[r][c] = 2;
					break;
				}
			}
		}
		synBoard();
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