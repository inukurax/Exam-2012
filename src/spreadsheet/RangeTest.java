package spreadsheet;

import org.junit.Test;

import quickcheck.PositionGenerator;

/** Tests of the range selection functionality.
 */
public class RangeTest {
	
	private PositionGenerator posGen;
		
	@org.junit.Before
	public void intializeExpressionGenerator() {
		this.posGen = new PositionGenerator(0, 2);
	}

  @Test
  public void test() {
	  Position pos1 = posGen.next();
	  Position pos2 = posGen.next();

	 Range range = new Range(pos1, pos2);
	 System.out.println(String.format("x: %d, y: %d", pos1.getRow() ,pos2.getColumn()));
	 System.out.println(range.getDescription());
  }
  
}
