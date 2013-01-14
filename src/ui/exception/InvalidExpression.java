package ui.exception;

public final class InvalidExpression
    extends Exception {

  public static final long serialVersionUID = 1L;

  public InvalidExpression(final String message) {
    super(message);
  }

}
