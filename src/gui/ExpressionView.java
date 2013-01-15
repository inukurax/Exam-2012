package gui;

import gui.control.ExpressionKeyListener;

import javax.swing.JTextField;

import spreadsheet.Expression;
import spreadsheet.Observer;

/** An expression observer.
 */
public final class ExpressionView
    extends JTextField
    implements Observer<Expression> {

  public static final long serialVersionUID = 1L;

  public static final ExpressionView instance = new ExpressionView();

  private ExpressionView() {
    // This is a singleton.
    this.addKeyListener(new ExpressionKeyListener());
  }

  @Override
  public void notify(Expression expression) {
    final String value = expression.getDescription();

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        ExpressionView.this.setText(value);
      }
    });
  }
  
  public void clearText() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        ExpressionView.this.setText("");
      }
    });
  }

}
