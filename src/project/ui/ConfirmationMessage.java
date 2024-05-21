package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ConfirmationMessage implements ActionListener {
    private final JFrame frame = new JFrame("Waiting Confirmation Message"); 
    private final JButton okButton = new JButton("OK");

    public ConfirmationMessage() {		
		okButton.setBounds(230, 350, 220, 40);
		okButton.setFocusable(false);
	    okButton.addActionListener(this);
		
		JLabel confirmationMessageLabel = new JLabel("Please wait for the resort to confirm your reservation. Thank you!");
		confirmationMessageLabel.setBounds(90, 50, 500, 250);
		confirmationMessageLabel.setOpaque(true);
		confirmationMessageLabel.setBackground(new Color (100, 255, 255, 64));
		confirmationMessageLabel.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,12));
		confirmationMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		confirmationMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        ImageIcon icon = new ImageIcon("beach2.png");
		 
		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(800,700, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 800,700);
       
        frame.setSize(700, 500);
        frame.setLayout(null); 
        frame.add(confirmationMessageLabel);
        frame.setResizable(false);
        frame.add(okButton);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == okButton) {
			frame.dispose();
    	}       
    }
}
