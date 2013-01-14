import gui.ExpressionView;
import gui.MainFrame;
import gui.StatusView;
import gui.TabbedView;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import spreadsheet.Application;

/** A spreadsheet application with a graphical user interface (GUI). */
public final class GUI {

  private GUI() {
    // This class need not be instantiated.
  }

  /** Starts up the application with a GUI. 
   * @throws UnsupportedLookAndFeelException 
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   * @throws ClassNotFoundException */
  public static void main(String[] _)
      throws 
        ClassNotFoundException,
        InstantiationException,
        IllegalAccessException,
        UnsupportedLookAndFeelException {
    
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    
    Application.instance.showEvent.addObserver(ExpressionView.instance);
    Application.instance.errorEvent.addObserver(StatusView.instance.errorView);
    Application.instance.selectEvent.addObserver(StatusView.instance.positionView);
    Application.instance.newSpreadsheetEvent.addObserver(TabbedView.instance.newSpreadsheetObserver);
    Application.instance.removeSpreadsheetEvent.addObserver(TabbedView.instance.removeSpreadsheetObserver);
    
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        MainFrame.instance.setVisible(true);
      }
    });
  }

}
