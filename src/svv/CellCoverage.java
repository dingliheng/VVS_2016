package svv;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import svv.GameConstant.cellState;

import static svv.GameConstant.cellState.Empty;

public class CellCoverage {
	int allstate = 100;
	int curVisited = 0;
	Set<Point> Points;

	//Set<Cell> visited;
	HashMap<Point, Set<cellState>> visited;

	CellCoverage(BoardState bs){
		//visited = new HashSet<Cell>();
		visited = new HashMap<>();
		Points = new HashSet<Point>();
		int numOfSlot = bs.getEmptyPoints().size();
		allstate = numOfSlot*(bs.statePerCell-1);
		for(Point p: bs.getEmptyPoints()){
			Points.add(p);
			Set<cellState> states = new HashSet<>();
			visited.put(p, states);
		}
	}

	public void update(BoardState bs){
//		for(Point coor : bs.matrix.keySet()){
//			if(Points.contains(coor) && visited.add(bs.matrix.get(coor))){
//				curVisited++;
//			}
//		}
		for (Point coor: Points) {
			Cell cell = bs.matrix.get(coor);
			if (cell.getState()!=Empty && visited.get(coor).add(cell.getState())) {
				curVisited++;
			}

		}
	}

	public float getCoverage(){
		return (float) curVisited / allstate;
	}

}
