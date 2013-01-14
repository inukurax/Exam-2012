package ui.exception;

public final class ExpectingString extends InvalidInput {

  public final static long serialVersionUID = 1L;

  public ExpectingString() {
    super("Expecting a string.");
  }

}
