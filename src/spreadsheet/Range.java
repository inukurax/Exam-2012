package spreadsheet;

import java.util.ArrayList;

final public class Range {
	
	private int rowMax;
	private int rowMin;
	private int columnMax;
	private int columnMin;
	private ArrayList<Position> posList;

	/**
	 * Construct a immutable object Range which holds the corners of
	 * two positions
	 * @param a non null Position
	 * @param b non null Position
	 * @throws IllegalPosition 
	 */
	public Range(final Position a, final Position b) {
		rowMin = Math.min(a.getRow(), b.getRow()); 
		rowMax = Math.max(a.getRow(), b.getRow()); 
		columnMin = Math.min(a.getColumn(), b.getColumn()); 
		columnMax = Math.max(a.getColumn(), b.getColumn()); 
	}
	
	/**
	 * Returns a list of positions. 
	 * Creates a new list if it hasn't been used before
	 * @return ArracolumnList<Position> of all Positions in ranges
	 */
	public ArrayList<Position> getPositionsInRange() {
		if (posList == null) {
			posList = new ArrayList<Position>();
			addPositions();
		}
		return posList;	
	}

	/**
	 * Adds all position in a Range to posList
	 */
	private void addPositions() {
		int row = rowMin;
		int column = columnMin;
		while (column <= columnMax) {
			if (row <= rowMax) {
				posList.add(new Position(column, row));
				row++;
			}
			else {
				row = rowMin;
				column++;
			}
		}
	}
}
