package project.ui;

import project.service.UserService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoverPage implements ActionListener {
    private final JFrame launchPageFrame;
    private final UserService userService;
    private final JFrame frame = new JFrame();
    private final JButton adminButton = new JButton("Admin");
    private final JButton superAdminButton = new JButton("Super Admin");
    private final JButton exitButton = new JButton("Back");
    private final JFrame loginFrame;

    public CoverPage(JFrame launchPageFrame, UserService userService, JFrame loginFrame) {
        this.launchPageFrame = launchPageFrame;
        this.userService = userService; // Initialize userService
        this.loginFrame = loginFrame;

        adminButton.setBounds(100, 50, 150, 40);
        adminButton.setFocusable(false);
        adminButton.addActionListener(this);

        superAdminButton.setBounds(100, 100, 150, 40);
        superAdminButton.setFocusable(false);
        superAdminButton.addActionListener(this);

        exitButton.setBounds(125, 170, 100, 30);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        // GUI icon
        ImageIcon icon = new ImageIcon("beach2.png"); // Set my imported icon

        // GUI background
        ImageIcon backgroundImageIcon = new ImageIcon("Ocean Wallpaper.jpg");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 350, 300);

        frame.setLocation(300, 250);
        frame.add(exitButton);
        frame.add(adminButton);
        frame.add(superAdminButton);
        frame.add(backgroundLabel);
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLayout(null);
        frame.setTitle("Resort");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            frame.dispose();
            new NewWindow_Admin(launchPageFrame, userService);
        }
        if (e.getSource() == superAdminButton) {
            frame.dispose();
            new NewWindow_SuperAdmin(userService);
        } else if (e.getSource() == exitButton) {
            frame.dispose();
            loginFrame.setVisible(true);
        }
    }
}
