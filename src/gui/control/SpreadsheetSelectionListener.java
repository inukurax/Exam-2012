package gui.control;

import gui.SpreadsheetView;
import gui.StatusView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;

/** A listener for changing selections in {@link gui.SpreadsheetView}.
 */
public final class SpreadsheetSelectionListener
    implements ListSelectionListener {
  
  private final SpreadsheetView view;
  
  public SpreadsheetSelectionListener(final SpreadsheetView view) {
    this.view = view;
  }

  public void valueChanged(ListSelectionEvent event) {
    if (event.getValueIsAdjusting()) {
      return;
    }
    
    StatusView.instance.errorView.clear();

    final int[] selectedRows = view.getSelectedRows();
    final int[] selectedColumns = view.getSelectedColumns();

    final Position position =
      new Position(selectedColumns[0], selectedRows[0]);
    
    final Position position2 =
    	      new Position(selectedColumns[selectedColumns.length - 1], 
    	    		  selectedRows[selectedRows.length - 1]);
    final Range range = new Range(position, position2);
    	  
    Application.instance.setCurrentRange(range);
    Application.instance.setCurrentPosition(position);
    Application.instance.showCurrentExpression();
  }
}
