package spreadsheet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spreadsheet.arithmetic.Int;
import spreadsheet.exception.InvalidReference;

/** Tests of the copy-paste operations.
 */
public class CopyPasteTest {

  @Test
  public void test() {
	  Position pos = new Position(0,0);	
	  Position pos1 = new Position(1,1);
	  Position pos2 = new Position(2,2);
	  Application.instance.set(pos2, new Int(1));

	  Application.instance.set(pos, new Reference(Application.instance.getWorksheet(), pos2));
	  Application.instance.setCurrentPosition(pos);

	   try {
		assertEquals("Result", Application.instance.get(pos).copy(1, 1).toInt(), 
				Application.instance.get(pos1).toInt());

	} catch (InvalidReference e) {
	}

  }
  
}
