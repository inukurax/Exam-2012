package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.textual.Concat;
import spreadsheet.textual.Text;


/** A generator for the Concat class */
public class ConcatGenerator extends ExpressionGenerator {

	/**
	 * Generates a random Concat Expression
	 * @return ExpressionInfo about the newly made Concat Expression
	 */
	@Override
	public ExpressionInfo next() {
		final String val1 = this.strGen.next();
		final String val2 = this.strGen.next();
		final Text tConst1 = new Text(val1);
		final Text tConst2 = new Text(val2);
		final Concat concat = new Concat(tConst1, tConst2);
		return new ExpressionInfo(concat , tConst1, tConst2);
		
	}

}
