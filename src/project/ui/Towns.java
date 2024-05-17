package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import project.util.DatabaseConnectionFactory;
import project.util.UserTypes;

public class Towns extends JFrame implements ActionListener { // Prompts after user log in
	private final UserDto userDto;
	JFrame frame = new JFrame("Where to?");
	JLabel label = new JLabel("TOWN");
	JButton button = new JButton("Carcar"); // CARCAR BUTTON id: 3
	JButton button1 = new JButton("Barili");// Barili BUTTON id: 2
	JButton button2 = new JButton("Moalboal");// Moalboal BUTTON id: 4
	JButton button3 = new JButton("Alcoy");// Alcoy BUTTON id: 1
	JButton button4 = new JButton("SanTander");// SAN TANDER BUTTON id: 6
	JButton button5 = new JButton("Oslob");// OSLOB BUTTON id: 5
	JButton button6 = new JButton("EXIT");// EXIT BUTTON
	//

	public Towns(UserDto userDto) {
		this.userDto = userDto;
		// Set logo to the frame
		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);
		// Add components to the frame

		label.setBounds(200, 35, 200, 125);
		label.setFont(new Font("+", Font.PLAIN, 28));

		button.setBounds(70, 180, 150, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(250, 180, 150, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(70, 240, 150, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);

		button3.setBounds(250, 240, 150, 40);
		button3.setFocusable(false);
		button3.addActionListener(this);

		button4.setBounds(70, 300, 150, 40);
		button4.setFocusable(false);
		button4.addActionListener(this);

		button5.setBounds(250, 300, 150, 40);
		button5.setFocusable(false);
		button5.addActionListener(this);

		button5.setBounds(250, 300, 150, 40);
		button5.setFocusable(false);
		button5.addActionListener(this);

		button6.setBounds(160, 370, 150, 40);
		button6.setFocusable(false);
		button6.addActionListener(this);

		frame.setLocation(300, 250);
		frame.add(button);
		frame.add(button6);
		frame.add(button5);
		frame.add(button4);
		frame.add(button3);
		frame.add(button2);
		frame.add(button1);
		frame.add(label);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(500, 550);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			Carcar window = new Carcar(this.userDto);
		} else if (e.getSource() == button1) {
			frame.dispose();
			Barili window = new Barili(this.userDto);
		} else if (e.getSource() == button2) {
			frame.dispose();
			Moalboal window = new Moalboal(this.userDto);
		} else if (e.getSource() == button3) {
			frame.dispose();
			Alcoy window = new Alcoy(this.userDto);

		} else if (e.getSource() == button4) {
			frame.dispose();
			SanTander window = new SanTander(this.userDto);
		} else if (e.getSource() == button5) {
			frame.dispose();
			Oslob window = new Oslob(this.userDto);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

	public static class Carcar implements ActionListener { // CARCAR FRAME
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

		public static void generateButton(JFrame frame, Set<String> resortNames) {

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
				System.out.println(query);
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

	public static class Barili implements ActionListener {
		private final UserDto userDto;
		JFrame frame = new JFrame("Barili");
		JButton button = new JButton("Back");

		Barili(UserDto userDto) {
			this(userDto, null);
		}

		Barili(UserDto userDto, String resortName) {
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

		public static void generateButton(JFrame frame, Set<String> resortNames) {

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
					stmt.setInt(1, 2);
					stmt.setLong(2, this.userDto.getId());
				} else {
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, 2);
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

	public static class Moalboal implements ActionListener {
		private final UserDto userDto;
		JFrame frame = new JFrame("Moalboal");
		JButton button = new JButton("Back");

		Moalboal(UserDto userDto) {
			this(userDto, null);
		}

		Moalboal(UserDto userDto, String resortName) {
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

		public static void generateButton(JFrame frame, Set<String> resortNames) {

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
					stmt.setInt(1, 4);
					stmt.setLong(2, this.userDto.getId());
				} else {
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, 4);
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

	public static class Alcoy implements ActionListener {
		private final UserDto userDto;
		JFrame frame = new JFrame("Alcoy");
		JButton button = new JButton("Back");

		Alcoy(UserDto userDto) {
			this(userDto, null);
		}

		Alcoy(UserDto userDto, String resortName) {
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

		public static void generateButton(JFrame frame, Set<String> resortNames) {

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
					stmt.setInt(1, 1);
					stmt.setLong(2, this.userDto.getId());
				} else {
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, 1);
				}
				System.out.println(query);
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

	public static class SanTander implements ActionListener {
		private final UserDto userDto;
		JFrame frame = new JFrame("San Tander");
		JButton button = new JButton("Back");

		SanTander(UserDto userDto) {
			this(userDto, null);
		}

		SanTander(UserDto userDto, String resortName) {
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

		public static void generateButton(JFrame frame, Set<String> resortNames) {

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
					stmt.setInt(1, 5);
					stmt.setLong(2, this.userDto.getId());
				} else {
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, 5);
				}
				System.out.println(query);
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

	public static class Oslob implements ActionListener {
		private final UserDto userDto;
		JFrame frame = new JFrame("Oslob");
		JButton button = new JButton("Back");

		Oslob(UserDto userDto) {
			this(userDto, null);
		}

		Oslob(UserDto userDto, String resortName) {
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

		public static void generateButton(JFrame frame, Set<String> resortNames) {

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
				System.out.println(query);
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

}
