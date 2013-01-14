/** The base package for the model of the spreadsheet application.
 *
 * A spreadsheet is a mapping from positions to expressions. A position is a
 * column-row pair. There are three types of expressions: arithmetic, logical
 * and textual. Expressions can be converted freely from one type to another.
 * However, conversion is not symmetric in general.
 *
 * There are four basic constants, Int, Text, True and False, respectively.
 * Furthermore, there are a series of unary, binary, etc.  expressions,
 * allowing for expressions to be arbitrarily nested.
 *
 */
package spreadsheet;
