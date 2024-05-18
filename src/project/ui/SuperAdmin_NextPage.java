package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SuperAdmin_NextPage implements ActionListener {
	JFrame frame = new JFrame("Super Admin");
	JLabel label = new JLabel("Welcome Super Admin!");
	JButton button = new JButton("View Registered Resort");
	JButton button1 = new JButton("Approval");
	JButton button2 = new JButton("View Wallet");
	JButton button3 = new JButton("Exit");

	public SuperAdmin_NextPage() {

		button.setBounds(140, 130, 230, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(140, 190, 230, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(140, 250, 230, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);

		button3.setBounds(140, 310, 230, 40);
		button3.setFocusable(false);
		button3.addActionListener(this);

		label.setBounds(160, 40, 250, 80);
		label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 500);

		frame.add(button3);
		frame.add(button2);
		frame.add(button1);
		frame.add(button);
		frame.add(label);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			ViewInfo window = new ViewInfo();
		}
	}
}
