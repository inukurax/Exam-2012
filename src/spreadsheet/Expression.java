package spreadsheet;

import spreadsheet.exception.CycleException;
import spreadsheet.exception.InvalidReference;
import spreadsheet.textual.Text;

public abstract class Expression {

  private final Type type;

  protected Expression(final Type type) {
    this.type = type;
  }

  /** Evaluates the expression. */
  public boolean toBoolean() {
    return this.type.toBoolean(this);
  }

  /** Evaluates the expression. */
  public int toInt() {
    return this.type.toInt(this);
  }

  /** Evaluates the expression.
   *
   * Guaranteed to be not null. */
  public String toString() {
    return this.type.toString(this);
  }

  public abstract void checkAcyclic(final Path path)
    throws CycleException;

  public abstract String getDescription();
  
  public boolean refersTo(final Spreadsheet spreadsheet) {
    return false;
  }
  
  public Expression copy(final int columnOffset, final int rowOffset) 
		  throws InvalidReference {
	  Position pos = Application.instance.getCurrentPosition();
	  Position newPos = new Position(pos.getColumn() + columnOffset, pos.getRow() + rowOffset);
	  if (newPos.getColumn() < 0 || newPos.getRow() < 0)
		  throw new InvalidReference();
	  Application.instance.set(pos, new Text(""));
	  Application.instance.set(newPos, this);
	  return this;
  }
}
