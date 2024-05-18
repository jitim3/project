package project.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.service.UserService;
import project.service.impl.DefaultUserService;

public class LaunchPage implements ActionListener {
	private final UserService userService = new DefaultUserService();
	JFrame frame = new JFrame();
	JButton button = new JButton("Customer");
	JButton button1 = new JButton("Admin");
	JButton button2 = new JButton("Super Admin");

	public LaunchPage() {

		button.setBounds(75, 50, 200, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(75, 100, 200, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(75, 150, 200, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);

		// GUI ICON
		ImageIcon icon = new ImageIcon("beach2.png"); // Set my Imported Icon

		// GUI BACKGROUND
		ImageIcon backgroundImageIcon = new ImageIcon("beach.jpg");
		Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
		frame.setIconImage(backgroundImage);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		frame.setLocation(300, 250);
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setTitle("Resort");
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			NewWindow_Customer window = new NewWindow_Customer(this.userService);
		} else if (e.getSource() == button1) {
			frame.dispose();
			NewWindow_Admin window = new NewWindow_Admin();
		} else {
			frame.dispose();
			NewWindow_SuperAdmin window = new NewWindow_SuperAdmin();
		}

	}

}
