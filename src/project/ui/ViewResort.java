package project.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.ResortDto;

public class ViewResort {
	public final JFrame frame = new JFrame("View Resort");

	public ViewResort(ResortDto resortDto) {
//		this.frame.setLayout(new GridLayout(registeredResorts.size(), 1));

		if (resortDto != null) {
			JLabel resortLabel = new JLabel(resortDto.name());
			this.frame.add(resortLabel);
		}

		frame.setSize(500, 550);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
