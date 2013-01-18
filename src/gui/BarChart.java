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
	private String row1Name = "";
	private int columns;
	private int rows;
	private int row2Value;
	
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
		this.rows = rows;
	}
	
	/**
	 * Adds all expressions evaluated toString from row 1, to names list.
	 */
	private void updateNameList() {
		if (!names.isEmpty())
			names.clear();
		int k = 1;
			for (Expression exp : ref) {
				if (k == 1 && columns != 1) {
					row1Name = exp.toString();
					k++;
					continue;
				}
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
			if (k == (columns + 1) && rows != 1) {
				row2Value = exp.toInt();
				continue;
			}
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
			if (k == (columns + 1) && rows != 1) {
				row1Name = exp.toString();
				continue;
			}
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
			if (k == 1 && columns != 1) {
				row2Value = exp.toInt();
				k++;
				continue;
			}
			valuesOpposit.add(exp.toInt());
			if (k >= columns)
				break;
			k++;
		}
	}
	
	public ArrayList<Integer> getValues() { 
		this.updateValueList();
		return values;
	}

	public ArrayList<String> getNames() {
		this.updateNameList();
		return names;
	}

	public String getRow1Name() {
		return row1Name;
	}

	public int getRow2Value() {
		return row2Value;
	}

	public ArrayList<String> getNamesOpposit() {
		this.oppositNameList();
		return namesOpposit;
	}

	public ArrayList<Integer> getValuesOpposit() {
		this.oppositValueList();
		return valuesOpposit;
	}
}
