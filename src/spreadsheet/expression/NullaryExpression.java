package spreadsheet.expression;

import spreadsheet.Expression;
import spreadsheet.Path;
import spreadsheet.Type;
import spreadsheet.exception.CycleException;

public abstract class NullaryExpression
    extends Expression {

  protected NullaryExpression(final Type type) {
    super(type);
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    return;
  }

}
