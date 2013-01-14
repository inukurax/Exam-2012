package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.arithmetic.Int;


/** A generator for the AConst class */
public class IntGenerator extends ExpressionGenerator {

	/**
	 * Generates a random AConst Expression
	 * @return ExpressionInfo about the newly made AConst Expression
	 */
	@Override
	public ExpressionInfo next() {
		final int value = this.intGen.next();
		final Int aConst = new Int(value);
		return new ExpressionInfo(aConst);
	}
}