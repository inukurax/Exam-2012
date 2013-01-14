package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Expression;
import spreadsheet.logical.False;
import spreadsheet.logical.True;

public class TrueFalseGenerator extends ExpressionGenerator {

	/**
	 * Generates a random True or False Expression
	 * @return ExpressionInfo about the newly made Disjunct Expression
	 */
	@Override
	public ExpressionInfo next() {
		final boolean val = this.boolGen.next();
		final Expression exp = val ? new True() : new False();
		return new ExpressionInfo(exp);
		
	}

}
