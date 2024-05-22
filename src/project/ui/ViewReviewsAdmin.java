	package project.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReviewsAdmin implements ActionListener {
	
	JFrame frame = new JFrame("View Reviews"); 
    JButton btnEditInfo = new JButton("Edit Information");
    JScrollBar scrollBar = new JScrollBar();
    JLabel lblNewLabel = new JLabel("VIEW REVIEWS");
    JLabel lblNewLabel_1 = new JLabel();
    JButton btnExit = new JButton("EXIT");

    public ViewReviewsAdmin() {
		
    	scrollBar.setBounds(629, 64, 17, 267); 
    	
    	btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnExit.setBounds(308, 389, 89, 23);
        btnExit.setFocusable(false);
        btnExit.addActionListener(this);
        
        lblNewLabel_1.setBounds(30, 64, 615, 267);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color (100, 255, 255, 64));
        
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(265, 22, 171, 31);

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
        frame.add(btnExit);
        frame.add(lblNewLabel_1);
        frame.add(lblNewLabel);
        frame.add(scrollBar);
        frame.add(backgroundLabel); 
        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == btnExit) {
            frame.dispose();
        }
    	
    }
}
	