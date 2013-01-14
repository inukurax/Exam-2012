package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.language.Language;

import gui.control.ExitListener;
import gui.control.NewSpreadsheetListener;
import gui.control.RemoveSpreadsheetListener;

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
    this.add(this.newSpreadsheetMenu());
  }

  private JMenu newFileMenu() {
    final JMenu menu = new JMenu(Language.instance.file());
    menu.add(this.newExitMenuItem());
    return menu;
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
