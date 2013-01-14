package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.Type;

final class ArithmeticType extends Type {

  public static final ArithmeticType instance = new ArithmeticType();

  private ArithmeticType() {
    // This is a singleton.
  }

  /* Assumes that expression overrides toInt(). */
  public int toInt(final Expression expression) {
    return expression.toInt();
  }

  /* Assumes that expression overrides toInt(). */
  public boolean toBoolean(final Expression expression) {
    return expression.toInt() != 0;
  }

  /* Assumes that expression overrides toInt(). */
  public String toString(final Expression expression) {
    return Integer.toString(expression.toInt());
  }

}
