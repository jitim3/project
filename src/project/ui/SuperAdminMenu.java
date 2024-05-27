package project.ui;

import project.dto.UserDto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SuperAdminMenu implements ActionListener {
    private final JFrame launchPageFrame;
    private final UserDto userDto;
    private final JFrame frame = new JFrame("Super Admin");
    private final JLabel label = new JLabel("Welcome Super Admin!");
    private final JButton viewRegisteredResortButton = new JButton("View Registered Resort");
    private final JButton viewWalletButton = new JButton("View Wallet");
    private final JButton viewTransactionButton = new JButton("View Transaction");
    private final JButton exitButton = new JButton("Exit");
    private String windowEventSource = "";

    public SuperAdminMenu(JFrame launchPageFrame, UserDto userDto) {
        this.launchPageFrame = launchPageFrame;
        this.userDto = userDto;
        viewRegisteredResortButton.setBounds(220, 100, 230, 40);
        viewRegisteredResortButton.setFocusable(false);
        viewRegisteredResortButton.addActionListener(this);

        viewWalletButton.setBounds(220, 160, 230, 40);
        viewWalletButton.setFocusable(false);
        viewWalletButton.addActionListener(this);

        viewTransactionButton.setBounds(220, 220, 230, 40);
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
        frame.add(viewRegisteredResortButton);
        frame.add(label);
        frame.add(backgroundLabel);
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"viewRegisteredResortButton".equals(windowEventSource)
                        && !"viewWalletButton".equals(windowEventSource)
                        && !"viewTransactionButton".equals(windowEventSource)) {
                    launchPageFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewRegisteredResortButton) {
            windowEventSource = "viewRegisteredResortButton";
            frame.dispose();
            new SuperAdminRegisteredResortView(frame, userDto);
        } else if (e.getSource() == viewWalletButton) {
            windowEventSource = "viewWalletButton";
            frame.dispose();
            new SuperAdminWallet(frame);
        } else if (e.getSource() == viewTransactionButton) {
            windowEventSource = "viewTransactionButton";
            frame.dispose();
            new SuperAdminTransaction(frame);
        } else if (e.getSource() == exitButton) {
            frame.dispose();
            launchPageFrame.setVisible(true);
        }
    }
}
