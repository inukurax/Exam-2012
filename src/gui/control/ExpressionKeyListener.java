package gui.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.ExpressionView;
import gui.StatusView;
import gui.TabbedView;

import spreadsheet.Application;
import spreadsheet.command.Set;
import ui.ExpressionInterpreter;

/** A listener for the Enter key in the {@link gui.ExpressionView}.
 *  changes: now also repaints TabbedView
 *  and uses Set() Command for setting Expression
 */
public class ExpressionKeyListener implements KeyListener {

  @Override
  public void keyPressed(KeyEvent event) {
    if (event.getKeyCode() != KeyEvent.VK_ENTER) {
      return;
    }
    StatusView.instance.errorView.clear();
    try {
      new Set(ExpressionInterpreter.interpret(
    		  ExpressionView.instance.getText())).execute();
      TabbedView.instance.repaint();
    } catch (Exception e) {
      Application.instance.reportError(e);
    }
  }

  @Override
  public void keyReleased(KeyEvent _) {
    // Wait.
  }

  @Override
  public void keyTyped(KeyEvent _) {
    // Wait.
  }

}
