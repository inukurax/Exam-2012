package spreadsheet.command;

import spreadsheet.Application;

public final class NewSpreadsheet
    extends Command {

  public void execute() {
    Application.instance.forceNewSpreadsheet();
  }
  
}
