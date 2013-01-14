package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.command.RemoveSpreadsheet;

/** A listener for the remove spreadsheet menu item.
 * <p>
 * No need for more than one, hence the singleton implementation.
 */
public final class RemoveSpreadsheetListener
    implements ActionListener {

  public static final RemoveSpreadsheetListener instance = new RemoveSpreadsheetListener();

  private RemoveSpreadsheetListener() {
    // This is a singleton.
  }

  public void actionPerformed(ActionEvent event) {
    new RemoveSpreadsheet().execute();
  }

}
