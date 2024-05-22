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
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class view_WriteCustomer_Reviews implements ActionListener {
	
	JFrame frame = new JFrame("REVIEWS"); 
    JLabel lblReviewsBackground = new JLabel();
    JScrollBar scrollBar = new JScrollBar();
    JButton btnCreateReview = new JButton("Create Review");
    JButton btnExit = new JButton("Exit");
    
    public view_WriteCustomer_Reviews() {
		
    		
scrollBar.setBounds(635, 63, 17, 256);
    	
    	JLabel lblTitle = new JLabel("REVIEWS");
    	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblTitle.setBounds(283, 11, 120, 38);
        
        lblReviewsBackground.setBounds(36, 63, 616, 256);
        lblReviewsBackground.setOpaque(true);
        lblReviewsBackground.setBackground(new Color(100, 255, 255, 64));
        
        btnCreateReview.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        btnCreateReview.setBounds(294, 362, 109, 28);
        btnCreateReview.setFocusable(false);
        btnCreateReview.addActionListener(this);
        
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        btnExit.setBounds(294, 409, 109, 23);
        btnExit.setFocusable(false);
        btnExit.addActionListener(this);
    	
        ImageIcon image = new ImageIcon("beach2.png");
        ImageIcon bg = new ImageIcon("bgdagat.jpg");

        Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);
        

        frame.setIconImage(image.getImage());
        frame.setSize(700, 500);
        frame.getContentPane().setLayout(null); 
        frame.setResizable(false);
        frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        frame.add(lblTitle);
        frame.add(lblReviewsBackground);
        frame.add(scrollBar);
        frame.add(btnCreateReview);
        frame.add(btnExit);
        frame.add(backgroundLabel); 
        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == btnCreateReview) {
            WriteReview write = new WriteReview();
            frame.dispose();
        } else if (e.getSource() == btnExit) {
            frame.dispose();
        }
    }
    
    public class WriteReview implements ActionListener {

        JFrame frame = new JFrame("Write Reviews"); 
        JLabel lblNewLabel = new JLabel("Create Your Review");
        JLabel lblNewLabel_1 = new JLabel();
        JButton btnExit = new JButton("EXIT");
        JButton btnUpload = new JButton("UPLOAD");
       

        public WriteReview() {
    		
        	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setBounds(239, 31, 200, 26);
            
            JLabel lblRate = new JLabel("RATE FROM 1-10");
            JTextField rate = new JTextField();
            lblRate.setHorizontalAlignment(SwingConstants.CENTER);
            lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            lblRate.setBounds(145, 101, 109, 14);
            rate.setBounds(264, 98, 96, 20);
            rate.setColumns(10);
            
            JLabel lblReviewPrompt = new JLabel("WRITE YOUR REVIEW");
            JTextArea reviews = new JTextArea();
            lblReviewPrompt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            lblReviewPrompt.setBounds(153, 151, 138, 14);
            reviews.setBounds(145, 176, 398, 138);
            
            lblNewLabel_1.setBounds(105, 78, 484, 269);
            lblNewLabel_1.setOpaque(true);
            lblNewLabel_1.setBackground(new Color(100, 255, 255, 64));
            
            btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
            btnExit.setBounds(220, 408, 89, 23);
            btnExit.setFocusable(false);
            
            btnUpload.setFont(new Font("Times New Roman", Font.PLAIN, 11));
            btnUpload.setBounds(390, 408, 89, 23);
            btnUpload.setFocusable(false);
        	
            ImageIcon image = new ImageIcon("beach2.png");
            ImageIcon bg = new ImageIcon("bgdagat.jpg");

            Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setBounds(0, 0, 700, 500);
            

            frame.setIconImage(image.getImage());
            frame.setSize(700, 500);
            frame.getContentPane().setLayout(null); 
            frame.setResizable(false);
            frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            frame.add(lblNewLabel);
            frame.add(lblRate);
            frame.add(rate);
            frame.add(lblReviewPrompt);
            frame.add(reviews);
            frame.add(lblNewLabel_1);
            frame.add(btnExit);
            frame.add(btnUpload);
            frame.add(backgroundLabel); 
            frame.add(backgroundLabel);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	
        }
    }}
