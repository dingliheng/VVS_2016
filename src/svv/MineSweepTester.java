package svv;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;

import svv.GameConstant.Winner;
import svv.GameConstant.cellState;


public class MineSweepTester extends BoardState implements Configs{



	public static MineSweeper mineSweeper;
	public JButton[][] jb1;
	public cellState[] states = {cellState.One,cellState.Two,cellState.Three,cellState.Four,cellState.Five,cellState.Six,cellState.Seven,cellState.Eight,cellState.Nine,cellState.Flag,cellState.Zero,cellState.Empty};

	public MineSweepTester(){
		mineSweeper = new MineSweeper("Hello Miner");
		jb1 = mineSweeper.jb;
		for(int i = 0; i < MINE_SIZE; i++){
			for(int j = 0; j < MINE_SIZE; j++){
				String s = jb[i][j].getText();
//				System.out.println(jb[i][j].getText());
//				System.out.println("OK");
				if(s.equals("")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
				}else if(s.equals("mine")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Nine));
				}else if(s.equals("0")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Zero));
				}else if(s.equals("1")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.One));
				}else if(s.equals("2")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Two));
				}else if(s.equals("3")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Three));
				}else if(s.equals("4")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Four));
				}else if(s.equals("5")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Five));
				}else if(s.equals("6")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Six));
				}else if(s.equals("7")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Seven));
				}else if(s.equals("8")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Eight));
				}else if(s.equals("flag")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Flag));
				}
			}
		}
	}


	@Override
	public boolean isValid() {
		int count = 0;
		int d = 0;
		for(int i = 0; i < MINE_SIZE; i++){
			for(int j = 0; j < MINE_SIZE; j++){
				if(map[i][j] == MINE){
					count++;
				}
				if(jb[i][j].getText() != Integer.toString(mineSweeper.dfs(i, j, d++))){
					return false;
				}
			}
		}
		if(count != MINE_COUNT){
			return false;
		}

		return true;
	}

	@Override
	public Winner getWinner() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {

				if (matrix.get(new Point(i,j)).getState()==cellState.Nine) {
					return Winner.Second;
				}else if(jb[i][j].isEnabled()){
					return Winner.Neither;
				}
			}
		}
		if(!isValid()) {
			return Winner.Second;
		}else {
			return Winner.First;
		}
	}

	@Override
	public BoardState getInitState() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public BoardState initBoardConstant() {
		// TODO Auto-generated method stub
		rows = MINE_SIZE-1;
		cols = MINE_SIZE-1;
		numOfPlayer = 1;
		statePerCell = 12;
		w = Winner.Neither;
		return this;
	}

	@Override
	public List<Move> getPossibleMove() {
		// TODO Auto-generated method stub
		List<Move> res = new ArrayList<>();
		Set<cellState> set = new HashSet<cellState>();
		for (int i = 0;i <= 11; i++) {
			set.add(states[i]);
		}
		for (int i=0; i<MINE_SIZE; i++) {
			for (int j=0; j<MINE_SIZE; j++) {
				if (matrix.get(new Point(i,j)).getState()!=cellState.Nine && matrix.get(new Point(i,j)).getState()!=cellState.Flag) {
					for (int k = 0; k < 12; k++) {
						res.add(new Move(turn,new Cell(i, j, states[k])));
					}
				}
			}
		}
		return res;
	}

}
