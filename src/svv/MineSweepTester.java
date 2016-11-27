package svv;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import svv.GameConstant.Winner;
import svv.GameConstant.cellState;

public class MineSweepTester extends BoardState{
	public MineSweeper mineSweeper;
	public JButton[][] myjb;
	public int[][] mymap;
	public int[][] mymap2;
	public cellState[] states = {cellState.Zero,cellState.One,cellState.Two,cellState.Three,cellState.Four,cellState.Five,cellState.Six,cellState.Seven,cellState.Eight,cellState.Nine,cellState.Empty};

	public MineSweepTester(){
		mineSweeper = new MineSweeper("hello");
		mineSweeper.init();
		myjb = mineSweeper.jb;
		mymap2 = mineSweeper.map2;
		for (int i=0; i<10 ; i++) {
			for(int j=0; j<10; j++) {
				//String content = myjb[i][j].getText();
				int num2 = mymap2[i][j];
//				if(myjb[i][j].isEnabled() && myjb[i][j].getText().equals("")){
//					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
//				}
				if(num2 == 9){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Nine));
				}else{
					if(num2 == 0){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Zero));
					}else if(num2 == 1){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.One));
					}else if(num2 == 2){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Two));
					}else if(num2 == 3){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Three));
					}else if(num2 == 4){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Four));
					}else if(num2 == 5){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Five));
					}else if(num2 == 6){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Six));
					}else if(num2 == 7){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Seven));
					}else if(num2 == 8){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Eight));
					}
//						else if(content.equals("flag")){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Flag));
//					}
				}
			}
		}

	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i=0; i<10 ; i++) {
			for(int j=0; j<10; j++) {
				if(mymap2[i][j] == 9){
					cnt++;
				}
			}
		}
		if(cnt == 10){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Winner getWinner() {
		// TODO Auto-generated method stub
		for (int i=0; i<10 ; i++) {
			for(int j=0; j<10; j++) {
				if (matrix.get(new Point(i,j)).getState()==cellState.Nine) {
					return Winner.Second;
				}else if(matrix.get(new Point(i,j)).getState()==cellState.Empty){
					return Winner.Neither;
				}
			}
		}
		return Winner.First;
	}

	@Override
	public BoardState getInitState() {
		// TODO Auto-generated method stub
		rows = 10;
		cols = 10;
		numOfPlayer = 1;
		statePerCell = 12;
		w = Winner.Neither;
		return this;
	}

	@Override
	public BoardState initBoardConstant() {
		mineSweeper = new MineSweeper("hello");
		mineSweeper.init();
		myjb = mineSweeper.jb;
		mymap2 = mineSweeper.map2;
		for (int i=0; i<10 ; i++) {
			for(int j=0; j<10; j++) {
				//String content = myjb[i][j].getText();
				int num2 = mymap2[i][j];
				if(myjb[i][j].isEnabled() && myjb[i][j].getText().equals("")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
				}
				if(num2 == 9){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Nine));
				}else{
					if(num2 == 0){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Zero));
					}else if(num2 == 1){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.One));
					}else if(num2 == 2){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Two));
					}else if(num2 == 3){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Three));
					}else if(num2 == 4){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Four));
					}else if(num2 == 5){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Five));
					}else if(num2 == 6){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Six));
					}else if(num2 == 7){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Seven));
					}else if(num2 == 8){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Eight));
					}
//						else if(content.equals("flag")){
//						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Flag));
//					}
				}
			}
		}
	return this;
	}

	@Override
	public List<Move> getPossibleMove() {
		// TODO Auto-generated method stub
		List<Move> res = new ArrayList<>();
		for (int i=0; i<10; i++) {
			for (int j = 0; j < 10; j++) {
				if(myjb[i][j].isEnabled()){
					if(myjb[i][j].getText().equals("1")){
					res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.One)));
					}else if(myjb[i][j].getText().equals("2")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Two)));
					}else if(myjb[i][j].getText().equals("3")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Three)));
					}else if(myjb[i][j].getText().equals("4")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Four)));
					}else if(myjb[i][j].getText().equals("5")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Five)));
					}else if(myjb[i][j].getText().equals("6")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Six)));
					}else if(myjb[i][j].getText().equals("7")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Seven)));
					}else if(myjb[i][j].getText().equals("8")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Eight)));
					}else if(myjb[i][j].getText().equals("9")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Nine)));
					}else if(myjb[i][j].getText().equals("")){
						res.add(new Move(turn,new Cell(i, j, GameConstant.cellState.Zero)));
					}
				}
			}
		}
		return res;
	}

}