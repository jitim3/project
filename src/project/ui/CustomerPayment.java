package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import project.dto.CreateCottageReservationDto;
import project.dto.CreateReservationDto;
import project.dto.CreateRoomReservationDto;
import project.dto.ResortDto;
import project.service.ReservationService;
import project.service.impl.DefaultReservationService;

public class CustomerPayment implements ActionListener {
	private final ReservationService reservationService;
	private final long userId;
	private final ResortDto resortDto;
	private CreateReservationDto createReservationDto;
	private final JFrame frame = new JFrame("Amount");
	private final JLabel lblEnterAmount = new JLabel("Enter exact amount:");
	private final JTextField amountTextField = new JTextField();
	private final JButton confirmButton = new JButton("Confirm");
	private final JButton backButton = new JButton("Back");
	private final JLabel displayTotal = new JLabel();
	private final JFrame parentFrame;
	private String windowEventSource = "";

	public CustomerPayment(Long userId, ResortDto resortDto, CreateReservationDto createReservationDto, JFrame parentFrame) {
		this.reservationService = new DefaultReservationService();
		this.userId = userId;
		this.resortDto = resortDto;
		this.createReservationDto = createReservationDto;
		this.parentFrame = parentFrame;
		
		lblEnterAmount.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEnterAmount.setBounds(114, 135, 150, 14);
		amountTextField.setBounds(240, 132, 96, 20);
		amountTextField.setColumns(10);
		amountTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		confirmButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		confirmButton.setBounds(247, 223, 89, 23);

		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		backButton.setBounds(101, 223, 89, 23);

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"confirmButton".equals(windowEventSource)) {
					parentFrame.setVisible(true);
				}
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			windowEventSource = "confirmButton";
			frame.dispose();
			BigDecimal amount;
			try {
				amount = new BigDecimal(Integer.parseInt(amountTextField.getText()));
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Invalid amount. Please enter amount.", "Payment Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Long reservationId = null;
			
			if (createReservationDto instanceof CreateRoomReservationDto createRoomReservationDto) {
				CreateRoomReservationDto newRoomReservationDto = createRoomReservationDto
						.amount(amount)
						.createdAt(Instant.now());
				reservationId = this.reservationService.createRoomReservation(newRoomReservationDto);
			} else if (createReservationDto instanceof CreateCottageReservationDto createCottageReservationDto) {
				CreateCottageReservationDto newCottageReservationDto = createCottageReservationDto
						.amount(amount)
						.createdAt(Instant.now());
				reservationId = this.reservationService.createCottageReservation(newCottageReservationDto);
			}
			
			if (reservationId == null || reservationId == 0) {
				JOptionPane.showMessageDialog(null, "Reservation was not successful. Please try again.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
			} else {			
				new ConfirmationMessage();
			}
		} else if (e.getSource() == backButton) {
			frame.dispose();
		}
	}
}