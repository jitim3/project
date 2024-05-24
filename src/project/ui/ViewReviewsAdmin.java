package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import project.dto.ReviewDto;
import project.service.ReviewService;
import project.service.impl.DefaultReviewService;

public class ViewReviewsAdmin implements ActionListener {
	private final ReviewService reviewService;
	JFrame frame = new JFrame("View Reviews");
	JButton btnEditInfo = new JButton("Edit Information");
	JScrollBar scrollBar = new JScrollBar();
	JLabel lblNewLabel = new JLabel("VIEW REVIEWS");
	JLabel lblNewLabel_1 = new JLabel();
	JButton btnExit = new JButton("EXIT");
	private final JFrame parentFrame;

	public ViewReviewsAdmin(JFrame parentFrame, long resortId) {
		this.parentFrame = parentFrame;
		this.reviewService = new DefaultReviewService();
		
		List<ReviewDto> reviewDtos = this.reviewService.getReviewsByResortId(resortId);
		String reviews = reviewDtos.stream()
				.map(reviewDto -> """
						User ID: %s
						Rate:    %d
						Comment: %s
						""".formatted(reviewDto.userId(), reviewDto.rate(), reviewDto.comment()))
				.peek(System.out::println)
				.collect(Collectors.joining("\n\n"));
		JTextArea textArea = new JTextArea(reviews); 
        textArea.setWrapStyleWord(true); 
        textArea.setLineWrap(true); 
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 		  
        scrollBar = scrollPane.getVerticalScrollBar(); 
		scrollBar.setBounds(629, 64, 17, 267);

		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnExit.setBounds(308, 389, 89, 23);
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);

		lblNewLabel_1.setBounds(30, 64, 615, 267);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(100, 255, 255, 64));

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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				parentFrame.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			frame.dispose();
		}

	}
}
