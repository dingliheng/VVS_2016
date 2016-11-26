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


    int[][] map;
    /**
     * 检测某点周围是否有雷，周围点的坐标可由该数组计算得到
     */
    private int[][] mv = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
            { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static MineSweeper mineSweeper;
	public JButton[][] jb1;
	public int[][] map1;
	public cellState[] states = {cellState.Empty,cellState.One,cellState.Three,cellState.Flag,cellState.Mine};
	public MineSweepTester(MineSweeper _mineSweeper){
		mineSweeper = _mineSweeper;
//		jb1 = mineSweeper.jb;
		map1 = mineSweeper.map;
		for(int i = 0; i < MINE_SIZE; i++){
			for(int j = 0; j < MINE_SIZE; j++){
				if(map1[i][j] == 1){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.One));
				}else if(map1[i][j] == 2){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Two));
				}else if(map1[i][j] == 0){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Zero));
				}

			}
		}
	}


	@Override
	public boolean isValid() {
		int count = 0;
		for(int i = 0; i < MINE_SIZE; i++){
			for(int j = 0; j < MINE_SIZE; j++){
				if(map1[i][j] == 1){
					count++;
				}
				if(map1[i][j] == 2 && jb1[i][j].isEnabled()){
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
		for(int i=0;i<MINE_SIZE;i++) {
			for(int j=0;j<MINE_SIZE;j++) {

				if (matrix.get(new Point(i,j)).getState()==cellState.One) {
					return Winner.Second;
				}else if(matrix.get(new Point(i,j)).getState()==cellState.Zero){
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
		statePerCell = 3;
		w = Winner.Neither;
		return this;
	}

	@Override
    public void makeMove(Move move) throws Exception {

    }

	@Override
	public List<Move> getPossibleMove() {
		// TODO Auto-generated method stub
//		List<Move> res = new ArrayList<>();
//		Set<cellState> set = new HashSet<cellState>();
//		for (int i = 0;i < 3; i++) {
//			set.add(states[i]);
//		}
//		for (int i=0; i<MINE_SIZE; i++) {
//			for (int j=0; j<MINE_SIZE; j++) {
//				if (matrix.get(new Point(i,j)).getState()!=cellState.One && matrix.get(new Point(i,j)).getState()!=cellState.Flag) {
//					for (int k = 0; k < 12; k++) {
//						res.add(new Move(turn,new Cell(i, j, states[k])));
//					}
//				}
//			}
//		}
//		return res;
//	}

		List<Move> res = new ArrayList<>();
		Set<cellState> set = new HashSet<cellState>();
		for(int i = 0; i < 3; i++){
			set.add(states[i]);
		}
		for (int i=0; i<MINE_SIZE; i++) {
			for (int j=0; j<MINE_SIZE; j++) {
				if (matrix.get(new Point(i,j)).getState()==cellState.Zero){
					for (int k=0; k<2; k++) {
						res.add(new Move(turn,new Cell(i, j, states[k])));
					}
				}

			}
		}

		return res;
	}
}