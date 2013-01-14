package gui.control;

import gui.TabbedView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import spreadsheet.command.ChangeWorksheet;

/** A listener for changing tabs.
 * <p>
 * No need for more than one, hence the singleton implementation.
 */
public final class TabChangeListener
    implements ChangeListener {

  public static final TabChangeListener instance = new TabChangeListener();

  private TabChangeListener() {
    // This is a singleton.
  }

  @Override
  public void stateChanged(ChangeEvent event) {
    final String title =
        TabbedView.instance.getTitleAt(TabbedView.instance.getSelectedIndex());
    new ChangeWorksheet(title).execute();
    
  }

}
