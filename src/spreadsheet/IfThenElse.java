package spreadsheet;

import spreadsheet.expression.TernaryExpression;

public final class IfThenElse extends TernaryExpression {

	/**
	 * Constructor for the an IfThenElse expression
	 * @param condition non null Expression
	 * @param ifTrue non null Expression
	 * @param ifFalse non null Expression
	 */
	public IfThenElse(final Expression condition, final Expression ifTrue, 
			final Expression ifFalse) {
		super(GenericType.instance ,condition, ifTrue, ifFalse);
	}

	@Override
	public boolean toBoolean() {
		return this.alpha.toBoolean() ? 
				this.beta.toBoolean() : this.gamma.toBoolean();
	}

	@Override
	public String toString() {
		return this.alpha.toBoolean() ? 
				this.beta.toString() : this.gamma.toString();
	}

	@Override
	public int toInt() {
		return this.alpha.toBoolean() ? 
				this.beta.toInt() : this.gamma.toInt();
	}
	
	@Override
	public String getDescription() {
		return String.format("If %s Then %s Else %s", 
				this.alpha.getDescription(), 
				this.beta.getDescription(), this.gamma.getDescription());
	}
}
