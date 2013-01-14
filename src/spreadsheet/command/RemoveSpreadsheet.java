package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.exception.OutcastReferenced;

public final class RemoveSpreadsheet
    extends Command {

  public void execute() {
    try {
      Application.instance.removeSpreadsheet();
    } catch (OutcastReferenced e) {
      Application.instance.reportError(e);
    }
  }

}
