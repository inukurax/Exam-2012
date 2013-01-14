/** A package of logical expressions.
 *
 * In the package, we define a common type, @see LogicalType . All expressions
 * in this pacakge must call the super constructor with an instance of this
 * type, i.e. be expressions of type ``logical''. In addition, all expressions
 * in this package must override the method toBoolean(), and must not override
 * either toInt() or toString() (these are handled by LogicalType).
 */
package spreadsheet.logical;
