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
    this.position = onePos ? range.getPosA() : Application.instance.getCurrentPosition();
  }
  
  public Reference(final Spreadsheet spreadsheet, final Position position) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.range = new Range(position, position);
    this.onePos = true;
    this.position = position;
  }

  private Expression getExpression() {
	  boolean mouseMarksRange = Application.instance
			  .getCurrentRange().isOnePosition();
	  if ((this.isRange() && !this.isSum()) || !mouseMarksRange)
		  throw new RangeReferenceException();
	  final Expression expression = iterator().next();
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
	  
	  final Reference ref = new Reference(spreadsheet,new Range(newRefPosA, newRefPosB));
	  System.out.println(ref.getDescription());
	  return ref;
  }
  
  /**
   * Check if Reference got a Range or only a Position
   * @return true if it got more than one Position, otherwise false
   */
  public boolean isRange() {
	  return !this.onePos;
  }

  @Override
  public String getDescription() {
	final String positionDescription = isRange() ? 
			this.range.getDescription()
			: this.position.getDescription();
			
    if (Application.instance.getWorksheet().equals(this.spreadsheet)) {
      return positionDescription;
    } else {
      return String.format("%s!%s",
        this.spreadsheet.getName(),
        positionDescription);
    }
  }
  
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

  public boolean isSum() {
	return isSum;
}

public void setSum(boolean isSum) {
	this.isSum = isSum;
}

@Override
  public Iterator<Expression> iterator() {
	  ArrayList<Expression> list = new ArrayList<Expression>();
	for (Position pos : range.getPositionsInRange()) {
		list.add(this.spreadsheet.get(pos));
	}
	return list.iterator();
  }

}
