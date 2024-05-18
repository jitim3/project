package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
import project.service.impl.DefaultUserService;
import project.util.UserTypes;

public class NewWindow_Admin implements ActionListener {
	private final UserService userService = new DefaultUserService();
	JFrame frame = new JFrame("Admin");
	JLabel label = new JLabel();
	JButton button = new JButton("Log in");
	JButton button1 = new JButton("Sign up");
	JButton button2 = new JButton("EXIT");
//	Connection conn;
//	PreparedStatement psInsert;
//	Statement stmt;

	public NewWindow_Admin() {



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
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.add(backgroundLabel);
		frame.setLayout(null);
		frame.setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			AdminDatabaseLogin AdminlogInwindow = new AdminDatabaseLogin(userService);
		} else if (e.getSource() == button1) {
			frame.dispose();
			AdminDatabaseSignup AdminsignUpwindow = new AdminDatabaseSignup(userService);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	class AdminDatabaseLogin extends JFrame implements ActionListener {
		private final UserService userService;
		JFrame frame = new JFrame("Admin Log in");
		private JTextField usernameField;
		private JPasswordField passwordField;
		private Connection conn;
		private PreparedStatement psInsert;
		private Statement stmt;

		AdminDatabaseLogin(final UserService userService) {
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
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(350, 300);
			frame.setLocationRelativeTo(null);
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
						JOptionPane.showMessageDialog(this, "Login successful!", "Login",
								JOptionPane.INFORMATION_MESSAGE);
						loginSuccessful = true;
						MenuAdmin menuAdmin = new MenuAdmin(userDtOptional.get());
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.",
								"Login Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		}
	}

	class AdminDatabaseSignup extends JFrame implements ActionListener {
		private final UserService userService;
		JFrame frame = new JFrame("Admin Sign up");
		private JTextField usernameField;
		private JPasswordField passwordField;
		private Connection conn;
		private PreparedStatement psInsert;
		private Statement stmt;
		private JButton signUpButton;
		private JButton exitButton;

		public AdminDatabaseSignup(final UserService userService) {
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

			frame.setLayout(null);
			frame.add(usernameField);
			frame.add(passwordLabel);
			frame.add(passwordField);
			frame.add(signUpButton);
			frame.add(exitButton);
			frame.add(usernameLabel);
			frame.add(backgroundLabel);
			frame.setIconImage(icon.getImage());
			frame.setSize(350, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exitButton) {
				frame.dispose();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Close the sign-up window when the "Exit" button
																		// is clicked
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

				boolean created = this.userService.createUser(username, password, UserTypes.ADMIN.id());
				if (created) {
					JOptionPane.showMessageDialog(this, "Account created successfully!", "Sign Up",
							JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					AdminDatabaseLogin window = new AdminDatabaseLogin(this.userService);
				} else {
					JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Sign Up Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}
	
}