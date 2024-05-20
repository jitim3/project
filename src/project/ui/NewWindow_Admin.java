package project.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.service.UserService;

public class NewWindow_Admin implements ActionListener {
	private final UserService userService;
	private final JFrame frame = new JFrame("Admin");
	private final JLabel label = new JLabel();
	private final JButton loginButton = new JButton("Log in");
	private final JButton signupButton = new JButton("Sign up");
	private final JButton exitButton = new JButton("EXIT");

	public NewWindow_Admin(final UserService userService) {
		this.userService = userService;

		// Import logo for the Customer frame
		ImageIcon icon = new ImageIcon("beach2.png");

		// Import background for the Customer frame
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setIconImage(icon.getImage());
		frame.add(loginButton);
		frame.add(signupButton);
		frame.add(exitButton);
		frame.add(backgroundLabel);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		loginButton.setBounds(75, 50, 200, 40);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);

		signupButton.setBounds(75, 100, 200, 40);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);

		exitButton.setBounds(75, 150, 200, 40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			frame.dispose();
			AdminDatabaseLogin AdminlogInwindow = new AdminDatabaseLogin(userService);
		} else if (e.getSource() == signupButton) {
			frame.dispose();
			AdminDatabaseSignup AdminsignUpwindow = new AdminDatabaseSignup(userService);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}	
}