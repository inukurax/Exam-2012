package quickcheck;

import quickcheck.Generator;
import quickcheck.IntegerGenerator;
import spreadsheet.Position;

public final class PositionGenerator
    extends Generator<Position> {

  private final IntegerGenerator columnGen, rowGen;

  public PositionGenerator() {
    this.columnGen = new IntegerGenerator(0, Integer.MAX_VALUE - 1);
    this.rowGen = new IntegerGenerator(0, Integer.MAX_VALUE - 1);
  }
  
  public PositionGenerator(int min, int max) {
	    this.columnGen = new IntegerGenerator(min, max);
	    this.rowGen = new IntegerGenerator(min, max);
	  }

  public Position next() {
    final int column = this.columnGen.next();
    final int row = this.rowGen.next();
    final Position position = new Position(column, row);
    return position;
  }

}
