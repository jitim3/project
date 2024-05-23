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
import javax.swing.SwingConstants;

class AdminWithdraw extends JFrame implements ActionListener{
	
	
	JFrame frame = new JFrame("ADMIN WITHDRAW");
	private JTextField amountField;

	JLabel label = new JLabel("WITHDRAW", SwingConstants.CENTER);
	JButton widbutton = new JButton(" CONFIRM WITHDRAW");
	JButton exitbutton = new JButton("EXIT");


	public AdminWithdraw(){
	
	
	JLabel balance = new JLabel("ENTER WITHDRAWAL AMOUNT: ");// with textfield
	amountField = new JTextField();
	
	balance.setBounds(100,150,300,30);
	balance.setFont(new Font("Times New Roman", Font.BOLD, 17));
	
	amountField.setBounds(380, 150, 150, 30);
	amountField.setFont(new Font("Times New Roman", Font.BOLD,17));
	amountField.setPreferredSize(new Dimension(20, 50));
	
	label.setBounds(195,80,300,30);
	label.setFont(new Font("Times New Roman", Font.BOLD, 30));
	
    widbutton.setBounds (250, 250, 200, 30);
    widbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
    widbutton.setFocusable(false);
    
    exitbutton.setBounds (250, 300, 200, 30); 
    exitbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
    exitbutton.setFocusable(false);
    
	frame.setResizable(false);
	
	// cover imageIcon
	ImageIcon icon = new ImageIcon ("beach2.png");
	
	// for the background
	ImageIcon coverbackground = new ImageIcon ("background.jpg");
	Image backgroundImage = coverbackground.getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT);
	JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
	backgroundLabel.setBounds(0, 0, 700, 500);
	
	//frame.add(withdrawAmount);
	frame.add(amountField);
	frame.add(balance);
	frame.add(widbutton);
	frame.add(exitbutton);
	frame.add(label);
	frame.setIconImage(icon.getImage());
	frame.add(backgroundLabel);
	frame.setSize(700,500);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == widbutton) {
			frame.dispose();
			AdminWithdraw_Confirmation adminwallet = new AdminWithdraw_Confirmation();
		} else if (e.getSource() == exitbutton) {
			frame.dispose();
			AdminDatabaseSignup AdminsignUpwindow = new AdminDatabaseSignup(userService);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
		}
	}