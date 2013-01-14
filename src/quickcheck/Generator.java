package quickcheck;

import java.util.Random;

public abstract class Generator<T> {

  protected final Random random = new Random();

  public abstract T next();

}
