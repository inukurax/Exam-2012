package spreadsheet;

import org.junit.Test;

import quickcheck.ExpressionGenerator;
import quickcheck.PositionGenerator;
import quickcheck.generators.IfThenElseGenerator;

/** Tests of the range selection functionality.
 */
public class RangeTest {
	
	private PositionGenerator posGen;
		
	@org.junit.Before
	public void intializeExpressionGenerator() {
		this.posGen = new PositionGenerator();
	}

  @Test
  public void test() {
	 Range range = new Range(posGen.next(), posGen.next());
	for (Position pos : range.getPositionsInRange())
	 System.out.println(String.format("x: %d, y: %d", pos.getRow() ,pos.getColumn()));
    
  }
  
}
