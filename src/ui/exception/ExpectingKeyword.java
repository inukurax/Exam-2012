package ui.exception;

public final class ExpectingKeyword extends InvalidInput {

  public final static long serialVersionUID = 1L;

  public ExpectingKeyword() {
    super("Expecting a keyword.");
  }

}
