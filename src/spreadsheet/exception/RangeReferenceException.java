package spreadsheet.exception;

public class RangeReferenceException extends UnsupportedOperationException {

	private static final long serialVersionUID = 1L;

	  public RangeReferenceException() {
	    super("Can not evaluate a Range of Expressions!");
	  }
}
