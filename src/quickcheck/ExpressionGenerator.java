package quickcheck;

/** ExpressionGenerator allows us to make a generator 
 * for each type of Expression by being abstract
 */
public abstract class ExpressionGenerator 
	 extends Generator<ExpressionInfo> {
	
	//Different types of generators for our Expression generators
	protected final IntegerGenerator intGen;
	protected final BooleanGenerator boolGen;
	protected final StringGenerator strGen;
	protected final ConstGenerator constGen;
	protected final RangeGenerator rangeGen;
	protected final PositionGenerator posGen;




	/** ExpressionGenerator initializes the aforementioned variables */
	public ExpressionGenerator() {
		this.intGen = new IntegerGenerator();
	    this.boolGen = new BooleanGenerator();
	    this.strGen = new StringGenerator();
	    this.constGen = new ConstGenerator();
	    this.rangeGen = new RangeGenerator();
	    this.posGen = new PositionGenerator();
	}
	
	/** To be defined in subclasses */
	public abstract ExpressionInfo next();
}
