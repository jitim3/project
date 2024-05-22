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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

class AdminWithdraw_Confirmation extends JFrame implements ActionListener{
	
	//private final UserService userService = new DefaultUserService();
		JFrame frame = new JFrame("ADMIN CONFIRMATION");
		private JPasswordField passField;
	
		JLabel label = new JLabel("CONFIRMATION", SwingConstants.CENTER);
		JButton confirm = new JButton(" CONFIRM ");
		JButton exitbutton = new JButton("EXIT");
	
	
		public AdminWithdraw_Confirmation(){
		
		
		JLabel pass = new JLabel("ENTER PASSWORD: ");// with textfield
		passField = new JPasswordField();
		
		pass.setBounds(130,150,300,30);
		pass.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		passField.setBounds(310, 150, 200, 30);
		passField.setFont(new Font("Times New Roman", Font.BOLD,17));
		passField.setPreferredSize(new Dimension(20, 50));
		
		label.setBounds(95,80,500,30);
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
	    confirm.setBounds (250, 250, 200, 30);
	    confirm.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    confirm.setFocusable(false);
	    
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
		frame.add(passField);
		frame.add(pass);
		frame.add(confirm);
		frame.add(exitbutton);
		frame.add(label);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(700,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		}
}
