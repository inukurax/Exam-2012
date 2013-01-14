package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

/** Models an addition of two other expressions
 */
public final class Add
    extends BinaryExpression {

  /** Constructs a new Add expression with two other expressions as
   * arguments
   */
  public Add(
      final Expression alpha,
      final Expression beta) {
    super(ArithmeticType.instance, alpha, beta);
  }

  @Override
  public int toInt() {
    return this.alpha.toInt() + this.beta.toInt();
  }

}
