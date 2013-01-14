import spreadsheet.Application;
import cli.ErrorView;
import cli.ExpressionView;
import cli.Interpreter;

/** A spreadsheet application with a command-line interface (CLI). */
public final class CLI {

  private CLI() {
    // This class need not be instantiated.
  }

  /** Starts up the application with a CLI. */
  public static void main(String[] _) {

    Application.instance.showEvent.addObserver(ExpressionView.instance);
    Application.instance.errorEvent.addObserver(ErrorView.instance);

    new Interpreter().prompt();
  }

}
