package gui;

import gui.control.TabChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import spreadsheet.Application;
import spreadsheet.Observer;
import spreadsheet.Spreadsheet;

/** The center view of the main frame.
 * <p>
 * Represents a tabbed pane with spreadsheets.
 */
public final class TabbedView
    extends JTabbedPane {

  public static final long serialVersionUID = 1L;

  public static final TabbedView instance = new TabbedView();

  public final NewSpreadsheetObserver newSpreadsheetObserver;
  public final RemoveSpreadsheetObserver removeSpreadsheetObserver;
  
  private TabbedView() {
    super();
    this.newSpreadsheetObserver = new NewSpreadsheetObserver();
    this.removeSpreadsheetObserver = new RemoveSpreadsheetObserver();
    this.tabPlacement = BOTTOM;
    for (final Spreadsheet spreadsheet : Application.instance.getSpreadsheets()) {
      this.addTab(spreadsheet);  
    }
    this.addChangeListener(TabChangeListener.instance);
  }

  private void addTab(final Spreadsheet spreadsheet) {
    final JScrollPane scrollPane = new JScrollPane(new SpreadsheetView(spreadsheet));
    
    super.addTab(spreadsheet.getName(), scrollPane);
  }
  
  public final class NewSpreadsheetObserver implements Observer<Spreadsheet> {

    @Override
    public void notify(Spreadsheet spreadsheet) {
      TabbedView.this.addTab(spreadsheet);
    }
    
  }
  
  public final class RemoveSpreadsheetObserver implements Observer<Spreadsheet> {

    @Override
    public void notify(Spreadsheet spreadsheet) {
      final int index = TabbedView.this.indexOfTab(spreadsheet.getName());
      if (index == -1) {
        return;
      }
      TabbedView.this.removeTabAt(index);
    }
    
  }

}
