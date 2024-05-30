package project.ui;

import project.dto.CreateReservationDto;
import project.dto.ResortDto;
import project.service.ReservationService;
import project.util.AppUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

public class CustomerPayment {
    private final ReservationService reservationService;
    private final long userId;
    private final ResortDto resortDto;
    private CreateReservationDto createReservationDto;
    private final BigDecimal amount;
    private final JFrame frame = new JFrame();
    private final JLabel lblEnterAmount = new JLabel();
    private final JFormattedTextField amountTextField = new JFormattedTextField();
    private final JButton confirmButton = new JButton("Confirm");
    private final JButton backButton = new JButton("Back");
    private final JLabel displayTotal = new JLabel();
    private final JFrame parentFrame;
    private String windowEventSource = "";

    public CustomerPayment(Long userId, ResortDto resortDto, CreateReservationDto createReservationDto, JFrame parentFrame) {
        this.reservationService = new ReservationService();
        this.userId = userId;
        this.resortDto = resortDto;
        this.createReservationDto = createReservationDto;
        this.parentFrame = parentFrame;

        amount = createReservationDto.amount();

        frame.setTitle("Amount: PHP " + amount.toString());

        lblEnterAmount.setText("Enter exact amount: ");
        lblEnterAmount.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblEnterAmount.setBounds(114, 135, 150, 14);
        amountTextField.setBounds(240, 132, 96, 20);
        amountTextField.setColumns(10);
        AppUtils.currency(amountTextField);

        confirmButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        confirmButton.setBounds(247, 223, 89, 23);
        confirmButton();

        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        backButton.setBounds(101, 223, 89, 23);
        backButton.addActionListener(actionEvent -> frame.dispose());

        displayTotal.setBounds(101, 25, 235, 82);
        displayTotal.setOpaque(true);
        displayTotal.setBackground(new Color(100, 255, 255, 64));

        ImageIcon image = new ImageIcon("beach2.png");
        ImageIcon bg = new ImageIcon("figma.jpg");

        Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);

        frame.setIconImage(image.getImage());
        frame.setSize(452, 308);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        frame.add(confirmButton);
        frame.add(backButton);
        frame.add(displayTotal);
        frame.add(lblEnterAmount);
        frame.add(amountTextField);
        frame.add(backgroundLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"confirmButton".equals(windowEventSource)) {
                    parentFrame.setVisible(true);
                }
            }

        });
    }

    private void confirmButton() {
        confirmButton.addActionListener(actionEvent -> {
            windowEventSource = "confirmButton";
            BigDecimal amountInput;
            try {
                String amountText = amountTextField.getText();
                amountText = amountText != null && !amountText.isBlank() ? amountText.replace(",", "") : "0.00";
                amountInput = BigDecimal.valueOf(Double.parseDouble(amountText))
                        .setScale(2, RoundingMode.HALF_UP);
                if (amount.compareTo(amountInput) != 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter amount.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            CreateReservationDto newReservationDto = createReservationDto.createdAt(Instant.now());
            Long reservationId = this.reservationService.createReservation(newReservationDto);
            if (reservationId == null || reservationId == 0) {
                JOptionPane.showMessageDialog(null, "Reservation was not successful. Please try again.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                new ConfirmationMessage();
            }
        });
    }
}