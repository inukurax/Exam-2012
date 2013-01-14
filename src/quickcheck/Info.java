package quickcheck;

public abstract class Info<T> {

  protected final T value;

  protected Info(final T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }

  /** User-friendly description of the generated object. */
  public abstract String toString();

}
