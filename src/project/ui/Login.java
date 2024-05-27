package project.ui;

import project.dto.UserDto;
import project.service.UserService;
import project.service.impl.DefaultUserService;
import project.util.AppUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;

public class Login extends JFrame implements ActionListener {
    private final UserService userService;
    private final JFrame frame = new JFrame("User Log in");
    private final JLabel usernameLabel = new JLabel("Username:");
    private final JTextField usernameField = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("Login");
    private final JButton signupButton = new JButton("Sign up");
    private final JButton exitButton = new JButton("Exit");
    private final JButton iconButton;
    private final JFrame launchPageFrame;
    private String windowEventSource = "";

    public Login(JFrame launchPageFrame) {
        this.launchPageFrame = launchPageFrame;
        this.userService = new DefaultUserService();

        frame.setLayout(null); // Use null layout for absolute positioning

        // Load and resize the login icon
        ImageIcon iconlogin = new ImageIcon("admin.png");
        Image iconImage = iconlogin.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconlogin = new ImageIcon(iconImage);

        // Create a JButton with the resized icon
        iconButton = new JButton(resizedIconlogin);
        iconButton.setBounds(600, 20, 35, 35); // Adjust size to match the resized icon
        iconButton.setFocusable(false);
        iconButton.addActionListener(this);

        usernameLabel.setBounds(230, 66, 150, 80);
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        usernameField.setBounds(320, 94, 150, 25);
        usernameField.setPreferredSize(new Dimension(100, 70));

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        passwordLabel.setBounds(230, 140, 150, 80);
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        passwordField.setBounds(320, 168, 150, 25);
        passwordField.setPreferredSize(new Dimension(100, 70));

        loginButton.setBounds(220, 300, 100, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        signupButton.setBounds(380, 300, 100, 30);
        signupButton.setFocusable(false);
        signupButton.addActionListener(this);

        exitButton.setBounds(300, 350, 100, 30);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signupButton);
        frame.add(iconButton);
        frame.add(backgroundLabel);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"iconButton".equals(windowEventSource) && !"signupButton".equals(windowEventSource) && !"loginButton".equals(windowEventSource)) {
                    launchPageFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == iconButton) {
            windowEventSource = "iconButton";
            frame.dispose();
            new CoverPage(launchPageFrame, frame, userService);
        } else if (source == signupButton) {
            windowEventSource = "signupButton";
            frame.dispose();
            new CustomerSignup(launchPageFrame, userService, frame);
        } else if (source == loginButton) {
            windowEventSource = "loginButton";
            boolean loginSuccessful = false;
            while (!loginSuccessful) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                Optional<UserDto> userDtOptional = this.userService.getUserByUsernameAndPassword(username, password);
                if (userDtOptional.isPresent()) {
                    JOptionPane.showMessageDialog(this, "Login successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    loginSuccessful = true;
                    UserDto loggedInUserDto = userDtOptional.get();
                    int userTypeId = loggedInUserDto.getUserType().id();
                    if (AppUtils.isUserTypeSuperAdmin(userTypeId)) {
                        new SuperAdminMenu(launchPageFrame, loggedInUserDto);
                    } else if (AppUtils.isUserTypeAdmin(userTypeId)) {
                        new AdminMenu(launchPageFrame, loggedInUserDto);
                    } else if (AppUtils.isUserTypeCustomer(userTypeId)) {
                        new CustomerMenu(launchPageFrame, loggedInUserDto);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid user type. Please contact the Administrator.", "Login Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }
}