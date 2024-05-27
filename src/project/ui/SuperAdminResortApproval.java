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

public class SuperAdminResortApproval implements ActionListener {
    private final JFrame frame = new JFrame("APPROVAL"); //DAPAT DITO MAPUNTA ANG GENERATED BUTTONS PARA SA REGISTERED RESORT (ONCE MA APPROVE, MAPOST)
    private final UserDto userDto;
    private final JLabel resortsToBeApprovedLabel = new JLabel("RESORTS TO BE APPROVED");
    private final JButton exitButton = new JButton("Exit");
    private final JFrame superAdminMenu;

    public SuperAdminResortApproval(JFrame superAdminMenu, UserDto userDto) {
        this.superAdminMenu = superAdminMenu;
        this.userDto = userDto;

        exitButton.setBounds(275, 350, 150, 40);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        resortsToBeApprovedLabel.setBounds(150, 40, 500, 30);
        resortsToBeApprovedLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        frame.add(resortsToBeApprovedLabel);
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
