package quickcheck;

import spreadsheet.Expression;

public class ExpressionInfo extends Info<Expression> {
	
	//Variables used by constructor
	//name is for storing the name of the Expression
	private String name;
	private Expression arg1;
	private Expression arg2;
	private Expression arg3;


	/**
	 * Const expression
	 */
	public ExpressionInfo(Expression exp) {
		super(exp);
		name = exp.getDescription();
	}
	
	/**
	 * Overloaded for unary expression
	 * @param exp Expression
	 * @param arg1 argument for exp
	 */
	public ExpressionInfo(Expression exp, Expression arg1) {
		super(exp);	
		this.arg1 = arg1;
		name = exp.getDescription();
	}
	
	
	/**
	 * Overloaded for binary expression
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
	 * Overloaded for ternary expression
	 * @param exp Expression
	 * @param arg1 argument for exp
	 * @param arg2 argument2 for exp
	 * @param arg3 argugment 3 for exp
	 */
	public ExpressionInfo(Expression exp, Expression arg1, Expression arg2, Expression arg3) {
		super(exp);	
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
		name = exp.getDescription();
	}


	/**
	 * The expected result of a integer Expression
	 * @return integer result
	 */
	public int intResult() {
		String className = this.getValue().getClass().getSimpleName();
		
		switch (className) {
		case "Int" : return this.getValue().toInt();
		case "Neg" : return arg1.toInt() * (-1);
		case "Add" : return arg1.toInt() + this.arg2.toInt();
		case "True" : return this.getValue().toInt();
		case "False" : return this.getValue().toInt();
		case "Text" : return this.getValue().toInt();
		case "IfThenElse" : return arg1.toBoolean() ? this.arg2.toInt() : this.arg3.toInt();
		default : return -99999;
		}
	}
	
	/**
	 * The expected result of a boolean Expression
	 * @return boolean result
	 */
	public boolean boolResult() {
		String className = this.getValue().getClass().getSimpleName();
		
		switch (className) {
		case "Int" : return this.getValue().toBoolean();
		case "True" : return this.getValue().toBoolean();
		case "False" : return this.getValue().toBoolean();
		case "Text" : return this.getValue().toBoolean();
		case "IfThenElse" : 
			return arg1.toBoolean() ? this.arg2.toBoolean() : this.arg3.toBoolean();
		default : return false;
		}
	}
	
	/**
	 * The expected result of a String Expression
	 * @return String result
	 */
	public String strResult() {
		String className = this.getValue().getClass().getSimpleName();
		
		switch (className) {
		case "Int" : return this.getValue().toString();
		case "True" : return this.getValue().toString();
		case "False" : return this.getValue().toString();
		case "Text" : return this.getValue().toString();
		case "IfThenElse" : 
			return arg1.toBoolean() ? arg2.toString() : arg3.toString();
		default : return null;
		}
	}
	
	/** Converts to String in format 
	 * "new Expression(Input,input2);" 
	 * for troubleshooting */
	@Override
	public String toString() {
		return name;
	}
}
