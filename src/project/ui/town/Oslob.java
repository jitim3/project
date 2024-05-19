package project.ui.town;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.UserDto;
import project.ui.Towns;
import project.util.DatabaseConnectionFactory;
import project.util.UserTypes;

public class Oslob implements Town {
	private final UserDto userDto;
	JFrame frame = new JFrame("Oslob");
	JButton button = new JButton("Back");

	public Oslob(UserDto userDto) {
		this(userDto, null);
	}

	public Oslob(UserDto userDto, String resortName) {
		this.userDto = userDto;
		if (resortName == null || resortName.trim().isEmpty()) {
			Set<String> resortNames = this.getRegisteredResorts();
			generateButton(frame, resortNames);
		} else {
			generateButton(frame, Collections.singleton(resortName));
		}
		button.setBounds(370, 420, 100, 25);
		button.setFocusable(false);
		button.addActionListener(this);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);

		frame.setLocation(300, 250);
		frame.add(button);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			Towns window = new Towns(this.userDto);

		}
	}

	@Override
	public void generateButton(JFrame frame, Set<String> resortNames) {
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);

		int y = 65;
		for (String resortName : resortNames) {
			JButton resortButton = new JButton(resortName);
			resortButton.setBounds(50, y, 400, 75);
			resortButton.setOpaque(false);
			resortButton.setFocusable(false);
			frame.getContentPane().add(resortButton);
			frame.add(resortButton);

			y = y + 85;
		}

		frame.setLayout(null);
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
	}

	private Set<String> getRegisteredResorts() {
		Set<String> resorts = new HashSet<>();

		try {
			Connection conn = DatabaseConnectionFactory.getConnection();
			PreparedStatement stmt;
			String query = "SELECT name FROM resort WHERE town_id = ?";
			if (this.userDto != null && this.userDto.getUserType().id() == UserTypes.ADMIN.id()) {
				query += " AND user_id = ?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, 6);
				stmt.setLong(2, this.userDto.getId());
			} else {
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, 6);
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				resorts.add(rs.getString("name"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return resorts;
	}
}
