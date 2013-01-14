package quickcheck;

import spreadsheet.Expression;

public class ExpressionInfo extends Info<Expression> {
	
	//Variables used by constructor
	//name is for storing the name of the Expression
	private String name;
	private Expression arg1;
	private Expression arg2;


	/**
	 * Unary expression
	 */
	public ExpressionInfo(Expression exp) {
		super(exp);	
		name = exp.getDescription();
	}
	
	/**
	 * Overloaded for binary expression
	 * @param exp Expression
	 * @param arg1 argument for exp
	 */
	public ExpressionInfo(Expression exp, Expression arg1) {
		super(exp);	
		this.arg1 = arg1;
		name = exp.getDescription();
	}
	
	
	/**
	 * Overloaded for ternary expression
	 * @param exp Expression
	 * @param arg1 argument for exp
	 * @param arg2 argument2 for exp
	 */
	public ExpressionInfo(Expression exp, Expression arg1, Expression arg2) {
		super(exp);	
		this.arg1 = arg1;
		this.arg2 = arg2;
		name = exp.getDescription();
	}


	/**
	 * The expected result of a integer Expression
	 * @return integer result
	 */
	public int intResult() {
		Expression exp = this.getValue();
		String className = exp.getClass().getSimpleName();
		
		switch (className) {
		case "Int" : return this.getValue().toInt();
		case "Neg" : return exp.toInt() * (-1);
		case "Add" : return exp.toInt() + this.arg1.toInt();
		case "True" : return exp.toInt();
		case "False" : return exp.toInt();
		case "Text" : return exp.toInt();
		case "IfThenElse" : return exp.toBoolean() ? arg1.toInt() : arg2.toInt();
		default : return -99999;
		}
	}
	
	/**
	 * The expected result of a boolean Expression
	 * @return boolean result
	 */
	public boolean boolResult() {
		Expression exp = this.getValue();
		String className = exp.getClass().getSimpleName();
		
		switch (className) {
		case "Int" : return this.getValue().toBoolean();
		case "True" : return exp.toBoolean();
		case "False" : return exp.toBoolean();
		case "Text" : return exp.toBoolean();
		case "IfThenElse" : return exp.toBoolean() ? arg1.toBoolean() : arg2.toBoolean();
		default : return false;
		}
	}
	
	/**
	 * The expected result of a String Expression
	 * @return String result
	 */
	public String strResult() {
		Expression exp = this.getValue();
		String className = exp.getClass().getSimpleName();
		
		switch (className) {
		case "Int" : return this.getValue().toString();
		case "True" : return exp.toString();
		case "False" : return exp.toString();
		case "Text" : return exp.toString();
		case "IfThenElse" : return exp.toBoolean() ? this.arg1.toString() : this.arg2.toString();
		default : return null;
		}
	}
	
	/** Converts to String in format 
	 * "new Expression(Input,input2);" 
	 * for troubleshooting */
	@Override
	public String toString() {
		name += (arg1 != null) ? " " + arg1.getDescription() : "";
		name += (arg2 != null) ? " " + arg2.getDescription() : "";
		return name;
	}
}
