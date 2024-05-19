package project.ui.town;

import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;

public interface Town extends ActionListener {
	JFrame getFrame();
	
	void generateButton(JFrame frame, Set<String> resortNames);
}
