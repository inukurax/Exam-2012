package spreadsheet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quickcheck.ExpressionInfo;
import quickcheck.PositionGenerator;
import quickcheck.RangeGenerator;
import quickcheck.Values;
import quickcheck.generators.ReferenceGenerator;
import quickcheck.generators.TextIntTrueFalseGenerator;

import spreadsheet.arithmetic.Int;
import spreadsheet.exception.InvalidReference;

/** Tests of the copy-paste operations.
 */
public class CopyPasteTest {
	
	  private TextIntTrueFalseGenerator expGen;
	  private PositionGenerator posGen;
	  private ReferenceGenerator refGen;
	  private RangeGenerator rangeGen;

	
	  @org.junit.Before
	  public void intializeExpressionGenerator() {
	    this.expGen = new TextIntTrueFalseGenerator();
	    this.posGen = new PositionGenerator();
	    this.refGen = new ReferenceGenerator();
	    this.rangeGen = new RangeGenerator();
	    
	  }

  @Test
  public void test() {   
	final Position copy = posGen.next();
	final Position paste = posGen.next();
	final Values<ExpressionInfo> expList =  new Values<ExpressionInfo>(this.expGen);
	for (ExpressionInfo exp : expList) {
		try {
			int columnOffset =  copy.getColumnOffset(paste);
			int rowOffset = copy.getRowOffset(paste);
			Application.instance.set(copy, exp.getValue());
			Expression newExp = exp.getValue().copy(columnOffset, rowOffset);
			Application.instance.set(paste, newExp);
			assertEquals("Result", Application.instance.get(copy).copy(columnOffset, rowOffset).toString(), 
					Application.instance.get(paste).toString());
			} catch (InvalidReference e) {
				System.out.println("Failed with InvalidReference");
			}
	}
	
	final Range copy2 = this.rangeGen.next();
	final Range paste2 = this.rangeGen.next();

		  
	final Values<ExpressionInfo> refList =  new Values<ExpressionInfo>(this.refGen);
	for (ExpressionInfo exp : refList) {
		try {
			int columnOffset =  copy2.getPosA().getColumnOffset(paste2.getPosA());
			int rowOffset = copy2.getPosA().getRowOffset(paste2.getPosA());
			Application.instance.set(copy, exp.getValue());
			Expression newExp = exp.getValue().copy(columnOffset, rowOffset);
			Application.instance.set(paste2.getPosA(), newExp);
			assertEquals("Result", Application.instance.get(copy2.getPosA()).copy(columnOffset, rowOffset).toString(), 
					Application.instance.get(paste2.getPosA()).toString());
		} catch (InvalidReference e) {
			System.out.println("Failed with InvalidReference");

		}

	}

  }
  
}
