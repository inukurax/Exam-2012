package quickcheck;

import quickcheck.Generator;
import quickcheck.IntegerGenerator;
import spreadsheet.Position;

public final class PositionGenerator
    extends Generator<Position> {

  private final IntegerGenerator columnGen, rowGen;

  public PositionGenerator() {
    this.columnGen = new IntegerGenerator();
    this.rowGen = new IntegerGenerator();
  }

  public Position next() {
    final int column = this.columnGen.next();
    final int row = this.rowGen.next();
    final Position position = new Position(column, row);
    return position;
  }

}
