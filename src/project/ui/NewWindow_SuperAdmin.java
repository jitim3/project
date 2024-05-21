package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.dto.UserDto;
import project.service.UserService;

public class NewWindow_SuperAdmin implements ActionListener {
	private final UserService userService;
	private final JPasswordField passwordField = new JPasswordField();
	private final JFrame frame = new JFrame("Super Admin");
	private final JLabel superAdminLabel = new JLabel("Super Admin");
	private final JLabel enterPasswordLabel = new JLabel("Enter Password:");
	private final JButton enterButton = new JButton("Enter");
	private final JButton exitButton = new JButton("Exit");

	public NewWindow_SuperAdmin(final UserService userService) {
		this.userService = userService;
		
		enterButton.setBounds(80, 165, 100, 30);
		enterButton.setFocusable(false);
		enterButton.addActionListener(this);

		exitButton.setBounds(200, 165, 100, 30);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);

		superAdminLabel.setBounds(150, 60, 150, 80);
		superAdminLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		enterPasswordLabel.setBounds(72, 123, 100, 25);
		enterPasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		passwordField.setBounds(155, 123, 100, 25);
		passwordField.setPreferredSize(new Dimension(100, 70));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 400, 350);

		frame.add(exitButton);
		frame.add(enterButton);
		frame.add(passwordField);
		frame.add(enterPasswordLabel);
		frame.add(superAdminLabel);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
			String password = new String(passwordField.getPassword());
			Optional<UserDto> userDtOptional = this.userService.getSuperAdmin("sa", password);
			if (userDtOptional.isPresent()) {
				JOptionPane.showMessageDialog(null, "Log in Successfully!", "Log in", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				SuperAdmin_NextPage window = new SuperAdmin_NextPage();
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			frame.dispose();
		}
	}
	
	public class SuperAdmin_NextPage implements ActionListener {
		JFrame frame = new JFrame("Super Admin");
		JLabel label = new JLabel("Welcome Super Admin!");
		
		JButton button = new JButton("View Registered Resort");
		JButton button1 = new JButton("Approval");
		JButton button2 = new JButton("View Wallet");
		JButton button3 = new JButton("View Transaction");
		JButton button4 = new JButton("Exit");

		public SuperAdmin_NextPage() {

			button.setBounds(220, 100, 230, 40);
			button.setFocusable(false);
			button.addActionListener(this);

			button1.setBounds(220, 160, 230, 40);
			button1.setFocusable(false);
			button1.addActionListener(this);

			button2.setBounds(220, 220, 230, 40);
			button2.setFocusable(false);
			button2.addActionListener(this);

			button3.setBounds(220, 280, 230, 40);
			button3.setFocusable(false);
			button3.addActionListener(this);
			
			button4.setBounds(220, 340, 230, 40);
			button4.setFocusable(false);
			button4.addActionListener(this);

			label.setBounds(180,40,500,30);
			label.setFont(new Font("Times New Roman", Font.BOLD, 30));

			ImageIcon icon = new ImageIcon("beach2.png");

			ImageIcon background = new ImageIcon("figma.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 700, 500);
			
			frame.add(button4);
			frame.add(button3);
			frame.add(button2);
			frame.add(button1);
			frame.add(button);
			frame.add(label);
			frame.add(backgroundLabel);
			frame.setIconImage(icon.getImage());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(700, 500);
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button) {
				frame.dispose();
				Superadmin_Registered_Resort window = new Superadmin_Registered_Resort();
			} else if (e.getSource()==button1) {
				frame.dispose();
				AprovalResort window2 = new AprovalResort();
			}else if (e.getSource()==button2) {
				frame.dispose();
				SuperAdminWallet window3 = new SuperAdminWallet();
				
			}else if (e.getSource()==button3) {
				frame.dispose();
				SuperAdmin_Transaction window = new SuperAdmin_Transaction();
			}else if (e.getSource()==button4) {
				System.exit(0); 
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.",
						"Login Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	public class Superadmin_Registered_Resort implements ActionListener {
		
		JFrame frame = new JFrame("Registered Resort");
		JLabel label = new JLabel("VIEW ALL RESORT");
		JButton exitButton = new JButton("BACK");
		
		public Superadmin_Registered_Resort () {
			
			 exitButton.setBounds(275,350, 150, 40);
		     exitButton.addActionListener(this);
		     exitButton.setFocusable(false);
			
			 label.setBounds(210, 40, 500, 30);
		     label.setFont(new Font("Times New Roman", Font.BOLD, 30));

		     ImageIcon icon = new ImageIcon("beach2.png");

				ImageIcon background = new ImageIcon("figma.jpg");
				Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
				JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
				backgroundLabel.setBounds(0, 0, 700, 500);
				
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

	/* diri na part is katong sa document so once na mo click siya sa resort name then makita na 
	   niya ang document na gi send ni admin nga na verify na sa super admin.*/

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==exitButton) {
			frame.dispose();
			SuperAdmin_NextPage window = new SuperAdmin_NextPage();
			}
		}
	}
	public class AprovalResort implements ActionListener  {
		

		JFrame frame = new JFrame("APPROVAL");
		JLabel label = new JLabel("RESORTS TO BE APPROVED");
		JButton exitButton = new JButton("Exit");
		
		public AprovalResort() {
			 exitButton.setBounds(275,350, 150, 40);
		     exitButton.addActionListener(this);
		     exitButton.setFocusable(false);
			
			 label.setBounds(150, 40, 500, 30);
		     label.setFont(new Font("Times New Roman", Font.BOLD, 30));

		     ImageIcon icon = new ImageIcon("beach2.png");

				ImageIcon background = new ImageIcon("figma.jpg");
				Image backgroundImage = background.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
				JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
				backgroundLabel.setBounds(0, 0, 700, 500);
				
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
			if (e.getSource() == exitButton) {
				frame.dispose();
				//DocsVerification   window = new DocsVerification ();
			
			}
		}
	}
	class SuperAdminWallet implements ActionListener {
		
		JFrame frame = new JFrame("SUPER ADMIN WALLET");
		
		JLabel label = new JLabel("VIEW WALLET", SwingConstants.CENTER);
		JLabel balance = new JLabel("BALANCE: ", SwingConstants.CENTER);
		JLabel money = new JLabel("0.00 ", SwingConstants.CENTER);
		JButton widButton = new JButton("WITHDRAW");
		JButton exitbutton = new JButton("EXIT");
		
		public SuperAdminWallet(){
		
			money.setBounds(200,150,300,30);
			money.setFont(new Font("Times New Roman", Font.BOLD, 17));
			
			balance.setBounds(100, 150, 300, 30);
			balance.setFont(new Font("Times New Roman", Font.BOLD, 17));
			
		    widButton.setBounds (270, 250, 150, 30);
		    widButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		    widButton.setFocusable(false);
		    widButton.addActionListener(this);
		    
		    exitbutton.setBounds (270, 300, 150, 30); 
		    exitbutton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		    exitbutton.setFocusable(false);
		    exitbutton.addActionListener(this);
		    
			label.setBounds(195,80,300,30);
			label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
			// cover imageIcon
			ImageIcon icon = new ImageIcon ("beach2.png");
			
			// for the background
			ImageIcon coverbackground = new ImageIcon ("figma.jpg");
			Image backgroundImage = coverbackground.getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 700, 500);
			
			frame.add(balance);
			frame.add(money);
			frame.add(widButton);
			frame.add(exitbutton);
			frame.add(label);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setSize(700,500);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== widButton) {
				frame.dispose();
				SuperAdminWithdraw window = new SuperAdminWithdraw();
			}else if (e.getSource()==exitbutton) {
				System.exit(0);
			}
		}
	}
	class SuperAdminWithdraw implements ActionListener{
		
		
		JFrame frame = new JFrame("WITHDRAW");
		JTextField amountField = new JTextField();;
	
		JLabel label = new JLabel("WITHDRAW",SwingConstants.CENTER);
		JButton widbutton = new JButton(" CONFIRM WITHDRAW");
		JButton exitbutton = new JButton("EXIT");
	
	
		public SuperAdminWithdraw(){
		
		
		JLabel balance = new JLabel("ENTER WITHDRAWAL AMOUNT: ");// with textfield
		
		balance.setBounds(100,150,300,30);
		balance.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		amountField.setBounds(380, 150, 150, 30);
		amountField.setFont(new Font("Times New Roman", Font.BOLD,17));
		amountField.setPreferredSize(new Dimension(20, 50));
		
		label.setBounds(195,80,300,30);
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
	    widbutton.setBounds (250, 250, 200, 30);
	    widbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    widbutton.addActionListener(this);
	    widbutton.setFocusable(false);
	    
	    exitbutton.setBounds (250, 300, 200, 30); 
	    exitbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    exitbutton.addActionListener(this);
	    exitbutton.setFocusable(false);
	    
		frame.setResizable(false);
		
		// cover imageIcon
		ImageIcon icon = new ImageIcon ("beach2.png");
		
		// for the background
		ImageIcon coverbackground = new ImageIcon ("figma.jpg");
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
		frame.setLayout(null);
		frame.setSize(700,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==widbutton) {
			frame.dispose();
			SuperAdminConfirmation window = new SuperAdminConfirmation();
		}else {
			System.exit(0);
		}
		
	}
}
	class SuperAdminConfirmation implements ActionListener{
		
		//private final UserService userService = new DefaultUserService();
			JFrame frame = new JFrame("SUPER ADMIN CONFIRMATION");
			private JPasswordField passField;
		
			JLabel label = new JLabel("CONFIRMATION", SwingConstants.CENTER);
			JButton confirm = new JButton(" CONFIRM ");
			JButton exitbutton = new JButton("EXIT");
		
		
			public SuperAdminConfirmation(){
			
			
			JLabel pass = new JLabel("ENTER PASSWORD: ");// with textfield
			passField = new JPasswordField();
			
			pass.setBounds(130,150,300,30);
			pass.setFont(new Font("Times New Roman", Font.BOLD, 17));
			
			passField.setBounds(310, 150, 200, 30);
			passField.setFont(new Font("Times New Roman", Font.BOLD,17));
			passField.setPreferredSize(new Dimension(20, 50));
			
			label.setBounds(105,80,500,30);
			label.setFont(new Font("Times New Roman", Font.BOLD, 30));
			
		    confirm.setBounds (250, 250, 200, 30);
		    confirm.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    confirm.addActionListener(this);
		    confirm.setFocusable(false);
		    
		    exitbutton.setBounds (250, 300, 200, 30); 
		    exitbutton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    exitbutton.addActionListener(this);
		    exitbutton.setFocusable(false);
		    
			frame.setResizable(false);
			
			// cover imageIcon
			ImageIcon icon = new ImageIcon ("beach2.png");
			
			// for the background
			ImageIcon coverbackground = new ImageIcon ("figma.jpg");
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
			if (e.getSource()==confirm) {
				JOptionPane.showMessageDialog(null,"Withdraw Successful!","Success!",JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				SuperAdmin_NextPage window = new SuperAdmin_NextPage();
			}else {
				JOptionPane.showMessageDialog(null,"Incorrect Password","Incorrect Password",JOptionPane.ERROR_MESSAGE);
			}
		
			}
	}
		  class SuperAdmin_Transaction implements ActionListener {
			
			JFrame frame = new JFrame("SUPER ADMIN TRANSACTION");
			JLabel label = new JLabel("VIEW TRANSACTION");
			JLabel details = new JLabel("Transaction Details: ");
			JLabel date = new JLabel("Date");
			JLabel time = new JLabel("Time");
			JLabel transaction = new JLabel("Description");
			JButton exitButton = new JButton("Exit");
			
		 public SuperAdmin_Transaction(){
			
			 label.setFont(new Font("Times New Roman", Font.BOLD, 30));
			 label.setBounds(200, 40, 500, 30);
			
			 details.setBounds(270,70, 500, 40);
		     details.setFont(new Font("Times New Roman", Font.BOLD, 20));
		     
		     date.setBounds(250,100, 150, 40);
		     date.setFont(new Font("Times New Roman", Font.BOLD, 15));
		     
		     time.setBounds(320,100, 500, 40);
		     time.setFont(new Font("Times New Roman", Font.BOLD, 15));
		     
		     transaction.setBounds(370,100, 500, 40);
		     transaction.setFont(new Font("Times New Roman", Font.BOLD, 15));
		     
			 exitButton.setBounds(275,370, 150, 40);
		     exitButton.addActionListener(this);
		     exitButton.setFocusable(false);

		     ImageIcon icon = new ImageIcon("beach2.png");

				ImageIcon background = new ImageIcon("figma.jpg");
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
			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        frame.setSize(700, 500);
			        frame.setLayout(null);
			        frame.setVisible(true);
			        frame.setResizable(false);
				}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}

	}

	}
