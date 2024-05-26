package project.ui;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import project.dto.CreateReservationDto;
import project.dto.CreateRoomReservationDto;
import project.dto.ResortDto;
import project.util.AppUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Properties;

public class Checkin extends JFrame implements ActionListener {
	private static final String DATE_FORMAT = "dd-MMM-yyyy";
	private final long userId;
	private final ResortDto resortDto;
	private final CreateRoomReservationDto createRoomReservationDto;
	private final BigDecimal ratePerNight;
	private final JDatePickerImpl datePicker;
	private final JFrame frame = new JFrame("Check in");
	private final JLabel label = new JLabel("CHECK IN");
	private final JLabel label1 = new JLabel("Enter days to Stay");
	private final JLabel label2 = new JLabel("Enter date");
	private final JTextField stayTextField = new JTextField("1");
	private final JButton exit = new JButton("EXIT");
	private final JButton next = new JButton("NEXT");

	public Checkin(long userId, ResortDto resortDto, CreateRoomReservationDto createRoomReservationDto, BigDecimal ratePerNight) {
		this.userId = userId;
		this.resortDto = resortDto;
		this.createRoomReservationDto = createRoomReservationDto;
		this.ratePerNight = ratePerNight;

		next.setBounds(250, 285, 75, 25);
		next.setFocusable(false);
		next.addActionListener(this);
		next.setOpaque(false);

		exit.setBounds(100, 285, 75, 25);
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setOpaque(false);

		label2.setBounds(100, 170, 800, 80);
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));

		stayTextField.setBounds(100, 125, 150, 25);
		stayTextField.setPreferredSize(new Dimension(150, 100));
		AppUtils.numeric(stayTextField);

		label1.setBounds(100, 75, 800, 80);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 15));

		label.setBounds(180, 15, 800, 80);
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 500, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 500);

		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl panel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(panel, new AbstractFormatter() {
			@Override
			public String valueToString(Object value) {
				if (value != null) {
					Calendar calendar = (Calendar) value;
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					return format.format(calendar.getTime());
				}

				return "";
			}

			@Override
			public Object stringToValue(String text) {
				return "";
			}
		});
		frame.setLayout(null);

		datePicker.setBounds(100, 220, 200, 30); // Adjust these values as needed
		frame.add(next);
		frame.add(exit);
		frame.add(label2);
		frame.add(stayTextField);
		frame.add(label1);
		frame.add(label);
		frame.add(datePicker);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.pack();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource() == next) {
			int numberOfStay = getNumberOfStay(stayTextField.getText());
			
			LocalDate startDate = this.getEnterDate(datePicker.getJFormattedTextField().getText());
			if (startDate == null || startDate.isEqual(LocalDate.now()) || startDate.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Invalid enter date. Please enter enter date.", "Payment Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			LocalDate endDate = startDate.plusDays(numberOfStay - 1L);
			BigDecimal amount = ratePerNight.multiply(BigDecimal.valueOf(numberOfStay));
			
			CreateReservationDto createReservationDto = createRoomReservationDto
					.startDate(startDate)
					.endDate(endDate)
					.amount(amount);
			frame.dispose();
			new CustomerInformation(userId, resortDto, createReservationDto);
		}

	}
	
	private int getNumberOfStay(String stay) {
		int numberOfStay;
		try {
			numberOfStay = Integer.parseInt(stay);
			if (numberOfStay < 1) {
				numberOfStay = 1;
			}
		} catch (NumberFormatException e2) {
			stayTextField.setText("1");
			numberOfStay = 1;
		}
		
		return numberOfStay;
	}
	
	private LocalDate getEnterDate(String date) {
		if (date == null || date.isBlank()) {
			return null;
		}
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			return LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
		
	}
}
