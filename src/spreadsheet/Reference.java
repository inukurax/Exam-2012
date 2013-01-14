package spreadsheet;

import java.util.ArrayList;
import java.util.Iterator;

import spreadsheet.exception.CycleException;
import spreadsheet.textual.Text;

public final class Reference
    extends Expression implements Iterable<Expression> {

  private final Spreadsheet spreadsheet;
  private final Range range;
  private Position position = null;
  private boolean onePos;

  public Reference(final Spreadsheet spreadsheet, final Range range) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.range = range;
    this.onePos = false;
  }
  
  public Reference(final Spreadsheet spreadsheet, final Position position) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.range = new Range(position, position);
    this.onePos = true;
    this.position = position;
  }

  private Expression getExpression() {
    final Expression expression = iterator().next();
    if (expression == null) {
      return new Text("");
    }
    return expression;
  }

  public boolean toBoolean() {
	  if (this.isRange())
		  throw new UnsupportedOperationException();
    return this.getExpression().toBoolean();
  }

  public int toInt() {
	  if (this.isRange())
		  throw new UnsupportedOperationException();
    return this.getExpression().toInt();
  }

  public String toString() {
	  if (this.isRange())
		  throw new UnsupportedOperationException();
    return this.getExpression().toString();
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    if (path.contains(this)) {
      throw new CycleException(new Path(this, path));
    }
    getExpression().checkAcyclic(new Path(this, path));
  }
  
  /**
   * Check if Reference got a Range or only a Position
   * @return true if it got more than one Position, otherwise false
   */
  public boolean isRange() {
	  return !this.onePos;
  }

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

  @Override
  public Iterator<Expression> iterator() {
	  ArrayList<Expression> list = new ArrayList<Expression>();
	for (Position pos : range.getPositionsInRange()) {
		list.add(this.spreadsheet.get(pos));
	}
	return list.iterator();
  }

}
