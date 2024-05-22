package project.ui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.ResortDto;

public class ViewResort {
	public final JFrame frame = new JFrame("View Resort");

	public ViewResort(List<ResortDto> registeredResorts) {
		this.frame.setLayout(new GridLayout(registeredResorts.size(), 1));

		registeredResorts.forEach(resortDto -> {
			JLabel resortLabel = new JLabel(resortDto.name());
			this.frame.add(resortLabel);
		});

		
		frame.setSize(500, 550);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
