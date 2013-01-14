package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.exception.NoSuchSpreadsheet;

public final class ChangeWorksheet
    extends Command {

  private final String name;
  private final String current;


  /* Assumes that name is not null. */
  public ChangeWorksheet(final String name) {
	this.current =Application.instance.getWorksheet().getName();
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

	@Override
	public void undo() {
		// should not be able to undo changeWorksheet
		// should always be current tab.
	}

}
