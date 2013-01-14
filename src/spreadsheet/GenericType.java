package spreadsheet;

/** A generic type used by e.g. {@link spreadsheet.Reference}.
 *
 * A generic type, in contrast to a specific type, assumes that
 * all type conversion functions are overridden in the expression
 * type.
 */
final class GenericType extends Type {

  public static final GenericType instance = new GenericType();

  private GenericType() {
    // This is a singleton.
  }

  /* Assumes that expression overrides toInt. */
  public int toInt(final Expression expression) {
    return expression.toInt();
  }

  /* Assumes that expression overrides toBoolean. */
  public boolean toBoolean(final Expression expression) {
    return expression.toBoolean();
  }

  /* Assumes that expression overrides toString. */
  public String toString(final Expression expression) {
    return expression.toString();
  }

}
