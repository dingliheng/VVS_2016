package svv;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class CellCoverage {
	int allstate = 0;
	int curVisited = 0;
	Set<Point> Points;

	Set<Cell> visited;

	CellCoverage(BoardState bs){
		visited = new HashSet<Cell>();
		Points = new HashSet<Point>();
		int numOfSlot = bs.getEmptyPoints().size();
		allstate = numOfSlot * (bs.statePerCell-1);
		for(Point p: bs.getEmptyPoints()){
			Points.add(p);
		}
	}

	public void update(BoardState bs){
		for(Point coor : bs.matrix.keySet()){
			if(Points.contains(coor) && visited.add(bs.matrix.get(coor))){
				curVisited++;
			}
		}
	}

	public float getCoverage(){
		return (float) curVisited / allstate;
	}


}
