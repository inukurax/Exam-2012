package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import spreadsheet.Observer;
import spreadsheet.Position;

/** The main frame status bar.
 * <p>
 * Meant for showing errors and the current cell selection.
 */
public final class StatusView
    extends JPanel {

  public static final long serialVersionUID = 1L;

  public static final StatusView instance = new StatusView();
  
  public final ErrorView errorView;
  public final PositionView positionView;

  private StatusView() {
    super();
    // This is a singleton.
    this.setLayout(new BorderLayout());
    this.errorView = new ErrorView();
    this.positionView = new PositionView();
    
    this.setMinimumSize(new Dimension(0, 20));
    
    this.add(this.errorView, BorderLayout.CENTER);
    this.add(this.positionView, BorderLayout.LINE_END);
  }
  
  public final class ErrorView
      extends JLabel
      implements Observer<Exception> {
    private static final long serialVersionUID = 1L;

    public ErrorView() {
      this.setForeground(Color.red);
    }
    
    @Override
    public void notify(final Exception exception) {
      final String message = exception.getMessage();
      java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
          ErrorView.this.setText(message);
        }
        
      });
    }
    
    public void clear() {
      java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
          ErrorView.this.setText("");
        }
        
      });
    }
  }
  
  private final class PositionView
      extends JLabel
      implements Observer<Position> {
    private static final long serialVersionUID = 1L;
    private final static String NO_SELECTION = "-";
    
    public PositionView() {
      super(NO_SELECTION);
    }
    
    @Override
    public void notify(final Position position) {
      final String description = this.getDescription(position);
      java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
          PositionView.this.setText(description);
        }
        
      });
    }
    
    private String getDescription(final Position position) {
      if (position == null) {
        return NO_SELECTION;
      }
      return position.getDescription();
    }
    
  }

}