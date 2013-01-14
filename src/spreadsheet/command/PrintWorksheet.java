package spreadsheet.command;

import spreadsheet.Application;

public final class PrintWorksheet
    extends Command {

  public void execute() {
    Application.instance.printWorksheet();
  }

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

}
