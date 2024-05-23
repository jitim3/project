package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import project.service.UserService;
import project.service.impl.DefaultUserService;

public class LaunchPage implements ActionListener {
	private final UserService userService;
	private final JFrame frame = new JFrame("SouthShore Hub Oasis");
	private final JButton getStartedButton = new JButton("Get Started");
	private final JLabel nameLabel = new JLabel("SOUTHSHORE             HUB OASIS", SwingConstants.CENTER);
	private final JLabel taglineLabel = new JLabel("Where Every      Stay is a Story", SwingConstants.CENTER);

	public LaunchPage() {
		this.userService = new DefaultUserService(); // Initialize userService

		nameLabel.setBounds(70, 155, 500, 70);
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		taglineLabel.setBounds(95, 190, 500, 70);
		taglineLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

		getStartedButton.setBounds(270, 300, 150, 30);
		getStartedButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getStartedButton.addActionListener(this);
		getStartedButton.setFocusable(false);

		// Cover imageIcon
		ImageIcon icon = new ImageIcon("beach2.png");
		
		// Background image
		ImageIcon coverBackground = new ImageIcon("firstpage.jpg");
		Image backgroundImage = coverBackground.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);
		
		frame.add(getStartedButton);
		frame.add(nameLabel);
		frame.add(taglineLabel);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getStartedButton) {
			frame.dispose();
			Updated_Login login= new Updated_Login(userService);
			//new Coverpage(userService); // Pass userService to Coverpage
		}
	}

	public static void main(String[] args) {
		new LaunchPage();
	}
}