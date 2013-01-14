package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;

public final class Set
    extends Command {

  private final Position position;
  private final Expression expression;
private Position pos;
private Expression currentExp;

  public Set(final Position position, final Expression expression) {
    this.position = position;
    this.expression = expression;
    currentExp = Application.instance.get(pos);
  }

  public void execute() {
    Application.instance.set(position, expression);
  }

	@Override
	public void undo() {
		Application.instance.set(position, this.currentExp);
	}

}
