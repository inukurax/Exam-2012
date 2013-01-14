package spreadsheet.arithmetic;

import java.util.Iterator;

import spreadsheet.Expression;
import spreadsheet.Reference;
import spreadsheet.expression.UnaryExpression;

public class Sum extends UnaryExpression {

	private Reference reference;
	
	public Sum(final Reference reference) {
		super(ArithmeticType.instance, reference);
		this.reference = reference;
	}
	
	  @Override
	  public int toInt() {
		  int sum = 0;
		  Iterator<Expression> list = reference.iterator();
		 while (list.hasNext()) {
			 sum += list.next().toInt();
		 }
		return sum;
			  
	}
	  
}
