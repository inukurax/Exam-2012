package ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.IfThenElse;
import spreadsheet.Reference;
import spreadsheet.Spreadsheet;
import spreadsheet.arithmetic.Add;
import spreadsheet.arithmetic.Int;
import spreadsheet.arithmetic.Neg;
import spreadsheet.arithmetic.Sum;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.logical.And;
import spreadsheet.logical.Eq;
import spreadsheet.logical.False;
import spreadsheet.logical.Gt;
import spreadsheet.logical.Lt;
import spreadsheet.logical.Not;
import spreadsheet.logical.Or;
import spreadsheet.logical.True;
import spreadsheet.textual.Concat;
import spreadsheet.textual.Text;
import ui.exception.IllegalStartOfExpression;
import ui.exception.InvalidExpression;
import ui.exception.InvalidPosition;

public final class ExpressionInterpreter {

  private ExpressionInterpreter() {
    // This class should not be instantiated.
  }
  
  /**
   * @param text assumed not null.
   * @return null if the command was not recognized.
   */
  public static Expression interpret(final String text)
      throws
      InvalidPosition,
      NoSuchSpreadsheet,
      IllegalStartOfExpression,
      InvalidExpression {
    return ExpressionInterpreter.interpret(new Scanner(text));
  }

  /**
   * @param scanner assumed not null.
   * @return null if the command was not recognized.
   */
  public static Expression interpret(final Scanner scanner)
      throws
        InvalidPosition,
        NoSuchSpreadsheet,
        IllegalStartOfExpression,
        InvalidExpression {

    String keyword = null;

    try {
      keyword = scanner.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStartOfExpression();
    }

    switch(keyword) {
      case "Int":
        return new Int(
          scanner.nextInt());
      case "Neg":
        return new Neg(
          interpret(scanner));
      case "Add":
        return new Add(
          interpret(scanner),
          interpret(scanner));
      case "True":
        return new True();
      case "False":
        return new False();
      case "Not":
        return new Not(
          interpret(scanner));
      case "And":
        return new And(
          interpret(scanner),
          interpret(scanner));
      case "Or":
        return new Or(
          interpret(scanner),
          interpret(scanner));
      case "Text":
        return new Text(scanner.next());
      case "Concat":
        return new Concat(
          interpret(scanner),
          interpret(scanner));
      case "Lt":
    	  return new Lt(interpret(scanner),
          interpret(scanner));
      case "Eq": 
    	  return new Eq(interpret(scanner),
          interpret(scanner));
      case "Gt": 
    	  return new Gt(interpret(scanner),
    	          interpret(scanner));
      case "If":
    	  String next = "";
    	  Expression condition = interpret(scanner);
    	  next = scanner.hasNext() ? scanner.next() : next; 
    	  if (next.equals("Then")) {
    		  Expression ifTrue = interpret(scanner);
        	  next = scanner.hasNext() ? scanner.next() : next; 
    		  if (scanner.hasNext() && next.equals("Else")) {
        		  Expression ifFalse = interpret(scanner);
        		  return new IfThenElse(condition, ifTrue, ifFalse);
    		  }
    	  }
    	  throw new InvalidExpression(scanner.hasNext() ?
    					  (next + scanner.nextLine())
    					  : keyword);  
      case "Sum" : return new Sum(interpretReference(scanner.next()));
      default:
        return interpretReference(keyword);
    }
  }

  private static Reference interpretReference(String text)
      throws
        NoSuchSpreadsheet,
        InvalidPosition {

    Spreadsheet spreadsheet = null;
    final int indexOfBang = text.indexOf('!');
    if (indexOfBang != -1) {
      final String name = text.substring(0, indexOfBang);
      spreadsheet = Application.instance.getSpreadsheet(name);
      text = text.substring(indexOfBang + 1, text.length());
    } else {
      spreadsheet = Application.instance.getWorksheet();
    }
    final int indexOfColon = text.indexOf(':');
    if (indexOfColon != -1)
    	return new Reference(spreadsheet, RangeInterpreter.interpret(text));
    else
    	return new Reference(spreadsheet, PositionInterpreter.interpret(text));
  }

}
