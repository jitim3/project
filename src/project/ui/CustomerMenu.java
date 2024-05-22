package project.ui;

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

public class CustomerMenu implements ActionListener {
	private final UserDto userDto;
	private final ResortService resortService;
	JFrame frame = new JFrame("Customer Menu");
	JButton viewResortsButton = new JButton("View Resorts");
	JButton profileButton = new JButton("Profile");
	JButton exitButton = new JButton("EXIT");

	public CustomerMenu(UserDto userDto) {
		this.userDto = userDto;
		this.resortService = new DefaultResortService();

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		viewResortsButton.setBounds(75, 50, 200, 40);
		viewResortsButton.setFocusable(false);
		viewResortsButton.addActionListener(this);

		profileButton.setBounds(75, 100, 200, 40);
		profileButton.setFocusable(false);
		profileButton.addActionListener(this);

		exitButton.setBounds(75, 150, 200, 40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		frame.add(viewResortsButton);
		frame.add(profileButton);
		frame.add(exitButton);
		frame.setResizable(false);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewResortsButton) { // For the Log in menu
			frame.dispose();
			new Towns(frame, userDto);
		} else if (e.getSource() == profileButton) { // For the sign up menu
			new CustomerInformation();
		} else {
			frame.dispose();
		}
	}
}
