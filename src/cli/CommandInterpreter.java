package cli;

import java.util.Scanner;
import java.util.NoSuchElementException;

import ui.exception.*;

import spreadsheet.command.*;
import spreadsheet.exception.*;

import ui.ExpressionInterpreter;
import ui.PositionInterpreter;

public final class CommandInterpreter {

  private final Scanner scanner;

  public CommandInterpreter(final Scanner scanner) {
    this.scanner = scanner;
  }

  public Command interpret()
      throws InvalidInput {
    final String keyword = this.nextKeyword();
    switch(keyword) {
      case "exit":
        return new Exit();
      case "pws":
        return new PrintWorksheet();
      case "ns":
        return new NewSpreadsheet();
      case "ls":
        return new ListSpreadsheets();
      case "cws":
        return new ChangeWorksheet(this.nextString());
      case "mv":
        return new MoveSpreadsheet(this.nextString(), this.nextString());
      case "set":
        try {
          return new Set(
            PositionInterpreter.interpret(scanner.next()),
            ExpressionInterpreter.interpret(scanner));
        } catch (InvalidPosition _) {
          return new Fail("Invalid position.");
        } catch (NoSuchSpreadsheet e) {
          return new Fail(e.getMessage());
        } catch (IllegalStartOfExpression e) {
          return new Fail(e.getMessage());
        } catch (InvalidExpression e) {
          return new Fail(e.getMessage());
        } catch (NoSuchElementException e) {
          e.printStackTrace();
          return new Fail("Missing position.");
        }
      case "get":
        try {
          return new Get(
            PositionInterpreter.interpret(scanner.next()));
        } catch (InvalidPosition _) {
          return new Fail("Invalid position.");
        }
      case "save":
        return new Save(this.nextString());
    }
    return new Fail(
      String.format("Illegal start of command, \"%s\".", keyword));
  }

  private String nextKeyword()
      throws ExpectingKeyword {
    try {
      return this.scanner.next();
    } catch (final NoSuchElementException e) {
      throw new ExpectingKeyword();
    }
  }

  private String nextString()
      throws ExpectingString {
    String s = this.tryDarkMagic();
    System.out.println(s);
    if (s != null) {
      return s;
    }

    try {
      s = scanner.next();
    } catch (final NoSuchElementException e) {
      throw new ExpectingString();
    }
    System.out.println(s);
    return s;
  }

  private String tryDarkMagic() {
    final String darkMagic = "\"([^\\\\\"]|\\\\\")*\"";
    String s = null;
    this.scanner.useDelimiter("\n");
    if (this.scanner.hasNext(darkMagic)) {
      s = this.scanner.next();
      s = s.substring(1, s.length() - 1);
    }
    this.scanner.reset();
    return s;
  }

}
