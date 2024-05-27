package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.dto.UserDto;
import project.service.UserService;

public class AdminLogin extends JFrame implements ActionListener {
	private final JFrame launchPageFrame;
	private final UserService userService;
	JFrame frame = new JFrame("Admin Log in");
	private JTextField usernameField;
	private JPasswordField passwordField;

	public AdminLogin(JFrame launchPageFrame, UserService userService) {
		this.launchPageFrame = launchPageFrame;
		this.userService = userService;
		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField();
		JButton exitButton = new JButton("Exit");

		JButton loginButton = new JButton("Log in");

		usernameLabel.setBounds(35, 40, 150, 80);
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		usernameField.setBounds(125, 68, 150, 25);
		usernameField.setPreferredSize(new Dimension(100, 70));

		passwordLabel.setBounds(35, 90, 150, 80);
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		passwordField.setBounds(125, 118, 150, 25);
		passwordField.setPreferredSize(new Dimension(100, 70));

		loginButton.setBounds(50, 185, 100, 30);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);

		exitButton.setBounds(180, 185, 100, 30);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		frame.add(usernameLabel);
		frame.add(usernameField);
		frame.add(passwordLabel);
		frame.add(passwordField);
		frame.add(loginButton);
		frame.add(exitButton);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) { // Check if the action command is "Exit"
			System.exit(0); // Exit the program
		} else if (e.getActionCommand().equals("Log in")) {
			boolean loginSuccessful = false;
			while (!loginSuccessful) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				Optional<UserDto> userDtOptional = this.userService.getAdmin(username, password);
				if (userDtOptional.isPresent()) {
					JOptionPane.showMessageDialog(this, "Login successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
					loginSuccessful = true;
					new AdminMenu(launchPageFrame, userDtOptional.get());
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
	}
}
