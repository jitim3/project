package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Admin_Trasaction implements ActionListener {
	private final JFrame frame = new JFrame("ADMIN TRANSACTION");
	private final JLabel label = new JLabel("VIEW TRANSACTION");
	private final JLabel details = new JLabel("Transaction Details: ");
	private final JLabel date = new JLabel("Date");
	private final JLabel time = new JLabel("Time");
	private final JLabel transaction = new JLabel("Description");
	private final JButton exitButton = new JButton("Exit");
	private final JFrame parentFrame;

	public Admin_Trasaction(JFrame parentFrame) {
		this.parentFrame = parentFrame;
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				parentFrame.setVisible(true);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			frame.dispose();
		}
	}
}