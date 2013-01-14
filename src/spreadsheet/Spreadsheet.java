package spreadsheet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public final class Spreadsheet {

  private static final String NAME_PREFIX = "Sheet";
  private static int NAME_OFFSET = 0;

  private final HashMap<Position, Expression> map;
  private String name;
  private int maxColumn = 20, maxRow = 20;

  public Spreadsheet() {
    this.name = Spreadsheet.NAME_PREFIX + Spreadsheet.NAME_OFFSET;
    Spreadsheet.NAME_OFFSET += 1;
    this.map = new HashMap<Position, Expression>();
  }

  /* Assumes that name is not null. */
  public Spreadsheet(final String name) {
    this.name = name;
    this.map = new HashMap<Position, Expression>();
  }

  /* Guaranteed not null. */
  public String getName() {
    return this.name;
  }

  /* Assumes name is not null. */
  public void setName(final String name) {
    this.name = name;
  }

  /* Assumes position and expression are not null. */
  public void set(final Position position, final Expression expression) {
    this.map.put(position, expression);
  }

  /* Assumes position is not null.
   * Returns null if the position is not associated with an expression. */
  public Expression get(final Position position) {
    return this.map.get(position);
  }

  public int getMaxColumn() {
    return this.maxColumn;
  }

  public int getMaxRow() {
    return this.maxRow;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Spreadsheet)) {
      return false;
    }
    return this.name.equals(((Spreadsheet)other).name);
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  public void save(final PrintWriter printer) {
    for (final Map.Entry<Position, Expression> entry : this.map.entrySet()) {
      printer.println(String.format("set %s %s",
        entry.getKey().getDescription(),
        entry.getValue().getDescription()));
    }
  }

  public void rename(final String newName) {
    this.name = newName;
  }

  public boolean refersTo(Spreadsheet outcast) {
    for (final Map.Entry<Position, Expression> entry : this.map.entrySet()) {
      if (entry.getValue().refersTo(outcast)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public String toString() {
    return this.name;
  }

}
