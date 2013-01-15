package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Expression;

/** A generator for the Const expression class */
public class TextIntTrueFalseGenerator extends ExpressionGenerator {

	/**
	 * Generates a random const Expression
	 * @return ExpressionInfo about the newly made const Expression
	 */
	@Override
	public ExpressionInfo next() {
		final Expression val = this.constGen.next();
		return new ExpressionInfo(val);
	}

}
