package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.UnaryExpression;

/** Models the boolean operation "not"
 */
public final class Not
    extends UnaryExpression {

  public Not(final Expression alpha) {
    super(LogicalType.instance, alpha);
  }

  @Override
  public boolean toBoolean() {
    return !this.alpha.toBoolean();
  }

  public String getDescription() {
    return String.format(
      "Not %s",
      this.alpha.getDescription());
  }

}
