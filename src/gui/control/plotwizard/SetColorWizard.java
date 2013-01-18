package gui.control.plotwizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import gui.MainFrame;
import gui.Plot;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class SetColorWizard extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox<ColorName> jcbColor;
	private JPanel imgPanel;
	private JButton cancelButton;
	private JButton saveButton;
	private JButton darkButton;
	private JButton brightButton;
	private JColorChooser jccColor;
	private JButton jbColor;
	private Plot plot;
	private JDialog jdialog;
	private JButton jbOk;
	private JButton jbClose;

	public SetColorWizard(final Plot plot) {
		super(MainFrame.instance, true);
		this.plot = plot;
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		jcbColor = new JComboBox<ColorName>();
		addColors();
		jcbColor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				ColorName color = (ColorName) jcbColor.getSelectedItem();
				plot.setColor(color.getColor());
				plot.plotPaint(plot.getGraphics());
				imgPanel.repaint();
			}
			
		});
		
		jbColor = new JButton("Color Chooser");
		jbColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
		        jdialog.setVisible(true);
			    }
			});
		
		imgPanel = new JPanel() {
			private static final long serialVersionUID = 11L;

			public void paintComponent(Graphics g) {
				g.drawImage(plot.getImage(), 0, 0, this.getWidth(),
						this.getHeight(), null);
			}
		};
		
		imgPanel.setToolTipText("This is just a preview, in poor quality");
		
		darkButton = new JButton("Darker");
		darkButton.setToolTipText("Makes the Bars darker");
		brightButton = new JButton("Brighter");		
		brightButton.setToolTipText("Makes the Bars brigther");

		cancelButton = new JButton("Cancel");
		saveButton = new JButton("Save Image");
		
		darkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plot.darker();
				plot.plotPaint(plot.getGraphics());
				imgPanel.repaint();
			}
		});
		
		brightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plot.brigther();
				plot.plotPaint(plot.getGraphics());
				imgPanel.repaint();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SavePlotWizard(plot);
			}
		});
		JPanel top = new JPanel();
		top.add(jbColor);
		top.add(jcbColor);

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		buttonPanel.setLayout(flowLayout);
		buttonPanel.add(brightButton);
		buttonPanel.add(darkButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);
		
        imgPanel.setPreferredSize(new Dimension(plot.getWidth() + 30, 
        		plot.getHeight() + 30)); 
        
		add(top, BorderLayout.PAGE_START);
		
		add(imgPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);

        setResizable(false);
        jccColor = new JColorChooser();
        jdialog = new JDialog(this, true);
        jbOk = new JButton("Ok");
        jbOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				plot.setColor(jccColor.getColor());
				plot.plotPaint(plot.getGraphics());
				imgPanel.repaint();				
			}
        	
        });
        jbClose = new JButton("Close");
        jbClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jdialog.dispose();
			}
        	
        });

        jdialog.setLayout(new FlowLayout());
        jdialog.add(jbOk);
        jdialog.add(jbClose);

        jdialog.setResizable(false);
        jdialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jdialog.add(jccColor);
        jdialog.pack();
		pack();
		
		setLocationRelativeTo(MainFrame.instance);
		setTitle("Plot Wizard");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Adds some colors to the combobox
	 */
	private void addColors() {
		jcbColor.addItem(new ColorName(Color.LIGHT_GRAY, "Light Grey"));
		jcbColor.addItem(new ColorName(Color.BLACK, "Black"));
		jcbColor.addItem(new ColorName(Color.RED, "Red"));
		jcbColor.addItem(new ColorName(Color.CYAN, "Cyan"));
		jcbColor.addItem(new ColorName(Color.GREEN, "Green"));
		jcbColor.addItem(new ColorName(Color.MAGENTA, "Magenta"));
		jcbColor.addItem(new ColorName(Color.ORANGE, "Orange"));
		jcbColor.addItem(new ColorName(Color.PINK, "Pink"));
		jcbColor.addItem(new ColorName(Color.YELLOW, "Yellow"));
	}
	
	/**
	 * Class used for storing Colors in JCombobox
	 */
	private class ColorName {
		
		private String name;
		private Color color;
		
		public ColorName(final Color color, final String name) {
			this.name = name;
			this.color = color;
		}

		public Color getColor() {
			return color;
		}
		
		public String toString() {
			return this.name;
		}
	}
}
