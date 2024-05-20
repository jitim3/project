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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import project.dto.ResortDto;
import project.service.ResortService;
import project.util.AppUtils;

public class DisplayFrame implements ActionListener {
	private final ResortService resortService;
	private final ResortDto resortDto;
	private final JFrame frame;
	private JButton reservationButton = new JButton("Make a reservation");
	private JButton viewReviewsButton = new JButton("View Reviews");
	private JButton transactionButton = new JButton("Transaction");
	private JButton exitButton = new JButton("EXIT");

	public DisplayFrame(ResortService resortService, long resortId) {
		this.resortService = resortService;
		this.resortDto = this.resortService.getResortById(resortId)
				.orElse(new ResortDto());
		this.frame = new JFrame(resortDto.name());

		JLabel resortNameLabel = new JLabel(resortDto.name()); // RESORT NAME
		resortNameLabel.setBounds(310, 30, 300, 45);
		resortNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		resortNameLabel.setForeground(Color.black);
		resortNameLabel.setOpaque(true);
		resortNameLabel.setBackground(new Color(255, 255, 255, 64));
		resortNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resortNameLabel.setVerticalAlignment(SwingConstants.CENTER);

		JLabel locationLabel = new JLabel(resortDto.location()); // LOCATION AREA
		locationLabel.setBounds(348, 90, 230, 30);
		locationLabel.setOpaque(true);
		locationLabel.setBackground(new Color(255, 255, 255, 64));
		locationLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		locationLabel.setVerticalAlignment(SwingConstants.CENTER);

		JTextArea descriptionTextArea = new JTextArea(resortDto.description()); // DESCRIPTION
		descriptionTextArea.setBounds(40, 520, 780, 180);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setBackground(new Color(255, 255, 255, 64));
		descriptionTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JTextArea howToGetThereTextArea = new JTextArea(resortDto.howToGetThere());
		howToGetThereTextArea.setBounds(40, 710, 780, 180);
		howToGetThereTextArea.setLineWrap(true);
		howToGetThereTextArea.setWrapStyleWord(true);
		howToGetThereTextArea.setEditable(false);
		howToGetThereTextArea.setBackground(new Color(255, 255, 255, 64));
		howToGetThereTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel resortImageLabel = new JLabel(); // IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
		resortImageLabel.setBounds(40, 225, 350, 250); // Set bounds as per your requirement
		String resortImagePath = AppUtils.imagePath(this.resortDto.resortImage())
				.orElse("");
		ImageIcon resortImageIcon = new ImageIcon(resortImagePath);
		Image resortImage = resortImageIcon.getImage().getScaledInstance(
				resortImageLabel.getWidth(), 
				resortImageLabel.getHeight(),
				Image.SCALE_SMOOTH
			);
		resortImageLabel.setIcon(new ImageIcon(resortImage));

		JLabel poolImageLabel = new JLabel(); // IMPORT THE POOL JFILECHOOSER FROM FILL UP FORM
		poolImageLabel.setBounds(475, 225, 350, 250); // Set bounds as per your requirement
		String poolImagePath = AppUtils.imagePath(this.resortDto.poolImage())
				.orElse("");
		ImageIcon imageIcon1 = new ImageIcon(poolImagePath);
		Image poolImage = imageIcon1.getImage().getScaledInstance(
				poolImageLabel.getWidth(), 
				poolImageLabel.getHeight(),
				Image.SCALE_SMOOTH
			);
		poolImageLabel.setIcon(new ImageIcon(poolImage));

		reservationButton.setBounds(360, 940, 150, 25);
		reservationButton.setFocusable(false);
		reservationButton.addActionListener(this);
		reservationButton.setOpaque(false);

		viewReviewsButton.setBounds(360, 985, 150, 25);
		viewReviewsButton.setFocusable(false);
		viewReviewsButton.addActionListener(this);
		viewReviewsButton.setOpaque(false);

		transactionButton.setBounds(360, 1025, 150, 25);
		transactionButton.setFocusable(false);
		transactionButton.addActionListener(this);
		transactionButton.setOpaque(false);

		exitButton.setBounds(380, 1075, 110, 25);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		exitButton.setOpaque(false);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon backgroundImageIcon = new ImageIcon("figma.jpg");
		Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(900, 1180, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 900, 1180);

		JPanel panel = new JPanel();

		panel.add(exitButton);
		panel.add(transactionButton);
		panel.add(viewReviewsButton);
		panel.add(reservationButton);
		panel.setLayout(null);
		panel.add(howToGetThereTextArea);
		panel.add(locationLabel); // RESORT LOCATION
		panel.add(resortNameLabel); // RESORT NAME
		panel.add(resortImageLabel); // RESORT PICTURE
		panel.add(poolImageLabel); // POOL PICTURE
		panel.add(descriptionTextArea);// DESCRIPTION AREA
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
		if (e.getSource() == reservationButton) {

		} else if (e.getSource() == viewReviewsButton) {

		} else if (e.getSource() == transactionButton) {

		} else if (e.getSource() == exitButton) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}