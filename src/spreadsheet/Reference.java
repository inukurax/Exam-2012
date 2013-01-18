package spreadsheet;

import java.util.ArrayList;
import java.util.Iterator;

import spreadsheet.exception.CycleException;
import spreadsheet.exception.InvalidReference;
import spreadsheet.exception.RangeReferenceException;
import spreadsheet.textual.Text;

public final class Reference
    extends Expression implements Iterable<Expression> {

  private final Spreadsheet spreadsheet;
  private final Range range;
  private Position position;
  private boolean onePos;
  private boolean isSum = false;

  public Reference(final Spreadsheet spreadsheet, final Range range) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.range = range;
    this.onePos = range.isOnePosition();
    this.position = onePos ? range.getPosA() : 
    	Application.instance.getCurrentPosition();
  }
  
  public Reference(final Spreadsheet spreadsheet, final Position position) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.range = new Range(position, position);
    this.onePos = true;
    this.position = position;
  }
  
  /**
   * Fails when multiply cells is marked and user clicks on 
   * a cell with a reference expression thats marked.
   * 
   * 
   * @return first expression in the reference
   * @throws RangeReferenceException if reference is used on a range
   * or a more than one cell is marked
   *  
   */
  private Expression getExpression() {
	  boolean mouseMarksRange = !Application.instance
			  .getCurrentRange().isOnePosition();
	  if ((this.isRange() && !this.isSum()) || mouseMarksRange)
		  throw new RangeReferenceException();

	  final Expression expression = Application.instance.get(position);
	  if (expression == null) {
		  return new Text("");
	  }
	  return expression;
  }

  public boolean toBoolean() {
    return this.getExpression().toBoolean();
  }

  public int toInt() {
    return this.getExpression().toInt();
  }

  public String toString() {
    return this.getExpression().toString();
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    if (path.contains(this)) {
      throw new CycleException(new Path(this, path));
    }
    getExpression().checkAcyclic(new Path(this, path));
  }
  
  @Override
  /**
   * Copies an reference, and moves the reference Range Positions 
   * to match @param ColumnOffset and @param rowOffset
   */
  public Expression copy(final int columnOffset, final int rowOffset) 
		  throws InvalidReference {
	  final Position refPosA = this.position;
	  final Position newRefPosA = new Position(refPosA.getColumn() + columnOffset,
			  refPosA.getRow() + rowOffset);
	  final Position refPosB = range.getPosB();
	  final Position newRefPosB = new Position(refPosB.getColumn() + columnOffset,
			  refPosB.getRow() + rowOffset);
	  
	  if (newRefPosA.getColumn() < 0 || newRefPosA.getRow() < 0
			  || newRefPosB.getColumn() < 0 || newRefPosB.getRow() < 0)
		  throw new InvalidReference();
	  
	  final Range range = new Range(newRefPosA, newRefPosB);
	  final Reference ref = new Reference(spreadsheet, range);
	  return ref;
  }
  
  /**
   * Check if Reference got a Range or only a Position
   * @return true if it got more than one Position, otherwise false
   */
  public boolean isRange() {
	  return !this.onePos;
  }
  
  /**
   * Describes a Reference as A0:B9,
   * will use old notation if it hold only one Position.
   */
  @Override
  public String getDescription() {
	final String positionDescription = isRange() ? this.range.getDescription() 
			: this.position.getDescription();
    if (Application.instance.getWorksheet().equals(this.spreadsheet))
      return positionDescription;
    else
      return String.format("%s!%s", this.spreadsheet.getName(), 
    		  positionDescription);
  }
  
  // changed to compare two ranges instead of positions
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Reference)) {
      return false;
    }
    final Reference otherReference = (Reference)other;
    return
        otherReference.spreadsheet.equals(this.spreadsheet) &&
        otherReference.range.equals(range);
  }
  
  @Override
  public boolean refersTo(final Spreadsheet spreadsheet) {
    return this.spreadsheet.equals(spreadsheet);
  }
  
  /**
   * Accessor method for isSum
   * @return true if Reference is used in a Sum Expression
   */
  public boolean isSum() {
	return isSum;
	}
	
  	/**
  	 * Method for setting isSUm
  	 * @param isSum should be set true if reference is used in 
  	 * a sum Expression, otherwise false
  	 */
	public void setSum(boolean isSum) {
		this.isSum = isSum;
	}
	
	/**
	 * Gets the position refered to.
	 * @return the position in topLeft corner of the Range
	 */
	public Position getRefPosition() {
		return this.position;
	}
	
	/**
	 * Accessor method for getting the Range
	 * @return non null Range
	 */
	public Range getRefRange() {
		return this.range;
	}
	
	/**
	 * Accessor method for getting the spreadsheet refered to.
	 * @return
	 */
	public Spreadsheet getRefSpreadsheet() {
		return this.spreadsheet;
	}
	
	/**
	 * Runs through all expression on position that has a reference.
	 * adds them to an iterable list, guaranteed to contain no null expression.
	 */
	@Override
	public Iterator<Expression> iterator() {
		ArrayList<Expression> list = new ArrayList<Expression>();
		for (Position pos : range.getPositionsInRange()) {
			Expression exp = this.spreadsheet.get(pos);
			if (exp != null)
				list.add(exp);
			else
				list.add(new Text(""));
		}
		return list.iterator();
	}

}
