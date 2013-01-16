
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import spreadsheet.Application;
import spreadsheet.Expression;
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

			Application.instance.set(new Position(1,1), new Int(10));
			Application.instance.set(new Position(2,0), new Text("persons"));

			Application.instance.set(new Position(2,1), new Int(5));


		Application.instance.setCurrentRange(range);
		Application.instance.setCurrentPosition(new Position(0,0));
		
		final int column = range.getColumnCount();
		final int row = range.getRowCount();
		int rowMaxHeight = row * 70;
				
		BufferedImage cat = new BufferedImage(((column- 1) * 80) + 30, rowMaxHeight, BufferedImage.TYPE_INT_RGB);
		// 30 should be startPos
		JFrame canvas = new JFrame();
		canvas.setSize(cat.getWidth()+100,cat.getHeight()+100);
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setTitle("It's a cat.");
		Container pane = canvas.getContentPane();
		ColorPanel panel = new ColorPanel(cat, column , row);
		panel.simplePaint(cat.getGraphics());
		pane.add(panel);
		
		canvas.setVisible(true);
	    ImageIO.write(cat, "png", new File("image.png"));

	}
}
 
class ColorPanel extends JPanel{

	private ArrayList<String> firstRow = new ArrayList<String>();
	private ArrayList<Integer> secondRow;
	
	private int PIXELMULTIPLIER = 5;
	private int width;
	private BufferedImage img;
	private final Reference ref;
	private final Range range;
	private int startPos;
	private String row1Name;
	private String row2Name;
	private int imgWidth;
	private int imgHeight;
	
	public ColorPanel(BufferedImage image, int column, int row){
	startPos = 30;
	img = image;
	imgWidth = img.getWidth();
	imgHeight = img.getHeight();
	int divide = (column - 1) == 0 ? 1 : column - 1; // to not get diveded by zero error
	width = (imgWidth - startPos) / divide;
	range = Application.instance.getCurrentRange();		
	ref = new Reference(Application.instance.getWorksheet(), range);

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
	public void simplePaint(Graphics g) {
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
 
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, null, 0,0);

	}
}