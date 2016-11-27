package svv;

import java.awt.Point;
import java.util.Map;

import svv.GameConstant.Winner;
import javax.swing.*;

public class MineSweepSocket implements GameSocket{

	private MineSweepTester mineSweepTester;

	public MineSweepSocket(){
		mineSweepTester = new MineSweepTester();
	}

	public MineSweepSocket(MineSweepTester _mineSweeperTest){
		mineSweepTester = _mineSweeperTest;
	}

	public void synBoard(){
		JButton[][] mybutton = mineSweepTester.mineSweeper.jb;
		int[][] mymap = mineSweepTester.mineSweeper.map;
		Map matrix=mineSweepTester.matrix;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j< 10; j++){
				String s = mybutton[i][j].getText();
				int m = mymap[i][j];
				if(mymap[i][j] != 1) {
					if (s.equals("")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Empty));
					} else if (s.equals("1")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.One));
					} else if (s.equals("2")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Two));
					} else if (s.equals("3")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Three));
					} else if (s.equals("4")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Four));
					} else if (s.equals("5")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Five));
					} else if (s.equals("6")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Six));
					} else if (s.equals("7")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Seven));
					} else if (s.equals("8")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Eight));
					}
				}else if(mymap[i][j] == 1){//没有点开的雷
					if(s.equals("")) {
						matrix.put(new Point(i, j), new Cell(i, j, GameConstant.cellState.Mine));
					}
				}
			}
		}
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
		synBoard();
	}

	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int c = m.update.col;

		GameConstant.cellState state = m.update.getState();
		mineSweepTester.matrix.get(new Point(r,c)).setState(state);

			if(state == GameConstant.cellState.One){
				mineSweepTester.mineSweeper.jb[r][c].setText("1");
			}else if(state == GameConstant.cellState.Two){
				mineSweepTester.mineSweeper.jb[r][c].setText("2");
			}else if(state == GameConstant.cellState.Three){
				mineSweepTester.mineSweeper.jb[r][c].setText("3");
			}else if(state == GameConstant.cellState.Four){
				mineSweepTester.mineSweeper.jb[r][c].setText("4");
			}else if(state == GameConstant.cellState.Five){
				mineSweepTester.mineSweeper.jb[r][c].setText("5");
			}else if(state == GameConstant.cellState.Six){
				mineSweepTester.mineSweeper.jb[r][c].setText("6");
			}else if(state == GameConstant.cellState.Seven){
				mineSweepTester.mineSweeper.jb[r][c].setText("7");
			}else if(state == GameConstant.cellState.Eight){
				mineSweepTester.mineSweeper.jb[r][c].setText("8");
			}else if(state == GameConstant.cellState.Mine){
				mineSweepTester.mineSweeper.jb[r][c].setText("mine");
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