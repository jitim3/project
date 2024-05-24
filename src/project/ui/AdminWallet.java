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
import javax.swing.SwingConstants;

import project.service.impl.DefaultUserService;

class AdminWallet extends JFrame implements ActionListener {
	private final JFrame frame = new JFrame("ADMIN WALLET");	
	private final JButton widbutton = new JButton("WITHDRAW");
	private final JButton exitbutton = new JButton("EXIT");
	private final JLabel label = new JLabel("VIEW WALLET", SwingConstants.CENTER);
	private final JLabel balance = new JLabel("BALANCE: ", SwingConstants.CENTER);
	private final JLabel money = new JLabel("0.00 ", SwingConstants.CENTER);
	private final JFrame parentFrame;
	private String windowEventSource = "";
	
	public AdminWallet(JFrame parentFrame) {	
		this.parentFrame = parentFrame;	
		money.setBounds(200,150,300,30);
		money.setFont(new Font("Times New Roman", Font.BOLD, 17));
		balance.setBounds(100, 150, 300, 30);
		balance.setFont(new Font("Times New Roman", Font.BOLD, 17));
		label.setBounds(195,80,300,30);
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));
	    widbutton.setBounds (270, 250, 150, 30);
	    widbutton.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    widbutton.setFocusable(false);
	    exitbutton.setBounds (270, 300, 150, 30); 
	    exitbutton.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    exitbutton.setFocusable(false);
	    
		frame.setResizable(false);
		
		// cover imageIcon
		ImageIcon icon = new ImageIcon ("beach2.png");
		
		// for the background
		ImageIcon coverbackground = new ImageIcon ("background.jpg");
		Image backgroundImage = coverbackground.getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);
		
		frame.add(balance);
		frame.add(money);
		frame.add(widbutton);
		frame.add(exitbutton);
		frame.add(label);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(700,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(!"widbutton".equals(windowEventSource) && !"exitbutton".equals(windowEventSource)) {
					parentFrame.setVisible(true);
				}
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == widbutton) {
			this.windowEventSource = "widbutton";
			frame.dispose();
			new AdminWithdraw();
		} else if (e.getSource() == exitbutton) {
			this.windowEventSource = "exitbutton";
			frame.dispose();
			new AdminDatabaseSignup(new DefaultUserService());
		} else {
			frame.dispose();
		}	
	}
}