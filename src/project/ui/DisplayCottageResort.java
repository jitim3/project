package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import project.dto.CreateCottageReservationDto;
import project.dto.CreateReservationDto;
import project.dto.ResortDto;
import project.util.AppUtils;
import project.util.ReservationStatus;

public class DisplayCottageResort implements ActionListener {
	private final long userId;
	private final ResortDto resortDto;
	private final BigDecimal amount;
	private final JFrame frame = new JFrame("Cottage Information");
	private final JLabel cottagesLabel = new JLabel("COTTAGES");
	private final JLabel cottageFeeLabel = new JLabel("COTTAGE FEE");
	private final JButton reserveNowButton = new JButton("RESERVE NOW");
	private final JButton exitButton = new JButton("EXIT");

	public DisplayCottageResort(long userId, ResortDto resortDto) {
		this.userId = userId;
		this.resortDto = resortDto;

		amount = (this.resortDto.cottageFee() != null
				? this.resortDto.cottageFee()
				: BigDecimal.ZERO
		).setScale(2, RoundingMode.HALF_UP);
		
		reserveNowButton.setBounds(315, 530, 220, 25);
		reserveNowButton.setFocusable(false);
		reserveNowButton.addActionListener(this);
		reserveNowButton.setOpaque(false);

		exitButton.setBounds(315, 560, 220, 25);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		exitButton.setOpaque(false);

		JLabel displayLabel3 = new JLabel(amount.toString()); // COTTAGE FEE
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reserveNowButton) {
			frame.dispose();
			CreateReservationDto createReservationDto = new CreateCottageReservationDto(userId, resortDto.id(), ReservationStatus.PENDING, amount);
			new CustomerInformation(userId, resortDto, createReservationDto);
		}
	}
}
