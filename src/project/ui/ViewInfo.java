package project.ui;

import javax.swing.JFrame;

public class ViewInfo {
	private final JFrame frame = new JFrame("View Information");

	public ViewInfo() {
		frame.setSize(250, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
