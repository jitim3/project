package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
public class NewWindow_SuperAdmin implements ActionListener{
	
	static char [] pass = {'a','d','m','i','n'};
	static JPasswordField password = new JPasswordField();
	JFrame frame = new JFrame("Super Admin");
	JLabel label = new JLabel("Super Admin");
	JLabel label1 = new JLabel("Enter Password:");
	JButton button = new JButton("Enter");
	JButton button1 = new JButton("Exit");
	NewWindow_SuperAdmin (){
		
		
		button.setBounds(80,165,100,30);
		button.setFocusable(false);
		button.addActionListener(this);
		
		button1.setBounds(200,165,100,30);
		button1.setFocusable(false);
		button1.addActionListener(this);
		
		label.setBounds(150,60,150,80);
		label.setFont(new Font("Times New Roman",Font.PLAIN,20));
		
		label1.setBounds(72, 123,100,25);
		label1.setFont(new Font("Times New Roman",Font.PLAIN,12));
		
		password.setBounds(155,123,100,25);
		password.setPreferredSize(new Dimension(100,70));
		
		ImageIcon icon = new ImageIcon("beach2.png");
		
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 400, 350);
		
		frame.add(button1);
		frame.add(button);
		frame.add(password);
		frame.add(label1);
		frame.add(label);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			char[] passwordInfo = password.getPassword();
			if (Arrays.equals(passwordInfo, pass)) {
				JOptionPane.showMessageDialog(null,"Log in Successfully!","Log in",JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				SuperAdmin_NextPage window = new SuperAdmin_NextPage();
			}else {
				JOptionPane.showMessageDialog(null,"Incorrect password!","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else {
			frame.dispose(); 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	class SuperAdmin_NextPage implements ActionListener{
		JFrame frame = new JFrame("Super Admin");
		JLabel label = new JLabel("Welcome Super Admin!");
		JButton button = new JButton("View Registered Resort");
		JButton button1 = new JButton("Approval");
		JButton button2 = new JButton("View Wallet");
		JButton button3 = new JButton("Exit");
		
		SuperAdmin_NextPage(){
			
			button.setBounds(140,130,230,40);
			button.setFocusable(false);
			button.addActionListener(this);
			
			button1.setBounds(140,190,230,40);
			button1.setFocusable(false);
			button1.addActionListener(this);
			
			
			button2.setBounds(140,250,230,40);
			button2.setFocusable(false);
			button2.addActionListener(this);
			
			button3.setBounds(140,310,230,40);
			button3.setFocusable(false);
			button3.addActionListener(this);
			
			
			
			label.setBounds(160,40,250,80);
			label.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,15));
			
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
			frame.setSize(500,500);
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setResizable(false);
			
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				frame.dispose();
				viewInfo window = new viewInfo();	
			}
		}
	}
	class viewInfo{
		JFrame frame = new JFrame("View Information");
		
		viewInfo(){
			
			frame.setSize(250, 250);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
		}
	}
}