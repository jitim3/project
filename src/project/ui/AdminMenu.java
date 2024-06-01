package project.ui;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminMenu implements ActionListener {
    private final JFrame launchPageFrame;
    private final UserDto userDto;
    private final ResortService resortService;
    private ResortDto registeredResort;
    private final JFrame frame = new JFrame("Admin Menu");
    private final JButton registerResortButton = new JButton("Register resort");
    private final JButton viewRegisteredResortButton = new JButton("View registered resort");
    private final JButton exitButton = new JButton("EXIT");
    private String windowEventSource = "";

    public AdminMenu(JFrame launchPageFrame, UserDto userDto) {
        this.launchPageFrame = launchPageFrame;
        this.userDto = userDto;
        this.resortService = new ResortService();

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("beach3.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 350, 300);

        registerResortButton.setBounds(75, 50, 200, 40);
        registerResortButton.setFocusable(false);
        registerResortButton.addActionListener(this);

        viewRegisteredResortButton.setBounds(75, 100, 200, 40);
        viewRegisteredResortButton.setFocusable(false);
        viewRegisteredResortButton.addActionListener(this);

        exitButton.setBounds(75, 150, 200, 40);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        frame.add(registerResortButton);
        frame.add(viewRegisteredResortButton);
        frame.add(exitButton);
        frame.setResizable(false);
        frame.add(backgroundLabel);
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"registerResortButton".equals(windowEventSource) && !"viewRegisteredResortButton".equals(windowEventSource)) {
                    launchPageFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerResortButton) { // For the Log in menu
            windowEventSource = "registerResortButton";
            frame.dispose();
            new AdminResortRegistration(frame, this.userDto, this.resortService);
        } else if (e.getSource() == viewRegisteredResortButton) { // For the sign up menu
            windowEventSource = "viewRegisteredResortButton";
            frame.dispose();
            new AdminRegisteredResortMenu(launchPageFrame, frame, this.userDto, this.resortService);
        } else {
            frame.dispose();
            launchPageFrame.setVisible(true);
        }
    }
}
