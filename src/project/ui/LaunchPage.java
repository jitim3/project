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
	private final JFrame frame = new JFrame("SouthShore Hub Oasis");
	private final JButton getStartedButton = new JButton("Get Started");
	private final JLabel nameLabel = new JLabel("SOUTHSHORE             HUB OASIS", SwingConstants.CENTER);
	private final JLabel taglineLabel = new JLabel("Where Every      Stay is a Story", SwingConstants.CENTER);

	public LaunchPage() {
		nameLabel.setBounds(70, 155, 500, 70);
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		taglineLabel.setBounds(95, 190, 500, 70);
		taglineLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

		getStartedButton.setBounds(270, 300, 150, 30);
		getStartedButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getStartedButton.addActionListener(this);
		getStartedButton.setFocusable(false);

		// cover imageIcon
		ImageIcon icon = new ImageIcon("beach2.png");

		// for the background
		ImageIcon coverbackground = new ImageIcon("firstpage.jpg");
		Image backgroundImage = coverbackground.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
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
			new Coverpage();
		}
	}
}

class Coverpage implements ActionListener {
	private final UserService userService;
	private final JFrame frame = new JFrame();
	private final JButton customerButton = new JButton("Customer");
	private final JButton adminButton = new JButton("Admin");
	private final JButton superAdminButton = new JButton("Super Admin");

	public Coverpage() {
		this.userService = new DefaultUserService();

		customerButton.setBounds(75, 50, 200, 40);
		customerButton.setFocusable(false);
		customerButton.addActionListener(this);

		adminButton.setBounds(75, 100, 200, 40);
		adminButton.setFocusable(false);
		adminButton.addActionListener(this);

		superAdminButton.setBounds(75, 150, 200, 40);
		superAdminButton.setFocusable(false);
		superAdminButton.addActionListener(this);

		// GUI ICON
		ImageIcon icon = new ImageIcon("beach2.png"); // Set my Imported Icon

		// GUI BACKGROUND
		ImageIcon backgroundImageIcon = new ImageIcon("beach.jpg");
		Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
		frame.setIconImage(backgroundImage);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		frame.setLocation(300, 250);
		frame.add(customerButton);
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
		if (e.getSource() == customerButton) {
			frame.dispose();
			new NewWindow_Customer(this.userService);
		} else if (e.getSource() == adminButton) {
			frame.dispose();
			new NewWindow_Admin(this.userService);
		} else {
			frame.dispose();
			new NewWindow_SuperAdmin(this.userService);
		}
	}
}
