package quickcheck.generators;

import quickcheck.ExpressionGenerator;
import quickcheck.ExpressionInfo;
import spreadsheet.Application;
import spreadsheet.Range;
import spreadsheet.Reference;

public class ReferenceGenerator extends ExpressionGenerator {

	@Override
	public ExpressionInfo next() {
		Range range = this.rangeGen.next();
		return new ExpressionInfo(new Reference(
				Application.instance.getWorksheet(), range));
	}


}
