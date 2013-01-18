package gui;

import java.util.ArrayList;

import spreadsheet.Expression;
import spreadsheet.Reference;

/**
 * This class contains a lot of accessor methods for use with a Plot.
 */
public class BarChart {
	
	private ArrayList<Integer> values;
	private ArrayList<Integer> valuesOpposit;
	private ArrayList<String> names;
	private ArrayList<String> namesOpposit;
	private Reference ref;
	private int columns;

	
	/**
	 * Used to setup all the info a Plot needs
	 * @param ref assumes non null Reference, that referes over Positions
	 * that should be used in the plot.
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public BarChart(Reference ref, int rows,  int columns) {
		this.values = new ArrayList<Integer>();
		this.names = new ArrayList<String>();
		this.valuesOpposit = new ArrayList<Integer>();
		this.namesOpposit = new ArrayList<String>();
		this.ref = ref;
		this.columns = columns;
	}
	
	/**
	 * Adds all expressions evaluated toString from row 1, to names list.
	 */
	private void updateNameList() {
		if (!names.isEmpty())
			names.clear();
		int k = 1;
			for (Expression exp : ref) {
				names.add(exp.toString());
				if (k >= columns)
					break;
				k++;
			}	
	}
	/**
	 * Adds all expression evaluated toInt from row 2, to values list
	 */
	private void updateValueList() {
		if (!values.isEmpty())
			values.clear();
		int k = 0;
		for (Expression exp : ref) { 
			k++;
			if (k <= columns)
				continue;
			values.add(exp.toInt());	
		}	
	}
	
	
	/**
	 * Sets name list to hold expression from row 2 instead of 1
	 */
	private void oppositNameList() {
		if (!namesOpposit.isEmpty())
			namesOpposit.clear();
		int k = 0;
		for (Expression exp : ref) { 
			k++;
			if (k <= columns)
				continue;
			namesOpposit.add(exp.toString());	
		}	
	}
	/**
	 * Sets value list to be hold expression from row 1 instead of 2
	 */
	private void oppositValueList() {
		if (!valuesOpposit.isEmpty())
			valuesOpposit.clear();
		int k = 1;
		for (Expression exp : ref) {
			valuesOpposit.add(exp.toInt());
			if (k >= columns)
				break;
			k++;
		}
	}
	
	/**
	 * Accessor method for getting int vaues from a reference.
	 * @return Expression.toInt of row 2 in an ArrayList
	 */
	public ArrayList<Integer> getValues() { 
		if (values.isEmpty())
			this.updateValueList();
		return values;
	}

	/**
	 * Accessor method for getting names from a reference.
	 * @return Expression.toString of row 1 in an ArrayList
	 */
	public ArrayList<String> getNames() {
		if (names.isEmpty())
			this.updateNameList();
		return names;
	}

	/**
	 * Accessor method for getting names from a reference.
	 * @return Expression.toString of row 2 in an ArrayList
	 */
	public ArrayList<String> getNamesOpposit() {
		if (namesOpposit.isEmpty())
			this.oppositNameList();
		return namesOpposit;
	}

	/**
	 * Accessor method for getting int vaues from a reference.
	 * @return Expression.toInt of row 1 in an ArrayList
	 */
	public ArrayList<Integer> getValuesOpposit() {
		if (valuesOpposit.isEmpty())
			this.oppositValueList();
		return valuesOpposit;
	}
}
