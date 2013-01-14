package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.textual.Text;

/** A generator for the TConst class */
public class TextGenerator extends ExpressionGenerator {

	/**
	 * Generates a random TConst Expression
	 * @return ExpressionInfo about the newly made TConst Expression
	 */
	@Override
	public ExpressionInfo next() {
		final String value = this.strGen.next();
		final Text tConst = new Text(value);
		return new ExpressionInfo(tConst);
	}

}
