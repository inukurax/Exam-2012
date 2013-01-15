package spreadsheet;

public interface Change {
	
	public abstract void undo();
	public abstract String getDescription();
}
