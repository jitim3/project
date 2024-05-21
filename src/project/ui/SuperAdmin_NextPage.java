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
	private final JLabel label = new JLabel("Welcome Super Admin!");
	private final JButton viewRegisteredResortButton = new JButton("View Registered Resort");
	private final JButton approvalButton = new JButton("Approval");
	private final JButton viewWalletButton = new JButton("View Wallet");
	private final JButton viewTransactionButton = new JButton("View Transaction");
	private final JButton exitButton = new JButton("Exit");

	public SuperAdmin_NextPage() {
		viewRegisteredResortButton.setBounds(220, 100, 230, 40);
		viewRegisteredResortButton.setFocusable(false);
		viewRegisteredResortButton.addActionListener(this);

		approvalButton.setBounds(220, 160, 230, 40);
		approvalButton.setFocusable(false);
		approvalButton.addActionListener(this);

		viewWalletButton.setBounds(220, 220, 230, 40);
		viewWalletButton.setFocusable(false);
		viewWalletButton.addActionListener(this);

		viewTransactionButton.setBounds(220, 280, 230, 40);
		viewTransactionButton.setFocusable(false);
		viewTransactionButton.addActionListener(this);

		exitButton.setBounds(220, 340, 230, 40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		label.setBounds(180, 40, 500, 30);
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);

		frame.add(exitButton);
		frame.add(viewTransactionButton);
		frame.add(viewWalletButton);
		frame.add(approvalButton);
		frame.add(viewRegisteredResortButton);
		frame.add(label);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewRegisteredResortButton) {
			frame.dispose();
			new Superadmin_Registered_Resort(frame);
		} else if (e.getSource() == approvalButton) {
			frame.dispose();
			new ApprovalResort(frame);
		} else if (e.getSource() == viewWalletButton) {
			frame.dispose();
			new SuperAdminWallet(frame);
		} else if (e.getSource() == viewTransactionButton) {
			frame.dispose();
			new SuperAdmin_Transaction(frame);
		} else if (e.getSource() == exitButton) {
			System.exit(0);
			frame.dispose();
		}
	}
}
