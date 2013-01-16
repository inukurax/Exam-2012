package spreadsheet;

import org.junit.Test;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import quickcheck.Values;
import quickcheck.generators.IfThenElseGenerator;


/** Tests of the if-then-else operation.
 */
public class IfThenElseTest {
	
	  private ExpressionGenerator ifThenElseGen;
		
	  @org.junit.Before
	  public void intializeExpressionGenerator() {
	    this.ifThenElseGen = new IfThenElseGenerator();
	  }

  @Test
  public void test() {
	    final Values<ExpressionInfo> infos =
	  	      new Values<ExpressionInfo>(this.ifThenElseGen);

	  	    for (final ExpressionInfo info : infos) {
	  	      final int expected = info.intResult();
	  	      final int actual = info.getValue().toInt();

	  	      if (actual == expected) {
	  	        continue;
	  	      }

	  	      System.err.println("IfThenElse.toInt() failed. " + info);
  		      System.err.println("Expected: " + expected + " - got : " + actual);

	  	    }
	  	    for (final ExpressionInfo info : infos) {
	  		      final String expected = info.strResult();
	  		      final String actual = info.getValue().toString();
	  		      	
	  		      if (actual.equals(expected)) {
	  		        continue;
	  		      }

	  		      System.err.println("IfThenElse.toString() failed. " + info);
	  		      System.err.println("Expected: " + expected + " - got : " + actual);

	  		      
	  	    }
	  	    
	  	    for (final ExpressionInfo info : infos) {
	  		      final boolean expected = info.boolResult();
	  		      final boolean actual = info.getValue().toBoolean();

	  		      if (actual == expected) {
	  		        continue;
	  		      }

	  		      System.err.println("IfThenElse.toBoolean() failed. " + info);
	  		      System.err.println("Expected: " + expected + " - got : " + actual);

	  	    }
  	}
}
