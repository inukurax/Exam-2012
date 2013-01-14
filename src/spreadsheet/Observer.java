package spreadsheet;

public interface Observer<V> {

  void notify(final V value);

}
