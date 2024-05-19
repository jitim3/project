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
import javax.swing.SwingUtilities;

import project.dto.UserDto;
import project.service.UserService;
import project.service.impl.DefaultUserService;
import project.util.UserTypes;

public class NewWindow_Customer implements ActionListener {
	private final UserService userService;
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	JButton button = new JButton("Log in");
	JButton button1 = new JButton("Sign up");
	JButton button2 = new JButton("EXIT");

	NewWindow_Customer(final UserService userService) {
		this.userService = userService;
		// Import logo for the Customer frame
		ImageIcon icon = new ImageIcon("beach2.png");

		// Import background for the Customer frame
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);
		// ==> Setting up the components
		label.setBounds(150, 50, 150, 100);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		frame.setLocation(300, 250);
		frame.add(label);
		frame.setTitle("Selection tab");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setVisible(true);

		frame.setIconImage(icon.getImage());
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.add(backgroundLabel);
		frame.setResizable(false);

		button.setBounds(75, 50, 200, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(75, 100, 200, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(75, 150, 200, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) { // For the Log in menu
			frame.dispose();
			new DatabaseLogin(this.userService);
		} else if (e.getSource() == button1) { // For the sign up menu
			frame.dispose();
			new DatabaseSignup(this.userService);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}

class DatabaseSignup extends JFrame implements ActionListener { // Sign up Menu
	private static final long serialVersionUID = -6259664142720174365L;
	private final UserService userService;
	JFrame frame = new JFrame("User Sign up");
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton signUpButton;
	private JButton exitButton;

	DatabaseSignup(final UserService userService) {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			dispose(); // Close the sign-up window when the "Exit" button is clicked
		} else if (e.getSource() == signUpButton) {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());

			boolean usernameExists = this.userService.isUserExists(username);
			if (usernameExists) {
				JOptionPane.showMessageDialog(this,
						"Error: Username already exists. Please choose a different username.", "Sign Up Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			boolean created = this.userService.createUser(username, password, UserTypes.CUSTOMER.id());
			if (created) {
				JOptionPane.showMessageDialog(this, "Account created successfully!", "Sign Up",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				new DatabaseLogin(this.userService);
			} else {
				JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Sign Up Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}

class DatabaseLogin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
	private UserDto userDto;
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Towns townsFrame;
	private JFrame successFrame;

	DatabaseLogin(final UserService userService) {
		this.userService = userService;

		frame = new JFrame("User Log in");

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
		exitButton.addActionListener(this);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(this);

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
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		successFrame = new JFrame("Successful Login"); // HIMOAN UG CLASS FOR DESIGN
		JButton okButton = new JButton("OK");
		okButton.setBounds(150, 100, 100, 30);
		okButton.addActionListener(e -> {
			successFrame.dispose(); // Close the success frame
			frame.dispose(); // Close the login frame
			townsFrame = new Towns(this.userDto); // Open the Towns frame
		});
		successFrame.add(okButton);
		successFrame.setSize(400, 200);
		successFrame.setLayout(null);
		successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		successFrame.setResizable(false);
		successFrame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Login")) {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			Optional<UserDto> userDtOptional = this.userService.getCustomer(username, password);
			userDtOptional.ifPresentOrElse(userDto -> {
				this.userDto = userDto;
				successFrame.setVisible(true); // Show the success frame
				frame.dispose();
			}, () -> {
				JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
				return;
			});
		}
	}

	public static void main(String[] args) {
		// new databaseLogin();
		// new Towns();

		SwingUtilities.invokeLater(() -> new DatabaseLogin(new DefaultUserService()));

	}
}
