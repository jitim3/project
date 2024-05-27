package project.ui;

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

public class SuperAdminTransaction implements ActionListener {
    private final JFrame frame = new JFrame("SUPER ADMIN TRANSACTION");
    private final JLabel viewTransactionLabel = new JLabel("VIEW TRANSACTION");
    private final JLabel transactionDetailsLabel = new JLabel("Transaction Details: ");
    private final JLabel dateLabel = new JLabel("Date");
    private final JLabel timeLabel = new JLabel("Time");
    private final JLabel descriptionLabel = new JLabel("Description");
    private final JButton exitButton = new JButton("Back");
    private final JFrame superAdminMenu;

    public SuperAdminTransaction(JFrame superAdminMenu) {
        this.superAdminMenu = superAdminMenu;

        viewTransactionLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        viewTransactionLabel.setBounds(200, 40, 500, 30);

        transactionDetailsLabel.setBounds(270, 70, 500, 40);
        transactionDetailsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        dateLabel.setBounds(250, 100, 150, 40);
        dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

        timeLabel.setBounds(320, 100, 500, 40);
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

        descriptionLabel.setBounds(370, 100, 500, 40);
        descriptionLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

        exitButton.setBounds(275, 370, 150, 40);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        frame.add(timeLabel);
        frame.add(descriptionLabel);
        frame.add(transactionDetailsLabel);
        frame.add(dateLabel);
        frame.add(viewTransactionLabel);
        frame.add(exitButton);
        frame.add(backgroundLabel);
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                superAdminMenu.setVisible(true);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            frame.dispose();
            superAdminMenu.setVisible(true);
        }
    }
}
