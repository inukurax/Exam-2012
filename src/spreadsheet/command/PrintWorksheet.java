package spreadsheet.command;

import spreadsheet.Application;

public final class PrintWorksheet
    extends Command {

  public void execute() {
    Application.instance.printWorksheet();
  }

}
