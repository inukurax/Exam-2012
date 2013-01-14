package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Expression;
import spreadsheet.logical.And;
import spreadsheet.logical.False;
import spreadsheet.logical.True;

/** A generator for the Conjunct class */
public class AndGenerator extends ExpressionGenerator {

	/**
	 * Generates a random Conjunct Expression
	 * @return ExpressionInfo about the newly made Conjunct Expression
	 */
	@Override
	public ExpressionInfo next() {
		final boolean val1 = this.boolGen.next();
		final boolean val2 = this.boolGen.next();
		final Expression lConst1 = val1 ? new True() : new False();
		final Expression lConst2 = val2 ? new True() : new False();
		final And conjunct = new And(lConst1, lConst2);
		return new ExpressionInfo(conjunct, lConst1, lConst2);
		
	}

}
