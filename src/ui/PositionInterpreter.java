package ui;

import spreadsheet.Position;

import ui.exception.*;

public final class PositionInterpreter {

  private PositionInterpreter() {
    // The class should not be instantiated.
  }

  public static Position interpret(final String text)
      throws InvalidPosition {

    int i = text.length() - 1;
    if (i < 1) {
      throw new InvalidPosition(text);
    }

    char c = text.charAt(i);
    if (!isNumeric(c)) {
      throw new InvalidPosition(text);
    }
    int row = 0;
    int offset = 1;
    do {
      row += (c - '0') * offset;
      offset *= 10;
      --i;
      if (i < 0) {
        throw new InvalidPosition(text);
      }
      c = text.charAt(i);
    } while (isNumeric(c));

    if (!isAlpha(c)) {
      throw new InvalidPosition(text);
    }

    int column = (c - 'A');
    if (i == 0) {
      return new Position(column, row);
    }
    --i;
    offset = 26;
    c = text.charAt(i);
    do {
      column += (c - 'A' + 1) * offset;
      if (i == 0) {
        return new Position(column, row);
      }
      offset *= 26;
      --i;
      c = text.charAt(i);
    } while (isAlpha(c));

    throw new InvalidPosition(text);
  }

  private static boolean isAlpha(final char c) {
    return c >= 'A' && c <= 'Z';
  }

  private static boolean isNumeric(final char c) {
    return c >= '0' && c <= '9';
  }

}
