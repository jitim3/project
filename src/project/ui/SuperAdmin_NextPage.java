package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SuperAdmin_NextPage implements ActionListener {
	private final JFrame frame = new JFrame("Super Admin");
	private final JLabel welcomeSuperAdminLabel = new JLabel("Welcome Super Admin!");
	private final JButton vewRegisteredResortButton = new JButton("View Registered Resort");
	private final JButton approvalButton = new JButton("Approval");
	private final JButton viewWalletButton = new JButton("View Wallet");
	private final JButton exitButton = new JButton("Exit");

	public SuperAdmin_NextPage() {
		vewRegisteredResortButton.setBounds(140, 130, 230, 40);
		vewRegisteredResortButton.setFocusable(false);
		vewRegisteredResortButton.addActionListener(this);

		approvalButton.setBounds(140, 190, 230, 40);
		approvalButton.setFocusable(false);
		approvalButton.addActionListener(this);

		viewWalletButton.setBounds(140, 250, 230, 40);
		viewWalletButton.setFocusable(false);
		viewWalletButton.addActionListener(this);

		exitButton.setBounds(140, 310, 230, 40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		welcomeSuperAdminLabel.setBounds(160, 40, 250, 80);
		welcomeSuperAdminLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 500);

		frame.add(exitButton);
		frame.add(viewWalletButton);
		frame.add(approvalButton);
		frame.add(vewRegisteredResortButton);
		frame.add(welcomeSuperAdminLabel);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vewRegisteredResortButton) {
			frame.dispose();
			ViewInfo window = new ViewInfo();
		}
	}
}
