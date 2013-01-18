package gui;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import spreadsheet.Application;
import spreadsheet.Range;
import spreadsheet.Reference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** A class for drawing plots.
 */
public final class Plot {

	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Integer> values = new ArrayList<Integer>();;
	private BufferedImage image;
	private final Reference ref;
	private final Range range;
	private int imgWidth;
	private int imgHeight;
	private BarChart barChart;
	private int leftBar;
	private Color barColor = Color.LIGHT_GRAY;
	private PlotType type;
	private int row;
	private int column;
	private Graphics graphics;
	private int value;
	private boolean first = true;
	
	/**
	 * enum for setting what type of plot should be made.
	 */
	public enum PlotType {
		ONEONE, ONEX, TWOX, XTWO;
		
		public String toString() {
			switch (this) {
			case ONEONE:
				return "One * One";
			case ONEX:
				return "One * x";
			case TWOX:
				return "Two * x";
			case XTWO:
				return "x * Two";
			default:
				return null;
				
			}
		}
	}

	/** Constructs a new RGB  image.
	 */
	public Plot(final int width, final int height){
	    this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    this.graphics = this.image.getGraphics();
		this.imgWidth = image.getWidth();
		this.imgHeight = image.getHeight();
		this.range = Application.instance.getCurrentRange();		
		this.ref = new Reference(Application.instance.getWorksheet(), range);
		column = range.getColumnCount();
		row = range.getRowCount();
		this.barChart = new BarChart(ref, row, column);
		this.leftBar = 40;
	}
	
	public void plotPaint(Graphics g) {
		if (first)
			setup();
		if (type.equals(PlotType.TWOX)) {
			this.names = barChart.getNames();
			if (names.isEmpty())
				names.add(barChart.getRow1Name());
			this.values = barChart.getValues();
			if (values.isEmpty()) 
				values.add(barChart.getRow2Value());
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, imgWidth, imgHeight);
		int minValue = 0;
		int maxValue = 0;
		if (type.equals(PlotType.XTWO)) {
			names = this.barChart.getNamesOpposit();
			if (names.isEmpty())
				names.add(barChart.getRow1Name());
			values = this.barChart.getValuesOpposit();
			if (values.isEmpty()) 
				values.add(barChart.getRow2Value());
		}
		if (this.type.equals(PlotType.ONEX)) {
			values = barChart.getValuesOpposit();
		}
		for (int i = 0; i < values.size(); i++) {
			minValue =  Math.min(minValue,  values.get(i));
			maxValue =  Math.max(maxValue,  values.get(i));
		}
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int barWidth = (imgWidth - 1 - leftBar) / values.size();
				
		FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
			  
		int bottom = fontMetrics.getHeight(); // height of text
		//scales the bars so we can have values larger than img height
		double scale = (imgHeight - (bottom * 2)) / (double)(maxValue - minValue);
		int ycord = imgHeight - fontMetrics.getDescent();
		// draws the y-axis
		g.setColor(Color.BLACK);
		g.drawLine(leftBar, imgHeight - bottom, leftBar, 0);
			  
		//WIP: was trying to draw more values on the y-axis,
		//but it was unprecise.
//		int lineY = imgHeight - (bottom + 6);
//		for (double i = 0; i <= 10; i++) {
//			g.setColor(Color.GRAY);
//			g.drawString(String.format("%.0f",
//		        	(imgHeight- lineY - 22) / scale), 0, lineY);
//			g.setColor(Color.LIGHT_GRAY);
//			g.drawLine(leftBar + 1, lineY, imgWidth, lineY);
//			lineY -= ((imgHeight -bottom ) / 10);
//		}
		if (type.equals(PlotType.ONEONE) || type.equals(PlotType.ONEX)) {
			g.setColor(barColor);
			int barX =leftBar + 2;
			
			scale = (imgHeight - (bottom * 2)) / (double)(value);

			int height = (int) (value * scale);
			int barY = imgHeight - bottom;
			g.drawLine(5, barY, imgWidth, barY);
			g.drawString(Integer.toString(0), 5, barY);
			barY -= height;
			g.drawLine(5, barY, imgWidth, barY);
			g.drawString(Integer.toString(value), 5, barY);

			g.setColor(Color.LIGHT_GRAY);
			g.setColor(barColor);
			g.fillRect(barX, barY, imgWidth - leftBar, height);
			g.setColor(Color.BLACK);
			g.drawRect(barX, barY,imgWidth - leftBar, height);
			return;
		}
		g.drawLine(leftBar - 15, imgHeight - bottom, imgWidth, imgHeight - bottom);
		g.drawString("-" + Integer.toString(0) + "-", leftBar - 15, imgHeight - bottom);
	
		// draws the bars
		for (int i = 0; i < values.size(); i++) {
			int barX = i * barWidth + leftBar;
			int height = (int) (values.get(i) * scale);
			int barY = imgHeight - bottom;
			barY -= height;

			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(5, barY, imgWidth, barY);
			g.setColor(barColor);
			g.fillRect(barX, barY, barWidth, height);
			g.setColor(Color.BLACK);
			g.drawRect(barX, barY, barWidth, height);
			String str = names.get(i);
			int nameWidth = fontMetrics.stringWidth(str);
			int xcord = leftBar + i * barWidth + (barWidth - nameWidth) / 2;
			g.drawString(str, xcord, ycord);
			g.drawString(Integer.toString(values.get(i)), 5, barY);
		}  
	}
	private void setup() {
		switch (row) {
		case 1 : 
			if (column == 1) {
				this.type = PlotType.ONEONE;
				value = Application.instance.get().toInt();
				this.values.add(value);
				break;
			}
			else
				type = PlotType.ONEX;
			break;
		default : type = PlotType.TWOX;
			break;
		}
		first  = false;
	}

	/**
	 * Sets the type of plot to paint.
	 * @param type of plot
	 */
	public void setType(PlotType type) {
		this.type = type;	
	}
	
	public ArrayList<PlotType> getLegalTypes() {
		ArrayList<PlotType> array = new ArrayList<PlotType>();
		switch (row) {
		case 1 : 
			array.add(PlotType.ONEONE);
			if (row != 1)
				array.add(PlotType.ONEONE);
			break;
		case 2 : 
			array.add(PlotType.TWOX);
			array.add(PlotType.XTWO);
			break;
		}
		return array;
	}

	/** Attempts to save the image as a png file.
	 *
	 * @param path The desired filename, including extension.
	 */
	public void save(final String path) throws IOException {
		ImageIO.write(this.image, "png", new File(path));
	}
  
	public Graphics getGraphics() {
		return this.graphics;
	}
  
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.image, null, 0,0);
	}
  
	public BufferedImage getImage() {
		return this.image;
	} 
	
	public int getWidth() {
		return this.imgWidth;
	}
	
	public int getHeight() {
		return this.imgHeight;
	}
	/** 
	 * Sets the Color of the bars
	 * @param color rgb
	 */
	public void setColor(Color color) {
		this.barColor = color;
	}
	
	public void brigther() {
		this.barColor = this.barColor.brighter();
	}
	
	public void darker() {
		this.barColor = this.barColor.darker();
	}
}
