package spreadsheet.arithmetic;

import java.util.Scanner;

import spreadsheet.expression.NullaryExpression;

/** Models an integer
 *
 * Was called an AConst, but was changed for clarity reasons
 */
public final class Int
    extends NullaryExpression {

  private final int value;

  /** Constructs a new Int with the integer argument as its value
   */
  public Int(final int value) {
    super(ArithmeticType.instance);
    this.value = value;
  }

  /** Constructs an Int by using the scanner as input 
   */
  public Int(final Scanner scanner) {
    super(ArithmeticType.instance);
    this.value = scanner.nextInt();
  }

  @Override
  public int toInt() {
    return this.value;
  }

  public String getDescription() {
    return String.format("%s %d",
      this.getClass().getSimpleName(),
      this.value
    );
  }

}
