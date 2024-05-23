package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Admin_Trasaction implements ActionListener {

	JFrame frame = new JFrame("ADMIN TRANSACTION");
	JLabel label = new JLabel("VIEW TRANSACTION");
	JLabel details = new JLabel("Transaction Details: ");
	JLabel date = new JLabel("Date");
	JLabel time = new JLabel("Time");
	JLabel transaction = new JLabel("Description");
	JButton exitButton = new JButton("Exit");

	public Admin_Trasaction() {
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label.setBounds(200, 40, 500, 30);

		details.setBounds(270, 70, 500, 40);
		details.setFont(new Font("Times New Roman", Font.BOLD, 20));

		date.setBounds(190, 100, 150, 40);
		date.setFont(new Font("Times New Roman", Font.BOLD, 15));

		time.setBounds(320, 100, 500, 40);
		time.setFont(new Font("Times New Roman", Font.BOLD, 15));

		transaction.setBounds(450, 100, 500, 40);
		transaction.setFont(new Font("Times New Roman", Font.BOLD, 15));

		exitButton.setBounds(310, 370, 100, 30);
		exitButton.addActionListener(this);
		exitButton.setFocusable(false);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("background.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);

		frame.add(time);
		frame.add(transaction);
		frame.add(details);
		frame.add(date);
		frame.add(label);
		frame.add(exitButton);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			frame.dispose();
		}
	}
}