package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;

public final class Set
    extends Command {

  private final Position position;
  private final Expression expression;

  public Set(final Position position, final Expression expression) {
    this.position = position;
    this.expression = expression;
  }

  public void execute() {
    Application.instance.set(position, expression);
  }

}
