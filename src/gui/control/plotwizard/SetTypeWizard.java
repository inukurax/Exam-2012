package gui.control.plotwizard;

import gui.MainFrame;
import gui.Plot;
import gui.Plot.PlotType;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetTypeWizard extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jlType;
	private JComboBox<Plot.PlotType> jbcType;
	private JButton cancelButton;
	private JButton nextButton;
	private JPanel imgPanel;
	
	public SetTypeWizard(final Plot plot) {
		super(MainFrame.instance, true);
		jlType = new JLabel("Choose type of plot:");
		jbcType = new JComboBox<Plot.PlotType>();
		for (Plot.PlotType type : plot.getLegalTypes()) {
				jbcType.addItem(type);
		}
		jbcType.setSelectedIndex(0);
		jbcType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				plot.setType((PlotType) jbcType.getSelectedItem());
				plot.plotPaint(plot.getGraphics());
				imgPanel.repaint();
			}
			
		});
		cancelButton = new JButton("Cancel");
		nextButton = new JButton("Next");
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SetColorWizard(plot);
			}
		});
		
		add(jlType);
		add(jbcType);
		plot.plotPaint(plot.getGraphics());
		imgPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(plot.getImage(), 0, 0, this.getWidth(),
						this.getHeight(), null);
			}
		};
        imgPanel.setPreferredSize( new Dimension(plot.getWidth() + 10, 
        		plot.getHeight() + 10)); 
		add(imgPanel);

		add(cancelButton);
		add(nextButton);
	    FlowLayout experimentLayout = new FlowLayout();
        setPreferredSize( new Dimension(plot.getWidth() + 15,
        		180 + plot.getHeight())); 
        setResizable(false);
        pack();
        setLayout(experimentLayout);
		setTitle("Plot Wizard");
		setLocationRelativeTo(MainFrame.instance);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		}
}
