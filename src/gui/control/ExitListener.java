package gui.control;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import spreadsheet.command.Exit;

/** A listener for the exit menu item.
 * <p>
 * No need for more than one, hence the singleton implementation.
 */
public final class ExitListener
    implements ActionListener {

  public static final ExitListener instance = new ExitListener();

  private ExitListener() {
    // This is a singleton.
  }

  public void actionPerformed(ActionEvent event) {
    new Exit().execute();
  }

}
