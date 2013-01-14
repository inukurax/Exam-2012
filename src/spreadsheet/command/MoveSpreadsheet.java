package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Spreadsheet;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.SpreadsheetAlreadyExists;

public final class MoveSpreadsheet
    extends Command {

  private final String originalName, newName;

  /* Assumes that name is not null. */
  public MoveSpreadsheet(
      final String originalName,
      final String newName) {
    this.originalName = originalName;
    this.newName = newName;
  }

  public void execute() {
    if (this.originalName.equals(this.newName)) {
      return;
    }

    final Application app = Application.instance;

    final Spreadsheet sheet = app.getSpreadsheet(originalName);

    if (sheet == null) {
      app.reportError(new NoSuchSpreadsheet(originalName));
    }

    if (app.getSpreadsheet(newName) != null) {
      app.reportError(new SpreadsheetAlreadyExists(newName));
    }

    sheet.setName(this.newName);
  }

}
