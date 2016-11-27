package svv;

import java.awt.Point;
import java.util.Map;

import javax.swing.JButton;

import svv.GameConstant.Winner;

public class MineSweepSocket implements GameSocket {
	private MineSweepTester mineSweepTester;
//	public MineSweepSocket(){
//		mineSweepTester = new MineSweepTester();
//	}
//	public MineSweepSocket(MineSweeper _mineSweeper){
//		mineSweepTester = new MineSweepTester(_mineSweeper);
//	}
	public BoardState getBoard() {
		// TODO Auto-generated method stub
		return mineSweepTester;
	}
//	public void synBoard(){
//		JButton[][] myButton = mineSweepTester.mineSweeper.jb;
//		int[][] myMap2 = mineSweepTester.mineSweeper.map2;
//		int[][] myMap3 = mineSweepTester.mineSweeper.map3;
//		Map matrix=mineSweepTester.matrix;
//		for (int i=0; i<10 ; i++) {
//			for(int j=0; j<10; j++) {
//				//String content = myjb[i][j].getText();
//				int number = myMap2[i][j];
//				if(myButton[i][j].isEnabled() && myButton[i][j].getText().equals("")){
//					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
//				}
//				if(number == 9){
//					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Nine));
//				}else{
//					if(number == 0){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Zero));
//					}else if(number == 1){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.One));
//					}else if(number == 2){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Two));
//					}else if(number == 3){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Three));
//					}else if(number == 4){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Four));
//					}else if(number == 5){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Five));
//					}else if(number == 6){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Six));
//					}else if(number == 7){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Seven));
//					}else if(number == 8){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Eight));
//					}
//				}
//			}
//		}
//	}


	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		mineSweepTester.mineSweeper.startNewGame();
	//	synBoard();
	}


	@Override
	public void makeMove(Move m) {
		// TODO Auto-generated method stub
		int r = m.update.row;
		int l = m.update.col;
		GameConstant.cellState state = m.update.getState();
		mineSweepTester.mineSweeper.jb[r][l].setEnabled(true);
		if(state == GameConstant.cellState.Empty){
			int nums = mineSweepTester.mineSweeper.search(r,l);
			mineSweepTester.mineSweeper.jb[r][l].setEnabled(false);
			mineSweepTester.mineSweeper.jb[r][l].setText(Integer.toString(nums));
		}
		if(state == GameConstant.cellState.Zero){
			mineSweepTester.mineSweeper.map2[r][l] = 0;
		}else if(state == GameConstant.cellState.One){
			mineSweepTester.mineSweeper.map2[r][l] = 1;
		}else if(state == GameConstant.cellState.Two){
			mineSweepTester.mineSweeper.map2[r][l] = 2;
		}else if(state == GameConstant.cellState.Three){
			mineSweepTester.mineSweeper.map2[r][l] = 3;
		}else if(state == GameConstant.cellState.Four){
			mineSweepTester.mineSweeper.map2[r][l] = 4;
		}else if(state == GameConstant.cellState.Five){
			mineSweepTester.mineSweeper.map2[r][l] = 5;
		}else if(state == GameConstant.cellState.Four){
			mineSweepTester.mineSweeper.map2[r][l] = 6;
		}else if(state == GameConstant.cellState.Six){
			mineSweepTester.mineSweeper.map2[r][l] = 7;
		}else if(state == GameConstant.cellState.Seven){
			mineSweepTester.mineSweeper.map2[r][l] = 7;
		}else if(state == GameConstant.cellState.Eight){
			mineSweepTester.mineSweeper.map2[r][l] = 8;
		}else if(state == GameConstant.cellState.Mine){
			mineSweepTester.mineSweeper.map2[r][l] = 9;
		}


	//	synBoard();

	}

	@Override
	public Winner getWinner() {
		// TODO Auto-generated method stub
		int res = mineSweepTester.mineSweeper.getWinner();
		switch (res) {
			case 0: return Winner.Neither;
			case 1: return Winner.First;
			case 2: return Winner.Second;
		}
		return mineSweepTester.getWinner();
	}

}