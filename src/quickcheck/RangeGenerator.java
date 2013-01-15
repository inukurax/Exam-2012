package quickcheck;

import spreadsheet.Position;
import spreadsheet.Range;

public class RangeGenerator extends Generator<Range> {
	
	private PositionGenerator posGen;

	public RangeGenerator() {
		this.posGen = new PositionGenerator(0, Integer.MAX_VALUE - 1);
	}
	
	public RangeGenerator(final int min, final int max) {
		this.posGen = new PositionGenerator(min, max);
	}

	@Override
	public Range next() {
		final Position pos1 = posGen.next();
		final Position pos2 = posGen.next();
		final Range range = new Range(pos1, pos2);
		
		return range;
	}

}
