package cli;

import spreadsheet.Observer;

/** An error observer.
 * <p>
 * Prints the error to System.err.
 */
public final class ErrorView
    implements Observer<Exception> {

  public final static ErrorView instance = new ErrorView();

  private ErrorView() {
    // This is a singleton.
  }

  public void notify(final Exception exception) {
    System.err.println(exception.getMessage());
  }

}
