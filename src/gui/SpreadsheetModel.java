package gui;

import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;

import spreadsheet.Expression;
import spreadsheet.Spreadsheet;
import spreadsheet.Position;

final class SpreadsheetModel
    implements TableModel {

  private final Spreadsheet spreadsheet;

  public SpreadsheetModel(final Spreadsheet spreadsheet) {
    this.spreadsheet = spreadsheet;
  }

  public void addTableModelListener(TableModelListener listener) {
    return;
  }

  public Class<?> getColumnClass(int columnIndex) {
    return Expression.class;
  }

  public int getColumnCount() {
    return this.spreadsheet.getMaxColumn();
  }

  public String getColumnName(int columnIndex) {
    return null;
  }

  public int getRowCount() {
    return this.spreadsheet.getMaxRow();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return this.spreadsheet.get(new Position(columnIndex, rowIndex));
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  public void removeTableModelListener(TableModelListener listener) {
    throw new UnsupportedOperationException();
  }

  public void setValueAt(Object value, int rowIndex, int columnIndex) {
    throw new UnsupportedOperationException();
  }

}
