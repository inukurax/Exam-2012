package spreadsheet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


import quickcheck.RangeGenerator;
import quickcheck.Values;

/** Tests of the range selection functionality.
 */
public class RangeTest {
	
	private RangeGenerator rangeGen10000;
	private RangeGenerator rangeGen1000;


	@org.junit.Before
	public void intializeExpressionGenerator() {
		this.rangeGen1000 = new RangeGenerator(0, 1000);
		this.rangeGen10000 = new RangeGenerator(0, 10000);
	}

  @Test(timeout=10000)
  public void test() {
	  Range range1 = new Range(new Position(0,0), new Position(0,0));
	  	assertEquals("Result: ",1,range1.getPositionsInRange().size());
	    final Values<Range> a1000 =  new Values<Range>(this.rangeGen1000);
	    for (Range range : a1000) {
  	assertEquals("Result: ",(range.getColumnCount() * range.getRowCount()),range.getPositionsInRange().size());
	    }
	 
//	    final Values<Range> a10000 =  new Values<Range>(this.rangeGen10000);
//	    for (Range range : a10000) {
//	    	assertEquals("Result: ",(range.getColumnCount() * range.getRowCount()),range.getPositionsInRange().size());
//
//	    }


  }
  
}
