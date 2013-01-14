package spreadsheet.exception;

public final class SpreadsheetAlreadyExists extends Exception {

  public static final long serialVersionUID = 1L;

  public SpreadsheetAlreadyExists(final String name) {
    super(String.format("Spreadsheet \"%s\" already exists.", name));
  }

}
