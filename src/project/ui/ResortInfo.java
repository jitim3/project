package project.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Optional;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import project.dto.CreateRoomDto;
import project.dto.UpdateResortDto;
import project.service.ResortService;
import project.service.RoomService;
import project.service.impl.DefaultRoomService;

import project.util.AppUtils;
import project.util.RoomAvailabilityTypes;
import project.util.RoomTypes;

public class ResortInfo implements ActionListener {
	private static final Logger LOGGER = System.getLogger(ResortInfo.class.getName());
	private final long resortId;
	private final String resortNameCreated;
	private final ResortService resortService;
	private final RoomService roomService;

	// ==> FRAME
	private final JFrame frame = new JFrame("Fill up");
	// ==> LABELS
	private final JLabel label = new JLabel("Fill up resort information");
	private final JLabel resortInformation = new JLabel("RESORT DETAILS INFORMATION");
	private final JLabel resortName = new JLabel("Name of the resort");
	private final JLabel uploadResort = new JLabel("Upload image of the resort");
	private final JLabel resortLocation = new JLabel("Resort address/Location");
	private final static JLabel resortDescription = new JLabel("Resort description");
	private final JLabel uploadPool = new JLabel("Upload image of the pool");
	private final JLabel resortHTG = new JLabel("How to get there?");
	private final JLabel uploadCottage = new JLabel("Upload image of the cottage");
	private final JLabel resortPriceInformation = new JLabel("RESORT PRICE INFORMATION");
	private final JLabel resortEntranceFee = new JLabel("Enter entrance fee of the resort");
	private final JLabel resortCottageFee = new JLabel("Enter rental fee of the cottage");
	private final JLabel resortPoolFee = new JLabel("Enter pool entrace fee of the resort");
	private final JLabel resortRoomInformation = new JLabel("RESORT ROOMS INFORMATION");
	private final JLabel resortRoomNormal = new JLabel("Normal Room");
	private final JLabel resortPaxInformation = new JLabel("Number of pax");
	private final JLabel resortRoomAvailability = new JLabel("Room Availability");
	private final JLabel resortNumPax = new JLabel("Number of Pax");
	private final JLabel resortRoomRate = new JLabel("Room Rate per Night");
	private final JLabel resortRoomDescription = new JLabel("Room Description");
	private final JLabel resortRoomUploadImage = new JLabel("Upload images of the room");
	private final JLabel resortRoomFamily = new JLabel("Family Room");
	private final JLabel resortFamilyPaxInformation = new JLabel("Number of Pax");
	private final JLabel resortFamilyRoomRate = new JLabel("Room Rate per Night");
	private final JLabel resortFamilyRoomDescription = new JLabel("Room Description");
	private final JLabel resortFamilyRoomUploadImage = new JLabel("Upload images of the room");
	private File selectedImageFile; // RESORT DISPLAY
	 private File selectedImageFile1; // POOL DISPLAY
	 public static File selectedImageFile2; //COTTAGE DISPLAY
	 public static File selectedImageFile3; //ROOM DISPLAY
	 public static File selectedImageFile4; //FAMILY ROOM DISPLAY
	 

	private final JLabel resortImageLabel = new JLabel(); // USED FOR UPLOAD RESORT PICTURE
	private final JLabel poolImageLabel = new JLabel(); // USED FOR UPLOAD POOL PICTURE
	private final JLabel cottageImageLabel = new JLabel(); // USED FOR UPLOAD COTTAGE PICTURE

	private final JLabel normalRoomImage1Label = new JLabel(); // USED FOR UPLOAD ROOM 1st PIC
	private final JLabel normalRoomImage2Label = new JLabel(); // USED FOR UPLOAD ROOM 2nd PIC

	private final JLabel familyRoomImage1Label = new JLabel(); // USED FOR UPLOAD FAMILY ROOM 1st PIC
	private final JLabel familyRoomImage2Label = new JLabel(); // USED FOR UPLOAD FAMILY ROOM 2nd PIC

	// ==>COMBOBOX
	String roomAvailabilityChoices[] = RoomAvailabilityTypes.names();
	JComboBox roomAvailabilityTypeComboBox = new JComboBox(roomAvailabilityChoices);

	// ==> TEXTFIELDS
	static JTextField resortNameTextField = new JTextField();
	static JTextField resortLocationTextField = new JTextField();
	static JTextField resortEntranceFeeTextField = new JTextField();
	static JTextField resortCottageFeeTextField = new JTextField();
	static JTextField resortPoolFeeTextField = new JTextField();
	static JTextField normalRoomNumberOfPaxTextField = new JTextField();
	static JTextField normalRoomRatePerNightTextField = new JTextField();
	static JTextField familyRoomNumberPaxTextField = new JTextField();
	static JTextField familyRoomRatePerNightTextField = new JTextField();

	// JTEXTAREA WITH SCROLLPANES
	private final JTextArea resortDescriptionTextArea = new JTextArea(3, 20);
	private final JScrollPane resortNameScrollPane = new JScrollPane(resortDescriptionTextArea);

	private final JTextArea normalRoomDescriptionTextArea = new JTextArea(1, 10);
	private final JScrollPane resortRoomDescriptionScrollPane = new JScrollPane(normalRoomDescriptionTextArea);

	private final JTextArea familyRoomDescriptionTextArea = new JTextArea(1, 10);
	private final JScrollPane resortFamilyRoomDescriptionScrollPane = new JScrollPane(familyRoomDescriptionTextArea);

	private final JTextArea howToGoTextArea = new JTextArea(1, 10);
	private final JScrollPane resortHTGFieldScrollPane = new JScrollPane(howToGoTextArea);

	// ==> BUTTONS
	private final JButton resortImageButton = new JButton("Browse");
	private final JButton poolImageButton = new JButton("Browse");
	private final JButton cottageImageButton = new JButton("Browse");
	private final JButton displayButton = new JButton("Display");
	private final JButton normalRoomImage1Button = new JButton("Add Image");
	private final JButton normalRoomImage2Button = new JButton("Add Image");
	private final JButton familyRoomImage1Button = new JButton("Add Image");
	private final JButton familyRoomImage2Button = new JButton("Add Image");
	
	private	File resortImageFile;
	private	File poolImageFile;
	private	File cottageImageFile;
	private	File normalRoomImage1File;
	private	File normalRoomImage2File;;
	private	File familyRoomImage1File;
	private	File familyRoomImage2File;

