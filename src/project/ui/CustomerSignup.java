package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.service.UserService;
import project.util.UserTypes;

public class CustomerSignup extends JFrame implements ActionListener {
	private static final long serialVersionUID = -6259664142720174365L;
	private final UserService userService;
	private final JFrame frame = new JFrame("User Sign up");
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton signUpButton;
	private JButton exitButton;
	private final JFrame loginFrame;

	public CustomerSignup(UserService userService, JFrame loginFrame) {
		this.userService = userService;
		this.loginFrame = loginFrame;

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("Ocean Wallpaper.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField();
		exitButton = new JButton("Exit");
		signUpButton = new JButton("Sign up");

		usernameLabel.setBounds(35, 40, 150, 80);
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		usernameField.setBounds(125, 70, 150, 25);
		usernameField.setPreferredSize(new Dimension(100, 70));

		passwordLabel.setBounds(35, 90, 150, 80);
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		passwordField.setBounds(125, 118, 150, 25);
		passwordField.setPreferredSize(new Dimension(100, 70));

		signUpButton.setBounds(50, 185, 100, 30);
		signUpButton.setFocusable(false);
		signUpButton.addActionListener(this);

		exitButton.setBounds(180, 185, 100, 30);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		frame.add(usernameLabel);
		frame.add(usernameField);
		frame.add(passwordLabel);
		frame.add(passwordField);
		frame.add(signUpButton);
		frame.add(exitButton);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setLayout(null); // Use null layout for absolute positioning
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			frame.dispose(); // Close the sign-up window when the "Exit" button is clicked
		} else if (e.getSource() == signUpButton) {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());

			boolean usernameExists = this.userService.isUserExists(username);
			if (usernameExists) {
				JOptionPane.showMessageDialog(this, "Error: Username already exists. Please choose a different username.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			boolean created = this.userService.createUser(username, password, UserTypes.CUSTOMER.id());
			if (created) {
				JOptionPane.showMessageDialog(this, "Account created successfully!", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				loginFrame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
