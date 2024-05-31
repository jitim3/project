package project.ui;

import project.dto.UserDto;
import project.service.WalletService;
import project.util.AppUtils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SuperAdminWithdraw implements ActionListener {
	private final UserDto userDto;
	private final WalletService walletService;
	private final BigDecimal balance;
	private final JFrame frame = new JFrame("WITHDRAW");
	private final JFormattedTextField amountTextField = new JFormattedTextField();
	private final JLabel withdrawLabel = new JLabel("WITHDRAW", SwingConstants.CENTER);
	private final JButton confirmWithdrawButton = new JButton(" CONFIRM WITHDRAW");
	private final JButton exitButton = new JButton("EXIT");
	private final JFrame superAdminNextPageFrame;
	private final JFrame superAdminWalletFrame;
	private String closeSource;

	public SuperAdminWithdraw(JFrame superAdminNextPageFrame, JFrame superAdminWalletFrame, UserDto userDto, WalletService walletService, BigDecimal balance) {
		this.superAdminNextPageFrame = superAdminNextPageFrame;
		this.superAdminWalletFrame = superAdminWalletFrame;
		this.userDto = userDto;
		this.walletService = walletService;
		this.balance = balance;
		
		JLabel balanceLabel = new JLabel("ENTER WITHDRAWAL AMOUNT: ");// with textfield

		balanceLabel.setBounds(100, 150, 300, 30);
		balanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

		amountTextField.setBounds(380, 150, 150, 30);
		amountTextField.setFont(new Font("Times New Roman", Font.BOLD, 17));
		amountTextField.setPreferredSize(new Dimension(20, 50));
		AppUtils.currency(amountTextField);
		amountTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				confirmWithdrawButton.setEnabled(!amountTextField.getText().isBlank());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				confirmWithdrawButton.setEnabled(!amountTextField.getText().isBlank());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				confirmWithdrawButton.setEnabled(!amountTextField.getText().isBlank());
			}
		});

		withdrawLabel.setBounds(195, 80, 300, 30);
		withdrawLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

		confirmWithdrawButton.setBounds(250, 250, 200, 30);
		confirmWithdrawButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		confirmWithdrawButton.addActionListener(this);
		confirmWithdrawButton.setFocusable(false);
		confirmWithdrawButton.setEnabled(false);

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
		frame.add(balanceLabel);
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
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
			String amountText = amountTextField.getText();
			amountText = amountText != null && !amountText.isBlank() ? amountText.replace(",", "") : "0.00";
			BigDecimal amountInput = BigDecimal.valueOf(Double.parseDouble(amountText))
					.setScale(2, RoundingMode.HALF_UP);
			if (amountInput.compareTo(balance) <= 0) {
				frame.dispose();
				new SuperAdminWithdrawConfirmation(superAdminNextPageFrame, userDto, walletService, amountInput);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid amount. Please enter amount less than or equal to your current balance.", "Withdrawal Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			this.closeSource = "unknown";
			System.exit(0);
		}
	}
}
