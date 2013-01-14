package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

public final class Gt extends BinaryExpression {
	
	/**
	 * Constructor for the logical expression greater than
	 * @param alpha non null Expression
	 * @param beta non null Expression
	 */
	public Gt(final Expression alpha, final Expression beta) {
		super(LogicalType.instance, alpha, beta);
	}
	
	@Override
	public boolean toBoolean() {
		return this.alpha.toInt() > this.beta.toInt();
	}

}
