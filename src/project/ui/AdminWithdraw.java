package project.ui;

import project.dto.UserDto;
import project.service.WalletService;
import project.util.AppUtils;

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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

class AdminWithdraw extends JFrame implements ActionListener {
    private final UserDto userDto;
    private final WalletService walletService;
    private final BigDecimal balance;
    private final JFrame adminWalletFrame;
    private final JFrame frame = new JFrame("ADMIN WITHDRAW");
    private final JLabel balanceLabel = new JLabel("ENTER WITHDRAWAL AMOUNT: ");// with textfield
    private final JFormattedTextField amountField = new JFormattedTextField();
    private final JLabel label = new JLabel("WITHDRAW", SwingConstants.CENTER);
    private final JButton widbutton = new JButton(" CONFIRM WITHDRAW");
    private final JButton exitbutton = new JButton("EXIT");
    private final JFrame adminRegisteredResortMenuFrame;
    private String windowEventSource = "";

    public AdminWithdraw(JFrame adminRegisteredResortMenuFrame, JFrame adminWalletFrame, UserDto userDto, WalletService walletService, BigDecimal balance) {
        this.adminRegisteredResortMenuFrame = adminRegisteredResortMenuFrame;
        this.adminWalletFrame = adminWalletFrame;
        this.userDto = userDto;
        this.walletService = walletService;
        this.balance = balance;

        balanceLabel.setBounds(100, 150, 300, 30);
        balanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

        amountField.setBounds(380, 150, 150, 30);
        amountField.setFont(new Font("Times New Roman", Font.BOLD, 17));
        amountField.setPreferredSize(new Dimension(20, 50));
        AppUtils.currency(amountField);
        amountField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                widbutton.setEnabled(!amountField.getText().isBlank());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                widbutton.setEnabled(!amountField.getText().isBlank());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                widbutton.setEnabled(!amountField.getText().isBlank());
            }
        });

        label.setBounds(195, 80, 300, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        widbutton.setBounds(250, 250, 200, 30);
        widbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        widbutton.setFocusable(false);
        widbutton.setEnabled(false);

        exitbutton.setBounds(250, 300, 200, 30);
        exitbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        exitbutton.setFocusable(false);

        frame.setResizable(false);

        // cover imageIcon
        ImageIcon icon = new ImageIcon("beach2.png");

        // for the background
        ImageIcon coverbackground = new ImageIcon("background.jpg");
        Image backgroundImage = coverbackground.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        //frame.add(withdrawAmount);
        frame.add(amountField);
        frame.add(balanceLabel);
        frame.add(widbutton);
        frame.add(exitbutton);
        frame.add(label);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"widbutton".equals(windowEventSource)) {
                    adminRegisteredResortMenuFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == widbutton) {
            windowEventSource = "widbutton";
            String amountText = amountField.getText();
            amountText = amountText != null && !amountText.isBlank() ? amountText.replace(",", "") : "0.00";
            BigDecimal amountInput = BigDecimal.valueOf(Double.parseDouble(amountText))
                    .setScale(2, RoundingMode.HALF_UP);
            if (amountInput.compareTo(balance) <= 0) {
                frame.dispose();
                new AdminWithdrawConfirmation(adminRegisteredResortMenuFrame, userDto, walletService, amountInput);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter amount less than or equal to your current balance.", "Withdrawal Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            frame.dispose();
            adminRegisteredResortMenuFrame.setVisible(true);
        }
    }
}