package spreadsheet;

import java.util.ArrayList;

final public class Range {
	
	private int xMax;
	private int xMin;
	private int yMax;
	private int yMin;
	private ArrayList<Position> posList;

	public Range(final Position a, final Position b) {
		xMin = Math.min(a.getRow(), b.getRow()); 
		xMax = Math.max(a.getRow(), b.getRow()); 
		yMin = Math.min(a.getColumn(), b.getColumn()); 
		yMax = Math.max(a.getColumn(), b.getColumn()); 
		
		posList = new ArrayList<Position>();
		addPositions();
	}
	
	private void addPositions() {
		int x = xMin;
		int y = yMin;
		while (y <= yMax) {
			if (x <= xMax) {
				posList.add(new Position(x, y));
				x++;
			}
			else {
				x = xMin;
				y++;
			}
		}
	}
	
	public ArrayList<Position> getPositionsInRange() {
		return posList;	
	}

}
