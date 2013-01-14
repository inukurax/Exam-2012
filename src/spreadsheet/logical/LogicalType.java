package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.Type;

final class LogicalType extends Type {

  public static final LogicalType instance = new LogicalType();

  private LogicalType() {
    // This is a singleton.
  }

  /* Assumes that expression overrides toBoolean(). */
  public int toInt(final Expression expression) {
    return expression.toBoolean() ? 1 : 0;
  }

  /* Assumes that expression overrides toBoolean(). */
  public boolean toBoolean(final Expression expression) {
    return expression.toBoolean();
  }

  /* Assumes that expression overrides toBoolean(). */
  public String toString(final Expression expression) {
    return Boolean.toString(expression.toBoolean());
  }

}
