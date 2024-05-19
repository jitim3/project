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

public class Carcar implements Town { // CARCAR FRAME
	private UserDto userDto;
	JFrame frame = new JFrame("Carcar");
	public JButton back = new JButton("Back");

	public Carcar(UserDto userDto) {
		this(userDto, null);
	}

	public Carcar(UserDto userDto, String resortName) {
		this.userDto = userDto;
		// this.resortName = resortName;
		if (resortName == null || resortName.trim().isEmpty()) {
			Set<String> resortNames = this.getRegisteredResorts();
			generateButton(frame, resortNames);
		} else {
			generateButton(frame, Collections.singleton(resortName));
		}
		frame.setLocationRelativeTo(null);

		back.setBounds(370, 420, 100, 25);
		back.setFocusable(false);
		back.addActionListener(this);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);

		frame.add(back);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			frame.dispose();
			new Towns(this.userDto);
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
				stmt.setInt(1, 3);
				stmt.setLong(2, this.userDto.getId());
			} else {
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, 3);
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