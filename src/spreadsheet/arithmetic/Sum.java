package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.exception.InvalidReference;
import spreadsheet.expression.UnaryExpression;

public class Sum extends UnaryExpression {

	private final Reference reference;
	
	/**
	 * Constructs a Sum expression
	 * @param reference a reference to one or more Position
	 * assumes non null reference.
	 */
	public Sum(final Reference reference) {
		super(ArithmeticType.instance, reference);
		this.reference = reference;
		reference.setSum(true);
	}
	
	/**
	 * Evaluates an Sum expression to to integer.
	 */
	@Override
	public int toInt() {
		int sum = 0;
		for (Expression exp : reference) {
			if (exp != null) {
				sum += exp.toInt();
			}
		}
		reference.setSum(false);
		return sum;	  
	}  
	
	/**
	 * Copies this Sum expression but changed reference position 
	 * with columnOffset and rowOffset
	 * @columnOffset number of columns to move reference to the right
	 * @rowOffset number of  rows to move reference to the right
	 */
	  @Override
	  public Expression copy(final int columnOffset, final int rowOffset) 
			  throws InvalidReference {
		  final Position refPosA = this.reference.getRefRange().getPosA();
		  final Position newRefPosA = new Position(refPosA.getColumn() + columnOffset,
				  refPosA.getRow() + rowOffset);
		  final Position refPosB = this.reference.getRefRange().getPosB();
		  final Position newRefPosB = new Position(refPosB.getColumn() + columnOffset,
				  refPosB.getRow() + rowOffset);
		  
		  if (newRefPosA.getColumn() < 0 || newRefPosA.getRow() < 0
				  || newRefPosB.getColumn() < 0 || newRefPosB.getRow() < 0)
			  throw new InvalidReference();
		  
		  final Range range = new Range(newRefPosA, newRefPosB);
		  final Reference ref = new Reference(reference.getRefSpreadsheet(), range);
		  final Sum sum = new Sum(ref);
		  return sum;
	  }
}
