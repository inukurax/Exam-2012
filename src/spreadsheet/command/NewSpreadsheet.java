package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.History;
import spreadsheet.Spreadsheet;
import spreadsheet.exception.OutcastReferenced;

public final class NewSpreadsheet
    extends Command implements Change {
	
  private Spreadsheet sheet;

  public void execute() {
	  sheet = Application.instance.forceNewSpreadsheet();
	    History.instance.push(this);
  }
	
	@Override
	public void undo() {
		try {
			Application.instance.removeSpreadsheet(sheet);
		} catch (OutcastReferenced e) {
			Application.instance.reportError(e.getMessage());
		}
	}

	@Override
	public String getDescription() {
		return "Undo: New Spreadsheet";
	} 
}
