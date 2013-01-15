package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;

public final class Set
    extends Command implements Change {

  private final Expression expression;
  private final Position position;
  private Expression lastExp;
  
  public Set(final Expression expression) {
		 this.expression = expression;
		 this.position = null;
		 this.lastExp = Application.instance.get();
  }

  public Set(final Position position, final Expression expression) {
      this.position = position;
	  this.expression = expression;
	  this.lastExp = Application.instance.get(position);
}
  @Override
  public void execute() {
	if (this.position == null)
		 Application.instance.set(expression);
	else 
		 Application.instance.set(position, expression);

	 History.instance.push(this);
  }

	@Override
	public void undo() {
		if (this.position == null)
			Application.instance.set(this.lastExp);
		else
			Application.instance.set(position, this.lastExp);
	}

	@Override
	public String getDescription() {
		Position location = (position == null) ? 
				Application.instance.getCurrentPosition()
				: position;		
		return String.format("Undo: Set %s at %s", expression.getDescription(),
				location.getDescription());
	}
}
