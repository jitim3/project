package project.ui;

import project.dto.UserDto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerMenu implements ActionListener {
	private final JFrame launchPageFrame;
	private final UserDto userDto;
	private final JFrame frame = new JFrame("Customer Menu");
	private final JButton viewResortsButton = new JButton("View Resorts");
	private final JButton profileButton = new JButton("Profile");
	private final JButton exitButton = new JButton("EXIT");
	private String windowEventSource = "";

	public CustomerMenu(JFrame launchPageFrame, UserDto userDto) {
		this.launchPageFrame = launchPageFrame;
		this.userDto = userDto;

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		viewResortsButton.setBounds(75, 50, 200, 40);
		viewResortsButton.setFocusable(false);
		viewResortsButton.addActionListener(this);

		profileButton.setBounds(75, 100, 200, 40);
		profileButton.setFocusable(false);
		profileButton.addActionListener(this);

		exitButton.setBounds(75, 150, 200, 40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		frame.add(viewResortsButton);
		frame.add(profileButton);
		frame.add(exitButton);
		frame.setResizable(false);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"viewResortsButton".equals(windowEventSource) && !"profileButton".equals(windowEventSource)) {
					launchPageFrame.setVisible(true);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewResortsButton) { // For the Log in menu
			windowEventSource = "viewResortsButton";
			frame.dispose();
			new Towns(frame, userDto);
		} else if (e.getSource() == profileButton) { // For the sign up menu
			windowEventSource = "profileButton";
			frame.dispose();
			new CustomerViewOrUpdateInformaton(userDto.getId(), frame);
		} else {
			frame.dispose();
			launchPageFrame.setVisible(true);
		}
	}
}
