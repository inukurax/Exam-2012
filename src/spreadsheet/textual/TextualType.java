package spreadsheet.textual;

import spreadsheet.Expression;
import spreadsheet.Type;

final class TextualType extends Type {

  public static final TextualType instance = new TextualType();

  private TextualType() {
    // This is a singleton.
  }

  /* Assumes that expression overrides toString(). */
  public int toInt(final Expression expression) {
    return expression.toString().length();
  }

  /* Assumes that expression overrides toString(). */
  public boolean toBoolean(final Expression expression) {
    return expression.toString().length() > 0;
  }

  /* Assumes that expression overrides toString(). */
  public String toString(final Expression expression) {
    return expression.toString();
  }

}
