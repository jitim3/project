package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import project.dto.CreateCustomerDto;
import project.dto.CustomerDto;
import project.dto.UpdateCustomerDto;
import project.service.CustomerService;

public class CustomerViewOrUpdateInformation implements ActionListener {
	private final long userId;
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
	private final JButton saveButton = new JButton("Save"); 
	private final JButton backButton = new JButton("Back");
	private final JFrame parentFrame;
	private String windowEventSource = "";

	public CustomerViewOrUpdateInformation(long userId, JFrame parentFrame) {
		this.userId = userId;
		this.parentFrame = parentFrame;
		this.customerService = new CustomerService();
		
		Optional<CustomerDto> customerDtoOptional = this.customerService.getCustomerById(this.userId);
		customerDtoOptional.ifPresent(customerDto -> {
			this.firstNameTextField.setText(customerDto.firstName());
			this.lastNameTextField.setText(customerDto.lastName());
			this.contactNumberTextField.setText(customerDto.contactNumber());
			this.emailAddressTextField.setText(customerDto.emailAddress());
		});
		
		saveButton.setBounds(300, 350, 99, 50);
		saveButton.setFocusable(false);
		saveButton.addActionListener(this);

		backButton.setBounds(300, 400	, 99, 50);
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		
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

		lastNameLabel.setBounds(215, 70, 500, 180);
		lastNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lastNameTextField.setBounds(340, 150, 150, 25);
		lastNameTextField.setPreferredSize(new Dimension(150, 100));

		contactNumberLabel.setBounds(215, 160, 500, 85);
		contactNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contactNumberTextField.setBounds(340, 190, 150, 25);
		contactNumberTextField.setPreferredSize(new Dimension(150, 100));

		emailAddressLabel.setBounds(215, 200, 500, 85);
		emailAddressLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		emailAddressTextField.setBounds(340, 230, 150, 25);
		emailAddressTextField.setPreferredSize(new Dimension(150, 100));

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
		frame.add(saveButton);
		frame.add(backButton);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"saveButton".equals(windowEventSource) && !"backButton".equals(windowEventSource)) {
					parentFrame.setVisible(true);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			this.windowEventSource = "saveButton";
			String firstName = this.firstNameTextField.getText();
			String lastName = this.lastNameTextField.getText();
			String contactNumber = this.contactNumberTextField.getText();
			String emailAddress = this.emailAddressTextField.getText();
			List<String> errors = this.validateInputs(firstName, lastName, contactNumber, emailAddress);
			if (errors.isEmpty()) {
				boolean customerExists = this.customerService.isCustomerExists(this.userId);
				CustomerDto customerDto;
				if (customerExists) {
					UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto(this.userId, firstName, lastName, contactNumber, emailAddress, Instant.now());
					customerDto = this.customerService.updateCustomer(updateCustomerDto);
				} else {
					CreateCustomerDto createCustomerDto = new CreateCustomerDto(this.userId, firstName, lastName, contactNumber, emailAddress, Instant.now());
					customerDto = this.customerService.createCustomer(createCustomerDto);
				}
				
				if (customerDto != null) {
					JOptionPane.showMessageDialog(null, "Customer information has been saved.", "Customer Information", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				String message = errors.stream().collect(Collectors.joining("\n"));
				JOptionPane.showMessageDialog(null, message, "Customer Info Save Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == backButton) {
			this.windowEventSource = "backButton";
			frame.dispose();
			parentFrame.setVisible(true);
		}
	}
	
	private List<String> validateInputs(String firsName, String lastName, String contactNumber, String emailAddress) {
		List<String> errors = new ArrayList<String>();
		if (firsName == null || firsName.isBlank()) {
			errors.add("Invalid first name.");
		}
		if (lastName == null || lastName.isBlank()) {
			errors.add("Invalid last name.");
		}
		if (contactNumber == null || contactNumber.isBlank()) {
			errors.add("Invalid contact number.");
		}
		if (emailAddress == null || emailAddress.isBlank()) {
			errors.add("Invalid email address.");
		}
		
		return errors;
	}
}
