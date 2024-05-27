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

import project.dto.UserDto;
import project.service.UserService;

public class SuperAdminLogin implements ActionListener {
	private final JFrame launchPageFrame;
	private final UserService userService;
	private final JPasswordField passwordField = new JPasswordField();
	private final JFrame frame = new JFrame("Super Admin");
	private final JLabel superAdminLabel = new JLabel("Super Admin");
	private final JLabel enterPasswordLabel = new JLabel("Enter Password:");
	private final JButton enterButton = new JButton("Enter");
	private final JButton exitButton = new JButton("Exit");

	public SuperAdminLogin(JFrame launchPageFrame, final UserService userService) {
        this.launchPageFrame = launchPageFrame;
        this.userService = userService;

		enterButton.setBounds(80, 165, 100, 30);
		enterButton.setFocusable(false);
		enterButton.addActionListener(this);

		exitButton.setBounds(200, 165, 100, 30);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		superAdminLabel.setBounds(150, 60, 150, 80);
		superAdminLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		enterPasswordLabel.setBounds(72, 123, 100, 25);
		enterPasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		passwordField.setBounds(155, 123, 100, 25);
		passwordField.setPreferredSize(new Dimension(100, 70));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 400, 350);

		frame.add(exitButton);
		frame.add(enterButton);
		frame.add(passwordField);
		frame.add(enterPasswordLabel);
		frame.add(superAdminLabel);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 350);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
			String password = new String(passwordField.getPassword());
			Optional<UserDto> userDtOptional = this.userService.getSuperAdmin("sa", password);
			if (userDtOptional.isPresent()) {
				JOptionPane.showMessageDialog(null, "Log in Successfully!", "Log in", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				new SuperAdminMenu(launchPageFrame, userDtOptional.get());
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			frame.dispose();
		}
	}
}
