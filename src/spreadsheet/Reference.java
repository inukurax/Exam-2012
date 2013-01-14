package spreadsheet;

import spreadsheet.exception.CycleException;
import spreadsheet.textual.Text;

public final class Reference
    extends Expression {

  private final Spreadsheet spreadsheet;
  private final Position position;

  public Reference(final Spreadsheet spreadsheet, final Position position) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.position = position;
  }

  private Expression getExpression() {
    final Expression expression = this.spreadsheet.get(this.position);
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

  public String getDescription() {
    final String positionDescription = this.position.getDescription();
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
        otherReference.position.equals(this.position);
  }
  
  @Override
  public boolean refersTo(final Spreadsheet spreadsheet) {
    return this.spreadsheet.equals(spreadsheet);
  }

}
