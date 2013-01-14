package gui.control;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import spreadsheet.command.NewSpreadsheet;

/** A listener for the new spreadsheet menu item.
 * <p>
 * No need for more than one, hence the singleton implementation.
 */
public final class NewSpreadsheetListener
    implements ActionListener {

  public static final NewSpreadsheetListener instance = new NewSpreadsheetListener();

  private NewSpreadsheetListener() {
    // This is a singleton.
  }

  public void actionPerformed(ActionEvent event) {
    new NewSpreadsheet().execute();
  }

}
