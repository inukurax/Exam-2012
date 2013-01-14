package spreadsheet;

/* Immutable. */
public class Position {

  private int column, row;

  /** Assume column, row are nonnegative. */
  public Position(final int column, final int row) {
    this.column = column;
    this.row = row;
  }

  /** Guaranteed nonnegative. */
  public int getColumn() {
    return this.column;
  }

  /** Guaranteed nonnegative. */
  public int getRow() {
    return this.row;
  }

  /** Assume other is an instance of Position. */
  public boolean isEqualTo(final Position other) {
    return
      this.column == other.column &&
      this.row == other.row;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || (!(other instanceof Position))) {
      return false;
    }
    final Position position = (Position)other;
    return this.isEqualTo(position);
  }

  @Override
  public int hashCode() {
    return this.column + this.row;
  }

  /** Guaranteed not null. */
  public String getDescription() {
    final StringBuilder builder = new StringBuilder();

    int row = this.row;
    do {
      builder.append((char)(row % ('9'-'0' + 1) + '0'));
      row = row / ('9'-'0' + 1);
    } while (row != 0);

    int column = this.column;
    do {
      builder.append((char)(column % ('Z'-'A' + 1) + 'A'));
      column = column / ('Z'-'A' + 1) - 1;
    } while (column >= 0);

    return builder.reverse().toString();
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)", this.column, this.row);
  }

}
