package project.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import project.dto.UserDto;
import project.ui.Towns.Alcoy;
import project.ui.Towns.Barili;
import project.ui.Towns.Carcar;
import project.ui.Towns.Moalboal;
import project.ui.Towns.Oslob;
import project.ui.Towns.SanTander;
import project.util.DatabaseConnectionFactory;

public class TownRegister implements ActionListener {
	private final UserDto userDto;
	JFrame frame = new JFrame("Select Town to Register");
	JLabel label = new JLabel("Welcome Admin!");
	JLabel label1 = new JLabel("Please select town to register");
	JLabel label2 = new JLabel("Enter name of the resort");
	JRadioButton button = new JRadioButton("Carcar"); // id: 3
	JRadioButton button1 = new JRadioButton("Barili"); // id: 2
	JRadioButton button2 = new JRadioButton("Moalboal"); // id: 4
	JRadioButton button3 = new JRadioButton("Alcoy"); // id: 1
	JRadioButton button4 = new JRadioButton("SanTander"); // id: 6
	JRadioButton button5 = new JRadioButton("Oslob"); // id: 5
	JTextField field = new JTextField();
	JButton display = new JButton("Display");
	public String resortName;

	TownRegister(UserDto userDto) {
		this.userDto = userDto;

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 550, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 550);

		ImageIcon icon = new ImageIcon("beach2.png");

		button.setBounds(75, 120, 200, 70);
		button.setFocusable(false);
		button.addActionListener(this);
		button.setOpaque(false);

		button1.setBounds(135, 120, 200, 70);
		button1.setFocusable(false);
		button1.addActionListener(this);
		button1.setOpaque(false);

		button2.setBounds(190, 120, 200, 70);
		button2.setFocusable(false);
		button2.addActionListener(this);
		button2.setOpaque(false);

		button3.setBounds(265, 120, 200, 70);
		button3.setFocusable(false);
		button3.addActionListener(this);
		button3.setOpaque(false);

		button4.setBounds(320, 120, 200, 70);
		button4.setFocusable(false);
		button4.addActionListener(this);
		button4.setOpaque(false);

		button5.setBounds(200, 160, 150, 30);
		button5.setFocusable(false);
		button5.addActionListener(this);
		button5.setOpaque(false);

		display.setBounds(200, 280, 150, 30);
		display.setFocusable(false);
		display.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(button);
		group.add(button1);
		group.add(button2);
		group.add(button3);
		group.add(button4);
		group.add(button5);

		label2.setBounds(30, 190, 200, 125);
		label2.setFont(new Font("+", Font.PLAIN, 12));
		label2.setForeground(Color.BLACK);

		label1.setBounds(121, 35, 350, 125);
		label1.setFont(new Font("+", Font.PLAIN, 18));
		label1.setForeground(Color.BLACK);

		label.setBounds(141, 25, 300, 100);
		label.setFont(new Font("+", Font.BOLD, 26));
		label.setForeground(Color.BLACK);

		field.setBounds(180, 243, 200, 20);
		field.setPreferredSize(new Dimension(50, 190));

		frame.setLayout(null);
		frame.add(field);
		frame.add(display);
		frame.add(button5);
		frame.add(button4);
		frame.add(button3);
		frame.add(button2);
		frame.add(button1);
		frame.add(button);
		frame.add(label2);
		frame.add(label);
		frame.add(label1);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setVisible(true);
		frame.setSize(500, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == display) {
			resortName = field.getText();
			int resortId = 0;
			try (Connection conn = DatabaseConnectionFactory.getConnection();
					PreparedStatement psInsert = conn.prepareStatement("INSERT INTO resort (name, town_id, user_id) VALUES (?, ?, ?)")) {
				// Insert resort name into the database
				if (button.isSelected()) {
					
					resortId = 3;
				} else if (button1.isSelected()) {
					resortId = 2;
				} else if (button2.isSelected()) {
					resortId = 4;
				} else if (button3.isSelected()) {
					resortId = 1;
				} else if (button4.isSelected()) {
					resortId = 6;
				} else if (button5.isSelected()) {
					resortId = 5;
				}

				psInsert.setString(1, resortName);
				psInsert.setInt(2, resortId);
				psInsert.setLong(3, userDto.getId());
				psInsert.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			if (resortId == 3) {
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				int choice = JOptionPane.showConfirmDialog(null,
						"Do you want to proceed to Register Information Fill up?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				frame.dispose();
				if (choice == JOptionPane.YES_OPTION) {
					ResortInfo frame = new ResortInfo();
				} else {
					ResortInfo resortinfo = new ResortInfo();
					frame.dispose();
					Carcar carcar = new Carcar(this.userDto, resortName);
//		        	Carcar.generateButton(carcar.getFrame(), resortName);
				}
			} else if (resortId == 2) {
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				Barili barili = new Barili(this.userDto, resortName);
//		        	Barili.generateButton(barili.getFrame(), resortName);
			} else if (resortId == 4) {
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				Moalboal moalboal = new Moalboal(this.userDto, resortName);
			} else if (resortId == 1) {
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				Alcoy alcoy = new Alcoy(this.userDto, resortName);
//					Alcoy.generateButton(alcoy.getFrame(), resortName);
			} else if (resortId == 6) {
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				SanTander santander = new SanTander(this.userDto, resortName);
//					SanTander.generateButton(santander.getFrame(), resortName);
			} else if (resortId == 5) {
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				Oslob oslob = new Oslob(this.userDto, resortName);
				// Oslob.generateButton(oslob.getFrame(), resortName);
			}

		}
	}

}
