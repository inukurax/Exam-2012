package spreadsheet.exception;

import spreadsheet.Spreadsheet;

public final class OutcastReferenced
    extends Exception {

  public static final long serialVersionUID = 1L;

  /** Assumes that name is not null. */
  public OutcastReferenced(final Spreadsheet outcast, final Spreadsheet friend) {
    super(
      String.format("Attempted to remove %s, but %s refers to it.", outcast, friend));
  }

}
