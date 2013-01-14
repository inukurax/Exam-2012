package ui.exception;

public final class InvalidPosition
    extends Exception {

  public static final long serialVersionUID = 1L;

  public InvalidPosition(final String positionText) {
    super(String.format("Invalid position \"%s\".", positionText));
  }
  
}
