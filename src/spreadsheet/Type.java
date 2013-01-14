package spreadsheet;

public abstract class Type {

  public abstract int toInt(final Expression expression);

  public abstract boolean toBoolean(final Expression expression);

  public abstract String toString(final Expression expression);

}
