package spreadsheet.exception;

public final class NoSuchSpreadsheet
    extends Exception {

  public static final long serialVersionUID = 1L;

  /* Assumes that name is not null. */
  public NoSuchSpreadsheet(final String name) {
    super(
      String.format("No such spreadsheet, \"%s\".", name));
  }

}
