package spreadsheet.command;

import spreadsheet.Application;

public final class Exit
    extends Command {

  public void execute() {
    Application.instance.exit();
  }

}
