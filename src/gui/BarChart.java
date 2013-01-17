package gui;

import java.util.ArrayList;

import spreadsheet.Expression;
import spreadsheet.Reference;

public class BarChart {
	
	private ArrayList<Integer> values;
	private ArrayList<String> names;
	private Reference ref;
	private String row1Name = "";
	private String row2Name = "";
	private int columns;
	private int rows;
	
	public BarChart(Reference ref, int rows,  int columns) {
		this.values = new ArrayList<Integer>();
		this.names = new ArrayList<String>();
		this.ref = ref;
		this.columns = columns;
		this.rows = rows;
		
		updateList();
	}
	
	private void updateList() {
		int k = 1;
		if (rows == 2) {
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
		k = 0;
		for (Expression exp : ref) { 
			k++;
			if (k <= columns)
				continue;
			if (k == (columns + 1) && rows != 1) {
				row2Name = exp.toString();
				continue;
			}
			values.add(exp.toInt());	
		}		
	}

	public int getMaxValue() {
		int max = Integer.MIN_VALUE;
		for (Integer i : values) {
			max = Math.max(i, max);
		}
		return max;
	}

	public ArrayList<Integer> getValues() {
		return values;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public String getRow1Name() {
		return row1Name;
	}

	public String getRow2Name() {
		return row2Name;
	}
}
