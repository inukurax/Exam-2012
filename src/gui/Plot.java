package gui;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Range;
import spreadsheet.Reference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** A class for drawing plots.
 */
public final class Plot {

  private final BufferedImage image;
  private final Graphics graphics;
	private int PIXELMULTIPLIER = 5;
	private int pillarWidth;
	private final Reference ref;
	private final Range range;
	private int startPos;
	private String row1Name;
	private String row2Name;
	private int imgWidth;
	private int imgHeight;
	private int row;
	private int column;
	private ArrayList<String> firstRow;
	private ArrayList<Integer> secondRow ;
  
  private enum PlotType {
	  ONEONE, ONEROW, TWOROW;
  }

  /** Constructs a new RGB image.
   */
  public Plot(final int width, final int height) {
    this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    this.graphics = this.image.getGraphics();
    this.imgWidth = width;
    this.imgHeight = height;
    this.range = Application.instance.getCurrentRange();
	this.ref = new Reference(Application.instance.getWorksheet(), range);
	column = range.getColumnCount();
	row = range.getRowCount();
	BarChart barChart = new BarChart(ref, row, column);
    this.firstRow = barChart.getNames();
    this.secondRow = barChart.getValues();
    PIXELMULTIPLIER = barChart.getMaxValue() / (column - 1);
    paintPlot(this.getGraphics());
	startPos = 30;
	int divide = (column - 1) == 0 ? 1 : column - 1; // to not get diveded by zero error
	pillarWidth = (imgWidth - startPos) / divide;
	System.out.println("PixelMultiplier: " + PIXELMULTIPLIER);
  }
  
  private void paintPlot(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, imgWidth, imgHeight);
		g2d.setColor(Color.BLACK);
		int y = imgHeight - 10;
		int xPos = startPos + (pillarWidth / 3);

		
		// draws the strings from the first row
		for (String str : firstRow) {
			g2d.drawString(str , xPos, y);
			xPos += pillarWidth;
		}
		
		xPos = startPos;
		int strHeight = 10; // height of string in standard font in win 7
		g2d.drawLine(startPos, y + 1, imgWidth, y + 1);
		g2d.drawLine(startPos, imgHeight - strHeight, startPos, 0);
		// draws second row as pillars
		for (Integer i : secondRow) {
			int pillarHeight = i * PIXELMULTIPLIER;
			int yPos = y - pillarHeight - strHeight;
			System.out.println(pillarHeight + " correct height is: " + i);
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(xPos, yPos, pillarWidth, pillarHeight );
			g2d.setColor(Color.BLACK);
			g2d.drawRect(xPos, yPos, pillarWidth, pillarHeight );
			xPos += pillarWidth;
		}
		System.out.println(imgHeight + " and y:" + y);

		// draws numbers on left side
		int linePos = y - strHeight;
		for (int i = 0; i < (imgHeight - strHeight); i+= PIXELMULTIPLIER) {
			int actual = linePos + (imgHeight - strHeight) / PIXELMULTIPLIER;
			g2d.drawString(Integer.toString(actual) + "-" , startPos - 20, linePos);
			System.out.println(linePos);
			linePos -= strHeight;
		}
	
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

}
