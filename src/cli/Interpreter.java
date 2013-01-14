package cli;

import java.util.Scanner;

import spreadsheet.Application;
import ui.exception.InvalidInput;

/** An interpreter for the command-line interface. */
public final class Interpreter {

  private static final String PROMPT = "> ";

  private final Scanner scanner;

  /** Constructs an interpreter.
   * <p>
   * Takes input from System.in, and prompts System.out.
   * Issuing a command results in the model ({@link spreadsheet.Application})
   * being updated.
   */
  public Interpreter() {
    this.scanner = new Scanner(System.in);
  }

  /** Initializes the prompt.
   * <p>
   * Never terminates.
   */
  public void prompt() {
    String command = null;
    while(true) {
      System.out.print(Interpreter.PROMPT);
      command = this.scanner.nextLine();
      try {
        new CommandInterpreter(new Scanner(command)).interpret().execute();
      } catch (final InvalidInput e) {
        Application.instance.reportError(e);
      }
    }
  }

}

