package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Expression;
import spreadsheet.logical.IfThenElse;

public class IfThenElseGenerator extends ExpressionGenerator {

	@Override
	public ExpressionInfo next() {
		Expression condition = this.constGen.next();
		Expression ifTrue = this.constGen.next();
		Expression ifFalse = this.constGen.next();
		return new ExpressionInfo(new IfThenElse(condition, ifTrue, ifFalse), condition, ifTrue, ifFalse);
	}


}
