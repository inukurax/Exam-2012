package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Position;

public final class Get
    extends Command {

  private final Position position;

  public Get(final Position position) {
    this.position = position;
  }

  public void execute() {
    Application.instance.showExpression(this.position);
  }

}
