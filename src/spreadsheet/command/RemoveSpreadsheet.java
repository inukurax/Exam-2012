package spreadsheet.command;

import gui.StatusView;
import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.History;
import spreadsheet.Spreadsheet;
import spreadsheet.exception.OutcastReferenced;
import spreadsheet.exception.SpreadsheetAlreadyExists;

public final class RemoveSpreadsheet
    extends Command implements Change {
	
	private Spreadsheet lastSheet;

  public void execute() {
	  	StatusView.instance.errorView.setText("");
	  lastSheet = Application.instance.getWorksheet();
    try {
      Application.instance.removeSpreadsheet();
      History.instance.push(this);
    } catch (OutcastReferenced e) {
      Application.instance.reportError(e);
    }
  }

	@Override
	public void undo() {
		try {
			Application.instance.addSpreadsheet(lastSheet);
		} catch (SpreadsheetAlreadyExists e) {
		      Application.instance.reportError(e);
		}
	}

	@Override
	public String getDescription() {
		return "Undo: Removed Spreadsheet";
	}
}
