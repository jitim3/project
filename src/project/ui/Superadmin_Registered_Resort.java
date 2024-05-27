package project.ui;

import project.dto.UserDto;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Superadmin_Registered_Resort implements ActionListener {
	private final UserDto userDto;
	private final JFrame frame = new JFrame("Registered Resort");
	private final JLabel label = new JLabel("VIEW ALL RESORT");
	private final JButton exitButton = new JButton("BACK");
	private final JFrame superAdminNextPagFrame;

	public Superadmin_Registered_Resort(JFrame superAdminNextPagFrame, UserDto userDto) {
		this.superAdminNextPagFrame = superAdminNextPagFrame;
		this.userDto = userDto;

		exitButton.setBounds(275, 350, 150, 40);
		exitButton.addActionListener(this);
		exitButton.setFocusable(false);

		label.setBounds(210, 40, 500, 30);
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);

		frame.add(label);
		frame.add(exitButton);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (superAdminNextPagFrame != null) {
					superAdminNextPagFrame.setVisible(true);
				} else {
					new SuperAdmin_NextPage(userDto);
				}
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/*
	 * diri na part is katong sa document so once na mo click siya sa resort name
	 * then makita na niya ang document na gi send ni admin nga na verify na sa
	 * super admin.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			frame.dispose();
			if (superAdminNextPagFrame != null) {
				superAdminNextPagFrame.setVisible(true);
			} else {
				new SuperAdmin_NextPage(userDto);
			}
		}
	}
}
