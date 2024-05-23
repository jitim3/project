package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.dto.CreateReviewDto;
import project.dto.ReviewDto;
import project.service.ReviewService;

public class WriteReview implements ActionListener {
	private final Long userId;
	private final Long resortId;
	private final ReviewService reviewService;
	private final JFrame reviewsFrame;
	private final Reviews reviews;
	private final JFrame frame = new JFrame("Write Reviews");
	private final JLabel createYourReviewLabel = new JLabel("Create Your Review");
	private final JLabel rateLabel = new JLabel("RATE FROM 1-10");
	private final JTextField rateTextField = new JTextField();
	private final JLabel writeReviewLabel = new JLabel("WRITE YOUR REVIEW");
	private final JTextArea commentTextArea = new JTextArea();
	private final JLabel backgroundLabel = new JLabel();
	private final JButton exitButton = new JButton("EXIT");
	private final JButton saveButton = new JButton("SAVE");
	
	public WriteReview(Long userId, Long resortId, ReviewService reviewService, JFrame reviewsFrame, Reviews reviews) {
		this.userId = userId;
		this.resortId = resortId;
		this.reviewService = reviewService;
		this.reviewsFrame = reviewsFrame;
		this.reviews = reviews;
		
		createYourReviewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		createYourReviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createYourReviewLabel.setBounds(239, 31, 200, 26);

		rateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rateLabel.setBounds(145, 101, 109, 14);
		rateTextField.setBounds(264, 98, 96, 20);
		rateTextField.setColumns(10);
		rateTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});
		rateTextField.setInputVerifier(new InputVerifier() {				
			@Override
			public boolean verify(JComponent input) {
				String text = ((JTextField) input).getText();
		        try {
		            int value = Integer.parseInt(text);
		            return (value >= 0) && (value <= 10);
		        } catch (NumberFormatException e) {
		            return false;
		        }
			}
		});
		
		writeReviewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		writeReviewLabel.setBounds(153, 151, 138, 14);
		commentTextArea.setBounds(145, 176, 398, 138);

		backgroundLabel.setBounds(105, 78, 484, 269);
		backgroundLabel.setOpaque(true);
		backgroundLabel.setBackground(new Color(100, 255, 255, 64));

		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		exitButton.setBounds(220, 408, 89, 23);
		exitButton.setFocusable(false);

		saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		saveButton.setBounds(390, 408, 89, 23);
		saveButton.setFocusable(false);

		ImageIcon image = new ImageIcon("beach2.png");
		ImageIcon bg = new ImageIcon("bgdagat.jpg");

		Image backgroundImage = bg.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
		JLabel imageBackgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		imageBackgroundLabel.setBounds(0, 0, 700, 500);

		frame.setIconImage(image.getImage());
		frame.setSize(700, 500);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		frame.add(createYourReviewLabel);
		frame.add(rateLabel);
		frame.add(rateTextField);
		frame.add(writeReviewLabel);
		frame.add(commentTextArea);
		frame.add(backgroundLabel);
		frame.add(exitButton);
		frame.add(saveButton);
		frame.add(imageBackgroundLabel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				reviewsFrame.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("SOURCE :" + e.getSource());
		if (e.getSource() == saveButton) {
			int rate = Integer.parseInt(rateTextField.getText());
			String comment = commentTextArea.getText();
			System.out.println(rate + ":" + comment);
			if (comment != null && !comment.isBlank()) {
				CreateReviewDto createReviewDto = new CreateReviewDto(userId, resortId, rate, comment, Instant.now());
				ReviewDto createdReviewDto = this.reviewService.createReview(createReviewDto);
				if (createdReviewDto != null) {
					JOptionPane.showMessageDialog(null, "Review successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
					reviews.getReviews();
					frame.dispose();
				}
			}
		} else {
			frame.dispose();
		}
	}
}
