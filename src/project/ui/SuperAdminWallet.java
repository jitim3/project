package project.ui;

import project.dto.UserDto;
import project.service.WalletService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

public class SuperAdminWallet {
    private final WalletService walletService = new WalletService();
    private final BigDecimal balance;
    private final UserDto userDto;
    private final JFrame frame = new JFrame("SUPER ADMIN WALLET");
    private final JLabel viewWalletLabel = new JLabel("VIEW WALLET", SwingConstants.CENTER);
    private final JLabel balanceLabel = new JLabel("BALANCE: ", SwingConstants.CENTER);
    private final JLabel moneyLabel = new JLabel("0.00 ", SwingConstants.CENTER);
    private final JButton withdrawButton = new JButton("WITHDRAW");
    private final JButton exitButton = new JButton("EXIT");
    private final JFrame superAdminMenu;
    private String closeSource;

    public SuperAdminWallet(JFrame superAdminMenu, UserDto userDto) {
        this.superAdminMenu = superAdminMenu;
        this.userDto = userDto;

        balance = walletService.getSuperAdminBalance(userDto.getId());
        moneyLabel.setText(balance.toString());
        moneyLabel.setBounds(200, 150, 300, 30);
        moneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

        balanceLabel.setBounds(100, 150, 300, 30);
        balanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

        viewWalletLabel.setBounds(195, 80, 300, 30);
        viewWalletLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        withdrawButton.setBounds(270, 250, 150, 30);
        withdrawButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        withdrawButton.setFocusable(false);
        withdrawButton.setEnabled(balance.compareTo(BigDecimal.ZERO) > 0);
        withdrawButton.addActionListener(e -> withdrawButton());

        exitButton.setBounds(270, 300, 150, 30);
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> {
            frame.dispose();
            superAdminMenu.setVisible(true);
        });

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"withdrawButton".equals(closeSource)) {
                    superAdminMenu.setVisible(true);
                }
            }
        });
    }

    private void withdrawButton() {
        this.closeSource = "withdrawButton";
        frame.dispose();
        new SuperAdminWithdraw(superAdminMenu, frame, userDto, walletService, balance);
    }
}
