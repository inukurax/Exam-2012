package spreadsheet;

public final class Path {

  public final Reference head;
  public final Path tail;

  Path(final Reference head, final Path tail) {
    this.head = head;
    this.tail = tail;
  }
  
  public boolean contains(final Reference reference) {
    for (Path tail = this; tail != null; tail = tail.tail) {
      if (tail.head.equals(reference)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder(this.head.getDescription());
    for (Path path = this.tail; path != null; path = path.tail) {
      builder.append("->");
      builder.append(path.head.getDescription());
    }
    return builder.toString();
  }

}
