package spreadsheet.textual;

import spreadsheet.expression.NullaryExpression;

/** Models a Text expression
 */
public final class Text
  extends NullaryExpression {

  private final String value;

  /** Constructs a new Text expression.
   *
   * @param value Assumed to not be null.
   */
  public Text(final String value) {
    super(TextualType.instance);
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public String getDescription() {
    return String.format("%s %s",
      this.getClass().getSimpleName(),
      this.value
    );
  }

}
