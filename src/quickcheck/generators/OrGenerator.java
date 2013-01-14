package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Expression;
import spreadsheet.logical.False;
import spreadsheet.logical.Or;
import spreadsheet.logical.True;

/** A generator for the Disjunct class */
public class OrGenerator extends ExpressionGenerator {

	/**
	 * Generates a random Disjunct Expression
	 * @return ExpressionInfo about the newly made Disjunct Expression
	 */
	@Override
	public ExpressionInfo next() {
		final boolean val1 = this.boolGen.next();
		final boolean val2 = this.boolGen.next();
		final Expression lConst1 = val1 ? new True() : new False();
		final Expression lConst2 = val2 ? new True() : new False();
		final Or or = new Or(lConst1, lConst2);
		return new ExpressionInfo(or, lConst1, lConst2);
		
	}

}
