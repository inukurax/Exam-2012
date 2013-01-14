package ui.exception;

public final class IllegalStartOfExpression
    extends Exception {

  public final static long serialVersionUID = 1L;

  /* Assumes that keyword is not null, and not empty. */
  public IllegalStartOfExpression(final String keyword) {
    super(String.format(
      "Illegal start of expression \"%s\".", keyword));
  }

  public IllegalStartOfExpression() {
    super("Missing expression.");
  }

}
