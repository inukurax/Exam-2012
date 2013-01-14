package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Spreadsheet;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.OutcastReferenced;
import spreadsheet.exception.SpreadsheetAlreadyExists;

public final class RemoveSpreadsheet
    extends Command {
	
	private Spreadsheet current;

  public void execute() {
	  current = Application.instance.getWorksheet();
    try {
      Application.instance.removeSpreadsheet();
    } catch (OutcastReferenced e) {
      Application.instance.reportError(e);
    }
  }

	@Override
	public void undo() {
		try {
			Spreadsheet sheet = Application.instance.newSpreadsheet();
			Application.instance.changeWorksheet(sheet.getName());
		} catch (SpreadsheetAlreadyExists e) {
		      Application.instance.reportError(e);
		} catch (NoSuchSpreadsheet e) {
		      Application.instance.reportError(e);
		}
	}

}
