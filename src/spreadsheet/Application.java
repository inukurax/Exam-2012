package spreadsheet;

import java.io.PrintWriter;
import java.util.ArrayList;

import spreadsheet.exception.CycleException;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.OutcastReferenced;
import spreadsheet.exception.SpreadsheetAlreadyExists;
import spreadsheet.textual.Text;

/** A singleton class representing a spreadsheet application.
 *
 * The instance is initialized on first mention of the class.
 */
public final class Application {

  public static final Application instance = new Application();

  private ArrayList<Spreadsheet> spreadsheets;
  private Spreadsheet worksheet;
  private Position currentPosition;

  public final Observable<Expression> showEvent;
  public final Observable<Exception> errorEvent;
  public final Observable<Position> selectEvent;
  public final Observable<Spreadsheet> worksheetChangedEvent;
  public final Observable<Spreadsheet> newSpreadsheetEvent;
  public final Observable<Spreadsheet> removeSpreadsheetEvent;

  private Application() {
    this.worksheet = new Spreadsheet();
    this.spreadsheets = new ArrayList<Spreadsheet>();
    this.spreadsheets.add(this.worksheet);
    this.currentPosition = new Position(0, 0);

    this.showEvent = new Observable<Expression>() { };
    this.errorEvent = new Observable<Exception>() { };
    this.selectEvent = new Observable<Position>() { };
    this.worksheetChangedEvent = new Observable<Spreadsheet>() { };
    this.newSpreadsheetEvent = new Observable<Spreadsheet>() { };
    this.removeSpreadsheetEvent = new Observable<Spreadsheet>() { };
  }

  /**
   * @return Guaranteed not null.
   */
  public Position getCurrentPosition() {
    return this.currentPosition;
  }

  /**
   * @param position Assumed not null.
   */
  public void setCurrentPosition(final Position position) {
    this.currentPosition = position;
    this.selectEvent.notifyObservers(position);
  }

  /**
   * @return Guaranteed not null.
   */
  public Spreadsheet getWorksheet() {
    return this.worksheet;
  }

  /** Creates a new spreadsheet in the application.
   * @return The created spreadsheet; guaranteed not null.
   * @throws SpreadsheetAlreadyExists if user renamed spreadsheet
   * in a way that prohibits the automatic name generator.
   */
  public Spreadsheet newSpreadsheet()
      throws SpreadsheetAlreadyExists {
    final Spreadsheet sheet = new Spreadsheet();
    final String name = sheet.getName();
    if (this.getSpreadsheet(name) != null) {
      throw new SpreadsheetAlreadyExists(name);
    }
    this.spreadsheets.add(sheet);
    this.newSpreadsheetEvent.notifyObservers(sheet);
    return sheet;
  }
  
  public Spreadsheet forceNewSpreadsheet() {
    while(true) {
      try {
        return this.newSpreadsheet();
      } catch (SpreadsheetAlreadyExists e) {
        continue;
      }
    }
  }

  /** Removes the given spreadsheet.
   * 
   * If there is no spreadsheet by the given name,
   * nothing happens. Removes only the first occurence.
   *
   * @param outcast Assumed to be not null.
   * @throws OutcastReferenced if the outcast is referenced from another spreadsheet.
   */
  public void removeSpreadsheet(final Spreadsheet outcast) throws OutcastReferenced {
    final int indexOfOutcast = this.spreadsheets.indexOf(outcast);
    
    for (final Spreadsheet spreadsheet : this.spreadsheets) {
      if (!spreadsheet.equals(outcast) && spreadsheet.refersTo(outcast)) {
        throw new OutcastReferenced(outcast, spreadsheet);
      }
    }
    this.spreadsheets.remove(outcast);

    if (this.spreadsheets.size() == 0) {
      this.worksheet = this.forceNewSpreadsheet();
    } else if (outcast.equals(this.worksheet)) {
      if (indexOfOutcast < this.spreadsheets.size()) {
        this.worksheet = this.spreadsheets.get(indexOfOutcast);
      } else {
        this.worksheet = this.spreadsheets.get(indexOfOutcast - 1);
      }
    }
    this.removeSpreadsheetEvent.notifyObservers(outcast);
    this.worksheetChangedEvent.notifyObservers(this.worksheet);
  }
  
