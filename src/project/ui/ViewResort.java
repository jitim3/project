package project.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewResort {
	public final JFrame frame = new JFrame("View Resort");

	public ViewResort(java.util.List<String> registeredResorts) {
		this.frame.setLayout(new GridLayout(registeredResorts.size(), 1));

		for (String resortName : registeredResorts) {
			JLabel resortLabel = new JLabel(resortName);
			frame.add(resortLabel);
		}

		frame.setSize(500, 550);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
