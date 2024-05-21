package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import project.service.UserService;
import project.service.impl.DefaultUserService;

public class LaunchPage implements ActionListener {
JFrame frame = new JFrame("SouthShore Hub Oasis");
	
	JButton button = new JButton("Get Started");
	JLabel label = new JLabel("SOUTHSHORE             HUB OASIS", SwingConstants.CENTER);
	JLabel label2 = new JLabel("Where Every      Stay is a Story", SwingConstants.CENTER);

	public LaunchPage() {
		label.setBounds (70, 155, 500, 70);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label2.setBounds (95, 190, 500, 70);
        label2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        button.setBounds (270, 300, 150, 30);
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.addActionListener(this);
        button.setFocusable(false);
        
        
		
		
		// cover imageIcon
		ImageIcon icon = new ImageIcon ("beach2.png");
		
		// for the background
		ImageIcon coverbackground = new ImageIcon ("firstpage.jpg");
		Image backgroundImage = coverbackground.getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 700, 500);
		
		frame.add(button);
		frame.add(label);
		frame.add(label2);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(700,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			frame.dispose();
			coverpage window = new coverpage();
		}
	
		
	}
}
class coverpage implements ActionListener{
	private final UserService userService;
	private final JFrame frame = new JFrame();
	private final JButton button = new JButton("Customer");
	private final JButton button1 = new JButton("Admin");
	private final JButton button2 = new JButton("Super Admin");

	public coverpage() {
		this.userService = new DefaultUserService();
		
		button.setBounds(75, 50, 200, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(75, 100, 200, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(75, 150, 200, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);

		// GUI ICON
		ImageIcon icon = new ImageIcon("beach2.png"); // Set my Imported Icon

		// GUI BACKGROUND
		ImageIcon backgroundImageIcon = new ImageIcon("beach.jpg");
		Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
		frame.setIconImage(backgroundImage);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		frame.setLocation(300, 250);
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setTitle("Resort");
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			NewWindow_Customer window = new NewWindow_Customer(this.userService);
		} else if (e.getSource() == button1) {
			frame.dispose();
			NewWindow_Admin window = new NewWindow_Admin(this.userService);
		} else {
			frame.dispose();
			NewWindow_SuperAdmin window = new NewWindow_SuperAdmin(this.userService);
		}

	}

}
