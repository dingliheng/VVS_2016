package svv;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import svv.GameConstant.Winner;

public class Driver {

	static BoardState nextState(BoardState prev, Move _move) throws Exception {
		prev.makeMove(_move);
		return prev;
	}
	
//	static void genTests(String path, BoardState init) throws Exception{
//		
//		CellCoverage cc = new CellCoverage(init);
//		File f = new File(path);
//		FileWriter fw = new FileWriter(f.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		BoardState cur;
//		while(cc.getCoverage() < 1.0){
//			cur = init;
//			while(cur.getWinner() == Winner.Neither){
//				
//				List<Move> possibleMoves = cur.getPossibleMove();
//				Random r = new Random();
//				int movePicked = r.nextInt(possibleMoves.size());
//				Move curMove = possibleMoves.get(movePicked);
//				cur = nextState(cur, curMove);
//				cc.update(cur);
//				
//				bw.write(curMove.toString() + ", " + cur.toString() + "\n");
//			}
//			bw.write("\n");
//		}
//		bw.close();
//	}
	
	static float runTests(GameSocket gs, BoardState bs) throws Exception{
		
		int passCases = 0;
		int totalCases = 0;

		CellCoverage cc = new CellCoverage(bs.getInitState());
		BoardState cur;
        int rounds = 0;
		while(cc.getCoverage() < 1.0){
			cur = bs.getInitState();
			gs.startNewGame();
			rounds++;
			while(cur.getWinner() == Winner.Neither){
				
				List<Move> possibleMoves = cur.getPossibleMove();
				Random r = new Random();
				int movePicked = r.nextInt(possibleMoves.size());
				Move curMove = possibleMoves.get(movePicked);
				
				cur = nextState(cur, curMove);
				gs.makeMove(curMove);
				totalCases += 1;
				if(cur.cmp(gs.getBoard())){
					passCases += 1;
				}else {
					passCases += 0;
				}
				
				cc.update(cur);
			}
			
			totalCases += 1;
			if(cur.getWinner() == gs.getWinner()){
				passCases += 1;
			}else {
				Winner winner = cur.getWinner();
				Winner winner1 = gs.getWinner();
				passCases += 0;
			}
		}

		if(totalCases == 0){

			return 0;
		}
		System.out.println("Rounds: "+rounds);
		System.out.println("totalCases: "+totalCases);
		System.out.println("passedCases: "+passCases);
		System.out.println("failedCases: "+(totalCases-passCases));
		return passCases / totalCases;
		
	}

	public static void main(String[] args) throws Exception {
		
		//String path = "./";
		//genTests(path, new TicTacToeTester().getInitState());

	    System.out.println("---------------Sudoku Test---------------");
        for (int i=1;i<4;i++) {
            System.out.println("--------Test "+i+"--------");
            int emptySlots = 8*i;
            System.out.println("Empty Cells: "+ emptySlots);
            long start = System.currentTimeMillis();
            float testScore = runTests(new SudokuSocket(new SudokuTester(emptySlots)), new SudokuTester(emptySlots));
            long stop = System.currentTimeMillis();
            //System.out.println("Execution time: "+(float)(stop-start)/1000+"s");
            //System.out.println(testScore);
        }
        System.out.println();

        System.out.println("---------------MineSweeper Test ---------------");
        //MineSweeper _mineSweeper = new MineSweeper("hello");
        runTests(new MineSweepSocket(), new MineSweepTester());
        //	System.out.println(testScore);

        System.out.println();
        System.out.println("---------------TicTacToe Test---------------");
        for (int i=1;i<3;i++) {
            System.out.println("--------Test " + i + "--------");
            runTests(new TicTacToeSocket('2', 'x', 'y'), new TicTacToeTester('2', 'x', 'y'));
        }
//		System.out.println(testScore);
	}
	
}
