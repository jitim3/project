package project.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.UserDto;
import project.util.DatabaseConnectionFactory;

public class MenuAdmin implements ActionListener {
	private final UserDto userDto;
	private final Connection conn;
	JFrame frame = new JFrame("Menu");
	JButton button = new JButton("Register resort");
	JButton button1 = new JButton("View registered resort");
	JButton button2 = new JButton("EXIT");

	public MenuAdmin(UserDto userDto) {
		this.userDto = userDto;
		this.conn = DatabaseConnectionFactory.getConnection();

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		button.setBounds(75, 50, 200, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(75, 100, 200, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(75, 150, 200, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);

		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.setResizable(false);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) { // For the Log in menu
			frame.dispose();
			new TownRegister(this.userDto);
		} else if (e.getSource() == button1) { // For the sign up menu
			try {
				java.util.List<String> registeredResorts = getRegisteredResorts();
				new ViewResort(registeredResorts);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	private java.util.List<String> getRegisteredResorts() throws SQLException {
		java.util.List<String> resorts = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT name FROM resort WHERE user_id = ?");
		stmt.setLong(1, this.userDto.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			resorts.add(rs.getString("name"));
		}
		return resorts;
	}

}
