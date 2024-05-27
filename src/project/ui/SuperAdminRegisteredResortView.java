package project.ui;

import project.dto.UserDto;
import project.ui.town.Alcoy;
import project.ui.town.Barili;
import project.ui.town.Carcar;
import project.ui.town.Moalboal;
import project.ui.town.Oslob;
import project.ui.town.SanTander;

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

public class SuperAdminRegisteredResortView implements ActionListener {
    private final UserDto userDto;
    private final JFrame frame = new JFrame("Registered Resort");
    private final JLabel label = new JLabel("VIEW ALL RESORT");
    private final JButton carcarButton = new JButton("Carcar"); // CARCAR BUTTON id: 3
    private final JButton bariliButton = new JButton("Barili");// Barili BUTTON id: 2
    private final JButton moalBoalButton = new JButton("Moalboal");// Moalboal BUTTON id: 4
    private final JButton alcoyButton = new JButton("Alcoy");// Alcoy BUTTON id: 1
    private final JButton sanTanderButton = new JButton("SanTander");// SAN TANDER BUTTON id: 6
    private final JButton oslobButton = new JButton("Oslob");// OSLOB BUTTON id: 5
    private final JButton exitButton = new JButton("BACK");
    private final JFrame superAdminMenu;
    private String windowEventSource = "";

    public SuperAdminRegisteredResortView(JFrame superAdminMenu, UserDto userDto) {
        this.superAdminMenu = superAdminMenu;
        this.userDto = userDto;

        label.setBounds(210, 40, 500, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        carcarButton.setBounds(170, 100, 150, 40);
        carcarButton.setFocusable(false);
        carcarButton.addActionListener(this);

        bariliButton.setBounds(375, 100, 150, 40);
        bariliButton.setFocusable(false);
        bariliButton.addActionListener(this);

        moalBoalButton.setBounds(170, 180, 150, 40);
        moalBoalButton.setFocusable(false);
        moalBoalButton.addActionListener(this);

        alcoyButton.setBounds(375, 180, 150, 40);
        alcoyButton.setFocusable(false);
        alcoyButton.addActionListener(this);

        sanTanderButton.setBounds(170, 240, 150, 40);
        sanTanderButton.setFocusable(false);
        sanTanderButton.addActionListener(this);

        oslobButton.setBounds(375, 240, 150, 40);
        oslobButton.setFocusable(false);
        oslobButton.addActionListener(this);

        exitButton.setBounds(275, 350, 150, 40);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        frame.add(label);
        frame.add(carcarButton);
        frame.add(oslobButton);
        frame.add(sanTanderButton);
        frame.add(alcoyButton);
        frame.add(moalBoalButton);
        frame.add(bariliButton);
        frame.add(exitButton);
        frame.add(backgroundLabel);
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"carcarButton".equals(windowEventSource) && !"bariliButton".equals(windowEventSource)
                        && !"moalBoalButton".equals(windowEventSource) && !"alcoyButton".equals(windowEventSource)
                        && !"sanTanderButton".equals(windowEventSource) && !"oslobButton".equals(windowEventSource)) {
                    superAdminMenu.setVisible(true);
                }
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /*
     * diri na part is katong sa document so once na mo click siya sa resort name
     * then makita na niya ang document na gi send ni admin nga na verify na sa
     * super admin.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == carcarButton) {
            windowEventSource = "carcarButton";
            frame.dispose();
            new Carcar(this.userDto, frame);
        } else if (e.getSource() == bariliButton) {
            windowEventSource = "bariliButton";
            frame.dispose();
            new Barili(this.userDto, frame);
        } else if (e.getSource() == moalBoalButton) {
            windowEventSource = "moalBoalButton";
            frame.dispose();
            new Moalboal(this.userDto, frame);
        } else if (e.getSource() == alcoyButton) {
            windowEventSource = "alcoyButton";
            frame.dispose();
            new Alcoy(this.userDto, frame);
        } else if (e.getSource() == sanTanderButton) {
            windowEventSource = "sanTanderButton";
            frame.dispose();
            new SanTander(this.userDto, frame);
        } else if (e.getSource() == oslobButton) {
            windowEventSource = "oslobButton";
            frame.dispose();
            new Oslob(this.userDto, frame);
        } else {
            frame.dispose();
            superAdminMenu.setVisible(true);
        }
    }
}
