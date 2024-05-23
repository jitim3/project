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

public class ViewReservationAdmin implements ActionListener {

    JFrame frame = new JFrame("View Reservation"); 
    JLabel lblTitle = new JLabel("VIEW RESERVATION");
    JLabel lblDetails = new JLabel("RESERVATION DETAILS");
    JButton btnExit = new JButton("EXIT");
    JButton btnDone = new JButton("DONE");

    public ViewReservationAdmin() {
		
    	 lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
         lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
         lblTitle.setBounds(203, 22, 277, 20);
         
         lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
         lblDetails.setFont(new Font("Times New Roman", Font.PLAIN, 11));
         lblDetails.setBounds(271, 53, 143, 14);
         
         btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
         btnExit.setBounds(193, 404, 89, 23);
         btnExit.setFocusable(false);
         btnExit.addActionListener(this);
         
         btnDone.addActionListener(this);
         btnDone.setFont(new Font("Times New Roman", Font.PLAIN, 11));
         btnDone.setBounds(381, 404, 89, 23);
         btnDone.setFocusable(false);

        ImageIcon image = new ImageIcon("beach2.png");
        ImageIcon bg = new ImageIcon("bgdagat.jpg");

        Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);
        

        frame.setIconImage(image.getImage());
        frame.setSize(700, 500);
        frame.setLayout(null); 
        frame.setResizable(false);
        frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        frame.add(lblTitle);
        frame.add(lblDetails);
        frame.add(btnExit);
        frame.add(btnDone);
        frame.add(backgroundLabel); 
        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	 if (e.getSource() == btnExit) {
             frame.dispose();
         } else if (e.getSource() == btnDone) {
             // Implement the action to be performed when "DONE" is clicked
         }
    	
    }
}