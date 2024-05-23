package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

	public class  Admin_BusinessVerification implements ActionListener  {

		JFrame frame = new JFrame("DOCUMENT FOR VERIFICATION");
		JLabel label = new JLabel("UPLOADED DOCUMENTS");
		JButton approve = new JButton("APPROVE");
		JButton decline = new JButton("DECLINE");
		JButton exitButton = new JButton("Exit");
		
		public  Admin_BusinessVerification() {
			
			 approve.setBounds(150,320, 150, 40);
		     approve.addActionListener(this);
		     approve.setFocusable(false);
		     
		     decline.setBounds(400,320, 150, 40);
		     decline.addActionListener(this);
		     decline.setFocusable(false);
		     
			 exitButton.setBounds(275,370, 150, 40);
		     exitButton.addActionListener(this);
		     exitButton.setFocusable(false);
			
			 label.setBounds(170, 40, 500, 30);
		     label.setFont(new Font("Times New Roman", Font.BOLD, 30));

		     ImageIcon icon = new ImageIcon("beach2.png");

				ImageIcon background = new ImageIcon("background.jpg");
				Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
				JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
				backgroundLabel.setBounds(0, 0, 700, 500);
				
				 	frame.add(approve);
				 	frame.add(decline);
					frame.add(label);
			        frame.add(exitButton);
			        frame.add(backgroundLabel);
			        frame.setIconImage(icon.getImage());
			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        frame.setSize(700, 500);
			        frame.setLayout(null);
			        frame.setVisible(true);
			        frame.setResizable(false);
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == approve) {
				JOptionPane.showMessageDialog(null, "Admin Resort Approve", "Resort Approve", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
			}else if (e.getSource()== decline) {
				JOptionPane.showMessageDialog(null,  "Admin Resort Decline", "Resort has been decline", JOptionPane.ERROR_MESSAGE);
				frame.dispose();
			}else if (e.getSource()==exitButton) {
				System.exit(0); 
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.",
						"Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
