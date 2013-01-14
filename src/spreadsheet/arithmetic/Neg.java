package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.expression.UnaryExpression;

/** Models a negation
 */
public final class Neg
    extends UnaryExpression {

  /** Constructs a Neg expression using alpha as argument
   */
  public Neg(final Expression alpha) {
    super(ArithmeticType.instance, alpha);
  }

  @Override
  public int toInt() {
    return -this.alpha.toInt();
  }

}
