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

	
	static float runTests(GameSocket gs, BoardState bs) throws Exception{
		
		int passCases = 0;
		int totalCases = 0;

		CellCoverage cc = new CellCoverage(bs.getInitState());
		BoardState cur;
        int rounds = 0;
        int passesCases=0;
		while(cc.getCoverage() < 1.0){
			cur = bs.getInitState();
			gs.startNewGame();
			//System.out.println("point 1");
			rounds++;
			
			//System.out.println("winner : " + cur.getWinner());
			
			while(cur.getWinner() == Winner.Neither){
				
				List<Move> possibleMoves = cur.getPossibleMove();
				//System.out.println("possible move size" + possibleMoves.size());
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
			//System.out.println("is game won "+ );
			if(cur.getWinner() == gs.getWinner()){
				passCases += 1;
			}else {
				Winner winner = cur.getWinner();
				Winner winner1 = gs.getWinner();
				passCases += 0;
			}
			passesCases += 1;
		}
        System.out.println("Rounds: "+rounds);
        System.out.println("totalCases: "+totalCases);
        System.out.println("passCases: "+passCases);
        System.out.println("failedCases: "+(totalCases-passCases));
		return passCases / totalCases;
		
	}

	public static void main(String[] args) throws Exception {
		
		//String path = "./";
		//genTests(path, new TicTacToeTester().getInitState());

	    System.out.println("---------------tictactoe Test---------------");
        for (int i=1;i<11;i++) {
            System.out.println("--------Test "+(i)+"--------");
            //int emptySlots = (int)Math.pow(2,i+1);
           // System.out.println("Empty Slots: "+ emptySlots);
            long start = System.currentTimeMillis();
//            float testScore = runTests(new SudokuSocket(new SudokuTester(emptySlots)), new SudokuTester(emptySlots));
            float testScore = runTests(new TicTacToeSocket(new TicTacToeTester('2','x','y')), new TicTacToeTester('2','x','y'));
            long stop = System.currentTimeMillis();
            //System.out.println("Execution time: "+(float)(stop-start)/1000+"s");
            //System.out.println(testScore);
        }

//		float testScore = runTests(new TicTacToeSocket('2','x','y'), new TicTacToeTester('2','x','y'));
//		System.out.println(testScore);
	}
	
}
