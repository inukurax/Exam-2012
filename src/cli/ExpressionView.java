package cli;

import spreadsheet.Expression;

import spreadsheet.Observer;

/** An expression observer.
 *
 * Prints the expression string evaluation to System.out.
 */
public final class ExpressionView
    implements Observer<Expression> {

  public final static ExpressionView instance = new ExpressionView();

  private ExpressionView() {
    // This is a singleton.
  }

  public void notify(final Expression expression) {
    System.out.println(expression.toString());
  }

}
