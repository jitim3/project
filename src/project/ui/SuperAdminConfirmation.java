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
import javax.swing.SwingConstants;

public class SuperAdminConfirmation implements ActionListener {
	// private final UserService userService = new DefaultUserService();
	private final JFrame frame = new JFrame("SUPER ADMIN CONFIRMATION");
	private final JPasswordField passwordTextField;
	private final JLabel confirmationLabel = new JLabel("CONFIRMATION", SwingConstants.CENTER);
	private final JButton confirmButton = new JButton(" CONFIRM ");
	private final JButton exitButton = new JButton("EXIT");
	private final JFrame superAdminNextPageFrame;

	public SuperAdminConfirmation(JFrame superAdminNextPageFrame) {
		this.superAdminNextPageFrame = superAdminNextPageFrame;
		
		JLabel pass = new JLabel("ENTER PASSWORD: ");// with textfield
		passwordTextField = new JPasswordField();

		pass.setBounds(130, 150, 300, 30);
		pass.setFont(new Font("Times New Roman", Font.BOLD, 17));

		passwordTextField.setBounds(310, 150, 200, 30);
		passwordTextField.setFont(new Font("Times New Roman", Font.BOLD, 17));
		passwordTextField.setPreferredSize(new Dimension(20, 50));

		confirmationLabel.setBounds(105, 80, 500, 30);
		confirmationLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

		confirmButton.setBounds(250, 250, 200, 30);
		confirmButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		confirmButton.addActionListener(this);
		confirmButton.setFocusable(false);

		exitButton.setBounds(250, 300, 200, 30);
		exitButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		exitButton.addActionListener(this);
		exitButton.setFocusable(false);

		frame.setResizable(false);

		// cover imageIcon
		ImageIcon icon = new ImageIcon("beach2.png");

		// for the background
		ImageIcon coverbackground = new ImageIcon("figma.jpg");
		Image backgroundImage = coverbackground.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);

		// frame.add(withdrawAmount);
		frame.add(passwordTextField);
		frame.add(pass);
		frame.add(confirmButton);
		frame.add(exitButton);
		frame.add(confirmationLabel);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			JOptionPane.showMessageDialog(null, "Withdraw Successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
			this.superAdminNextPageFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect Password", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
		}
	}
}
