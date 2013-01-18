package spreadsheet;

import java.util.ArrayList;

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
	 * @param a assumes non null Position
	 * @param b assumes non null Position
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
	 * columnMax * rowMax 
	 * @return ArrayList<Position> of all Positions in the Range
	 */
	public ArrayList<Position> getPositionsInRange() {
		if (posList == null) {
			posList = new ArrayList<Position>(this.columnMax * this.rowMax);
			addPositions();
		}
		return posList;	
	}

	/**
	 * Adds all positions in a Range to an ArrayList.
	 * - adds every column on a row, then the same on next row.
	 */
	private void addPositions() {
		int row = rowMin;
		int column = columnMin;
		while (row <= rowMax) {
			if (column <= columnMax) {
				posList.add(new Position(column, row));
				column++;
			}
			else {
				column = columnMin;
				row++;
			}
		}
	}
	
	/**
	 * Check if Range only holds one Position
	 * @return true if Range holds only one Position, otherwise false.
	 */
	public boolean isOnePosition() {
		return this.posA.isEqualTo(posB);
	}
	
	/**
	 * Accessor method for getting the first Position
	 * used to construct the Range
	 * @return
	 */
	public Position getPosA() {
			return posA;
	}
	
	/**
	 * Accessor method for getting the second Position
	 * used to construct the Range
	 * @return
	 */
	public Position getPosB() {
		return posB;
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
	/**
	 * Overrides the equal method. Two Range' is set to be equal if their
	 * positions has the same rows and columns.
	 */
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

	/**
	 * Accessor method for getting the number of columns in the Range
	 * @return int representing columns in the Range
	 * Guaranteed return > 0
	 */
	public int getColumnCount() {
		int count = (this.columnMax  + 1) - this.columnMin;
		return count;
	}
	
	/**
	 * Accessor method for getting the number of rows in the Range
	 * @return int representing rows in the Range
	 */
	public int getRowCount() {
		int count = (this.rowMax + 1) - this.rowMin;
		return count;
	}
}
