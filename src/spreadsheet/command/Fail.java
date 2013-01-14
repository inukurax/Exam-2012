package spreadsheet.command;

import spreadsheet.Application;

public final class Fail
    extends Command {

  private final String message;

  /**
   * @param message assumed not null.
   */
  public Fail(final String message) {
    this.message = message;
  }

  public void execute() {
    Application.instance.reportError(this.message);
  }

}
