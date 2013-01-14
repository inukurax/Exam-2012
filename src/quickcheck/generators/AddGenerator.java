package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Expression;
import spreadsheet.arithmetic.Add;

/** A generator for the Add class */
public class AddGenerator extends ExpressionGenerator {

	/**
	 * Generates a random Add Expression
	 * @return ExpressionInfo about the newly made Add Expression
	 */
	@Override
	public ExpressionInfo next() {
		final Expression val1 = this.constGen.next();
		final Expression val2 = this.constGen.next();;
		final Add add = new Add(val1, val2);
		return new ExpressionInfo(add, val1, val2);
	}

}
