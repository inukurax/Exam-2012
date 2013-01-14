package spreadsheet.expression;

import spreadsheet.Expression;
import spreadsheet.Path;
import spreadsheet.Type;
import spreadsheet.exception.CycleException;

public abstract class BinaryExpression
    extends Expression {

  protected final Expression alpha, beta;

  protected BinaryExpression(
      final Type type,
      final Expression alpha,
      final Expression beta) {
    super(type);
    this.alpha = alpha;
    this.beta = beta;
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    this.alpha.checkAcyclic(path);
    this.beta.checkAcyclic(path);
  }

  public String getDescription() {
    return String.format("%s %s %s",
      this.getClass().getSimpleName(),
      this.alpha.getDescription(),
      this.beta.getDescription()
    );
  }

}
