package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;

public class CopyListener implements ActionListener {
	
	public final static CopyListener instance = new CopyListener();
	
	private CopyListener() {
		Application.instance.getCurrentPosition();
	}
	
	public 

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
