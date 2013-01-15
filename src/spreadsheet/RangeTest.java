package spreadsheet;

import org.junit.Test;


import quickcheck.RangeGenerator;
import quickcheck.Values;

/** Tests of the range selection functionality.
 */
public class RangeTest {
	
	private RangeGenerator rangeGen10000;
	private RangeGenerator rangeGen100000;
	private RangeGenerator rangeGen1000;
	private RangeGenerator rangeGenMax;

	@org.junit.Before
	public void intializeExpressionGenerator() {
		this.rangeGen1000 = new RangeGenerator(0, 1000);
		this.rangeGen10000 = new RangeGenerator(0, 10000);
		this.rangeGen100000 = new RangeGenerator(0, 100000);
		this.rangeGenMax = new RangeGenerator(0, Integer.MAX_VALUE - 1);
	}

  @Test(timeout=10000)
  public void test() {
	  
//	    final Values<Range> a1000 =  new Values<Range>(this.rangeGen1000);
//	    for (Range range : a1000) {
//	    	System.out.println("Size:" + range.getPositionsInRange().size());
//	    }
//	 
	    final Values<Range> a10000 =  new Values<Range>(this.rangeGen10000);
	    for (Range range : a10000) {
	    	System.out.println("Size:" + range.getPositionsInRange().size());

	    }
	    
//	    final Values<Range> a100000 =  new Values<Range>(this.rangeGen100000);
//	    for (Range range : a100000) {
//	    	System.out.println("Size:" + range.getPositionsInRange().size());
//	    }
//	    
//	    final Values<Range> max =  new Values<Range>(this.rangeGenMax);
//	    for (Range range : max) {
//	    	System.out.println("Size:" + range.getPositionsInRange().size());
//	    }

  }
  
}
