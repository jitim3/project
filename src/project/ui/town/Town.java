package project.ui.town;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public interface Town extends ActionListener {
	JFrame getFrame();
	
	void generateButton();
}
