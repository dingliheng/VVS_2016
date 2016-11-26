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
	public JButton[][] jb1 = mineSweeper.jb;
	public int[][] map1 = mineSweeper.map;
	public cellState[] states = {cellState.Zero,cellState.One,cellState.Two,cellState.Three,cellState.Four,cellState.Five,cellState.Six,cellState.Seven,cellState.Eight,cellState.Flag,cellState.Empty,cellState.Mine};
	public MineSweepTester(){
		mineSweeper = new MineSweeper("Hello Miner");
		//	map1 = mineSweeper.map;
		for(int i = 0; i < MINE_SIZE; i++){
			for(int j = 0; j < MINE_SIZE; j++){
				if(map1[i][j] != MINE){
					if(jb1[i][j].equals("0")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Zero));
					}else if(jb1[i][j].equals("1")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.One));
					}else if(jb1[i][j].equals("2")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Two));
					}else if(jb1[i][j].equals("3")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Three));
					}else if(jb1[i][j].equals("4")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Four));
					}else if(jb1[i][j].equals("5")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Five));
					}else if(jb1[i][j].equals("6")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Six));
					}else if(jb1[i][j].equals("7")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Seven));
					}else if(jb1[i][j].equals("8")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Eight));
					}
					else if(jb1[i][j].equals("")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
					}else if(jb1[i][j].equals("flag")){
						matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Flag));
					}
				}else if(map1[i][j] == MINE){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Mine));
				}

			}
		}
	}


	@Override
	public boolean isValid() {
		for(int i = 0; i < MINE_SIZE; i++){
			for(int j = 0; j < MINE_SIZE; j++){
				if(map1[i][j] != MINE){
					if(jb1[i][j].getText() != Integer.toString(mineSweeper.search(i,j))){
						return false;
					}
				}else{
					if(jb1[i][j].getText() != "mine"){
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public Winner getWinner() {
		for(int i=0;i<MINE_SIZE;i++) {
			for(int j=0;j<MINE_SIZE;j++) {

				if (matrix.get(new Point(i,j)).getState() == cellState.Mine) {
					return Winner.Second;
				}else {
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
		rows = MINE_SIZE;
		cols = MINE_SIZE;
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
		for(int i = 0; i < 12; i++){
			set.add(states[i]);
		}
		for (int i=0; i<MINE_SIZE; i++) {
			for (int j=0; j<MINE_SIZE; j++) {
				if (matrix.get(new Point(i,j)).getState()==cellState.Empty){
					for (int k=0; k<12; k++) {
						res.add(new Move(turn,new Cell(i, j, states[k])));
					}
				}

			}
		}

		return res;
	}
}