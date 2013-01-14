package spreadsheet.textual;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

/** Models a concatenation of two expressions
 */
public final class Concat
  extends BinaryExpression {

  /** Constructs a new concatenation
   *
   * @param alpha The first expression to be concatenated.
   * @param beta The other expression to be concatenated.
   */
  public Concat(
      final Expression alpha,
      final Expression beta) {
    super(TextualType.instance, alpha, beta);
  }

  @Override
  public String toString() {
    return this.alpha.toString() + this.beta.toString();
  }

}
