package quickcheck;
import java.lang.Iterable;
import java.util.Iterator;

public class Values<T> implements Iterable<T> {
	
	private static final int DEFAULT_MAX_RUN = 10;
	
	private Generator<T> generator;
	private int max_run;
	
	public Values (Generator<T> value){
		generator = value;
		max_run = DEFAULT_MAX_RUN;
	}
	
	public Values (Generator<T> value, int maxrun){
		generator = value;
		max_run = maxrun;
	}

	@Override
	public Iterator<T> iterator() {
		return new V_iterator();
	}
	
	private class V_iterator implements Iterator<T>{

		int runs;
		
		@Override
		public boolean hasNext() {
			return runs < Values.this.max_run;
		}

		@Override
		public T next() {
			if (hasNext()){
			this.runs++;
			return Values.this.generator.next();
			}
			return null;
		}

		@Override
		public void remove() {
			
			
		}
		
	}

}