package project.ui;

import project.dto.ReservationDto;
import project.dto.UserDto;
import project.service.ReservationService;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

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
		List<ReservationDto> reservationDtos = reservationService.getReservationsByUserId(userDto.getId());
		Object[][] data = reservationDtos.stream()
				.map(reservationDto -> {
					String type;
					if (reservationDto.resortId() != null && reservationDto.resortId() > 0) {
						type = "Resort: " + reservationDto.resortName();
					} else {
						type = "Room: " + reservationDto.roomType() + " Room from " + reservationDto.roomResortName();
					}
					return new Object[] {
							type,
							reservationDto.reservationDate(),
							reservationDto.endDate(),
							reservationDto.amount(),
							reservationDto.createdAt()
					};
				})
				.toArray(size -> new Object[size][1]);
		String[] columnNames = { "Type", "Reservation Date", "End Date", "Amount", "Created Date"};
		JTable transactionTable = new JTable(data, columnNames);
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