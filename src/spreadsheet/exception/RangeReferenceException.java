package spreadsheet.exception;

public class RangeReferenceException extends UnsupportedOperationException {

	private static final long serialVersionUID = 1L;

	  /* Assumes that name is not null. */
	  public RangeReferenceException() {
	    super("Cant evaluate a Range of Expressions, to a Position!");
	  }
}
