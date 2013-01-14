package spreadsheet.expression;

import spreadsheet.Expression;
import spreadsheet.Path;
import spreadsheet.Type;
import spreadsheet.exception.CycleException;

public abstract class UnaryExpression
    extends Expression {

  protected final Expression alpha;

  protected UnaryExpression(
      final Type type,
      final Expression alpha) {
    super(type);
    this.alpha = alpha;
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    this.alpha.checkAcyclic(path);
  }

  public String getDescription() {
    return String.format("%s %s",
      this.getClass().getSimpleName(),
      this.alpha.getDescription()
    );
  }

}
