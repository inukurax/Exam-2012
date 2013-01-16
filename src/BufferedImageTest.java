
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.arithmetic.Int;
import spreadsheet.textual.Text;

import java.awt.Graphics;
 
 
public class BufferedImageTest{
 
	public static void main(String[] args) throws java.io.IOException {
		final Range range = new Range(new Position(0,0), new Position(1,1));
			Application.instance.set(new Position(0,0), new Text("persons"));
			Application.instance.set(new Position(1,0), new Text("cars"));

			Application.instance.set(new Position(0,1),  new Int(5));

			Application.instance.set(new Position(1,1), new Int(8));

		Application.instance.setCurrentRange(range);
		Application.instance.setCurrentPosition(new Position(0,0));
		
		final int column = range.getColumnCount();
		final int row = range.getRowCount();
				
		BufferedImage cat = new BufferedImage((column * 60) + 30, row * 50, BufferedImage.TYPE_INT_RGB);
		// 30 should be startPos
		JFrame canvas = new JFrame();
		canvas.setSize(cat.getWidth()+100,cat.getHeight()+100);
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setTitle("It's a cat.");
		Container pane = canvas.getContentPane();
		ColorPanel panel = new ColorPanel(cat, column , row);
		pane.add(panel);
		canvas.setVisible(true);
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
	
	public ColorPanel(BufferedImage image, int column, int row){
		startPos = 30;
	img = image;
	width = (image.getWidth() - startPos) / column;
	range = Application.instance.getCurrentRange();		
	ref = new Reference(Application.instance.getWorksheet(), range);

	int k = 1;
	// draws the strings from the first row
	for (Expression exp : ref) {
		firstRow.add(exp.toString());
		if (k >= column)
			break;
		k++;
	}
	k = 0;
	// draws second row as pillars
	secondRow = new ArrayList<Integer>();
	for (Expression exp : ref) { 
		k++;
		if (k <= column)
			continue;
		secondRow.add(exp.toInt());
	}

	}
 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, null, 0,0);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, img.getWidth(), img.getHeight());

		g2d.setColor(Color.BLACK);
		int y = img.getHeight();
		int xPos = startPos;
		for (String str : firstRow) {
			g2d.drawString(str , xPos, y);
			xPos += 5 + width;
		}
		xPos = startPos;
		int strHeight = 10;
		
		
		for (Integer i : secondRow) {
			int pos = i * PIXELMULTIPLIER;
			int yPos = y - pos - strHeight;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(xPos, yPos, width, pos );
			g2d.setColor(Color.BLACK);
			g2d.drawRect(xPos, yPos, width, pos );
			xPos += width;
		}

	}
}