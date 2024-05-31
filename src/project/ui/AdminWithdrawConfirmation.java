package project.ui;

import project.dto.UserDto;
import project.service.UserService;
import project.service.WalletService;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class AdminWithdrawConfirmation extends JFrame implements ActionListener {
    private final UserDto userDto;
    private final UserService userService = new UserService();
    private final WalletService walletService;
    private final BigDecimal withdrawalAmount;
    //private final UserService userService = new DefaultUserService();
    JFrame frame = new JFrame("ADMIN CONFIRMATION");
    private final JPasswordField passwordTextField;

    JLabel label = new JLabel("CONFIRMATION", SwingConstants.CENTER);
    private final JButton confirmButton = new JButton(" CONFIRM ");
    JButton exitbutton = new JButton("EXIT");
    private final JFrame adminRegisteredResortMenuFrame;

    public AdminWithdrawConfirmation(JFrame adminRegisteredResortMenuFrame, UserDto userDto, WalletService walletService, BigDecimal withdrawalAmount) {
        this.adminRegisteredResortMenuFrame = adminRegisteredResortMenuFrame;
        this.userDto = userDto;
        this.walletService = walletService;
        this.withdrawalAmount = withdrawalAmount;

        JLabel pass = new JLabel("ENTER PASSWORD: ");// with textfield
        passwordTextField = new JPasswordField();

        pass.setBounds(130, 150, 300, 30);
        pass.setFont(new Font("Times New Roman", Font.BOLD, 17));

        passwordTextField.setBounds(310, 150, 200, 30);
        passwordTextField.setFont(new Font("Times New Roman", Font.BOLD, 17));
        passwordTextField.setPreferredSize(new Dimension(20, 50));
        passwordTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                confirmButton.setEnabled(!String.valueOf(passwordTextField.getPassword()).isBlank());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                confirmButton.setEnabled(!String.valueOf(passwordTextField.getPassword()).isBlank());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                confirmButton.setEnabled(!String.valueOf(passwordTextField.getPassword()).isBlank());
            }
        });

        label.setBounds(95, 80, 500, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        confirmButton.setBounds(250, 250, 200, 30);
        confirmButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        confirmButton.setFocusable(false);
        confirmButton.setEnabled(false);

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
        frame.add(passwordTextField);
        frame.add(pass);
        frame.add(confirmButton);
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
                adminRegisteredResortMenuFrame.setVisible(true);
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            String password = String.valueOf(passwordTextField.getPassword());
            Optional<UserDto> correctPassword = userService.getUserByUsernameAndPassword(userDto.getUsername(), password);
            if (correctPassword.isPresent()) {
                boolean result = this.walletService.withdraw(userDto.getId(), withdrawalAmount, Instant.now());
                if (result) {
                    JOptionPane.showMessageDialog(null, "Withdraw Successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Withdrawal was unsuccessful. Please try again.", "Withdrawal Error", JOptionPane.ERROR_MESSAGE);
                }
                frame.dispose();
                this.adminRegisteredResortMenuFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", "Withdrawal Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            frame.dispose();
        }
    }
}
