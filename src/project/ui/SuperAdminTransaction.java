package project.ui;

import project.dto.ReservationDto;
import project.dto.ResortDto;
import project.service.ReservationService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SuperAdminTransaction implements ActionListener {
    private final JFrame frame = new JFrame("SUPER ADMIN TRANSACTION");
    private final JLabel viewTransactionLabel = new JLabel("VIEW TRANSACTION");
    private final JLabel transactionDetailsLabel = new JLabel("Transaction Details: ");
    private final JButton exitButton = new JButton("Back");
    private final JFrame superAdminMenu;

    public SuperAdminTransaction(JFrame superAdminMenu) {
        this(superAdminMenu, null);
    }

    public SuperAdminTransaction(JFrame superAdminMenu, ResortDto resortDto) {
        this.superAdminMenu = superAdminMenu;

        viewTransactionLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        viewTransactionLabel.setBounds(200, 40, 500, 30);

        transactionDetailsLabel.setBounds(270, 70, 500, 40);
        transactionDetailsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        ReservationService reservationService = new ReservationService();
        List<ReservationDto> reservationDtos;
        if (resortDto == null) {
            reservationDtos = reservationService.getReservations();
        }  else {
            reservationDtos = reservationService.getReservationsByResortId(resortDto.id());
        }
        AtomicInteger number = new AtomicInteger(1);
        Object[][] data = reservationDtos.stream()
                .map(reservationDto -> {
                    String type;
                    if (reservationDto.resortId() != null && reservationDto.resortId() > 0) {
                        type = "Resort: " + reservationDto.resortName();
                    } else {
                        type = "Room: " + reservationDto.roomType() + " Room from " + reservationDto.roomResortName();
                    }
                    return new Object[] {
                            number.getAndAdd(1),
                            type,
                            reservationDto.reservationDate(),
                            reservationDto.endDate(),
                            reservationDto.amount(),
                            reservationDto.status(),
                            reservationDto.createdAt()
                    };
                })
                .toArray(size -> new Object[size][1]);
        String[] columnNames = {"No.", "Type", "Reservation Date", "End Date", "Amount", "Status", "Created Date"};
        JTable transactionTable = new JTable(data, columnNames);
        JScrollPane transactionScrollPane = new JScrollPane(transactionTable);
        transactionScrollPane.setBounds(20, 110, 645, 280);

        exitButton.setBounds(310, 410, 100, 30);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        frame.add(transactionDetailsLabel);
        frame.add(viewTransactionLabel);
        frame.add(transactionScrollPane);
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
