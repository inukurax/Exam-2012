package spreadsheet.expression;

import spreadsheet.Expression;
import spreadsheet.Path;
import spreadsheet.Type;
import spreadsheet.exception.CycleException;

public class TernaryExpression extends Expression {
	
	protected final Expression alpha, beta, gamma;

	/**
	 * Constructor for a ternary Expression. Holds 3 Expression
	 * @param type of Expression
	 * @param alpha non null Expression
	 * @param beta non null Expression
	 * @param gamma non null Expression
	 */
	public TernaryExpression(final Type type, final Expression alpha,
		      final Expression beta, final Expression gamma) {
		super(type);
		this.alpha = alpha;
		this.beta = beta;
		this.gamma = gamma;
	}

	@Override
	public void checkAcyclic(Path path) throws CycleException {
		this.alpha.checkAcyclic(path);
	    this.beta.checkAcyclic(path);
	    this.gamma.checkAcyclic(path);
	}

	@Override
	public String getDescription() {
		return String.format("%s %s %s %s", 
				this.getClass().getSimpleName(), this.alpha.getDescription(), 
				this.beta.getDescription(), this.gamma.getDescription());
	}

}
