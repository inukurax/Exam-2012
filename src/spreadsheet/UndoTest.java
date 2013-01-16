package spreadsheet;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import quickcheck.ConstGenerator;
import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import quickcheck.PositionGenerator;
import quickcheck.Values;
import quickcheck.generators.IfThenElseGenerator;
import quickcheck.generators.TextIntTrueFalseGenerator;
import spreadsheet.command.Set;
import spreadsheet.textual.Text;

/** Tests of the undo operation.
 */
public class UndoTest {
	
	  private ExpressionGenerator expGen;
	  private PositionGenerator posGen;
	private PositionGenerator smallPosGen;

		
	  @org.junit.Before
	  public void intializeExpressionGenerator() {
	    this.expGen = new TextIntTrueFalseGenerator();
	    this.posGen = new PositionGenerator();
	    this.smallPosGen = new PositionGenerator(0,5);

	  }

  @Test
  public void test() {
	    final Values<ExpressionInfo> infos =
	  	      new Values<ExpressionInfo>(this.expGen);
	    	// currentPosition (0,0) here
	  	    for (final ExpressionInfo info : infos) {
	  	    	new Set(info.getValue()).execute();
	  	      Change change = History.instance.pop();
	  	      if (change != null)
	  	    	change.undo();
	  	      final Expression actual = Application.instance.get();
	  	      final Expression expected = new Text("");
	  	      if (expected.toString().equals(actual.toString())) {
	  	        continue;
	  	      }

	  	      System.err.println("undo() failed. " + info);
  		      System.err.println("Expected: " + expected.toString() 
  		    		  + " -got-  " + actual.toString());
	  	    }
	  	    
	  	    // a random position
	  	    for (final ExpressionInfo info : infos) {
	  	    	Position pos = posGen.next();
	  	    	new Set(pos, info.getValue()).execute();
	  	      Change change = History.instance.pop();
	  	      if (change != null)
	  	    	change.undo();
	  	      final Expression actual = Application.instance.get(pos);
	  	      final Expression expected = new Text("");

	  	      if (expected.toString().equals(actual.toString())) {
	  	        continue;
	  	      }

	  	      System.err.println("undo() at random pos: " + pos.getDescription() 
	  	    		  + " failed. " + info);
  		      System.err.println("Expected: " + expected.toString() 
  		    		  + " -got-  " + actual.toString());
	  	    }
	  	    
	  	    // fills the history
	  	  final Values<ExpressionInfo> infos2 =
		  	      new Values<ExpressionInfo>(this.expGen,25);
	  	  ArrayList<Position> posList = new ArrayList<Position>();
	  	  ArrayList<Expression> expectedList = new ArrayList<Expression>();
	  	  for (final ExpressionInfo info : infos2) {
	  	    	Position pos = smallPosGen.next();
	  	    	posList.add(pos);
	  	    	final Expression expected = Application.instance.get(pos);
	  	    	expectedList.add(expected);
	  	    	new Set(pos, info.getValue()).execute();
	  	  }
	    	History.instance.printHistory();
	  	  for(int i = 0; i <= posList.size(); i++) {
	  		final Position pos = posList.get(i);
	  		final Expression expected = expectedList.get(i);
	  	    Change change = History.instance.pop();
	  		final Expression actual = Application.instance.get(pos);
	  	    if (change != null)
	  	    	change.undo();
	  	      if (expected.toString().equals(actual.toString())) {
		  	      System.out.println(i +"# SUCCES: " + expected.getDescription() 
	  		    		  + " -got-  " + actual.getDescription() + " at: " + pos.getDescription());
	  	    	  continue;
		  	  }

	  	      System.err.println(i +"#undo() at FIXED pos: " + pos.getDescription() 
	  	    		  + " failed. " + actual.getDescription());
  		      System.err.println(i +"#Expected: " + expected.getDescription() 
  		    		  + " -got-  " + actual.getDescription() + " at: " + pos.getDescription());
	  	    	
	  	  }
  }
  
}
  
  
