package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.arithmetic.Int;
import spreadsheet.arithmetic.Neg;

/** A generator for the Neg class */
public class NegGenerator extends ExpressionGenerator {

	/**
	 * Generates a random Neg Expression
	 * @return ExpressionInfo about the newly made Neg Expression
	 */
	@Override
	public ExpressionInfo next() {
		final int value = this.intGen.next();
		final Int aConst = new Int(value);
		final Neg neg = new Neg(aConst);
		return new ExpressionInfo(neg, aConst);
	}
}
