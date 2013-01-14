package quickcheck;

public final class BooleanGenerator
    extends Generator<Boolean> {

  @Override
  public Boolean next() {
    return this.random.nextBoolean();
  }

}
