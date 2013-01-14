package quickcheck;

public final class IntegerGenerator
    extends Generator<Integer> {

  private static final int DEFAULT_MIN = -1000;
  private static final int DEFAULT_MAX = 1000;

  private int min;
  private int range;

  public IntegerGenerator() {
    this(DEFAULT_MIN, DEFAULT_MAX);
  }

  /* [min,max] */
  public IntegerGenerator(final int min, final int max) {
    this.min = min;
    this.range = Math.abs(min - max) + 1;
  }

  public Integer next() {
    return this.random.nextInt(this.range) + this.min;
  }

}
