package spreadsheet.exception;

import spreadsheet.Path;

public final class CycleException
    extends Exception {

  public static final long serialVersionUID = 1L;

  public CycleException(final Path path) {
    super(String.format("Cycle: %s.", path.toString()));
  }

}
