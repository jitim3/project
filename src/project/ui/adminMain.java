package project.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class adminMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminMain frame = new adminMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminMain() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("beach2.png"));
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Edit Information");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton.setBounds(251, 53, 199, 46);
		btnNewButton.setFocusable(false);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Reviews");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewReviewsAdmin review = new ViewReviewsAdmin();
				review.setVisible(true);
				
				dispose();
			}
		});
		btnNewButton_1.setBounds(251, 119, 199, 46);
		btnNewButton_1.setFocusable(false);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Reservation");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_2.setBounds(251, 192, 199, 46);
		btnNewButton_2.setFocusable(false);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View Wallet");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_3.setBounds(251, 264, 199, 46);
		btnNewButton_3.setFocusable(false);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("View Transaction");
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_4.setBounds(251, 334, 199, 46);
		btnNewButton_4.setFocusable(false);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("EXIT");
		btnNewButton_5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_5.setBounds(309, 414, 85, 21);
		btnNewButton_5.setFocusable(false);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setIcon(new ImageIcon("figma.jpg"));
		lblNewLabel.setBounds(0, 0, 686, 463);
		contentPane.add(lblNewLabel);
	}
}
