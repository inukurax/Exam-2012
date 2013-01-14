package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

/** Models the boolean operation "and"
 */
public final class And
    extends BinaryExpression {

  public And(
      final Expression alpha,
      final Expression beta) {
    super(LogicalType.instance, alpha, beta);
  }

  @Override
  public boolean toBoolean() {
    return this.alpha.toBoolean() && this.beta.toBoolean();
  }

}
