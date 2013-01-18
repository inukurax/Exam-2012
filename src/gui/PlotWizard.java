package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlotWizard extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jlType;
	private JComboBox<String> jbcType;
	private JButton cancelButton;
	private JButton nextButton;
	private JPanel imgPanel;
	
	public PlotWizard(Plot plot) {
		super(MainFrame.instance, true);
		jlType = new JLabel("Choose type of plot:");
		jbcType = new JComboBox<String>();
		for (Plot.PlotType type : Plot.PlotType.values())
				jbcType.addItem(type.toString());
		jbcType.setSelectedIndex(2);
		jbcType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
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
		add(jlType);
		add(jbcType);
		add(cancelButton);
		add(nextButton);
	    FlowLayout experimentLayout = new FlowLayout();
        setPreferredSize( new Dimension(200,300)); 
        setLocationRelativeTo(MainFrame.instance);
        setResizable(false);
        pack();
        setLayout(experimentLayout);
		setTitle("Plot Wizard");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		}
}
