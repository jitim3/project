package project.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class AdminWithdraw extends JFrame implements ActionListener {
    private final JFrame launchPageFrame;
    private final JFrame frame = new JFrame("ADMIN WITHDRAW");
    private final JLabel balance = new JLabel("ENTER WITHDRAWAL AMOUNT: ");// with textfield
    private final JTextField amountField = new JTextField();
    private final JLabel label = new JLabel("WITHDRAW", SwingConstants.CENTER);
    private final JButton widbutton = new JButton(" CONFIRM WITHDRAW");
    private final JButton exitbutton = new JButton("EXIT");
    private final JFrame adminRegisteredResortMenuFrame;
    private String windowEventSource = "";

    public AdminWithdraw(JFrame launchPageFrame, JFrame adminRegisteredResortMenuFrame) {
        this.launchPageFrame = launchPageFrame;
        this.adminRegisteredResortMenuFrame = adminRegisteredResortMenuFrame;

        balance.setBounds(100, 150, 300, 30);
        balance.setFont(new Font("Times New Roman", Font.BOLD, 17));

        amountField.setBounds(380, 150, 150, 30);
        amountField.setFont(new Font("Times New Roman", Font.BOLD, 17));
        amountField.setPreferredSize(new Dimension(20, 50));

        label.setBounds(195, 80, 300, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        widbutton.setBounds(250, 250, 200, 30);
        widbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        widbutton.setFocusable(false);

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
        frame.add(balance);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == widbutton) {
            windowEventSource = "widbutton";
            frame.dispose();
            new AdminWithdraw_Confirmation();
        } else {
            frame.dispose();
            adminRegisteredResortMenuFrame.setVisible(true);
        }
    }
}