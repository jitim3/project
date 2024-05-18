package project.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ResortInfo implements ActionListener {

	 //==> FRAME
	 JFrame frame = new JFrame("Fill up");
	 //==> LABELS
	 JLabel label = new JLabel("Fill up resort information");
	 JLabel resortInformation = new JLabel("RESORT DETAILS INFORMATION");
	 JLabel resortName = new JLabel("Name of the resort");
	 JLabel uploadResort = new JLabel("Upload image of the resort");
	 JLabel resortLocation = new JLabel("Resort address/Location");
	 JLabel resortDescription = new JLabel("Resort description");
	 JLabel uploadPool = new JLabel("Upload image of the pool");
	 JLabel resortHTG = new JLabel ("How to get there?");
	 JLabel uploadCottage = new JLabel("Upload image of the cottage");
	 JLabel resortPriceInformation = new JLabel ("RESORT PRICE INFORMATION");
	 JLabel resortEntranceFee = new JLabel("Enter entrance fee of the resort");
	 JLabel resortCottageFee = new JLabel("Enter rental fee of the cottage");
	 JLabel resortPoolFee = new JLabel("Enter pool entrace fee of the resort");
	 JLabel resortRoomInformation = new JLabel ("RESORT ROOMS INFORMATION");
	 JLabel resortRoomNormal = new JLabel("Normal Room");
	 JLabel resortPaxInformation = new JLabel ("Number of pax");
	 JLabel resortRoomAvailability = new JLabel("Room Availability");
	 JLabel resortNumPax = new JLabel("Number of Pax");
	 JLabel resortRoomRate = new JLabel("Room Rate per Night");
	 JLabel resortRoomDescription = new JLabel("Room Description");
	 JLabel resortRoomUploadImage = new JLabel("Upload images of the room");
	 JLabel resortRoomFamily = new JLabel("Family Room");
	 JLabel resortFamilyPaxInformation = new JLabel("Number of Pax");
	 JLabel resortFamilyRoomRate = new JLabel("Room Rate per Night");
	 JLabel resortFamilyRoomDescription = new JLabel("Room Description");
	 JLabel resortFamilyRoomUploadImage = new JLabel("Upload images of the room");
	 
	 

	 JLabel selectedImageLabel = new JLabel(); //USED FOR UPLOAD RESORT PICTURE
	 JLabel selectedPoolImageLabel = new JLabel(); //USED FOR UPLOAD POOL PICTURE
	 JLabel selectedCottageImageLabel = new JLabel(); //USED FOR UPLOAD COTTAGE PICTURE
	 
	 JLabel selectedaddImage = new JLabel(); // USED FOR UPLOAD ROOM 1st PIC
	 JLabel selectedaddImage1 = new  JLabel(); // USED FOR UPLOAD ROOM 2nd PIC
	 
	 JLabel selectedFamilyaddImage = new JLabel(); // USED FOR UPLOAD FAMILY ROOM 1st PIC
	 JLabel selectedFamilyaddImage1 = new JLabel(); // USED FOR UPLOAD FAMILY ROOM 2nd PIC
	 
	 //==>COMBOBOX
	 String roomAvailabilityChoices[] = {"Day use","Night use","Day and Night use"};
	 JComboBox roomAvailability = new JComboBox(roomAvailabilityChoices);
	 
	 
	 //==> TEXTFIELDS
	 JTextField resortNameField = new JTextField();
	 JTextField resortLocationField = new JTextField();	 
	 JTextField resortEntranceFeeField = new JTextField();
	 JTextField resortCottageFeeField = new JTextField();
	 JTextField resortPoolFeeField = new JTextField();
	 JTextField resortHTGField = new JTextField();
	 JTextField resortNumPaxField = new JTextField();
	 JTextField resortRoomRateField = new JTextField();
	 JTextField resortFamilyPaxInformationField = new JTextField();
	 JTextField resortFamilyRoomRateField = new JTextField();
	 
	 //JTEXTAREA WITH SCROLLPANES
	 JTextArea resortDescriptionField = new JTextArea(3, 20);
	 JScrollPane resortNameScrollPane = new JScrollPane(resortDescriptionField);
	 
	 JTextArea resortRoomDescriptionField = new JTextArea(1, 10);
	 JScrollPane resortRoomDescriptionScrollPane = new JScrollPane(resortRoomDescriptionField);
	 
	 JTextArea resortFamilyRoomDescriptionField = new JTextArea(1, 10);
	 JScrollPane resortFamilyRoomDescriptionScrollPane = new JScrollPane(resortFamilyRoomDescriptionField);
	 
	 
	 
	  
	 //==> BUTTONS
	 JButton browseResort = new JButton("Browse");
	 JButton browsePool = new JButton("Browse");
	 JButton browseCottage = new JButton("Browse");
	 JButton Display = new JButton("Display");
	 JButton addImage = new JButton("Add Image");
	 JButton addImage1 = new JButton ("Add Image");
	 JButton familyAddImage = new JButton ("Add Image");
	 JButton familyAddImage1 = new JButton ("Add Image");
	 
	 public ResortInfo(){
		 
		 
		 //==> FOR LABELS
		 label.setBounds(250,15,400,80);
	     label.setFont(new Font("Times New Roman",Font.BOLD,32));
	     label.setForeground(Color.BLACK);
	     
	     resortInformation.setBounds(40,75,400,80);
	     resortInformation.setFont(new Font("arial",Font.BOLD,20));
	     
	     resortName.setBounds(40,125,400,80);
	     resortName.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortLocation.setBounds(450,125,400,80);
	     resortLocation.setFont(new Font("arial",Font.PLAIN,15));
	     
	     uploadResort.setBounds(40,170,400,80);
	     uploadResort.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortDescription.setBounds(450,170,400,80);
	     resortDescription.setFont(new Font("arial",Font.PLAIN,15));
	     
	     uploadPool.setBounds(40,500,400,80);
	     uploadPool.setFont(new Font("arial",Font.PLAIN,15));
	     
	     uploadCottage.setBounds(40,825,400,80);
	     uploadCottage.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortPriceInformation.setBounds(40,1150,400,80);
	     resortPriceInformation.setFont(new Font("arial",Font.BOLD,20));
	     
	     resortEntranceFee.setBounds(40, 1200,400,80);
	     resortEntranceFee.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortCottageFee.setBounds(40, 1250,400,80);
	     resortCottageFee.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortPoolFee.setBounds(40, 1300,400,80);
	     resortPoolFee.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortRoomInformation.setBounds(40,1375,400,80);
	     resortRoomInformation.setFont(new Font("arial",Font.BOLD,20));
	     
	     resortRoomNormal.setBounds(315,1465,400,80);
	     resortRoomNormal.setFont(new Font("arial",Font.BOLD,20));
	     
	     resortHTG.setBounds(450,500,400,80);
	     resortHTG.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortRoomAvailability.setBounds(75,1420,400,80);
	     resortRoomAvailability.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortNumPax.setBounds(75,1500,400,80);
	     resortNumPax.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortRoomRate.setBounds(75,1530,400,80);
	     resortRoomRate.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortRoomDescription.setBounds(435,1517,400,80);
	     resortRoomDescription.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortRoomUploadImage.setBounds(350,1580,400,80);
	     resortRoomUploadImage.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortRoomFamily.setBounds(315,1940,400,80);
	     resortRoomFamily.setFont(new Font("arial",Font.BOLD,20));
	     
	     resortFamilyPaxInformation.setBounds(75,1975,400,80);
	     resortFamilyPaxInformation.setFont(new Font("arial",Font.PLAIN,15));
	     
	     resortFamilyRoomRate.setBounds(75,2020,400,80);
	     resortFamilyRoomRate.setFont(new Font("arial",Font.PLAIN,15));
	     
	     
	     resortFamilyRoomDescription.setBounds(460,2000,400,80);
	     resortFamilyRoomDescription.setFont(new Font("arial",Font.PLAIN,15));
	     
	     
	     resortFamilyRoomUploadImage.setBounds(350,2080,400,80);
	     resortFamilyRoomUploadImage.setFont(new Font("arial",Font.PLAIN,15));
	     
	     
	     
	     
	     
	     
	     
	     //==> FOR TEXTFIELDS
	     resortNameField.setBounds(200,153,150,25);
	     resortNameField.setPreferredSize(new Dimension(200,175));
	     
	     resortLocationField.setBounds(625,153,150,25);
	     resortLocationField.setPreferredSize(new Dimension(200,175));
	     
	     resortEntranceFeeField.setBounds(275,1228,150,25);
	     resortEntranceFeeField.setPreferredSize(new Dimension(200,175));
	     
	     resortCottageFeeField.setBounds(275,1278,150,25);
	     resortCottageFeeField.setPreferredSize(new Dimension(200,175));
	     
	     resortPoolFeeField.setBounds(295,1328,150,25);
	     resortPoolFeeField.setPreferredSize(new Dimension(200,175));
	     
	     resortHTGField.setBounds(590,525,150,25);
	     resortHTGField.setPreferredSize(new Dimension(200,175));
	     
	     resortNumPaxField.setBounds(245,1527,150,25);
	     resortNumPaxField.setPreferredSize(new Dimension(200,175));
	     
	     resortRoomRateField.setBounds(245,1560,150,25);
	     resortRoomRateField.setPreferredSize(new Dimension(200,175));
	     
	     resortFamilyPaxInformationField.setBounds(245,2006,150,25);
	     resortFamilyPaxInformationField.setPreferredSize(new Dimension(200,175));
	     
	     resortFamilyRoomRateField.setBounds(245,2048,150,25);
	     resortFamilyRoomRateField.setPreferredSize(new Dimension(200,175));
	     
	     
	     
	     
	     
	     
	     	
	     //==> SCROLLPANE
	     resortNameScrollPane.setBounds(445,252,400,250);  // Set bounds for the JScrollPane
	     resortDescriptionField.setLineWrap(true);  // Enable line wrapping
	     resortDescriptionField.setWrapStyleWord(true);
	     
	     resortRoomDescriptionScrollPane.setBounds(600,1510,200,85);  // Set bounds for the JScrollPane
	     resortRoomDescriptionField.setLineWrap(true);  // Enable line wrapping
	     resortRoomDescriptionField.setWrapStyleWord(true);
	     
	     
	     resortFamilyRoomDescriptionScrollPane.setBounds(600,1995,200,85);  // Set bounds for the JScrollPane
	     resortFamilyRoomDescriptionField.setLineWrap(true);  // Enable line wrapping
	     resortFamilyRoomDescriptionField.setWrapStyleWord(true);
	     
	     

	     
	     
	     //==> FOR BUTTONS
	     browseResort.setBounds(245,197,150,25);
		 browseResort.setFocusable(false);
		 browseResort.addActionListener(this);
		 browseResort.setOpaque(false);
		 
		 browsePool.setBounds(245,525,150,25);
		 browsePool.setFocusable(false);
		 browsePool.addActionListener(this);
		 browsePool.setOpaque(false);
		 
		 browseCottage.setBounds(245,850,150,25);
		 browseCottage.setFocusable(false);
		 browseCottage.addActionListener(this);
		 browseCottage.setOpaque(false);
		 
		 Display.setBounds(372,2500,150,25);
		 Display.setFocusable(false);
		 Display.addActionListener(this);
		 Display.setOpaque(false);
	     
		 selectedImageLabel.setBounds(40, 250, 300, 250);								//FOR RESOSRT PICTURE			
	     selectedImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     selectedPoolImageLabel.setBounds(40, 575, 300, 250);								//FOR POOL PICTURE			
	     selectedPoolImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     selectedCottageImageLabel.setBounds(40, 900, 300, 250);								//FOR COTTAGE PICTURE			
	     selectedCottageImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     selectedaddImage.setBounds(100, 1690, 300, 250);								//FOR ROOM 1st PICTURE			
	     selectedaddImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     selectedaddImage1.setBounds(490, 1690, 300, 250);								//FOR ROOM 2nd PICTURE			
	     selectedaddImage1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     selectedFamilyaddImage.setBounds(100, 2190, 300, 250);								//FOR FAMILY ROOM 1st PICTURE			
	     selectedFamilyaddImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     selectedFamilyaddImage1.setBounds(490, 2190, 300, 250);								//FOR FAMILY ROOM 2nd PICTURE			
	     selectedFamilyaddImage1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	     
	     addImage.setBounds(175,1650,150,25);
	     addImage.setFocusable(false);
	     addImage.addActionListener(this);
	     addImage.setOpaque(false);
	     
	     addImage1.setBounds(575,1650,150,25);
	     addImage1.setFocusable(false);
	     addImage1.addActionListener(this);
	     addImage1.setOpaque(false);
	     
	     familyAddImage.setBounds(175,2150,150,25);
	     familyAddImage.setFocusable(false);
	     familyAddImage.addActionListener(this);
	     familyAddImage.setOpaque(false);
	     
	     familyAddImage1.setBounds(575,2150,150,25);
	     familyAddImage1.setFocusable(false);
	     familyAddImage1.addActionListener(this);
	     familyAddImage1.setOpaque(false);
	     
	     
	     
	     
	     //==> COMBOBOX
	     roomAvailability.setBounds(210,1445,120,30);
		 
		 ImageIcon icon = new ImageIcon("beach2.png");
		 
		 ImageIcon background = new ImageIcon("beach3.jpg");
		 Image backgroundImage = background.getImage().getScaledInstance(900,2580, Image.SCALE_DEFAULT);
		 JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		 backgroundLabel.setBounds(0, 0, 900,2580);
		 
		 JPanel panel = new JPanel();
		 
		 panel.add(Display);
		 panel.add(selectedFamilyaddImage1);
		 panel.add(selectedFamilyaddImage);
		 panel.add(familyAddImage1);
		 panel.add(familyAddImage);
		 panel.add(resortFamilyRoomUploadImage);
		 panel.add(resortFamilyRoomDescriptionScrollPane);
		 panel.add(resortFamilyRoomDescription);
		 panel.add(resortFamilyRoomRateField);
		 panel.add(resortFamilyRoomRate);
		 panel.add(resortFamilyPaxInformationField);
		 panel.add(resortFamilyPaxInformation);
		 panel.add(resortRoomFamily);
		 panel.add(selectedaddImage1);
		 panel.add(addImage1);
		 panel.add(selectedaddImage);
		 panel.add(addImage);
		 panel.add(resortRoomUploadImage);
		 panel.add(resortRoomDescriptionScrollPane);
		 panel.add(resortRoomDescription);
		 panel.add(resortRoomRateField);
		 panel.add(resortRoomRate);
		 panel.add(resortNumPaxField);
		 panel.add(resortNumPax);
		 panel.add(resortRoomAvailability);
		 panel.add(roomAvailability);
		 panel.add(resortHTGField);
		 panel.add(resortHTG);
		 panel.add(resortRoomNormal);
		 panel.add(resortRoomInformation);
		 panel.add(resortPoolFeeField);
		 panel.add(resortPoolFee);
		 panel.add(resortCottageFeeField);
		 panel.add(resortCottageFee);
		 panel.add(resortEntranceFeeField);
		 panel.add(resortEntranceFee);
		 panel.add(resortPriceInformation);
		 panel.add(selectedCottageImageLabel);
		 panel.add(browseCottage);
		 panel.add(uploadCottage);
		 panel.add(browsePool);
		 panel.add(uploadPool);
		 panel.add(resortNameScrollPane);
		 panel.add(resortDescription);
		 panel.add(resortLocationField);
		 panel.setLayout(null);
		 panel.add(resortLocation);
		 panel.add(resortNameField);
	     panel.add(label);	
	     panel.add(resortInformation);
	     panel.add(resortName);
	     panel.add(uploadResort);
	     panel.add(browseResort);
	     panel.add(selectedPoolImageLabel);
	     panel.add(selectedImageLabel);
	     panel.add(backgroundLabel);
	     panel.setPreferredSize(new Dimension(900, 2580));
		 
	     JScrollPane scrollPane = new JScrollPane(panel);
	     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     
	     frame.setContentPane(scrollPane);
	     frame.setIconImage(icon.getImage());
	     frame.setSize(900, 1000);
	     frame.setVisible(true);
	     frame.setLocationRelativeTo(null);
	     frame.setResizable(false);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 /*frame.add(uploadResort);
		 frame.add(browse);
		 frame.add(resortNameField);
		 frame.add(resortName);
		 frame.add(resortInformation);
		 frame.add(label);
		 frame.add(selectedImageLabel);
		 frame.setIconImage(icon.getImage());
		 frame.add(backgroundLabel); 
		 frame.setSize(900,800);
		 frame.setVisible(true);
		 frame.setLocationRelativeTo(null);
		 frame.setResizable(false);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		 
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==browseResort) {
			JFileChooser browseResortImage = new JFileChooser();
			int returnVal = browseResortImage.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
               File selectedFile = browseResortImage.getSelectedFile();
               String filePath = selectedFile.getAbsolutePath();
               ImageIcon selectedImage = new ImageIcon(filePath);

               // Resize the image to fit the JLabel
               Image img = selectedImage.getImage().getScaledInstance(selectedImageLabel.getWidth(), selectedImageLabel.getHeight(), Image.SCALE_SMOOTH);
               selectedImageLabel.setIcon(new ImageIcon(img));
           }
			
			
		}else if(e.getSource()==browsePool) {
			JFileChooser browsePoolImage = new JFileChooser();
			int returnVal1 = browsePoolImage.showOpenDialog(null);
			
			if (returnVal1 == JFileChooser.APPROVE_OPTION) {
               File selectedFile = browsePoolImage.getSelectedFile();
               String filePath = selectedFile.getAbsolutePath();
               ImageIcon selectedImage = new ImageIcon(filePath);

               // Resize the image to fit the JLabel
               Image img = selectedImage.getImage().getScaledInstance(selectedPoolImageLabel.getWidth(), selectedPoolImageLabel.getHeight(), Image.SCALE_SMOOTH);
               selectedPoolImageLabel.setIcon(new ImageIcon(img));
           }
		}else if(e.getSource()==browseCottage) {
			JFileChooser browseCottageImage = new JFileChooser();
			int returnVal2 = browseCottageImage.showOpenDialog(null);
			if (returnVal2 == JFileChooser.APPROVE_OPTION) {
               File selectedFile = browseCottageImage.getSelectedFile();
               String filePath = selectedFile.getAbsolutePath();
               ImageIcon selectedImage = new ImageIcon(filePath);

               // Resize the image to fit the JLabel
               Image img = selectedImage.getImage().getScaledInstance(selectedCottageImageLabel.getWidth(), selectedCottageImageLabel.getHeight(), Image.SCALE_SMOOTH);
               selectedCottageImageLabel.setIcon(new ImageIcon(img));
		}
		
	}
		else if(e.getSource()==addImage) {
		JFileChooser browseaddImage = new JFileChooser();
		int returnVal3 = browseaddImage.showOpenDialog(null);
			if (returnVal3 == JFileChooser.APPROVE_OPTION) {
				File selectedFile = browseaddImage.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();
				ImageIcon selectedImage = new ImageIcon(filePath);
				
				Image img = selectedImage.getImage().getScaledInstance(selectedaddImage.getWidth(), selectedaddImage.getHeight(), Image.SCALE_SMOOTH);
				selectedaddImage.setIcon(new ImageIcon(img));
			}
		}
		else if(e.getSource()==addImage1) {
			JFileChooser browseaddImage1 = new JFileChooser();
			int returnVal4 = browseaddImage1.showOpenDialog(null);
				if (returnVal4 == JFileChooser.APPROVE_OPTION) {
					File selectedFile = browseaddImage1.getSelectedFile();
					String filePath = selectedFile.getAbsolutePath();
					ImageIcon selectedImage = new ImageIcon(filePath);
					
					Image img = selectedImage.getImage().getScaledInstance(selectedaddImage1.getWidth(), selectedaddImage1.getHeight(), Image.SCALE_SMOOTH);
					selectedaddImage1.setIcon(new ImageIcon(img));
				}
			}
		else if(e.getSource()==familyAddImage) {
			JFileChooser browseaddImage3 = new JFileChooser();
			int returnVal4 = browseaddImage3.showOpenDialog(null);
			if (returnVal4 == JFileChooser.APPROVE_OPTION) {
				File selectedFile = browseaddImage3.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();
				ImageIcon selectedImage = new ImageIcon(filePath);
				
				Image img = selectedImage.getImage().getScaledInstance(selectedFamilyaddImage.getWidth(), selectedFamilyaddImage.getHeight(), Image.SCALE_SMOOTH);
				selectedFamilyaddImage.setIcon(new ImageIcon(img));
		}
	}
		else if(e.getSource()==familyAddImage1) {
			JFileChooser browseaddImage4 = new JFileChooser();
			int returnVal5 = browseaddImage4.showOpenDialog(null);
			if (returnVal5 == JFileChooser.APPROVE_OPTION) {
				File selectedFile = browseaddImage4.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();
				ImageIcon selectedImage = new ImageIcon(filePath);
				
				Image img = selectedImage.getImage().getScaledInstance(selectedFamilyaddImage1.getWidth(), selectedFamilyaddImage1.getHeight(), Image.SCALE_SMOOTH);
				selectedFamilyaddImage1.setIcon(new ImageIcon(img));
			}
		}
		else if(e.getSource()==Display) {
			frame.dispose();
			String inputText = resortNameField.getText();
			displayFrame frame = new displayFrame(inputText);
		}
}
	public static class displayFrame{
		 
		 JFrame frame = new JFrame();
		 JLabel resortHeaderLine = new JLabel("RESORT INFORMATION");
		 
		 
		 displayFrame(String inputText){
			 
			 resortHeaderLine.setBounds(50,15,400,80);
			 resortHeaderLine.setFont(new Font("Bell MT",Font.BOLD,30));
			 resortHeaderLine.setForeground(Color.black);
			 
			 JLabel displayLabel = new JLabel(inputText);
			 displayLabel.setBounds(50, 100, 300, 30);
			 
			 
			 ImageIcon icon = new ImageIcon("beach2.png");
			 
			 ImageIcon background = new ImageIcon("beach3.jpg");
			 Image backgroundImage = background.getImage().getScaledInstance(900,2580, Image.SCALE_DEFAULT);
			 JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			 backgroundLabel.setBounds(0, 0, 900,2580);
			 
			 JPanel panel = new JPanel();
			 
			 panel.add(resortHeaderLine);
		     panel.add(displayLabel); //RESORT NAME
			 panel.setLayout(null);
		     panel.add(backgroundLabel);
		     panel.setPreferredSize(new Dimension(900, 2580));
		     
		     JScrollPane scrollPane = new JScrollPane(panel);
		     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		     
		     
		     frame.setContentPane(scrollPane);
		     frame.setIconImage(icon.getImage());
			 frame.setSize(900, 2580);
			 frame.setVisible(true);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 frame.setLocationRelativeTo(null);
			 frame.setResizable(false);
		 }
		 
	 }
}
