package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import project.dao.entity.CommissionRate;
import project.dto.CreateReservationDto;
import project.dto.CustomerDto;
import project.dto.ResortDto;
import project.service.CustomerService;

public class CustomerInformation implements ActionListener {
	private final long userId;
	private final ResortDto resortDto;
	private final CreateReservationDto createReservationDto;
	private final BigDecimal computedAmount;
	private final CustomerService customerService;
	private final JFrame frame = new JFrame("Customer Information");
	private final JLabel customerInfoLabel = new JLabel("CUSTOMER INFORMATION");
	private final JLabel firstLNameLabel = new JLabel("First Name:");
	private final JTextField firstNameTextField = new JTextField();
	private final JLabel lastNameLabel = new JLabel("Last Name:");
	private final JTextField lastNameTextField = new JTextField();
	private final JLabel contactNumberLabel = new JLabel("Contact Number:");
	private final JTextField contactNumberTextField = new JTextField();
	private final JLabel emailAddressLabel = new JLabel("Email Address:");
	private final JTextField emailAddressTextField = new JTextField();
	private final JButton confirmButton = new JButton("Confirm");
	private final JFrame customerMenuFrame;
	private String windowSourceEvent = "";

	public CustomerInformation(JFrame customerMenuFrame, long userId, ResortDto resortDto, CreateReservationDto createReservationDto, BigDecimal computedAmount) {
		this.customerMenuFrame = customerMenuFrame;
		this.userId = userId;
		this.resortDto = resortDto;
		this.createReservationDto = createReservationDto;
        this.computedAmount = computedAmount;
        this.customerService = new CustomerService();

		Optional<CustomerDto> customerDtoOptional = this.customerService.getCustomerById(this.userId);
		customerDtoOptional.ifPresent(customerDto -> {
			this.firstNameTextField.setText(customerDto.firstName());
			this.lastNameTextField.setText(customerDto.lastName());
			this.contactNumberTextField.setText(customerDto.contactNumber());
			this.emailAddressTextField.setText(customerDto.emailAddress());
		});

		confirmButton.setBounds(300, 350, 99, 50);
		confirmButton.setFocusable(false);
		confirmButton.addActionListener(this);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(800, 700, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 800, 700);

		customerInfoLabel.setBounds(180, 25, 500, 50);
		customerInfoLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

		firstLNameLabel.setBounds(215, 80, 500, 85);
		firstLNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		firstNameTextField.setBounds(340, 110, 150, 25);
		firstNameTextField.setPreferredSize(new Dimension(150, 100));
		firstNameTextField.setEnabled(false);

		lastNameLabel.setBounds(215, 70, 500, 180);
		lastNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lastNameTextField.setBounds(340, 150, 150, 25);
		lastNameTextField.setPreferredSize(new Dimension(150, 100));
		lastNameTextField.setEnabled(false);

		contactNumberLabel.setBounds(215, 160, 500, 85);
		contactNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contactNumberTextField.setBounds(340, 190, 150, 25);
		contactNumberTextField.setPreferredSize(new Dimension(150, 100));
		contactNumberTextField.setEnabled(false);

		emailAddressLabel.setBounds(215, 200, 500, 85);
		emailAddressLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		emailAddressTextField.setBounds(340, 230, 150, 25);
		emailAddressTextField.setPreferredSize(new Dimension(150, 100));
		emailAddressTextField.setEnabled(false);

		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.add(customerInfoLabel);
		frame.add(firstLNameLabel);
		frame.add(firstNameTextField);
		frame.add(lastNameLabel);
		frame.add(lastNameTextField);
		frame.add(contactNumberLabel);
		frame.add(contactNumberTextField);
		frame.add(emailAddressLabel);
		frame.add(emailAddressTextField);
		frame.add(confirmButton);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"confirmButton".equals(windowSourceEvent)) {
					customerMenuFrame.setVisible(true);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			windowSourceEvent = "confirmButton";
			frame.dispose();
			new CustomerPayment(customerMenuFrame, frame, userId, resortDto, createReservationDto, computedAmount);
		} else {
			frame.dispose();
		}
	}

}
