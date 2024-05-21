package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.UserDto;
import project.service.ResortService;
import project.service.impl.DefaultResortService;
import project.ui.town.Alcoy;
import project.ui.town.Barili;
import project.ui.town.Carcar;
import project.ui.town.Moalboal;
import project.ui.town.Oslob;
import project.ui.town.SanTander;

public class Towns extends JFrame implements ActionListener { // Prompts after user log in
	private final UserDto userDto;
	private final ResortService resortService;
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
		this.resortService = new DefaultResortService();
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			Carcar window = new Carcar(this.userDto, frame);
		} else if (e.getSource() == button1) {
			frame.dispose();
			Barili window = new Barili(this.userDto, frame);
		} else if (e.getSource() == button2) {
			frame.dispose();
			Moalboal window = new Moalboal(this.userDto, frame);
		} else if (e.getSource() == button3) {
			frame.dispose();
			Alcoy window = new Alcoy(this.userDto, frame);
		} else if (e.getSource() == button4) {
			frame.dispose();
			SanTander window = new SanTander(this.userDto, frame);
		} else if (e.getSource() == button5) {
			frame.dispose();
			Oslob window = new Oslob(this.userDto, frame);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}
}
