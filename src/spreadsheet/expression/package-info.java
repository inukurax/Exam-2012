/** A package of abstract expression classes.
 *
 * This package defines the common data and behaviour of various expressions.
 * Generally, this has to do with expressions of the same ``arity'', i.e. the
 * same number of arguments.
 *
 * For instance, for a binary expression, we retain two arguments, and analyse
 * both of these recursively when performing cycle analysis. Likewise, for
 * unary expressions we retain one such argument, and for nullary expressions
 * --- no such arguments.
 */
package spreadsheet.expression;
