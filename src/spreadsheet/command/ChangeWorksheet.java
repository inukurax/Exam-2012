package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.exception.NoSuchSpreadsheet;

public final class ChangeWorksheet
    extends Command {

  private final String name;

  /* Assumes that name is not null. */
  public ChangeWorksheet(final String name) {
    this.name = name;
  }

  public void execute() {
    try {
      Application.instance.changeWorksheet(this.name);
    }
    catch (NoSuchSpreadsheet e) {
      Application.instance.reportError(e.getMessage());
    }
  }

}
