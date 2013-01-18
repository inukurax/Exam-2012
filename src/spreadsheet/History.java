package spreadsheet;

import java.util.ArrayList;

public class History {
	
	public static final History instance = new History(20);
	
	private int current = 0;
	private ArrayList<Change> changeList;

	private int max;
	
	/**
	 * Creates an ArrayList with initial size of <max>
	 * @param max elements that can be <push> to the list.
	 */
	private History(int max) {
		changeList = new ArrayList<Change>();
		this.max = max;
	}
	
	/**
	 * Adds a new Change to the history list
	 * keeps the size of the list at <max>,
	 * will remove the "oldest" element to prevent
	 * list size to be larger than <max> 
	 * @param change
	 */
	public void push(final Change change) {
		if (current >= max) {
			changeList.remove(0);
			current--;
		}
		changeList.add(change);
		current++;
	}
	
	/**
	 * Removes the last added Change from the list
	 * @return the Change that will be removed, null if history is empty
	 */
	public Change pop() {
		int index = changeList.size() - 1;
		if (index == -1)
			return null;
		Change change = changeList.get(index);
		changeList.remove(index);
		current--;
		return change;
	}
	
	/**
	 * Used for debugging, prints a describe of each change
	 * in the history.
	 */
	public void printHistory() {
		int index = 0;
		for (Change chg : this.changeList) {
			System.out.println(String.format("index %d : %s", index,chg.getDescription()));
			index++;
		}
	}
	
	

}
