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
	 * its creates with initial size of columnMax * rowMax (if rowMax not is 0)
	 * @return ArrayList<Position> of all Positions in the Range
	 */
	public List<Position> getPositionsInRange() {
		if (posList == null) {
			int r = (rowMax != 0) ? rowMax : 1;
			int c = (columnMax != 0) ? columnMax : 1;
			int capacity = r * c;
			posList = new ArrayList<Position>();
			Type type = rowMax > columnMax ? Type.ROW : Type.COLUMN;
			addPositions(type);
		}
		return posList;	
	}
	
	/**
	 * Creates a string to describe a Range (A0:B1)
	 * @return String of description of Range.
	 */
	public String getDescription() {
	    final StringBuilder builder = new StringBuilder();
		builder.append(this.posA.getDescription());
		builder.append(":");
		builder.append(this.posB.getDescription());
		
		return builder.toString();
	}

	/**
	 * Adds all positions in a Range to an ArrayList
	 * @param type of the largest, of either Column or Row,
	 * for more efficient method
	 */
	private void addPositions(Type type) {
		int row = rowMin;
		int column = columnMin;
		
		if (type.equals(Type.ROW)) {
			while (column <= columnMax) {
				if (row <= rowMax) {
					posList.add(new Position(column, row));
					row++;
				}
				else {
					if (trimSize(type))
						posList.trimToSize();
					row = rowMin;
					column++;
				}
			}
		}
		else {
			while (row <= rowMax) {
				if (column <= columnMax) {
					posList.add(new Position(column, row));
					column++;
				}
				else {
					if (trimSize(type))
						posList.trimToSize();
					column = columnMin;
					row++;
				}
			}
		}

	}
	
	public boolean trimSize(Type type) {
		if (type.equals(Type.COLUMN))
			return (columnMax > 1000);
		return (rowMax > 1000);
	}
	private enum Type {
		COLUMN, ROW
	}
}