	public ResortInfo(long resortId, String resortNameCreated, final ResortService resortService) {
		this.resortId = resortId;
		this.resortNameCreated = resortNameCreated;
		this.resortService = resortService;
		this.roomService = new DefaultRoomService();

		this.resortNameTextField.setText(this.resortNameCreated);

		// ==> FOR LABELS
		label.setBounds(250, 15, 400, 80);
		label.setFont(new Font("Times New Roman", Font.BOLD, 32));
		label.setForeground(Color.BLACK);

		resortInformation.setBounds(40, 75, 400, 80);
		resortInformation.setFont(new Font("arial", Font.BOLD, 20));

		resortName.setBounds(40, 125, 400, 80);
		resortName.setFont(new Font("arial", Font.PLAIN, 15));

		resortLocation.setBounds(450, 125, 400, 80);
		resortLocation.setFont(new Font("arial", Font.PLAIN, 15));

		uploadResort.setBounds(40, 170, 400, 80);
		uploadResort.setFont(new Font("arial", Font.PLAIN, 15));

		resortDescription.setBounds(450, 170, 400, 80);
		resortDescription.setFont(new Font("arial", Font.PLAIN, 15));

		uploadPool.setBounds(40, 500, 400, 80);
		uploadPool.setFont(new Font("arial", Font.PLAIN, 15));

		uploadCottage.setBounds(40, 825, 400, 80);
		uploadCottage.setFont(new Font("arial", Font.PLAIN, 15));

		resortPriceInformation.setBounds(40, 1150, 400, 80);
		resortPriceInformation.setFont(new Font("arial", Font.BOLD, 20));

		resortEntranceFee.setBounds(40, 1200, 400, 80);
		resortEntranceFee.setFont(new Font("arial", Font.PLAIN, 15));

		resortCottageFee.setBounds(40, 1250, 400, 80);
		resortCottageFee.setFont(new Font("arial", Font.PLAIN, 15));

		resortPoolFee.setBounds(40, 1300, 400, 80);
		resortPoolFee.setFont(new Font("arial", Font.PLAIN, 15));

		resortRoomInformation.setBounds(40, 1375, 400, 80);
		resortRoomInformation.setFont(new Font("arial", Font.BOLD, 20));

		resortRoomNormal.setBounds(315, 1465, 400, 80);
		resortRoomNormal.setFont(new Font("arial", Font.BOLD, 20));

		resortHTG.setBounds(450, 500, 400, 80);
		resortHTG.setFont(new Font("arial", Font.PLAIN, 15));

		resortRoomAvailability.setBounds(75, 1420, 400, 80);
		resortRoomAvailability.setFont(new Font("arial", Font.PLAIN, 15));

		resortNumPax.setBounds(75, 1500, 400, 80);
		resortNumPax.setFont(new Font("arial", Font.PLAIN, 15));

		resortRoomRate.setBounds(75, 1530, 400, 80);
		resortRoomRate.setFont(new Font("arial", Font.PLAIN, 15));

		resortRoomDescription.setBounds(435, 1517, 400, 80);
		resortRoomDescription.setFont(new Font("arial", Font.PLAIN, 15));

		resortRoomUploadImage.setBounds(350, 1580, 400, 80);
		resortRoomUploadImage.setFont(new Font("arial", Font.PLAIN, 15));

		resortRoomFamily.setBounds(315, 1940, 400, 80);
		resortRoomFamily.setFont(new Font("arial", Font.BOLD, 20));

		resortFamilyPaxInformation.setBounds(75, 1975, 400, 80);
		resortFamilyPaxInformation.setFont(new Font("arial", Font.PLAIN, 15));

		resortFamilyRoomRate.setBounds(75, 2020, 400, 80);
		resortFamilyRoomRate.setFont(new Font("arial", Font.PLAIN, 15));

		resortFamilyRoomDescription.setBounds(460, 2000, 400, 80);
		resortFamilyRoomDescription.setFont(new Font("arial", Font.PLAIN, 15));

		resortFamilyRoomUploadImage.setBounds(350, 2080, 400, 80);
		resortFamilyRoomUploadImage.setFont(new Font("arial", Font.PLAIN, 15));

		// ==> FOR TEXTFIELDS
		resortNameTextField.setBounds(200, 153, 150, 25);
		resortNameTextField.setPreferredSize(new Dimension(200, 175));
		resortNameTextField.setEnabled(false);

		howToGoTextArea.setBounds(625, 153, 150, 25);
		howToGoTextArea.setPreferredSize(new Dimension(200, 175));

		resortEntranceFeeTextField.setBounds(275, 1228, 150, 25);
		resortEntranceFeeTextField.setPreferredSize(new Dimension(200, 175));
		resortEntranceFeeTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		resortCottageFeeTextField.setBounds(275, 1278, 150, 25);
		resortCottageFeeTextField.setPreferredSize(new Dimension(200, 175));
		resortCottageFeeTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		resortPoolFeeTextField.setBounds(295, 1328, 150, 25);
		resortPoolFeeTextField.setPreferredSize(new Dimension(200, 175));
		resortPoolFeeTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		normalRoomNumberOfPaxTextField.setBounds(245, 1527, 150, 25);
		normalRoomNumberOfPaxTextField.setPreferredSize(new Dimension(200, 175));
		normalRoomNumberOfPaxTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		normalRoomRatePerNightTextField.setBounds(245, 1560, 150, 25);
		normalRoomRatePerNightTextField.setPreferredSize(new Dimension(200, 175));
		normalRoomRatePerNightTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		familyRoomNumberPaxTextField.setBounds(245, 2006, 150, 25);
		familyRoomNumberPaxTextField.setPreferredSize(new Dimension(200, 175));
		familyRoomNumberPaxTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		familyRoomRatePerNightTextField.setBounds(245, 2048, 150, 25);
		familyRoomRatePerNightTextField.setPreferredSize(new Dimension(200, 175));
		familyRoomRatePerNightTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});

		// ==> SCROLLPANE
		resortNameScrollPane.setBounds(445, 252, 400, 250); // Set bounds for the JScrollPane
		resortDescriptionTextArea.setLineWrap(true); // Enable line wrapping
		resortDescriptionTextArea.setWrapStyleWord(true);

		resortRoomDescriptionScrollPane.setBounds(600, 1510, 200, 85); // Set bounds for the JScrollPane
		normalRoomDescriptionTextArea.setLineWrap(true); // Enable line wrapping
		normalRoomDescriptionTextArea.setWrapStyleWord(true);

		resortFamilyRoomDescriptionScrollPane.setBounds(600, 1995, 200, 85); // Set bounds for the JScrollPane
		familyRoomDescriptionTextArea.setLineWrap(true); // Enable line wrapping
		familyRoomDescriptionTextArea.setWrapStyleWord(true);

		resortHTGFieldScrollPane.setBounds(590, 525, 220, 200);
		howToGoTextArea.setLineWrap(true);
		howToGoTextArea.setWrapStyleWord(true);

		// ==> FOR BUTTONS
		resortImageButton.setBounds(245, 197, 150, 25);
		resortImageButton.setFocusable(false);
		resortImageButton.addActionListener(this);
		resortImageButton.setOpaque(false);

		poolImageButton.setBounds(245, 525, 150, 25);
		poolImageButton.setFocusable(false);
		poolImageButton.addActionListener(this);
		poolImageButton.setOpaque(false);

		cottageImageButton.setBounds(245, 850, 150, 25);
		cottageImageButton.setFocusable(false);
		cottageImageButton.addActionListener(this);
		cottageImageButton.setOpaque(false);

		displayButton.setBounds(372, 2500, 150, 25);
		displayButton.setFocusable(false);
		displayButton.addActionListener(this);
		displayButton.setOpaque(false);

		resortImageLabel.setBounds(40, 250, 300, 250); // FOR RESOSRT PICTURE
		resortImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		poolImageLabel.setBounds(40, 575, 300, 250); // FOR POOL PICTURE
		poolImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		cottageImageLabel.setBounds(40, 900, 300, 250); // FOR COTTAGE PICTURE
		cottageImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		normalRoomImage1Label.setBounds(100, 1690, 300, 250); // FOR ROOM 1st PICTURE
		normalRoomImage1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		normalRoomImage2Label.setBounds(490, 1690, 300, 250); // FOR ROOM 2nd PICTURE
		normalRoomImage2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		familyRoomImage1Label.setBounds(100, 2190, 300, 250); // FOR FAMILY ROOM 1st PICTURE
		familyRoomImage1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		familyRoomImage2Label.setBounds(490, 2190, 300, 250); // FOR FAMILY ROOM 2nd PICTURE
		familyRoomImage2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		normalRoomImage1Button.setBounds(175, 1650, 150, 25);
		normalRoomImage1Button.setFocusable(false);
		normalRoomImage1Button.addActionListener(this);
		normalRoomImage1Button.setOpaque(false);

		normalRoomImage2Button.setBounds(575, 1650, 150, 25);
		normalRoomImage2Button.setFocusable(false);
		normalRoomImage2Button.addActionListener(this);
		normalRoomImage2Button.setOpaque(false);

		familyRoomImage1Button.setBounds(175, 2150, 150, 25);
		familyRoomImage1Button.setFocusable(false);
		familyRoomImage1Button.addActionListener(this);
		familyRoomImage1Button.setOpaque(false);

		familyRoomImage2Button.setBounds(575, 2150, 150, 25);
		familyRoomImage2Button.setFocusable(false);
		familyRoomImage2Button.addActionListener(this);
		familyRoomImage2Button.setOpaque(false);

		// ==> COMBOBOX
		roomAvailabilityTypeComboBox.setBounds(210, 1445, 120, 30);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(900, 2580, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 900, 2580);

		JPanel panel = new JPanel();

		panel.add(displayButton);
		panel.add(familyRoomImage2Label);
		panel.add(familyRoomImage1Label);
		panel.add(familyRoomImage2Button);
		panel.add(familyRoomImage1Button);
		panel.add(resortFamilyRoomUploadImage);
		panel.add(resortFamilyRoomDescriptionScrollPane);
		panel.add(resortFamilyRoomDescription);
		panel.add(familyRoomRatePerNightTextField);
		panel.add(resortFamilyRoomRate);
		panel.add(familyRoomNumberPaxTextField);
		panel.add(resortFamilyPaxInformation);
		panel.add(resortRoomFamily);
		panel.add(normalRoomImage2Label);
		panel.add(normalRoomImage2Button);
		panel.add(normalRoomImage1Label);
		panel.add(normalRoomImage1Button);
		panel.add(resortRoomUploadImage);
		panel.add(resortRoomDescriptionScrollPane);
		panel.add(resortRoomDescription);
		panel.add(normalRoomRatePerNightTextField);
		panel.add(resortRoomRate);
		panel.add(normalRoomNumberOfPaxTextField);
		panel.add(resortNumPax);
		panel.add(resortRoomAvailability);
		panel.add(roomAvailabilityTypeComboBox);
		panel.add(resortHTGFieldScrollPane);
		panel.add(resortHTG);
		panel.add(resortRoomNormal);
		panel.add(resortRoomInformation);
		panel.add(resortPoolFeeTextField);
		panel.add(resortPoolFee);
		panel.add(resortCottageFeeTextField);
		panel.add(resortCottageFee);
		panel.add(resortEntranceFeeTextField);
		panel.add(resortEntranceFee);
		panel.add(resortPriceInformation);
		panel.add(cottageImageLabel);
		panel.add(cottageImageButton);
		panel.add(uploadCottage);
		panel.add(poolImageButton);
		panel.add(uploadPool);
		panel.add(resortNameScrollPane);
		panel.add(resortDescription);
		panel.add(howToGoTextArea);
		panel.setLayout(null);
		panel.add(resortLocation);
		panel.add(resortNameTextField);
		panel.add(label);
		panel.add(resortInformation);
		panel.add(resortName);
		panel.add(uploadResort);
		panel.add(resortImageButton);
		panel.add(poolImageLabel);
		panel.add(resortImageLabel);
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
		/*
		 * frame.add(uploadResort); frame.add(browse); frame.add(resortNameField);
		 * frame.add(resortName); frame.add(resortInformation); frame.add(label);
		 * frame.add(selectedImageLabel); frame.setIconImage(icon.getImage());
		 * frame.add(backgroundLabel); frame.setSize(900,800); frame.setVisible(true);
		 * frame.setLocationRelativeTo(null); frame.setResizable(false);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == resortImageButton) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				resortImageFile = fileChooser.getSelectedFile();
				// File selectedFile = browseResortImage.getSelectedFile();
				if (resortImageFile != null) {
					String filePath = resortImageFile.getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(filePath);
					// String filePath = selectedFile.getAbsolutePath();
					// ImageIcon selectedImage = new ImageIcon(filePath);
					// Resize the image to fit the JLabel
					Image image = imageIcon.getImage().getScaledInstance(resortImageLabel.getWidth(), resortImageLabel.getHeight(), Image.SCALE_SMOOTH);
					resortImageLabel.setIcon(new ImageIcon(image));
				}
			}
		} else if (e.getSource() == poolImageButton) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				poolImageFile = fileChooser.getSelectedFile();
				if (poolImageFile != null) {
					String filePath = poolImageFile.getAbsolutePath();
					ImageIcon selectedImage = new ImageIcon(filePath);
					// Resize the image to fit the JLabel
					Image img = selectedImage.getImage().getScaledInstance(poolImageLabel.getWidth(), poolImageLabel.getHeight(), Image.SCALE_SMOOTH);
					poolImageLabel.setIcon(new ImageIcon(img));
				}
			}
		} else if (e.getSource() == cottageImageButton) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				cottageImageFile = fileChooser.getSelectedFile();
				String filePath = cottageImageFile.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(filePath);
				// Resize the image to fit the JLabel
				Image image = imageIcon.getImage().getScaledInstance(cottageImageLabel.getWidth(), cottageImageLabel.getHeight(), Image.SCALE_SMOOTH);
				cottageImageLabel.setIcon(new ImageIcon(image));
			}
		} else if (e.getSource() == normalRoomImage1Button) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				normalRoomImage1File = fileChooser.getSelectedFile();
				String filePath = normalRoomImage1File.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(filePath);
				Image image = imageIcon.getImage().getScaledInstance(normalRoomImage1Label.getWidth(), normalRoomImage1Label.getHeight(), Image.SCALE_SMOOTH);
				normalRoomImage1Label.setIcon(new ImageIcon(image));
			}
		} else if (e.getSource() == normalRoomImage2Button) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				normalRoomImage2File = fileChooser.getSelectedFile();
				String filePath = normalRoomImage2File.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(filePath);
				Image image = imageIcon.getImage().getScaledInstance(normalRoomImage2Label.getWidth(), normalRoomImage2Label.getHeight(), Image.SCALE_SMOOTH);
				normalRoomImage2Label.setIcon(new ImageIcon(image));
			}
		} else if (e.getSource() == familyRoomImage1Button) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				familyRoomImage1File = fileChooser.getSelectedFile();
				String filePath = familyRoomImage1File.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(filePath);
				Image image = imageIcon.getImage().getScaledInstance(familyRoomImage1Label.getWidth(), familyRoomImage1Label.getHeight(), Image.SCALE_SMOOTH);
				familyRoomImage1Label.setIcon(new ImageIcon(image));
			}
		} else if (e.getSource() == familyRoomImage2Button) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				familyRoomImage2File = fileChooser.getSelectedFile();
				String filePath = familyRoomImage2File.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(filePath);
				Image image = imageIcon.getImage().getScaledInstance(familyRoomImage2Label.getWidth(), familyRoomImage2Label.getHeight(), Image.SCALE_SMOOTH);
				familyRoomImage2Label.setIcon(new ImageIcon(image));
			}
		} else if (e.getSource() == displayButton) {
			frame.dispose();
			
			// Update resort
			String description = resortDescriptionTextArea.getText();
			String location = howToGoTextArea.getText();
			String howToGetThere = howToGoTextArea.getText();

			BigDecimal resortFee;
			try {
				resortFee = new BigDecimal(resortEntranceFeeTextField.getText());
			} catch (Exception mfe) {
				resortFee = new BigDecimal(0);
			}

			BigDecimal cottageFee;
			try {
				cottageFee = new BigDecimal(resortCottageFeeTextField.getText());
			} catch (NumberFormatException nfe) {
				cottageFee = new BigDecimal(0);
			}

			BigDecimal poolFee;
			try {
				poolFee = new BigDecimal(resortPoolFeeTextField.getText());
			} catch (Exception e2) {
				poolFee = new BigDecimal(0);
			}
			String resortImage = null;
			Optional<String> resortImageFilenameOptional = AppUtils.generateFilename(resortImageFile);
			if (resortImageFilenameOptional.isPresent()) {
				resortImage = resortImageFilenameOptional.get();
				try {
		            Files.createDirectories(Paths.get("./topzeluj"));
					AppUtils.saveImage(resortImageFile, resortImage);
				} catch (IOException ioe) {
					resortImage = null;
					LOGGER.log(Level.ERROR, "File " + resortImage + " was not saved.");
				}
			}
			String poolImage = null;
			Optional<String> poolImageFilenameOptional = AppUtils.generateFilename(poolImageFile);
			if (poolImageFilenameOptional.isPresent()) {
				poolImage = poolImageFilenameOptional.get();
				try {
					AppUtils.saveImage(poolImageFile, poolImage);
				} catch (IOException ioe) {
					poolImage = null;
					LOGGER.log(Level.ERROR, "File " + poolImage + " was not saved.");
				}
			}
			String cottageImage = null;
			Optional<String> cottageImageFilenameOptional = AppUtils.generateFilename(cottageImageFile);
			if (cottageImageFilenameOptional.isPresent()) {
				cottageImage = cottageImageFilenameOptional.get();
				try {
					AppUtils.saveImage(cottageImageFile, cottageImage);
				} catch (IOException ioe) {
					cottageImage = null;
					LOGGER.log(Level.ERROR, "File " + cottageImage + " was not saved.");
				}
			}
			Instant updatedAt = Instant.now();
			Instant createdAt = updatedAt;

			UpdateResortDto updateResortDto = new UpdateResortDto(
					resortId, 
					description,
					location, 
					howToGetThere,
					resortFee, 
					cottageFee, 
					poolFee, 
					resortImage, 
					poolImage, 
					cottageImage, 
					updatedAt
				);
			this.resortService.updateResort(updateResortDto);

			// Create rooms
			String roomAvailabilityTypeName = (String) roomAvailabilityTypeComboBox.getSelectedItem();
			int roomAvailabilityTypeId = RoomAvailabilityTypes.type(roomAvailabilityTypeName).id();

			// Normal room
			int normalNormalNumberOfPax;
			try {
				normalNormalNumberOfPax = Integer.parseInt(normalRoomNumberOfPaxTextField.getText());
			} catch (NumberFormatException nfe) {
				normalNormalNumberOfPax = 0;
			}
			BigDecimal normalRoomRatePerNight;
			try {
				normalRoomRatePerNight = new BigDecimal(normalRoomRatePerNightTextField.getText());
			} catch (NumberFormatException nfe) {
				normalRoomRatePerNight = new BigDecimal(0);
			}
			String normalRoomDescription = normalRoomDescriptionTextArea.getText();
			String normalRoomImage1 = null;
			Optional<String> normalRoomImage1NewFilenameOptional = AppUtils.generateFilename(normalRoomImage1File);
			if (normalRoomImage1NewFilenameOptional.isPresent()) {
				normalRoomImage1 = normalRoomImage1NewFilenameOptional.get();
				try {
					AppUtils.saveImage(normalRoomImage1File, normalRoomImage1);
				} catch (IOException ioe) {
					normalRoomImage1 = null;
					LOGGER.log(Level.ERROR, "File " + normalRoomImage1 + " was not saved.");
				}
			}
			String normalRoomImage2 = null;
			Optional<String> normalRoomImage2NewFilenameOptional = AppUtils.generateFilename(normalRoomImage2File);
			if (normalRoomImage2NewFilenameOptional.isPresent()) {
				normalRoomImage2 = normalRoomImage2NewFilenameOptional.get();
				try {
					AppUtils.saveImage(normalRoomImage2File, normalRoomImage2);
				} catch (IOException ioe) {
					normalRoomImage2 = null;
					LOGGER.log(Level.ERROR, "File " + normalRoomImage2 + " was not saved.");
				}
			}
			CreateRoomDto normalRoom = new CreateRoomDto(
					resortId, 
					roomAvailabilityTypeId, 
					RoomTypes.NORMAL.value(),
					normalNormalNumberOfPax, 
					normalRoomRatePerNight, 
					normalRoomDescription, 
					normalRoomImage1,
					normalRoomImage2, 
					createdAt
				);
			this.roomService.createRoom(normalRoom);

			// Family room
			int familyNormalNumberOfPax;
			try {
				familyNormalNumberOfPax = Integer.parseInt(familyRoomNumberPaxTextField.getText());
			} catch (NumberFormatException nfe) {
				familyNormalNumberOfPax = 0;
			}
			BigDecimal familyRoomRatePerNight;
			try {
				familyRoomRatePerNight = new BigDecimal(familyRoomRatePerNightTextField.getText());
			} catch (NumberFormatException nfe) {
				familyRoomRatePerNight = new BigDecimal(0);
			}
			String familyRoomDescription = familyRoomDescriptionTextArea.getText();
			String familyRoomImage1 = null;
			Optional<String> familyRoomImage1NewFilenameOptional = AppUtils.generateFilename(normalRoomImage1File);
			if (familyRoomImage1NewFilenameOptional.isPresent()) {
				familyRoomImage1 = familyRoomImage1NewFilenameOptional.get();
				try {
					AppUtils.saveImage(normalRoomImage1File, familyRoomImage1);
				} catch (IOException ioe) {
					familyRoomImage1 = null;
					LOGGER.log(Level.ERROR, "File " + familyRoomImage1 + " was not saved.");
				}
			}
			String familyRoomImage2 = null;
			Optional<String> familyRoomImage2NewFilenameOptional = AppUtils.generateFilename(familyRoomImage2File);
			if (familyRoomImage2NewFilenameOptional.isPresent()) {
				familyRoomImage2 = familyRoomImage2NewFilenameOptional.get();
				try {
					AppUtils.saveImage(familyRoomImage2File, familyRoomImage2);
				} catch (IOException ioe) {
					familyRoomImage2 = null;
					LOGGER.log(Level.ERROR, "File " + familyRoomImage2 + " was not saved.");
				}
			}
			CreateRoomDto familyRoom = new CreateRoomDto(
					resortId, 
					roomAvailabilityTypeId, 
					RoomTypes.FAMILY.value(),
					familyNormalNumberOfPax, 
					familyRoomRatePerNight, 
					familyRoomDescription, 
					familyRoomImage1,
					familyRoomImage2, 
					createdAt
				);
			this.roomService.createRoom(familyRoom);

			DisplayFrame displayFrame = new DisplayFrame(resortService, resortId);
		}}
		
		public static class displayFrame implements ActionListener{
			 
			 JFrame frame;
			 JButton reservation = new JButton("Make a reservation");
			 JButton viewReview = new JButton("View Reviews");
			 JButton transaction = new JButton("Transaction");
			 JButton exit = new JButton("EXIT");
			 JLabel resortFeeLabel1 = new JLabel("Resort Entrance Fee:");
			 JLabel poolFeeLabel1 = new JLabel("Pool Entrance Fee:");
			 
			 
			 displayFrame(String inputframeText ,String inputText, String inputText1,String imageResortPath,String imagePoolPath,String resortDescription, String inputText2,String inputText4,String inputText5){
				 
				 frame = new JFrame(inputframeText);
				 
				 resortFeeLabel1.setBounds(40, 515, 230, 30);
				 resortFeeLabel1.setFont(new Font("Times New Roman",Font.BOLD,20));
				 
				 poolFeeLabel1.setBounds(475, 515, 230, 30);
				 poolFeeLabel1.setFont(new Font("Times New Roman",Font.BOLD,20));
				 
				 JLabel poolFeeLabel = new JLabel (inputText4); 				//POOL FEE LABEL
				 poolFeeLabel.setBounds(570, 515, 230, 30);
			     poolFeeLabel.setForeground(Color.black);
			     poolFeeLabel.setOpaque(false);
			     poolFeeLabel.setBackground(new Color(255, 255, 255, 64));
			     poolFeeLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
			     poolFeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			     poolFeeLabel.setVerticalAlignment(SwingConstants.CENTER);
				 
				 JLabel resortFeeLabel = new JLabel (inputText4); 				//RESORT FEE 
			     resortFeeLabel.setBounds(135, 515, 230, 30);
			     resortFeeLabel.setForeground(Color.black);
			     resortFeeLabel.setOpaque(false);
			     resortFeeLabel.setBackground(new Color(255, 255, 255, 64));
			     resortFeeLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
			     resortFeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			     resortFeeLabel.setVerticalAlignment(SwingConstants.CENTER);
			    
				 
				 JLabel displayLabel = new JLabel(inputText);                     //RESORT NAME
				 displayLabel.setBounds(310, 30, 300, 45);
				 displayLabel.setFont(new Font("Times New Roman",Font.BOLD,30));
				 displayLabel.setForeground(Color.black);
				 displayLabel.setOpaque(true);
			     displayLabel.setBackground(new Color(255, 255, 255, 64));
			     displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
			     displayLabel.setVerticalAlignment(SwingConstants.CENTER);
			     
			     JLabel locationLabel = new JLabel (inputText1); 				//LOCATION AREA
			     locationLabel.setBounds(348, 90, 230, 30);
			     locationLabel.setOpaque(true);
			     locationLabel.setBackground(new Color(255, 255, 255, 64));
			     locationLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
			     locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
			     locationLabel.setVerticalAlignment(SwingConstants.CENTER);
			     
			     JTextArea descriptionArea = new JTextArea(resortDescription); //DESCRIPTION
			     descriptionArea.setBounds(40, 560, 780, 180);
			     descriptionArea.setLineWrap(true);
			     descriptionArea.setWrapStyleWord(true);
			     descriptionArea.setEditable(false);
			     descriptionArea.setBackground(new Color(255, 255, 255, 64));
			     descriptionArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			     
			     JTextArea htgAreaLabel = new JTextArea(inputText2);
			     htgAreaLabel.setBounds(40, 750, 780, 180);
			     htgAreaLabel.setLineWrap(true);
			     htgAreaLabel.setWrapStyleWord(true);
			     htgAreaLabel.setEditable(false);
			     htgAreaLabel.setBackground(new Color(255, 255, 255, 64));
			     htgAreaLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			     
			     
			     
			     JLabel imageLabel = new JLabel();								//IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
			     imageLabel.setBounds(40, 225, 350, 250); // Set bounds as per your requirement
			     ImageIcon imageIcon = new ImageIcon(imageResortPath);
			     Image img = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
			     imageLabel.setIcon(new ImageIcon(img));
			     
			     JLabel imageLabel1= new JLabel();								//IMPORT THE POOL JFILECHOOSER FROM FILL UP FORM
			     imageLabel1.setBounds(475, 225, 350, 250); // Set bounds as per your requirement
			     ImageIcon imageIcon1 = new ImageIcon(imagePoolPath);
			     Image img1 = imageIcon1.getImage().getScaledInstance(imageLabel1.getWidth(), imageLabel1.getHeight(), Image.SCALE_SMOOTH);
			     imageLabel1.setIcon(new ImageIcon(img1));
			     
			     reservation.setBounds(360,940,150,25);
			     reservation.setFocusable(false);
			     reservation.addActionListener(this);
			     reservation.setOpaque(false);
			     
			     
			     viewReview.setBounds(360,985,150,25);
			     viewReview.setFocusable(false);
			     viewReview.addActionListener(this);
			     viewReview.setOpaque(false);
			     
			     transaction.setBounds(360,1025,150,25);
			     transaction.setFocusable(false);
			     transaction.addActionListener(this);
			     transaction.setOpaque(false);
			     
			     exit.setBounds(380,1075,110,25);
			     exit.setFocusable(false);
			     exit.addActionListener(this);
			     exit.setOpaque(false);
			     
			     
			     
			     
			     
			     
				 ImageIcon icon = new ImageIcon("beach2.png");
				 
				 ImageIcon background = new ImageIcon("figma.jpg");
				 Image backgroundImage = background.getImage().getScaledInstance(900,1180, Image.SCALE_DEFAULT);
				 JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
				 backgroundLabel.setBounds(0, 0, 900,1180);
				 
				 JPanel panel = new JPanel();
				 
				 panel.add(poolFeeLabel);
				 panel.add(poolFeeLabel1);
				 panel.add(resortFeeLabel1);
				 panel.add(resortFeeLabel);
				 panel.add(exit);
				 panel.add(transaction);
				 panel.add(viewReview);
				 panel.add(reservation);	
				 panel.setLayout(null);
				 panel.add(htgAreaLabel);
				 panel.add(locationLabel);	//RESORT LOCATION
			     panel.add(displayLabel); //RESORT NAME
				 panel.add(imageLabel); //RESORT PICTURE
				 panel.add(imageLabel1); //POOL PICTURE
				 panel.add(descriptionArea);//DESCRIPTION AREA
			     panel.add(backgroundLabel);
			     panel.setPreferredSize(new Dimension(900, 1180));
			     
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

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==reservation) {
					frame.dispose();
					reservationChoices window = new reservationChoices();
				}else if (e.getSource()==viewReview) {
					
				}else if (e.getSource()==transaction) {
					
				}else if (e.getSource()==exit) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			
	}
	
	public static class reservationChoices implements ActionListener{
		
		JFrame frame = new JFrame("Select reservation choice");
		JButton dailyUse = new JButton("DAILY USE");
		JButton overnight = new JButton("OVERNIGHT");
		JButton exit = new JButton("EXIT");
		
		
		
		reservationChoices(){
			
			dailyUse.setBounds(172,150,150,35);
			dailyUse.setFocusable(false);
			dailyUse.addActionListener(this);
			dailyUse.setOpaque(false);
			
			overnight.setBounds(172,225,150,35);
			overnight.setFocusable(false);
			overnight.addActionListener(this);
			overnight.setOpaque(false);
			
			exit.setBounds(190,300,115,25);
			exit.setFocusable(false);
			exit.addActionListener(this);
			exit.setOpaque(false);
			
			
			
			
			
			ImageIcon icon = new ImageIcon("beach2.png");
			 
			ImageIcon background = new ImageIcon("figma.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500,500, Image.SCALE_AREA_AVERAGING);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500,500);
			
			
			frame.add(exit);
			frame.add(overnight);
			frame.add(dailyUse);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,500);
			frame.setResizable(false);
			
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==dailyUse) {
				frame.dispose();
				String inputText3= resortCottageFeeTextField.getText();
				displayCottageresort window = new displayCottageresort(selectedImageFile2.getAbsolutePath(),inputText3);
			}else if (e.getSource()==overnight) {
				frame.dispose();
				String inputText6= resortEntranceFeeTextField.getText();
				String inputText7= normalRoomNumberOfPaxTextField.getText();
				String inputText8= familyRoomNumberPaxTextField.getText();
				String inputText9= familyRoomRatePerNightTextField.getText();
				displayRoomResort window = new displayRoomResort(selectedImageFile3.getAbsolutePath(),inputText6,inputText7,inputText8,inputText9);
			}else if (e.getSource()==exit){
				System.exit(0);
			}
			
		}
	}

	 static class displayCottageresort implements ActionListener{
		
		JFrame frame = new JFrame("Cottage Information");
		JLabel cottageLabel = new JLabel("COTTAGES");
		JLabel cottageLabelFee = new JLabel("COTTAGE FEE");
		JButton reserveNow = new JButton ("RESERVE NOW");
		JButton exit = new JButton ("EXIT");
		
		public displayCottageresort(String imageCottagePath,String inputText3){
			
			
			 reserveNow.setBounds(315,530,220,25);
			 reserveNow.setFocusable(false);
		     reserveNow.addActionListener(this);
		     reserveNow.setOpaque(false);
		     
		     exit.setBounds(315,560,220,25);
			 exit.setFocusable(false);
		     exit.addActionListener(this);
		     exit.setOpaque(false);
			
			JLabel displayLabel3 = new JLabel(inputText3);                     //COTTAGE FEE 
			displayLabel3.setBounds(300, 470, 250, 45);
			displayLabel3.setFont(new Font("Times New Roman",Font.BOLD,23));
			displayLabel3.setForeground(Color.black);
			displayLabel3.setOpaque(true);
		    displayLabel3.setBackground(new Color(255, 255, 255, 64));
		    displayLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		    displayLabel3.setVerticalAlignment(SwingConstants.CENTER);
			
			cottageLabelFee.setBounds(347,415,400,80);
		    cottageLabelFee.setFont(new Font("Times New Roman",Font.BOLD,20));
			
			cottageLabel.setBounds(347,15,400,80);
		    cottageLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
			
			JLabel imageLabel3 = new JLabel();								//IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
		    imageLabel3.setBounds(50, 95, 700, 325); // Set bounds as per your requirement
		    ImageIcon imageIcon = new ImageIcon(imageCottagePath);
		    Image img3 = imageIcon.getImage().getScaledInstance(imageLabel3.getWidth(), imageLabel3.getHeight(), Image.SCALE_AREA_AVERAGING);
		    imageLabel3.setIcon(new ImageIcon(img3));
			
			ImageIcon icon = new ImageIcon("beach2.png");
			 
			ImageIcon background = new ImageIcon("figma.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(800,700, Image.SCALE_AREA_AVERAGING);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 800,700);
			
			frame.add(exit);
			frame.add(reserveNow);
			frame.add(displayLabel3);
			frame.add(cottageLabelFee);
			frame.add(cottageLabel);
			frame.add(imageLabel3);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setSize(800,700);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==reserveNow) {
				frame.dispose();
				CustomerInfo window = new CustomerInfo();
			}
			
		}
	 }
		public static class CustomerInfo implements ActionListener {

		    JFrame frame = new JFrame("Customer Information"); 
		    private JTextField firstnameField;
		    private JTextField lastnameField;
		    private JTextField contactNumField;
		    private JTextField emailAddField;
		    JButton confirmButton = new JButton("Confirm");
		   

		    public CustomerInfo() {
				
				confirmButton.setBounds(300, 350, 99, 50);
				confirmButton.setFocusable(false);
			  confirmButton.addActionListener(this);
				
				ImageIcon icon = new ImageIcon("beach2.png");
				 
				ImageIcon background = new ImageIcon("figma.jpg");
				Image backgroundImage = background.getImage().getScaledInstance(800,700, Image.SCALE_AREA_AVERAGING);
				JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
				backgroundLabel.setBounds(0, 0, 800,700);
		        
		        JLabel customerInfoLabel = new JLabel("CUSTOMER INFORMATION");
		        JLabel firstnameLabel = new JLabel("First Name:");
				firstnameField = new JTextField();
				JLabel lastnameLabel = new JLabel("Last Name:");
				lastnameField = new JTextField();
				JLabel contactNumLabel = new JLabel("Contact Number:");
				contactNumField = new JTextField();
				JLabel emailAddLabel = new JLabel("Email Address:");
				emailAddField = new JTextField();
				
				customerInfoLabel.setBounds(180, 25, 500, 50);
				customerInfoLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
				
				firstnameLabel.setBounds(215, 80, 500, 85);
				firstnameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				firstnameField.setBounds(340, 110, 150, 25);
				firstnameField.setPreferredSize(new Dimension(150, 100));
				
				lastnameLabel.setBounds(215, 70, 500, 180);
				lastnameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				lastnameField.setBounds(340, 150, 150, 25);
				lastnameField.setPreferredSize(new Dimension(150, 100));
				
				contactNumLabel.setBounds(215, 160, 500, 85);
				contactNumLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				contactNumField.setBounds(340, 190, 150, 25);
				contactNumField.setPreferredSize(new Dimension(150, 100));
				
				emailAddLabel.setBounds(215, 200, 500, 85);
				emailAddLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				emailAddField.setBounds(340, 230, 150, 25);
				emailAddField.setPreferredSize(new Dimension(150, 100));
				
				

		       
		        frame.setSize(700, 500);
		        frame.setLayout(null);
		        frame.setResizable(false);
		        frame.add(customerInfoLabel);
		        frame.add(firstnameLabel);
		        frame.add(firstnameField);
		        frame.add(lastnameLabel);
		        frame.add(lastnameField);
		        frame.add(contactNumLabel);
		        frame.add(contactNumField);
		        frame.add(emailAddLabel);
		        frame.add(emailAddField);
		        frame.add(confirmButton);
		        frame.setIconImage(icon.getImage());
		        frame.add(backgroundLabel);
		        frame.setVisible(true);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		        
		    }

		    @Override
		    public void actionPerformed(ActionEvent e) {
		       if (e.getSource()== confirmButton) {
		    	   frame.dispose();
		    	   ConfirmMssg window = new ConfirmMssg();
		       }
		    }

}
	static class displayRoomResort implements ActionListener{
		
		JFrame frame = new JFrame("Room Information");
		JLabel label = new JLabel("Rooms");
		JLabel labelRoom = new JLabel("Normal Room");
		JLabel paxLabel = new JLabel("Number of PAX");
		JLabel normalRoomPriceLabel = new JLabel("Room Fee");
		JButton reservebutton = new JButton("RESERVE NOW");
		JLabel familyLabelRoom = new JLabel("Family Room");
		JLabel familyPaxLabel = new JLabel("Number of PAX");
		JLabel familyRoomPriceLabel = new JLabel("Family Room Fee");
		JButton reservebutton1 = new JButton("RESERVE NOW");
		
		public displayRoomResort(String selectedaddImage,String inputText6,String inputText7,String inputText8,String inputText9){
			
			reservebutton1.setBounds(525,560,150,25);  						
			reservebutton1.setFocusable(false);
			reservebutton1.addActionListener(this);
			reservebutton1.setOpaque(false);
			
			reservebutton.setBounds(125,560,150,25);
			reservebutton.setFocusable(false);
			reservebutton.addActionListener(this);
			reservebutton.setOpaque(false);
			
			JLabel displayLabel7 = new JLabel(inputText9);                     //FAMILY ROOM FEE
			displayLabel7.setBounds(468, 515, 250, 35);
			displayLabel7.setFont(new Font("Times New Roman",Font.BOLD,15));
			displayLabel7.setForeground(Color.black);
			displayLabel7.setOpaque(true);
		    displayLabel7.setBackground(new Color(255, 255, 255, 64));
		    displayLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		    displayLabel7.setVerticalAlignment(SwingConstants.CENTER);
				
			JLabel displayLabel6 = new JLabel(inputText8);                     //FAMILY ROOM PAX
			displayLabel6.setBounds(468, 450, 250, 45);
			displayLabel6.setFont(new Font("Times New Roman",Font.BOLD,15));
			displayLabel6.setForeground(Color.black);
			displayLabel6.setOpaque(true);
		    displayLabel6.setBackground(new Color(255, 255, 255, 64));
		    displayLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		    displayLabel6.setVerticalAlignment(SwingConstants.CENTER);
			
			JLabel displayLabel5 = new JLabel(inputText7);                     //ROOM PAX
			displayLabel5.setBounds(70, 450, 250, 45);
			displayLabel5.setFont(new Font("Times New Roman",Font.BOLD,15));
			displayLabel5.setForeground(Color.black);
			displayLabel5.setOpaque(true);
		    displayLabel5.setBackground(new Color(255, 255, 255, 64));
		    displayLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		    displayLabel5.setVerticalAlignment(SwingConstants.CENTER);
			
			JLabel displayLabel4 = new JLabel(inputText6);                     //ROOM FEE 
			displayLabel4.setBounds(70, 515, 250, 35);
			displayLabel4.setFont(new Font("Times New Roman",Font.BOLD,15));
			displayLabel4.setForeground(Color.black);
			displayLabel4.setOpaque(true);
		    displayLabel4.setBackground(new Color(255, 255, 255, 64));
		    displayLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		    displayLabel4.setVerticalAlignment(SwingConstants.CENTER);
		    
		    familyRoomPriceLabel.setBounds(545,478, 200, 50);
		    familyRoomPriceLabel.setFont(new Font("Times New Roman",Font.BOLD,12));
		    
		    familyPaxLabel.setBounds(545,412, 200, 50);
		    familyPaxLabel.setFont(new Font("Times New Roman",Font.BOLD,12));
		    
		    familyLabelRoom.setBounds(535, 385, 200, 50);
		    familyLabelRoom.setFont(new Font("Times New Roman",Font.BOLD,20));
		    
		    normalRoomPriceLabel.setBounds(175, 478, 200, 50);
		    normalRoomPriceLabel.setFont(new Font("Times New Roman",Font.BOLD,12));
		    
		    paxLabel.setBounds(155,412, 200, 50);
		    paxLabel.setFont(new Font("Times New Roman",Font.BOLD,12));
		    
			label.setBounds(347,15,400,80);
		    label.setFont(new Font("Times New Roman",Font.BOLD,25));
		    
		    labelRoom.setBounds(140,385,200,50);
		    labelRoom.setFont(new Font("Times New Roman",Font.BOLD,20));
			
		    JLabel imageLabel5 = new JLabel();								//IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
		    imageLabel5.setBounds(430, 120,325,275); // Set bounds as per your requirement
		    ImageIcon imageIcon1 = new ImageIcon(selectedaddImage);
		    Image img5 = imageIcon1.getImage().getScaledInstance(imageLabel5.getWidth(), imageLabel5.getHeight(), Image.SCALE_AREA_AVERAGING);
		    imageLabel5.setIcon(new ImageIcon(img5));
		    
			JLabel imageLabel4 = new JLabel();								//IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
		    imageLabel4.setBounds(40, 120,325,275); // Set bounds as per your requirement
		    ImageIcon imageIcon = new ImageIcon(selectedaddImage);
		    Image img4 = imageIcon.getImage().getScaledInstance(imageLabel4.getWidth(), imageLabel4.getHeight(), Image.SCALE_AREA_AVERAGING);
		    imageLabel4.setIcon(new ImageIcon(img4));
			
			ImageIcon icon = new ImageIcon("beach2.png");
			 
			ImageIcon background = new ImageIcon("figma.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(800,700, Image.SCALE_AREA_AVERAGING);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 800,700);
			
			frame.add(displayLabel7);
			frame.add(reservebutton1);
			frame.add(familyRoomPriceLabel);
			frame.add(displayLabel6);
			frame.add(familyPaxLabel);
			frame.add(familyLabelRoom);
			frame.add(imageLabel5);
			frame.add(reservebutton);
			frame.add(normalRoomPriceLabel);
			frame.add(paxLabel);
			frame.add(displayLabel5);
			frame.add(displayLabel4);
			frame.add(labelRoom);
			frame.add(label);
			frame.add(imageLabel4);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setSize(800,700);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==reservebutton) {
				frame.dispose();
				Checkin window = new Checkin();
			}
			
		}
	}
	 public static class ConfirmMssg implements ActionListener {

		    JFrame frame = new JFrame("Waiting Confirmation Message"); 
		    JButton okButton = new JButton("OK");

		    public ConfirmMssg() {
				
				okButton.setBounds(230, 350, 220, 40);
				okButton.setFocusable(false);
			    okButton.addActionListener(this);
				
				JLabel cnfrmMssgBG = new JLabel("Please wait for the resort to confirm your reservation. Thank you!");
				cnfrmMssgBG.setBounds(90, 50, 500, 250);
				cnfrmMssgBG.setOpaque(true);
				cnfrmMssgBG.setBackground(new Color (100, 255, 255, 64));
				cnfrmMssgBG.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,12));
				cnfrmMssgBG.setHorizontalAlignment(SwingConstants.CENTER);
				cnfrmMssgBG.setVerticalAlignment(SwingConstants.CENTER);
				

		        
		        ImageIcon icon = new ImageIcon("beach2.png");
				 
				ImageIcon background = new ImageIcon("figma.jpg");
				Image backgroundImage = background.getImage().getScaledInstance(800,700, Image.SCALE_AREA_AVERAGING);
				JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
				backgroundLabel.setBounds(0, 0, 800,700);

		       
		        frame.setSize(700, 500);
		        frame.setLayout(null); 
		        frame.add(cnfrmMssgBG);
		        frame.setResizable(false);
		        frame.add(okButton);
		        frame.setIconImage(icon.getImage());
		        frame.add(backgroundLabel);
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
	 public static class Checkin extends JFrame{
			JDatePickerImpl datePicker;
			JFrame frame = new JFrame("Check in");	
			
		public Checkin(){
				SqlDateModel model = new SqlDateModel();
				Properties p = new Properties();
				p.put("text.day", "Day");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				JDatePanelImpl panel = new JDatePanelImpl(model, p);
				datePicker = new JDatePickerImpl(panel, new AbstractFormatter() {
					
					@Override
					public String valueToString(Object value) throws ParseException {
						// TODO Auto-generated method stub
						if (value != null) {
							
						Calendar calendar = (Calendar) value;
						SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
						String strDate = format.format(calendar.getTime());
						
							return strDate;
						}
						return "";
					}
					
					@Override
					public Object stringToValue(String text) throws ParseException {
						// TODO Auto-generated method stub
						return ""	;
					}
				});
				frame.add(datePicker);
				frame.pack();
				frame.setVisible(true);
			}
		    public class CustomFormat extends AbstractFormatter{

				@Override
				public Object stringToValue(String text) throws ParseException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String valueToString(Object value) throws ParseException {
					// TODO Auto-generated method stub
					return null;
				}
		    	
		    }}}}
