package spreadsheet.command;

import spreadsheet.Application;

public final class ListSpreadsheets
    extends Command {

  public void execute() {
    Application.instance.printSpreadsheets();
  }

}
