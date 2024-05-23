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

class Updated_Login  extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final UserService userService;
    private UserDto userDto;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Towns townsFrame;
    private JFrame successFrame;
    private JButton iconButton;
    
    Updated_Login (UserService userService) {
        this.userService = userService;

        frame = new JFrame("User Log in");
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

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        usernameLabel.setBounds(230, 66, 150, 80);
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        usernameField.setBounds(320, 94, 150, 25);
        usernameField.setPreferredSize(new Dimension(100, 70));

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        passwordLabel.setBounds(230, 140, 150, 80);
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        passwordField.setBounds(320, 168, 150, 25);
        passwordField.setPreferredSize(new Dimension(100, 70));

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(220, 300, 100, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(380, 300, 100, 30);
        signupButton.setFocusable(false);
        signupButton.addActionListener(this);

        JButton exitButton = new JButton("Exit");
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        successFrame = new JFrame("Successful Login");
        JButton okButton = new JButton("OK");
        okButton.setBounds(150, 100, 100, 30);
        okButton.addActionListener(e -> {
            successFrame.dispose();
            frame.dispose();
            townsFrame = new Towns(frame, this.userDto);
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
        if (e.getSource() == iconButton) {
        	 frame.dispose();
        	 Coverpage windowww = new Coverpage (userService);
            //JOptionPane.showMessageDialog(this, "Icon button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("Sign up")) {
            frame.dispose();
            DatabaseSignup windows = new DatabaseSignup(userService);
           
            DatabaseSignup window1 = new DatabaseSignup(userService);
        } else if (e.getActionCommand().equals("Login")) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Optional<UserDto> userDtOptional = this.userService.getCustomer(username, password);
            userDtOptional.ifPresentOrElse(userDto -> {
                this.userDto = userDto;
                successFrame.setVisible(true);
                frame.dispose();
            }, () -> {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            });
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Updated_Login(new DefaultUserService()));
    }
}
// cover page for admin and super admin choices================================================================================

class Coverpage implements ActionListener {
	private final UserService userService;
	private final JFrame frame = new JFrame();
	private final JButton adminButton = new JButton("Admin");
	private final JButton superAdminButton = new JButton("Super Admin");
	private final JButton exitButton = new JButton("Back");

	public Coverpage(UserService userService) {
		this.userService = userService; // Initialize userService

		adminButton.setBounds(100, 50, 150, 40);
		adminButton.setFocusable(false);
		adminButton.addActionListener(this);

		superAdminButton.setBounds(100, 100, 150, 40); 
		superAdminButton.setFocusable(false);
		superAdminButton.addActionListener(this);

		exitButton.setBounds(125, 170, 100, 30);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		// GUI icon
		ImageIcon icon = new ImageIcon("beach2.png"); // Set my imported icon

		// GUI background
		ImageIcon backgroundImageIcon = new ImageIcon("Ocean Wallpaper.jpg");
		Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		frame.setLocation(300, 250);
		frame.add(exitButton);
		frame.add(adminButton);
		frame.add(superAdminButton);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setTitle("Resort");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adminButton) {
			frame.dispose();
			//new NewWindow_Admin();
			NewWindow_Admin admin = new NewWindow_Admin(userService);
			
		} if (e.getSource() == superAdminButton) {
			frame.dispose();
			 NewWindow_SuperAdmin superadmin = new NewWindow_SuperAdmin(userService);
		} else if (e.getSource() == exitButton) {
			frame.dispose();
			Updated_Login back = new Updated_Login (userService);
		
		}
	}
}