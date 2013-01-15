package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.Spreadsheet;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.OutcastReferenced;
import spreadsheet.exception.SpreadsheetAlreadyExists;

public final class RemoveSpreadsheet
    extends Command implements Change {
	
	private Spreadsheet lastSheet;

  public void execute() {
	  lastSheet = Application.instance.getWorksheet();
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
			sheet.setName(lastSheet.getName());
			Application.instance.changeWorksheet(sheet.getName());
		} catch (SpreadsheetAlreadyExists e) {
		      Application.instance.reportError(e);
		} catch (NoSuchSpreadsheet e) {
		      Application.instance.reportError(e);
		}
	}
}
