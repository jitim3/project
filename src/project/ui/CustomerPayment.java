package project.ui;

import java.awt.Color;
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

import project.service.UserService;
import project.service.impl.DefaultUserService;

public class CustomerPayment implements ActionListener {

    JFrame frame = new JFrame("Amount"); 
    JButton btnConfirm = new JButton("Confirm");
    JButton btnBack = new JButton("Back");
    JLabel displayTotal = new JLabel();
    
   

    public CustomerPayment() {
		
    	JLabel lblEnterAmount = new JLabel("Enter exact amount:");
    	JTextField amount = new JTextField();
        lblEnterAmount.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblEnterAmount.setBounds(114, 135, 150, 14);
        amount.setBounds(240, 132, 96, 20);
        amount.setColumns(10);
        
        btnConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        btnConfirm.setBounds(247, 223, 89, 23);
        
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        btnBack.setBounds(101, 223, 89, 23);
        
        displayTotal.setBounds(101, 25, 235, 82);
        displayTotal.setOpaque(true);
        displayTotal.setBackground(new Color(100, 255, 255, 64));
    	
        ImageIcon image = new ImageIcon("beach2.png");
        ImageIcon bg = new ImageIcon("figma.jpg");

        Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 700, 500);
        

        frame.setIconImage(image.getImage());
        frame.setSize(452, 308);
        frame.getContentPane().setLayout(null); 
        frame.setResizable(false);
        frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        frame.add(btnConfirm);
        frame.add(btnBack);
        frame.add(displayTotal);
        frame.add(lblEnterAmount);
        frame.add(amount);
        frame.add(backgroundLabel); 
        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==btnConfirm) {
    		frame.dispose();
    		ConfirmationMessage window = new ConfirmationMessage();
    	}else if(e.getSource()==btnBack) {
    		frame.dispose();
    		CustomerInformation window = new CustomerInformation();
    	}
    }
}