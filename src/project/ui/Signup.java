package project.ui;

import project.service.UserService;
import project.util.UserTypes;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Stream;

public class Signup extends JFrame implements ActionListener {
    private final JFrame launchPageFrame;
    private final UserService userService;
    private final JFrame frame = new JFrame("User Sign up");
    private final JRadioButton adminRadioButton = new JRadioButton(UserTypes.ADMIN.description()); // id: 2
    private final JRadioButton customerRadioButton = new JRadioButton(UserTypes.CUSTOMER.description()); // id: 3
    private final JLabel usernameLabel = new JLabel("Username:");
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton signUpButton = new JButton("Sign up");
    private final JButton exitButton = new JButton("Exit");
    private final JFrame loginFrame;
    private String windowEventSource = "";

    public Signup(JFrame launchPageFrame, UserService userService, JFrame loginFrame) {
        this.launchPageFrame = launchPageFrame;
        this.userService = userService;
        this.loginFrame = loginFrame;

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("Ocean Wallpaper.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 350, 300);

        adminRadioButton.setBounds(60, 5, 200, 70);
        adminRadioButton.setFocusable(false);
        adminRadioButton.addActionListener(this);
        adminRadioButton.setOpaque(false);
        adminRadioButton.addActionListener(e -> signUpButton.setEnabled(!usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()));

        customerRadioButton.setBounds(170, 5, 200, 70);
        customerRadioButton.setFocusable(false);
        customerRadioButton.addActionListener(this);
        customerRadioButton.setOpaque(false);
        customerRadioButton.addActionListener(e -> signUpButton.setEnabled(!usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()));

        ButtonGroup userTypeRadioButtonGroup = new ButtonGroup();
        userTypeRadioButtonGroup.add(adminRadioButton);
        userTypeRadioButtonGroup.add(customerRadioButton);

        usernameLabel.setBounds(35, 40, 150, 80);
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        usernameField.setBounds(125, 70, 150, 25);
        usernameField.setPreferredSize(new Dimension(100, 70));
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                signUpButton.setEnabled(userTypeRadioButtonGroup.getSelection() != null &&
                        !usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()
                );
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                signUpButton.setEnabled(userTypeRadioButtonGroup.getSelection() != null &&
                        !usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()
                );
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                signUpButton.setEnabled(userTypeRadioButtonGroup.getSelection() != null &&
                        !usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()
                );
            }
        });

        passwordLabel.setBounds(35, 90, 150, 80);
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        passwordField.setBounds(125, 118, 150, 25);
        passwordField.setPreferredSize(new Dimension(100, 70));
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                signUpButton.setEnabled(userTypeRadioButtonGroup.getSelection() != null &&
                        !usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()
                );
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                signUpButton.setEnabled(userTypeRadioButtonGroup.getSelection() != null &&
                        !usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()
                );
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                signUpButton.setEnabled(userTypeRadioButtonGroup.getSelection() != null &&
                        !usernameField.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank()
                );
            }
        });

        signUpButton.setEnabled(false);
        signUpButton.setBounds(50, 185, 100, 30);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        exitButton.setBounds(180, 185, 100, 30);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        frame.add(customerRadioButton);
        frame.add(adminRadioButton);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signUpButton);
        frame.add(exitButton);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setLayout(null); // Use null layout for absolute positioning
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"signUpButton".equals(windowEventSource)) {
                    launchPageFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            frame.dispose(); // Close the sign-up window when the "Exit" button is clicked
            launchPageFrame.setVisible(true);
        } else if (e.getSource() == signUpButton) {
            windowEventSource = "signUpButton";
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean usernameExists = this.userService.isUserExists(username);
            if (usernameExists) {
                JOptionPane.showMessageDialog(this, "Error: Username already exists. Please choose a different username.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            String userTypeDescription = Stream.of(adminRadioButton, customerRadioButton)
                    .filter(JRadioButton::isSelected)
                    .map(JRadioButton::getText)
                    .findFirst()
                    .orElse(UserTypes.CUSTOMER.description());
            UserTypes userType = UserTypes.description(userTypeDescription)
                    .orElse(UserTypes.CUSTOMER);

            boolean created = this.userService.createUser(username, password, userType.id());
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
