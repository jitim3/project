package project.ui;

import project.dao.entity.CommissionRate;
import project.dto.UserDto;
import project.service.ReservationService;
import project.util.AppUtils;
import project.util.ReservationStatus;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminTransaction implements ActionListener {
    private final JFrame frame = new JFrame("ADMIN TRANSACTION");
    private final JLabel viewTransactionLabel = new JLabel("VIEW TRANSACTION");
    private final JLabel transactionDetailsLabel = new JLabel("Transaction Details: ");
    private final JButton exitButton = new JButton("Exit");
    private final JFrame parentFrame;

    public AdminTransaction(JFrame parentFrame, UserDto userDto) {
        this.parentFrame = parentFrame;

        viewTransactionLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        viewTransactionLabel.setBounds(200, 40, 500, 30);

        transactionDetailsLabel.setBounds(270, 70, 500, 40);
        transactionDetailsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        ReservationService reservationService = new ReservationService();
        List<Reservation> reservations = reservationService.getReservationsByUserId(userDto.getId())
                .stream()
                .map(reservationDto -> {
                    String type;
                    if (reservationDto.resortId() != null && reservationDto.resortId() > 0) {
                        type = "Resort: " + reservationDto.resortName();
                    } else {
                        type = "Room: " + reservationDto.roomType() + " Room from " + reservationDto.roomResortName();
                    }
                    return new Reservation(
                            reservationDto.id(),
                            type,
                            reservationDto.reservationDate(),
                            reservationDto.endDate(),
                            reservationDto.amount(),
                            reservationDto.status(),
                            reservationDto.createdAt()
                    );
                })
                .toList();

        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[]{"", "No.", "Type", "Reservation Date", "End Date", "Amount", "Status", "Created Date", "Action"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };
        AtomicInteger counter = new AtomicInteger(1);
        reservations.forEach(reservation -> {
            defaultTableModel.addRow(new Object[]{
                    reservation.id(),
                    counter.getAndAdd(1),
                    reservation.type(),
                    reservation.reservationDate(),
                    reservation.endDate(),
                    reservation.amount(),
                    reservation.status(),
                    reservation.createdAt(),
                    "Select Action"
            });
        });

        JTable transactionTable = new JTable(defaultTableModel);

        // Hide ID from the JTable
        TableColumn idColumn = transactionTable.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Select Action", "Confirm", "Decline"});
        TableColumn comboBoxColumn = transactionTable.getColumnModel().getColumn(8);
        comboBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) comboBox.getSelectedItem();
                int row = transactionTable.getSelectedRow();
                int column = transactionTable.getSelectedColumn();
                if (row != -1 && column == 8 && !"Select Action".equals(selectedItem)) {
                    long reservationId = (long) transactionTable.getValueAt(row, 0);
                    int input = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to " + (selectedItem != null ? selectedItem.toLowerCase() : selectedItem) + "?",
                            "Select an Option...",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (input == 0) {
                        Optional<ReservationStatus> reservationStatusOptional = Arrays.stream(ReservationStatus.values())
                                .filter(reservationStatus -> reservationStatus.value().startsWith(selectedItem))
                                .findFirst();
                        reservationStatusOptional.ifPresent(reservationStatus -> {
                            transactionTable.setValueAt(reservationStatus.value(), row, 6);
                            boolean updated = reservationService.updateReservationStatus(reservationId, reservationStatus, Instant.now());
                            if (updated) {
                                JOptionPane.showMessageDialog(null, "Reservation been " + reservationStatus.value().toLowerCase(), "Login", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
                    }
                    comboBox.setSelectedIndex(0);
                }
            }
        });

        JScrollPane transactionScrollPane = new JScrollPane(transactionTable);
        transactionScrollPane.setBounds(20, 110, 645, 280);

        exitButton.setBounds(310, 410, 100, 30);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("beach3.jpg");
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parentFrame.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            frame.dispose();
        }
    }
}