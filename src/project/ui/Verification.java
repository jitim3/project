package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.Instant;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import project.service.ResortService;
import project.util.AppUtils;

public class Verification extends JFrame implements ActionListener {
	private static final Logger LOGGER = System.getLogger(Verification.class.getName());
	private final long resortId;
	private final ResortService resortService;
	private final JFrame frame = new JFrame("");
	private final JLabel label = new JLabel("BUSINESS VERIFICATION");
	private final JLabel label1 = new JLabel("PLEASE UPLOAD YOUR BUSINESS PERMIT");
	private File selectedImageFile; // RESORT BUSINESS PERMIT DISPLAY
	private final JLabel selectedImageLabel = new JLabel(); // USED FOR UPLOAD BUSINESS PERMIT PICTURE
	private final JButton uploadImage = new JButton("UPLOAD IMAGE");
	private final JButton submitImage = new JButton("SUBMIT IMAGE");
	private final JButton exit = new JButton("EXIT");
	private final JFrame parentFrame;
	private String windowEventSource = "";

	public Verification(JFrame parentFrame, long resortId, ResortService resortService) {
		this.parentFrame = parentFrame;
		this.resortId = resortId;
		this.resortService = resortService;

		exit.setBounds(230, 505, 150, 25);
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setOpaque(false);

		submitImage.setBounds(230, 465, 150, 25);
		submitImage.setFocusable(false);
		submitImage.addActionListener(this);
		submitImage.setOpaque(false);

		uploadImage.setBounds(230, 425, 150, 25);
		uploadImage.setFocusable(false);
		uploadImage.addActionListener(this);
		uploadImage.setOpaque(false);

		selectedImageLabel.setBounds(40, 130, 500, 250); // FOR RESORT BUSINESS PERMIT PICTURE
		selectedImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		label.setBounds(145, 15, 400, 80);
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));

		label1.setBounds(175, 70, 800, 80);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 12));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figmapic.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 600, 600);

		frame.add(exit);
		frame.add(submitImage);
		frame.add(uploadImage);
		frame.add(selectedImageLabel);
		frame.add(label1);
		frame.add(label);
		frame.add(backgroundLabel);
		frame.setLayout(null);
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				parentFrame.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == uploadImage) {
			this.windowEventSource = "uploadImage";
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				selectedImageFile = fileChooser.getSelectedFile();
				// File selectedFile = browseResortImage.getSelectedFile();
				if (selectedImageFile != null) {
					String filePath = selectedImageFile.getAbsolutePath();
					ImageIcon selectedImage = new ImageIcon(filePath);

					// String filePath = selectedFile.getAbsolutePath();
					// ImageIcon selectedImage = new ImageIcon(filePath);

					// Resize the image to fit the JLabel
					Image image = selectedImage.getImage().getScaledInstance(selectedImageLabel.getWidth(),
							selectedImageLabel.getHeight(), Image.SCALE_SMOOTH);
					selectedImageLabel.setIcon(new ImageIcon(image));
				}
			}
		} else if (e.getSource() == submitImage) {
			this.windowEventSource = "submitImage";
			if (selectedImageFile == null) {
				JOptionPane.showMessageDialog(null, "No permit image selected. Please select permit image.",
						"Upload Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String permitImage = null;
			Optional<String> familyRoomImage2NewFilenameOptional = AppUtils.generateFilename(selectedImageFile);
			if (familyRoomImage2NewFilenameOptional.isPresent()) {
				permitImage = familyRoomImage2NewFilenameOptional.get();
				try {
					AppUtils.saveImage(selectedImageFile, permitImage);
				} catch (IOException ioe) {
					permitImage = null;
					LOGGER.log(Level.ERROR, "File " + permitImage + " was not saved.");
				}
			}
			
			boolean savedInDatabase = false;
			
			if (permitImage != null) {
				savedInDatabase = resortService.updatePermitImage(resortId, permitImage, Instant.now());
			} else {
				JOptionPane.showMessageDialog(this, "Permit image was not uploaded. Please try again",
						"Upload Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (savedInDatabase) {
				JOptionPane.showMessageDialog(this, "SUDMITTED.", "VERIFICATION", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				parentFrame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Permit image was not saved. Please try again",
						"Upload Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == exit) {
			frame.dispose();
		}
	}
}