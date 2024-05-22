package project.ui;

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
import javax.swing.SwingConstants;

public class SuperAdminWallet implements ActionListener {
	private final JFrame frame = new JFrame("SUPER ADMIN WALLET");
	private final JLabel viewWalletLabel = new JLabel("VIEW WALLET", SwingConstants.CENTER);
	private final JLabel balanceLabel = new JLabel("BALANCE: ", SwingConstants.CENTER);
	private final JLabel moneyLabel = new JLabel("0.00 ", SwingConstants.CENTER);
	private final JButton withdrawButton = new JButton("WITHDRAW");
	private final JButton exitButton = new JButton("EXIT");
	private final JFrame superAdminNextPageFrame;
	private String closeSource;

	public SuperAdminWallet(JFrame superAdminNextPageFrame) {
		this.superAdminNextPageFrame = superAdminNextPageFrame;

		moneyLabel.setBounds(200, 150, 300, 30);
		moneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

		balanceLabel.setBounds(100, 150, 300, 30);
		balanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

		withdrawButton.setBounds(270, 250, 150, 30);
		withdrawButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		withdrawButton.setFocusable(false);
		withdrawButton.addActionListener(this);

		exitButton.setBounds(270, 300, 150, 30);
		exitButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		viewWalletLabel.setBounds(195, 80, 300, 30);
		viewWalletLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

		// cover imageIcon
		ImageIcon icon = new ImageIcon("beach2.png");

		// for the background
		ImageIcon coverbackground = new ImageIcon("figma.jpg");
		Image backgroundImage = coverbackground.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);

		frame.add(balanceLabel);
		frame.add(moneyLabel);
		frame.add(withdrawButton);
		frame.add(exitButton);
		frame.add(viewWalletLabel);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(700, 500);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"withdrawButton".equals(closeSource)) {
					superAdminNextPageFrame.setVisible(true);
				}
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == withdrawButton) {
			this.closeSource = "withdrawButton";
			frame.dispose();
			new SuperAdminWithdraw(superAdminNextPageFrame, frame);
		} else if (e.getSource() == exitButton) {
//			System.exit(0);
			this.closeSource = "exitButton";
			frame.dispose();
			superAdminNextPageFrame.setVisible(true);
		} else {
			System.exit(0);
		}
	}
}
