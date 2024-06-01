package project.ui;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;

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

public class AdminRegisteredResortMenu implements ActionListener {
    private final JFrame launchPageFrame;
    private final UserDto userDto;
    private final ResortService resortService;
    private final JFrame frame = new JFrame("MENU");
    private final JButton btnViewInfo = new JButton("View Information");
    private final JButton btnViewReviews = new JButton("View Reviews");
    private final JButton btnViewReservation = new JButton("View Reservation");
    private final JButton btnViewWallet = new JButton("View Wallet");
    private final JButton btnViewTransaction = new JButton("View Transaction");
    private final JButton btnExit = new JButton("EXIT");
    private final JFrame adminMenu;
    private String windowEventSource = "";

    public AdminRegisteredResortMenu(JFrame launchPageFrame, JFrame adminMenu, UserDto userDto, ResortService resortService) {
        this.launchPageFrame = launchPageFrame;
        this.adminMenu = adminMenu;
        this.userDto = userDto;
        this.resortService = resortService;

        btnViewInfo.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnViewInfo.setBounds(251, 53, 199, 46);
        btnViewInfo.setFocusable(false);
        btnViewInfo.addActionListener(this);

        btnViewReviews.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnViewReviews.setBounds(251, 119, 199, 46);
        btnViewReviews.setFocusable(false);
        btnViewReviews.addActionListener(this);

        btnViewReservation.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnViewReservation.setBounds(251, 192, 199, 46);
        btnViewReservation.setFocusable(false);
        btnViewReservation.addActionListener(this);

        btnViewWallet.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnViewWallet.setBounds(251, 264, 199, 46);
        btnViewWallet.setFocusable(false);
        btnViewWallet.addActionListener(this);

        btnViewTransaction.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnViewTransaction.setBounds(251, 334, 199, 46);
        btnViewTransaction.setFocusable(false);
        btnViewTransaction.addActionListener(this);

        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnExit.setBounds(309, 414, 85, 21);
        btnExit.setFocusable(false);
        btnExit.addActionListener(this);

        ImageIcon image = new ImageIcon("beach2.png");
        ImageIcon bg = new ImageIcon("figma.jpg");

        Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        frame.setIconImage(image.getImage());
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.add(btnViewInfo);
        frame.add(btnViewReviews);
        frame.add(btnViewReservation);
        frame.add(btnViewWallet);
        frame.add(btnViewTransaction);
        frame.add(btnExit);
        frame.add(backgroundLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"btnViewInfo".equals(windowEventSource) && !"btnViewReviews".equals(windowEventSource)
                        && !"btnViewReservation".equals(windowEventSource) && !"btnViewWallet".equals(windowEventSource)
                        && !"btnViewTransaction".equals(windowEventSource)) {
                    adminMenu.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnViewInfo) {
            this.windowEventSource = "btnViewInfo";
            frame.dispose();
//            new ResortInfoUpdate(frame, userDto, resortDto, this.resortService);
            new AdminRegisteredResortView(adminMenu, userDto);
        } else if (e.getSource() == btnViewReviews) {
            this.windowEventSource = "btnViewReviews";
            frame.dispose();
            new AdminRegisteredResortReviews(adminMenu, userDto);
        } else if (e.getSource() == btnViewReservation) {
            this.windowEventSource = "btnViewReservation";
            frame.dispose();
            new ViewReservationAdmin(frame);
        } else if (e.getSource() == btnViewWallet) {
            this.windowEventSource = "btnViewWallet";
            frame.dispose();
            new AdminWallet(frame, userDto);
        } else if (e.getSource() == btnViewTransaction) {
            this.windowEventSource = "btnViewTransaction";
            frame.dispose();
            new AdminTransaction(frame, userDto);
        } else if (e.getSource() == btnExit) {
            frame.dispose();
            adminMenu.setVisible(true);
        }
    }
}