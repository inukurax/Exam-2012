package spreadsheet;

import java.util.ArrayList;

public abstract class Observable<V> {

  private final ArrayList<Observer<V>> observers
    = new ArrayList<>();

  public <T extends Observer<V>>void addObserver(T observer) {
    this.observers.add(observer);
  }

  public void notifyObservers(V value) {
    for (final Observer<V> observer : this.observers) {
      observer.notify(value);
    }
  }

}
