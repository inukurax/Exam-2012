package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Spreadsheet;
import spreadsheet.exception.OutcastReferenced;

public final class NewSpreadsheet
    extends Command {
	
	private Spreadsheet sheet;

  public void execute() {
	  sheet = Application.instance.forceNewSpreadsheet();
  }

	@Override
	public void undo() {
		try {
			Application.instance.removeSpreadsheet(sheet);
		} catch (OutcastReferenced e) {
			Application.instance.reportError(e.getMessage());
		}
	}
  
}
