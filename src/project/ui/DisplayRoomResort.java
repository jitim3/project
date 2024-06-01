package project.ui;

import project.dao.entity.CommissionRate;
import project.dto.CreateReservationDto;
import project.dto.ResortDto;
import project.dto.RoomDto;
import project.util.AppUtils;
import project.util.ReservationStatus;
import project.util.RoomTypes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class DisplayRoomResort implements ActionListener {
    private final long userId;
    private final ResortDto resortDto;
    private final CommissionRate commissionRate;
    private final JFrame frame = new JFrame("Room Information");
    private final JLabel roomsLabel = new JLabel("Rooms");
    private final JLabel normalRoomLabel = new JLabel("Normal Room");
    private final JLabel normalRoomNumberOfPaxLabel = new JLabel("Number of PAX");
    private final JLabel normalRoomFeeLabel = new JLabel("Room Fee");
    private final JButton normalRoomReserveNowButton = new JButton("RESERVE NOW");
    private final JLabel familyRoomLabel = new JLabel("Family Room");
    private final JLabel familyRoomNumberOfPaxLabel = new JLabel("Number of PAX");
    private final JLabel familyRoomFeeLabel = new JLabel("Family Room Fee");
    private final JButton familyRoomReserveNowButton = new JButton("RESERVE NOW");
    private RoomDto normalRoomDto;
    private RoomDto familyRoomDto;
    private BigDecimal normalRoomRatePerNightValue = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    private BigDecimal familyRoomRatePerNightValue = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    private final JFrame customerMenuFrame;

    public DisplayRoomResort(JFrame customerMenuFrame, Long userId, ResortDto resortDto, CommissionRate commissionRate) {
        this.customerMenuFrame = customerMenuFrame;
        this.userId = userId;
        this.resortDto = resortDto;
        this.commissionRate = commissionRate;

        Optional<RoomDto> normalRoomDtoOptional = this.resortDto.roomDtos().stream()
                .filter(roomDto -> RoomTypes.NORMAL.value().equals(roomDto.roomType())).findFirst();
        int normalRoomNumberOfPaxValue = 0;
        Optional<String> normalRoomImage1 = Optional.empty();
        if (normalRoomDtoOptional.isPresent()) {
            normalRoomDto = normalRoomDtoOptional.get();
            normalRoomNumberOfPaxValue = normalRoomDto.numberOfPax();
            normalRoomRatePerNightValue = (normalRoomDto.ratePerNight() != null
                    ? normalRoomDto.ratePerNight()
                    : BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
            normalRoomImage1 = AppUtils.imagePath(normalRoomDto.roomImage1());
        }

        Optional<RoomDto> familyRoomDtoOptional = this.resortDto.roomDtos().stream()
                .filter(roomDto -> RoomTypes.FAMILY.value().equals(roomDto.roomType())).findFirst();
        int familyRoomNumberOfPaxValue = 0;
        Optional<String> familyRoomImage1 = Optional.empty();
        if (familyRoomDtoOptional.isPresent()) {
            familyRoomDto = familyRoomDtoOptional.get();
            familyRoomNumberOfPaxValue = familyRoomDto.numberOfPax();
            familyRoomRatePerNightValue = (familyRoomDto.ratePerNight() != null
                    ? familyRoomDto.ratePerNight().setScale(2, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
            familyRoomImage1 = AppUtils.imagePath(familyRoomDto.roomImage1());
        }

        familyRoomReserveNowButton.setBounds(525, 560, 150, 25);
        familyRoomReserveNowButton.setFocusable(false);
        familyRoomReserveNowButton.addActionListener(this);
        familyRoomReserveNowButton.setOpaque(false);

        normalRoomReserveNowButton.setBounds(125, 560, 150, 25);
        normalRoomReserveNowButton.setFocusable(false);
        normalRoomReserveNowButton.addActionListener(this);
        normalRoomReserveNowButton.setOpaque(false);

        BigDecimal familyRoomRatePerNightFee = AppUtils.computeRateWithCommissionFee(familyRoomRatePerNightValue, commissionRate.rate());
        JLabel familyRoomRatePerNight = new JLabel(familyRoomRatePerNightFee.toString()); // FAMILY ROOM FEE
        familyRoomRatePerNight.setBounds(468, 515, 250, 35);
        familyRoomRatePerNight.setFont(new Font("Times New Roman", Font.BOLD, 15));
        familyRoomRatePerNight.setForeground(Color.black);
        familyRoomRatePerNight.setOpaque(true);
        familyRoomRatePerNight.setBackground(new Color(255, 255, 255, 64));
        familyRoomRatePerNight.setHorizontalAlignment(SwingConstants.CENTER);
        familyRoomRatePerNight.setVerticalAlignment(SwingConstants.CENTER);

        JLabel familyRoomNumberOfPax = new JLabel(String.valueOf(familyRoomNumberOfPaxValue)); // FAMILY ROOM PAX
        familyRoomNumberOfPax.setBounds(468, 450, 250, 45);
        familyRoomNumberOfPax.setFont(new Font("Times New Roman", Font.BOLD, 15));
        familyRoomNumberOfPax.setForeground(Color.black);
        familyRoomNumberOfPax.setOpaque(true);
        familyRoomNumberOfPax.setBackground(new Color(255, 255, 255, 64));
        familyRoomNumberOfPax.setHorizontalAlignment(SwingConstants.CENTER);
        familyRoomNumberOfPax.setVerticalAlignment(SwingConstants.CENTER);

        JLabel normalRoomNumberOfPax = new JLabel(String.valueOf(normalRoomNumberOfPaxValue)); // ROOM PAX
        normalRoomNumberOfPax.setBounds(70, 450, 250, 45);
        normalRoomNumberOfPax.setFont(new Font("Times New Roman", Font.BOLD, 15));
        normalRoomNumberOfPax.setForeground(Color.black);
        normalRoomNumberOfPax.setOpaque(true);
        normalRoomNumberOfPax.setBackground(new Color(255, 255, 255, 64));
        normalRoomNumberOfPax.setHorizontalAlignment(SwingConstants.CENTER);
        normalRoomNumberOfPax.setVerticalAlignment(SwingConstants.CENTER);

        BigDecimal normalRoomRatePerNightFee = AppUtils.computeRateWithCommissionFee(normalRoomRatePerNightValue, commissionRate.rate());
        JLabel normalRoomRatePerNight = new JLabel(normalRoomRatePerNightFee.toString()); // ROOM FEE
        normalRoomRatePerNight.setBounds(70, 515, 250, 35);
        normalRoomRatePerNight.setFont(new Font("Times New Roman", Font.BOLD, 15));
        normalRoomRatePerNight.setForeground(Color.black);
        normalRoomRatePerNight.setOpaque(true);
        normalRoomRatePerNight.setBackground(new Color(255, 255, 255, 64));
        normalRoomRatePerNight.setHorizontalAlignment(SwingConstants.CENTER);
        normalRoomRatePerNight.setVerticalAlignment(SwingConstants.CENTER);

        familyRoomFeeLabel.setBounds(545, 478, 200, 50);
        familyRoomFeeLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        familyRoomNumberOfPaxLabel.setBounds(545, 412, 200, 50);
        familyRoomNumberOfPaxLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        familyRoomLabel.setBounds(535, 385, 200, 50);
        familyRoomLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        normalRoomFeeLabel.setBounds(175, 478, 200, 50);
        normalRoomFeeLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        normalRoomNumberOfPaxLabel.setBounds(155, 412, 200, 50);
        normalRoomNumberOfPaxLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        roomsLabel.setBounds(347, 15, 400, 80);
        roomsLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        normalRoomLabel.setBounds(140, 385, 200, 50);
        normalRoomLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JLabel normalRoomImage1Label = new JLabel(); // IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
        normalRoomImage1Label.setBounds(430, 120, 325, 275); // Set bounds as per your requirement
        ImageIcon normalRoomImageIcon = new ImageIcon(normalRoomImage1.orElse(""));
        Image normalRoomImage = normalRoomImageIcon.getImage().getScaledInstance(normalRoomImage1Label.getWidth(), normalRoomImage1Label.getHeight(),
                Image.SCALE_AREA_AVERAGING);
        normalRoomImage1Label.setIcon(new ImageIcon(normalRoomImage));

        JLabel familyRoomImage1Label = new JLabel(); // IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
        familyRoomImage1Label.setBounds(40, 120, 325, 275); // Set bounds as per your requirement
        ImageIcon familyRoomImageIcon = new ImageIcon(familyRoomImage1.orElse(""));
        Image familyRoomImage = familyRoomImageIcon.getImage().getScaledInstance(familyRoomImage1Label.getWidth(), familyRoomImage1Label.getHeight(),
                Image.SCALE_AREA_AVERAGING);
        familyRoomImage1Label.setIcon(new ImageIcon(familyRoomImage));

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("figma.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 700, Image.SCALE_AREA_AVERAGING);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 700);

        frame.add(familyRoomRatePerNight);
        frame.add(familyRoomReserveNowButton);
        frame.add(familyRoomFeeLabel);
        frame.add(familyRoomNumberOfPax);
        frame.add(familyRoomNumberOfPaxLabel);
        frame.add(familyRoomLabel);
        frame.add(normalRoomImage1Label);
        frame.add(normalRoomReserveNowButton);
        frame.add(normalRoomFeeLabel);
        frame.add(normalRoomNumberOfPaxLabel);
        frame.add(normalRoomNumberOfPax);
        frame.add(normalRoomRatePerNight);
        frame.add(normalRoomLabel);
        frame.add(roomsLabel);
        frame.add(familyRoomImage1Label);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setResizable(false);
        frame.setSize(800, 700);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == normalRoomReserveNowButton) {
            frame.dispose();
            CreateReservationDto createRoomReservationDto = CreateReservationDto.createRoomReservation(
                    userId, normalRoomDto.id(), ReservationStatus.PENDING, commissionRate.id()
            );
            new Checkin(customerMenuFrame, userId, resortDto, createRoomReservationDto, normalRoomRatePerNightValue, commissionRate);
        } else if (e.getSource() == familyRoomReserveNowButton) {
            frame.dispose();
            CreateReservationDto createRoomReservationDto = CreateReservationDto.createRoomReservation(
                    userId, familyRoomDto.id(), ReservationStatus.PENDING, commissionRate.id()
            );
            new Checkin(customerMenuFrame, userId, resortDto, createRoomReservationDto, familyRoomRatePerNightValue, commissionRate);
        }
    }
}
