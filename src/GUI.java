import gui.ExpressionView;
import gui.MainFrame;
import gui.StatusView;
import gui.TabbedView;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.arithmetic.Add;
import spreadsheet.arithmetic.Int;
import spreadsheet.arithmetic.Sum;
import spreadsheet.textual.Concat;
import spreadsheet.textual.Text;

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
    
    // testing sets up start value.
	  Position posA = new Position(0,0);
	  Position posB = new Position(0,1);  
	  Position posC = new Position(1,0);
	  Position posD = new Position(1,1);
	  Position posE = new Position(2,0);
	  Position posF = new Position(2,1);

    Application.instance.set(posA, new Text("Kategori:"));
    Application.instance.set(posB, new Text("Point:"));
    Application.instance.set(posC, new Text("Musik"));
    Application.instance.set(posD, new Int(5));
    Application.instance.set(posE, new Text("Dans"));
    Application.instance.set(posF, new Int(10));

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        MainFrame.instance.setVisible(true);
      }
    });
  }

}
