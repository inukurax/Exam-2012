package ui.exception;

public final class InvalidCommandException
    extends Exception {

  public static final long serialVersionUID = 1L;

  public InvalidCommandException(final String command) {
    super(
      String.format("Invalid command \"%s\".", command));
  }

}
