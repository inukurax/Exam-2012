package quickcheck;

import spreadsheet.Expression;
import spreadsheet.arithmetic.Int;
import spreadsheet.logical.False;
import spreadsheet.logical.True;
import spreadsheet.textual.Text;


public class ConstGenerator extends Generator<Expression> {

	@Override
	public Expression next() {
		 IntegerGenerator intGen = new IntegerGenerator(1,3);
		 
		 StringGenerator strGen = new StringGenerator();
		 IntegerGenerator intGen1 = new IntegerGenerator();
		 BooleanGenerator boolGen = new BooleanGenerator();
		 final boolean bool = boolGen.next();
		switch (intGen.next()) {
		case 1 : return new Text(strGen.next());
		case 2: return new Int(intGen1.next());
		case 3: return bool ? new True() : new False();
		}
		return null;
	}

}