  /** Removes the current worksheet. Replaces with a new one.
  *
  * @throws OutcastReferenced if the outcast is referenced from another spreadsheet.
  */
  public void removeSpreadsheet() throws OutcastReferenced {
    this.removeSpreadsheet(this.worksheet);
  }

  /**
   * @param name Assumed not null.
   * @return null if the spreadsheet with the given name is absent. */
  public Spreadsheet getSpreadsheet(final String name) {
    for (final Spreadsheet spreadsheet : this.spreadsheets) {
      if (name.equals(spreadsheet.getName())) {
        return spreadsheet;
      }
    }
    return null;
  }

  /**
   * @param name Assumed not null. */
  public void changeWorksheet(final String name)
      throws NoSuchSpreadsheet {
    this.worksheet = this.getSpreadsheet(name);
  }

  /** Iterates over the currently active spreadsheets.
   */
  public Iterable<Spreadsheet> getSpreadsheets() {
    return this.spreadsheets;
  }

  /** Terminates the application.
   */
  public void exit() {
    System.exit(0);
  }

  /** Sets the expression at the given position/
   * 
   * @param position Assumed not null.
   * @param expression Can be null.
   */
  public void set(final Position position, final Expression expression) {
    try {
      expression.checkAcyclic(new Path(new Reference(this.worksheet, position), null));
    } catch (CycleException e) {
      this.errorEvent.notifyObservers(e);
      return;
    }
    this.worksheet.set(position, expression);
  }

  /** Sets the expression at the current position.
   * 
   * @param expression Can be null.
   */
  public void set(final Expression expression) {
    this.set(this.currentPosition, expression);
  }

  /** Gets the expression at the given position.
   * 
   * @param position Assumed not null.
   * @return Guaranteed not null.
   */
  public Expression get(final Position position) {
    final Expression expression = this.worksheet.get(position);
    if (expression == null) {
      return new Text("");
    }
    return expression;
  }

  /** Gets the expression at the current position.
   * 
   * @return Guaranteed not null.
   */
  public Expression get() {
    return this.get(this.currentPosition);
  }

  /** Saves the current state of the application to file.
   * 
   * @param printer Assumed not null.
   */
  public void save(final PrintWriter printer) {
    for (int i = 1; i < this.spreadsheets.size(); ++i) {
      printer.println("ns");
    }
    for (final Spreadsheet spreadsheet : this.spreadsheets) {
      spreadsheet.save(printer);
    }
  }

  /** Renames the current worksheet to have the given name.
   * 
   * @param newName Assumed not null.
   * @throws SpreadsheetAlreadyExists
   */
  public void rename(final String newName) throws SpreadsheetAlreadyExists {
    for (final Spreadsheet spreadsheet : this.spreadsheets) {
      if (spreadsheet.getName().equals(newName)) {
        throw new SpreadsheetAlreadyExists(newName);
      }
    }
  }

  /** Shows the expression at the given position.
   * 
   * @param position Assumed not null.
   */
  public void showExpression(final Position position) {
    this.showEvent.notifyObservers(this.get(position));
  }
  
  /** Shows the expression at the current position.
   */
  public void showCurrentExpression() {
    this.showEvent.notifyObservers(this.get());
  }

  /**
   * @param errorText Assumed not null.
   */
  public void reportError(final String errorText) {
    this.reportError(new Exception(errorText));
  }

  /**
   * @param exception Assumed not null.
   */
  public void reportError(final Exception exception) {
    this.errorEvent.notifyObservers(exception);
  }

  /** Prints the currently active spreadsheet names to System.out.
   */
  public void printSpreadsheets() {
    for (final Spreadsheet spreadsheet : this.spreadsheets) {
      System.out.println(spreadsheet.getName());  
    }
  }
  
  /** Print the worksheet name to System.out.
   */
  public void printWorksheet() {
    System.out.println(this.worksheet.getName());
  }

}
