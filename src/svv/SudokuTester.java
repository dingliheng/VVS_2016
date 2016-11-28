package svv;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sun.invoke.empty.Empty;
import svv.GameConstant.Winner;
import svv.GameConstant.cellState;

import javax.swing.*;


public class SudokuTester extends BoardState{
	//
	public Sudoku sudoku;
	public JTextField[][] myBoard;
	public cellState[] states = {cellState.One,cellState.Two,cellState.Three,cellState.Four,cellState.Five,cellState.Six,cellState.Seven,cellState.Eight,cellState.Nine, cellState.Zero};
	public SudokuTester(int emptySlots) {
		//System.out.println("sssss");
		sudoku = new Sudoku(emptySlots);
		myBoard = sudoku.tfCells;
		for (int i=0; i<9 ; i++) {
			for(int j=0; j<9; j++) {
				String content = myBoard[i][j].getText();
				if (content.equals("")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
				}else {
					matrix.put(new Point(i,j),new Cell(i,j, states[Integer.parseInt(content)-1]));
				}
			}
		}
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		for(int i = 0; i<9; i++){
			HashSet<cellState> rows = new HashSet<cellState>();
			HashSet<cellState> columns = new HashSet<cellState>();
			HashSet<cellState> cube = new HashSet<cellState>();
			for (int j = 0; j < 9;j++){
				if(matrix.get(new Point(i,j)).getState() != cellState.Empty) {
					//System.out.println(myBoard[i][j].getText());
					//System.out.println("sfsdf");
//					int num = Integer.parseInt(myBoard[i][j].getText());
//					if (num < 0 || num > 9) return false;
					if (!rows.add(matrix.get(new Point(i,j)).getState()))
						return false;
//					num = Integer.parseInt(myBoard[j][i].getText());
//					if (num < 0 || num > 9) return false;
//					if (!columns.add(num))
//						return false;
					if (!columns.add(matrix.get(new Point(j,i)).getState()))
						return false;
				}
				int RowIndex = 3*(i/3);
				int ColIndex = 3*(i%3);
//				if((!myBoard[RowIndex + j/3][ColIndex + j%3].getText().equals(""))) {
				if (matrix.get(new Point(RowIndex+j/3,ColIndex + j%3)).getState()!=cellState.Empty) {
//					int num = Integer.parseInt(myBoard[RowIndex + j / 3][ColIndex + j % 3].getText());
//					if (num < 0 || num > 9) return false;
					if (!cube.add(matrix.get(new Point(RowIndex+j/3,ColIndex + j%3)).getState()))
						return false;
				}
			}
		}
		return true;
	}

	@Override
	public
	Winner getWinner() {
		// TODO Auto-generated method stub
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				//if(myBoard[i][j].getText().equals("")) {
				if (matrix.get(new Point(i,j)).getState()==cellState.Empty) {
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
	public
	BoardState getInitState() {
		// TODO Auto-generated method stub
		//  with various difficulty levels.
		//SudokuTester res = new SudokuTester();
		//res.initBoardConstant();
		for (int i=0; i<9 ; i++) {
			for(int j=0; j<9; j++) {
				String content = myBoard[i][j].getText();
				if (content.equals("")){
					matrix.put(new Point(i,j),new Cell(i,j, GameConstant.cellState.Empty));
				}else {
					matrix.put(new Point(i,j),new Cell(i,j, states[Integer.parseInt(content)-1]));
				}
			}
		}
		return this;
	}

	@Override
	public BoardState initBoardConstant() {
		// TODO Auto-generated method stub
		rows = 9;
		cols = 9;
		numOfPlayer = 1;
		statePerCell = 10;
		w = Winner.Neither;
		return this;
	}

	@Override
	public List<Move> getPossibleMove() {
		// TODO Auto-generated method stub
		List<Move> res = new ArrayList<>();
		Set<cellState> set = new HashSet<cellState>();
		for (int i=1;i<=9;i++) {
			set.add(states[i-1]);
		}
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (matrix.get(new Point(i,j)).getState()==cellState.Empty) {
					for (int k=0; k<9; k++) {
						res.add(new Move(turn,new Cell(i, j, states[k])));
					}
				}
			}
		}
		/*
		for(int i = 0; i<9; i++){
			for (int j = 0; j < 9;j++){
				//if(myBoard[i][j].getText().equals("")) {
				if (matrix.get(new Point(i,j)).getState()==cellState.Empty) {
					Set<cellState> elems = new HashSet<>(set);
					for (int k=0;k<9;k++) {
						//if (!myBoard[i][k].getText().equals("")) {
						if (matrix.get(new Point(i,k)).getState()!=cellState.Empty) {
							//int elem = Integer.parseInt(myBoard[i][k].getText());
							elems.remove(matrix.get(new Point(i,k)).getState());
						}
					}
					for (int k=0;k<9;k++) {
						//if (!myBoard[k][j].getText().equals("")) {
						if (matrix.get(new Point(k,j)).getState()!=cellState.Empty) {
							//int elem = Integer.parseInt(myBoard[k][j].getText());
							elems.remove(matrix.get(new Point(k,j)).getState());
						}
					}
					int row = i/3*3;
					int col = j/3*3;
					for (int r=0;r<3;r++) {
						for (int l=0;l<3;l++) {
							//if (!myBoard[row+r][col+l].getText().equals("")) {
							if (matrix.get(new Point(row+r,col+l)).getState()!=cellState.Empty) {
								//int elem = Integer.parseInt(myBoard[row+r][col+l].getText());
								elems.remove(matrix.get(new Point(row+r,col+l)).getState());
							}
						}
					}
//					for (int newNum: elems) {
//						res.add(new Move(turn, new Cell(i,j,states[newNum-1])));
//					}
					for (cellState cellState: elems) {
						res.add(new Move(turn, new Cell(i,j,cellState)));
					}
				}
			}
		}*/
		return res;
	}

}