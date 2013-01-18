
import gui.BarChart;
import gui.Plot.PlotType;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.arithmetic.Int;
import spreadsheet.textual.Text;

import java.awt.Graphics;
import java.io.File;
 
 
public class BufferedImageTest{
 
	public static void main(String[] args) throws java.io.IOException {
		final Range range = new Range(new Position(0,0), new Position(2,1));
			Application.instance.set(new Position(0,0), new Text("Kategori:"));
			Application.instance.set(new Position(1,0), new Text("cars"));

			Application.instance.set(new Position(0,1),  new Text("antal"));

			Application.instance.set(new Position(1,1), new Int(1));
			Application.instance.set(new Position(2,0), new Text("persons"));

			Application.instance.set(new Position(2,1), new Int(1000));


		Application.instance.setCurrentRange(range);
		Application.instance.setCurrentPosition(new Position(0,0));

		final int column = range.getColumnCount();
		final int row = range.getRowCount();				
		// 30 should be plotXStart
		JFrame canvas = new JFrame();
		canvas.setSize(500,500);
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setTitle("It's a cat.");
		Container pane = canvas.getContentPane();
		ColorPanel panel = new ColorPanel((60 * column) + 40, 300);
		panel.simplePaint(panel.getGraphics());
		pane.add(panel);
		
		canvas.setVisible(true);

	}
}
 
class ColorPanel extends JPanel{

	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Integer> values;
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

	
	public ColorPanel(final int width, final int height){
	this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	this.imgWidth = image.getWidth();
	this.imgHeight = image.getHeight();
	this.range = Application.instance.getCurrentRange();		
	this.ref = new Reference(Application.instance.getWorksheet(), range);
	column = range.getColumnCount();
	row = range.getRowCount();
	switch (row) {
	case 1 : 
		if (column != 1)
			type = PlotType.ONEX;
		else
			type = PlotType.ONEONE;
		break;
	case 2 : type = PlotType.TWOX;
		break;
	}
	this.barChart = new BarChart(ref, row, column);
	this.names = barChart.getNames();
	this.values = barChart.getValues();
	this.leftBar = 40;
	}
	
	public void simplePaint(Graphics g) {
		// might be redundent null check
		if (values == null || values.size() == 0)
			return;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, imgWidth, imgHeight);
		int minValue = 0;
		int maxValue = 0;
		if (this.type.equals(PlotType.XTWO)) {
			this.barChart.oppositList();
			names = this.barChart.getNamesOpposit();
			values = this.barChart.getValuesOpposit();
		}
		
		for (int i = 0; i < values.size(); i++) {
			minValue =  Math.min(minValue,  values.get(i));
			maxValue =  Math.max(maxValue,  values.get(i));
		}
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int barWidth = (imgWidth - 1 - leftBar) / values.size();
				
		FontMetrics fontMetrics = g.getFontMetrics(getFont());
			  
		int bottom = fontMetrics.getHeight(); // height of text
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
//			  
		if (type.equals(PlotType.ONEONE)) {
			g.setColor(barColor);
			int barX =leftBar + 2;
			int value = Application.instance.get().toInt();
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
		// draws the bars
		g.drawLine(leftBar - 15, imgHeight - bottom, imgWidth, imgHeight - bottom);
		g.drawString("-" + Integer.toString(0) + "-", leftBar - 15, imgHeight - bottom);
		
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
			int nameWidth = fontMetrics.stringWidth(names.get(i));
			int xcord = leftBar + i * barWidth + (barWidth - nameWidth) / 2;
			g.drawString(names.get(i), xcord, ycord);
			g.drawString(Integer.toString(values.get(i)), 5, barY);
			
		}  
}
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
	
	public void setPillarColor(Color color) {
		this.barColor = color;
	}
 
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, null, 0,0);

	}
}