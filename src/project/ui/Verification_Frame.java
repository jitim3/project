package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Verification_Frame extends JFrame implements ActionListener{
	JFrame frame = new JFrame("");
	JLabel  label = new JLabel("BUSINESS VERIFICATION");
	JLabel label1 = new JLabel("PLEASE UPLOAD YOUR BUSINESS PERMIT");
	private File selectedImageFile; // RESORT BUSINESS PERMIT DISPLAY
	JLabel selectedImageLabel = new JLabel(); //USED FOR UPLOAD BUSINESS PERMIT PICTURE
	JButton uploadImage = new JButton("UPLOAD IMAGE");
	JButton submitImage = new JButton("SUBMIT IMAGE");
	JButton exit = new JButton("EXIT");
	public Verification_Frame(){
		
		exit.setBounds(230,505,150,25);
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setOpaque(false);
		
		submitImage.setBounds(230,465,150,25);
		submitImage.setFocusable(false);
		submitImage.addActionListener(this);
		submitImage.setOpaque(false);
		
		uploadImage.setBounds(230,425,150,25);
		uploadImage.setFocusable(false);
		uploadImage.addActionListener(this);
		uploadImage.setOpaque(false);
		
		selectedImageLabel.setBounds(40, 130, 500, 250);								//FOR RESORT BUSINESS PERMIT PICTURE			
	    selectedImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		label.setBounds(145,15,400,80);
	    label.setFont(new Font("Times New Roman",Font.BOLD,25));
	    
	    label1.setBounds(175,70,800,80);
	    label1.setFont(new Font("Times New Roman",Font.BOLD,12));
	    
		ImageIcon icon = new ImageIcon("beach2.png");
		 
		ImageIcon background = new ImageIcon("figmapic.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(600,600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 600,600);
		
		frame.add(exit);
		frame.add(submitImage);
		frame.add(uploadImage);
		frame.add(selectedImageLabel);
		frame.add(label1);
		frame.add(label);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==uploadImage) {
			JFileChooser browseResortImage = new JFileChooser();
			int returnVal = browseResortImage.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			selectedImageFile = browseResortImage.getSelectedFile();
               //File selectedFile = browseResortImage.getSelectedFile();
			if (selectedImageFile != null) {
                String filePath = selectedImageFile.getAbsolutePath();
                ImageIcon selectedImage = new ImageIcon(filePath);
			
               //String filePath = selectedFile.getAbsolutePath();
               //ImageIcon selectedImage = new ImageIcon(filePath);
               

               // Resize the image to fit the JLabel
               Image img = selectedImage.getImage().getScaledInstance(selectedImageLabel.getWidth(), selectedImageLabel.getHeight(), Image.SCALE_SMOOTH);
               selectedImageLabel.setIcon(new ImageIcon(img));
			 }
           }
		}else if(e.getSource()==submitImage) {
			frame.dispose();
			JOptionPane.showMessageDialog(this, "SUBMITTED.", "VERIFICATION", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getSource()==exit) {
			System.exit(0);
		}
	}
}