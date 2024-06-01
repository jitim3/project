package project.ui;

import org.jdatepicker.impl.JDatePickerImpl;
import project.dao.entity.CommissionRate;
import project.dto.CreateReservationDto;
import project.dto.ResortDto;
import project.util.AppUtils;
import project.util.ReservationStatus;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DisplayCottageResort implements ActionListener {
    private final long userId;
    private final ResortDto resortDto;
    private final int commissionRateId;
    private final BigDecimal computedAmount;
    private final JFrame frame = new JFrame("Cottage Information");
    private final JLabel cottagesLabel = new JLabel("COTTAGES");
    private final JLabel cottageFeeLabel = new JLabel("COTTAGE FEE");
    private final JDatePickerImpl reservationDatePicker;
    private final JButton reserveNowButton = new JButton("RESERVE NOW");
    private final JButton exitButton = new JButton("EXIT");
    private final JFrame customerMenuFrame;
    private String windowSourceEvent = "";

    public DisplayCottageResort(JFrame customerMenuFrame, long userId, ResortDto resortDto, CommissionRate commissionRate) {
        this.customerMenuFrame = customerMenuFrame;
        this.userId = userId;
        this.resortDto = resortDto;
        this.commissionRateId = commissionRate.id();

        BigDecimal amount = (this.resortDto.cottageFee() != null
                ? this.resortDto.cottageFee()
                : BigDecimal.ZERO
        ).setScale(2, RoundingMode.HALF_UP);

        JLabel reservationDateLabel = new JLabel("Reservation Date");
        reservationDateLabel.setBounds(350, 505, 400, 80);
        reservationDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        reservationDatePicker = AppUtils.createJDatePicker(AppUtils.DATE_FORMAT);
        reservationDatePicker.setBounds(315, 560, 220, 25); // Adjust these values as needed

        reserveNowButton.setBounds(315, 590, 220, 25);
        reserveNowButton.setFocusable(false);
        reserveNowButton.addActionListener(this);
        reserveNowButton.setOpaque(false);

        exitButton.setBounds(315, 620, 220, 25);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        exitButton.setOpaque(false);

        computedAmount = AppUtils.computeRateWithCommissionFee(amount, commissionRate.rate());
        JLabel displayLabel3 = new JLabel(computedAmount.toString()); // COTTAGE FEE
        displayLabel3.setBounds(300, 470, 250, 45);
        displayLabel3.setFont(new Font("Times New Roman", Font.BOLD, 23));
        displayLabel3.setForeground(Color.black);
        displayLabel3.setOpaque(true);
        displayLabel3.setBackground(new Color(255, 255, 255, 64));
        displayLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        displayLabel3.setVerticalAlignment(SwingConstants.CENTER);

        cottageFeeLabel.setBounds(347, 415, 400, 80);
        cottageFeeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        cottagesLabel.setBounds(347, 15, 400, 80);
        cottagesLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        JLabel imageLabel3 = new JLabel(); // IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
        imageLabel3.setBounds(50, 95, 700, 325); // Set bounds as per your requirement
        String cottageImagePath = AppUtils.imagePath(this.resortDto.cottageImage()).orElse("");
        ImageIcon imageIcon = new ImageIcon(cottageImagePath);
        Image img3 = imageIcon.getImage().getScaledInstance(imageLabel3.getWidth(), imageLabel3.getHeight(),
                Image.SCALE_AREA_AVERAGING);
        imageLabel3.setIcon(new ImageIcon(img3));

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 700, Image.SCALE_AREA_AVERAGING);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 700);

        frame.add(exitButton);
        frame.add(reserveNowButton);
        frame.add(reservationDatePicker);
        frame.add(reservationDateLabel);
        frame.add(displayLabel3);
        frame.add(cottageFeeLabel);
        frame.add(cottagesLabel);
        frame.add(imageLabel3);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setResizable(false);
        frame.setSize(800, 700);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"reserveNowButton".equals(windowSourceEvent)) {
                    customerMenuFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserveNowButton) {
            windowSourceEvent = "reserveNowButton";
            LocalDate reservationDate = this.getReservationDate(reservationDatePicker.getJFormattedTextField().getText());
            if (reservationDate == null || reservationDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, "Invalid reservation date. Please enter reservation date.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CreateReservationDto createCottageReservationDto = CreateReservationDto.createCottageReservation(
                    userId,
                    resortDto.id(),
                    reservationDate,
                    ReservationStatus.PENDING,
                    computedAmount,
                    commissionRateId
            );
            frame.dispose();
            new CustomerInformation(customerMenuFrame, userId, resortDto, createCottageReservationDto);
        } else {
            frame.dispose();
        }
    }

    private LocalDate getReservationDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppUtils.DATE_FORMAT);
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
