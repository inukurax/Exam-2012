package spreadsheet;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import quickcheck.ConstGenerator;
import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import quickcheck.PositionGenerator;
import quickcheck.Values;
import quickcheck.generators.IfThenElseGenerator;
import quickcheck.generators.TextIntTrueFalseGenerator;

/** Tests of the undo operation.
 */
public class UndoTest {
	
	  private TextIntTrueFalseGenerator expGen;
	  private PositionGenerator posGen;

		
	  @org.junit.Before
	  public void intializeExpressionGenerator() {
	    this.expGen = new TextIntTrueFalseGenerator();
	    this.posGen = new PositionGenerator();
	    
	  }

  @Test
  public void test() {
	  
	  final Position copy = posGen.next();
	  final Position paste = posGen.next();
	    final Values<ExpressionInfo> expList =  new Values<ExpressionInfo>(this.expGen);

	  for (ExpressionInfo exp : expList) {
		  
	  }
    assertTrue(true);
  }
  
}
