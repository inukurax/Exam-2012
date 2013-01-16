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
	private int width;
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
    this.firstRow = new ArrayList<String>();
    this.secondRow = new ArrayList<Integer>();
    this.range = Application.instance.getCurrentRange();
	this.ref = new Reference(Application.instance.getWorksheet(), range);
    setVariables();
    paintPlot(this.getGraphics());
  }
  
  
  private void setVariables() {
		startPos = 30;
		column = range.getColumnCount();
		row = range.getRowCount();
		int divide = (column - 1) == 0 ? 1 : column - 1; // to not get diveded by zero error
		width = (imgWidth - startPos) / divide;

		int k = 1;
		for (Expression exp : ref) {
			if (k == 1) {
				row1Name = exp.toString();
				k++;
				continue;
			}
			firstRow.add(exp.toString());
			if (k >= column)
				break;
			k++;
		}
		k = 0;
		secondRow = new ArrayList<Integer>();
		for (Expression exp : ref) { 
			k++;
			if (k <= column)
				continue;
			if (k == (column + 1)) {
				row2Name = exp.toString();
				continue;
			}
			secondRow.add(exp.toInt());	
		}
  }


  private void paintPlot(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, imgWidth, imgHeight);
		g2d.setColor(Color.BLACK);
		int y = imgHeight;
		int xPos = startPos + (width / 3);

		
		// draws the strings from the first row
		for (String str : firstRow) {
			g2d.drawString(str , xPos, y);
			xPos += width;
		}
		
		xPos = startPos;
		int strHeight = 11; // height of string in standard font in win 7
		g2d.drawLine(startPos, imgHeight - strHeight, imgWidth, imgHeight - strHeight);
		g2d.drawLine(startPos, imgHeight - strHeight, startPos, 0);
		// draws second row as pillars
		for (Integer i : secondRow) {
			int pos = i * PIXELMULTIPLIER;
			System.out.println("height: "+ pos);
			int yPos = y - pos - strHeight;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(xPos, yPos, width, pos );
			g2d.setColor(Color.BLACK);
			g2d.drawRect(xPos, yPos, width, pos );
			xPos += width;
		}
		
		// draws numbers on left side
		int start = imgHeight - strHeight;
		int linePos = imgHeight - strHeight;
		System.out.println("start: " + start);
		for (int i = 0; i < (imgHeight - strHeight); i+= 5) {
			g2d.drawString(Integer.toString((start - linePos) / PIXELMULTIPLIER) + "-" , startPos - 20, linePos);
			
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
