package gui.control;

import gui.SpreadsheetView;
import gui.StatusView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import spreadsheet.Application;
import spreadsheet.Position;

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

    if (selectedRows.length > 1 || selectedColumns.length > 1) {
      return;
    }

    final Position position =
      new Position(selectedColumns[0], selectedRows[0]);

    Application.instance.setCurrentPosition(position);
    Application.instance.showCurrentExpression();
  }

}
