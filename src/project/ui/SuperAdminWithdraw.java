package project.ui;

import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SuperAdminWithdraw implements ActionListener {
	private final JFrame frame = new JFrame("WITHDRAW");
	private final JTextField amountTextField = new JTextField();
	private final JLabel withdrawLabel = new JLabel("WITHDRAW", SwingConstants.CENTER);
	private final JButton confirmWithdrawButton = new JButton(" CONFIRM WITHDRAW");
	private final JButton exitButton = new JButton("EXIT");
	private final JFrame superAdminNextPageFrame;
	private final JFrame superAdminWalletFrame;
	private String closeSource;

	public SuperAdminWithdraw(JFrame superAdminNextPageFrame, JFrame superAdminWalletFrame) {
		this.superAdminNextPageFrame = superAdminNextPageFrame;
		this.superAdminWalletFrame = superAdminWalletFrame;
		
		JLabel balance = new JLabel("ENTER WITHDRAWAL AMOUNT: ");// with textfield

		balance.setBounds(100, 150, 300, 30);
		balance.setFont(new Font("Times New Roman", Font.BOLD, 17));

		amountTextField.setBounds(380, 150, 150, 30);
		amountTextField.setFont(new Font("Times New Roman", Font.BOLD, 17));
		amountTextField.setPreferredSize(new Dimension(20, 50));

		withdrawLabel.setBounds(195, 80, 300, 30);
		withdrawLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

		confirmWithdrawButton.setBounds(250, 250, 200, 30);
		confirmWithdrawButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		confirmWithdrawButton.addActionListener(this);
		confirmWithdrawButton.setFocusable(false);

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
		frame.add(amountTextField);
		frame.add(balance);
		frame.add(confirmWithdrawButton);
		frame.add(exitButton);
		frame.add(withdrawLabel);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setLayout(null);
		frame.setSize(700, 500);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"confirmWithdrawButton".equals(closeSource)) {
					superAdminWalletFrame.setVisible(true);
				}
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			this.closeSource = "exitButton";
			frame.dispose();
//			new SuperAdminConfirmation();
			superAdminWalletFrame.setVisible(true);
		} else if (e.getSource() == confirmWithdrawButton) {
			this.closeSource = "confirmWithdrawButton";
			frame.dispose();
			new SuperAdminConfirmation(superAdminNextPageFrame);
		} else {
			this.closeSource = "unknown";
			System.exit(0);
		}
	}
}
