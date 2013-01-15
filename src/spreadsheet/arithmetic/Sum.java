package spreadsheet.arithmetic;

import java.util.Iterator;

import spreadsheet.Expression;
import spreadsheet.Reference;
import spreadsheet.expression.UnaryExpression;

public class Sum extends UnaryExpression {

	private final Reference reference;
	
	public Sum(final Reference reference) {
		super(ArithmeticType.instance, reference);
		this.reference = reference;
		reference.setSum(true);
	}
	
	@Override
	public int toInt() {
		int sum = 0;
		Iterator<Expression> list = reference.iterator();
		while (list.hasNext()) {
			Expression exp = list.next();
			if (exp != null) {
				sum += exp.toInt();
			}
		}
		reference.setSum(false);
		return sum;	  
	}  
}
