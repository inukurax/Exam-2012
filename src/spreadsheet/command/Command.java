package spreadsheet.command;

import spreadsheet.Change;

public abstract class Command implements Change {

  public abstract void execute();

}
