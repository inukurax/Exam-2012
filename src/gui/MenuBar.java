package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.language.Language;

import gui.control.CopyListener;
import gui.control.ExitListener;
import gui.control.NewSpreadsheetListener;
import gui.control.PasteListener;
import gui.control.PlotListener;
import gui.control.RemoveSpreadsheetListener;
import gui.control.UndoListener;

/** The main frame menu bar.
 * <p>
 * The main frame has just one menu bar, hence the singleton implementation.
 */
public final class MenuBar
    extends JMenuBar {

  public static final long serialVersionUID = 1L;

  public static final MenuBar instance = new MenuBar();

  private MenuBar() {
    super();
    this.add(this.newFileMenu());
    this.add(this.newEditMenu());
    this.add(this.newSpreadsheetMenu());
    this.add(this.newDataMenu());

  }

  private JMenu newFileMenu() {
    final JMenu menu = new JMenu(Language.instance.file());
    menu.add(this.newExitMenuItem());
    return menu;
  }
  
  private JMenu newEditMenu() {
	    final JMenu menu = new JMenu(Language.instance.edit());
	    menu.add(this.newUndoMenuItem());
	    menu.add(this.newCopyMenuItem());
	    menu.add(this.newPasteMenuItem());
	    return menu;
	  }

  private JMenu newDataMenu() {
    final JMenu menu = new JMenu(Language.instance.data());
    menu.add(this.newPlotMenuItem());
    return menu;
  }
  
  private JMenuItem newPlotMenuItem() {
	    final JMenuItem menuItem = new JMenuItem(Language.instance.plot());
	    menuItem.addActionListener(PlotListener.instance);
	    return menuItem;
	  }
  
  private JMenuItem newUndoMenuItem() {
	    final JMenuItem menuItem = new JMenuItem(Language.instance.undo());
	    menuItem.addActionListener(UndoListener.instance);
	    return menuItem;
  }
  
  private JMenuItem newCopyMenuItem() {
	    final JMenuItem menuItem = new JMenuItem(Language.instance.copy());
	    menuItem.addActionListener(CopyListener.instance);
	    return menuItem;
	  }
  
  private JMenuItem newPasteMenuItem() {
	    final JMenuItem menuItem = new JMenuItem(Language.instance.paste());
	    menuItem.addActionListener(PasteListener.instance);
	    return menuItem;
	  }
  
  private JMenuItem newExitMenuItem() {
    final JMenuItem menuItem = new JMenuItem(Language.instance.exit());
    menuItem.addActionListener(ExitListener.instance);
    return menuItem;
  }
  
  private JMenu newSpreadsheetMenu() {
    final JMenu menu = new JMenu(Language.instance.spreadsheet());
    menu.add(this.newNewSpreadsheetMenuItem());
    menu.add(this.newRemoveSpreadsheetMenuItem());
    return menu;
  }
  
  private JMenuItem newNewSpreadsheetMenuItem() {
    final JMenuItem menuItem = new JMenuItem(Language.instance.newSpreadsheet());
    menuItem.addActionListener(NewSpreadsheetListener.instance);
    return menuItem;
  }
  
  private JMenuItem newRemoveSpreadsheetMenuItem() {
    final JMenuItem menuItem = new JMenuItem(Language.instance.removeSpreadsheet());
    menuItem.addActionListener(RemoveSpreadsheetListener.instance);
    return menuItem;
  }

}
