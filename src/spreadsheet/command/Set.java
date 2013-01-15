package spreadsheet.command;

import gui.TabbedView;
import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;

public final class Set
    extends Command implements Change {

  private final Expression expression;
  private final Position position;
  private final Expression lastExp;
  
  /**
   * Set command for GUI
   * set Expression at Position in current worksheet.
   * @param expression to set at current position
   */
  public Set(final Expression expression) {
		 this.expression = expression;
		 this.position = Application.instance.getCurrentPosition();
		 this.lastExp = Application.instance.get();
  }

  /**
   * Set command for Cli
   * set Expression at Position in current worksheet.
   * @param position to set at.
   * @param expression to set.
   */
  public Set(final Position position, final Expression expression) {
      this.position = position;
	  this.expression = expression;
	  this.lastExp = Application.instance.get(position);
}
  @Override
  public void execute() {
	  Application.instance.set(position, expression);
	  History.instance.push(this);
  }

	@Override
	public void undo() {
			Application.instance.set(position, this.lastExp);
		    Application.instance.showExpression(position);
		    TabbedView.instance.repaint();
	}

	@Override
	public String getDescription() {
		return String.format("Undo: Set %s at %s", expression.getDescription(),
				position.getDescription());
	}
}
