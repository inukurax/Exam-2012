package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.Expression;
import spreadsheet.Position;

public final class Set
    extends Command implements Change {

  private final Position position;
  private final Expression expression;
  private Expression lastExp;

  public Set(final Position position, final Expression expression) {
    this.position = position;
    this.expression = expression;
  }

  public void execute() {
	 this.lastExp = Application.instance.get(position);
    Application.instance.set(position, expression);
  }

	@Override
	public void undo() {
		Application.instance.set(position, this.lastExp);
	}
}
