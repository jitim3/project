package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CustomerInformation implements ActionListener {
	private final JFrame frame = new JFrame("Customer Information");
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField contactNumberTextField;
	private JTextField emailAddressTextField;
	private final JButton confirmButton = new JButton("Confirm");

	public CustomerInformation() {
		confirmButton.setBounds(300, 350, 99, 50);
		confirmButton.setFocusable(false);
		confirmButton.addActionListener(this);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(800, 700, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 800, 700);

		JLabel customerInfoLabel = new JLabel("CUSTOMER INFORMATION");
		JLabel firstLNameLabel = new JLabel("First Name:");
		firstNameTextField = new JTextField();
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameTextField = new JTextField();
		JLabel contactNumberLabel = new JLabel("Contact Number:");
		contactNumberTextField = new JTextField();
		JLabel emailAddressLabel = new JLabel("Email Address:");
		emailAddressTextField = new JTextField();

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
		frame.add(confirmButton);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			frame.dispose();
			ConfirmationMessage window = new ConfirmationMessage();
		}
	}

}
