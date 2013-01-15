package spreadsheet;

import java.util.ArrayList;
import java.util.List;

final public class Range {
	
	private int rowMax;
	private int rowMin;
	private int columnMax;
	private int columnMin;
	private Position posA;
	private Position posB;
	private ArrayList<Position> posList;

	/**
	 * Construct a immutable object Range which holds the corners of
	 * two positions
	 * @param a non null Position
	 * @param b non null Position
	 * @throws IllegalPosition 
	 */
	public Range(final Position a, final Position b) {
		this.rowMin = Math.min(a.getRow(), b.getRow()); 
		this.rowMax = Math.max(a.getRow(), b.getRow()); 
		this.columnMin = Math.min(a.getColumn(), b.getColumn()); 
		this.columnMax = Math.max(a.getColumn(), b.getColumn()); 
		this.posA = a;
		this.posB = b;
	}
	
	/**
	 * Returns a list of positions in the Range. 
	 * Creates a new list if it hasn't been used before
	 * its creates with initial size of 
	 * columnMax * rowMax (will ignore if row/columMax is 0)
	 * @return ArrayList<Position> of all Positions in the Range
	 */
	public List<Position> getPositionsInRange() {
		if (posList == null) {
			int r = (rowMax > 0) ? rowMax : 1;
			int c = (columnMax > 0) ? columnMax : 1;
			int capacity = r * c;
			posList = new ArrayList<Position>(capacity);
			addPositions();
		}
		return posList;	
	}

	/**
	 * Adds all positions in a Range to an ArrayList
	 * @param type of the largest, of either Column or Row,
	 * for more efficient method
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
	
	public boolean isOnePosition() {
		return this.posA.isEqualTo(posB);
	}
	
	public Position getStartPos() {
			return posA;
	}

	/**
	 * Creates a string to describe a Range "A0:B1"
	 * @return String of description of Range. 
	 * If Range only hold one Position, then it returns String like "A0"
	 */
	public String getDescription() {
	    if (posA.isEqualTo(posB))
	    	return posA.getDescription();
	    final StringBuilder builder = new StringBuilder();
		builder.append(this.posA.getDescription());
		builder.append(":");
		builder.append(this.posB.getDescription());
		
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || (!(other instanceof Range))) {
			return false;
	    }
	    final Range range = (Range)other;
	    return (range.columnMax == this.columnMax &&
	    		range.columnMin == this.columnMin &&
	    		this.rowMax == range.rowMax &&
	    		this.rowMin == range.rowMin);
	}
}
