package project.ui;

import project.dto.ReviewDto;
import project.service.ReviewService;
import project.service.impl.DefaultReviewService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reviews implements ActionListener {
    private final Long userId;
    private final Long resortId;
    private final ReviewService reviewService;
    private final JFrame frame = new JFrame("REVIEWS");
    private final JButton createReviewButton = new JButton("Create Review");
    private List<ReviewDto> reviewDtos = new ArrayList<>();
    private final JFrame parentFrame;
    private String windowEventSource = "";

    public Reviews(JFrame parentFrame, Long userId, Long resortId) {
        this.parentFrame = parentFrame;
        this.userId = userId;
        this.resortId = resortId;
        this.reviewService = new DefaultReviewService();

        this.getReviews();

        String reviews = this.reviewDtos.stream()
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
        JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.setBounds(635, 63, 17, 256);

        JLabel lblTitle = new JLabel("REVIEWS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblTitle.setBounds(283, 11, 120, 38);

        JLabel reviewsBackgroundLabel = new JLabel();
        reviewsBackgroundLabel.setBounds(36, 63, 616, 256);
        reviewsBackgroundLabel.setOpaque(true);
        reviewsBackgroundLabel.setBackground(new Color(100, 255, 255, 64));

        createReviewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        createReviewButton.setBounds(294, 362, 109, 28);
        createReviewButton.setFocusable(false);
        createReviewButton.addActionListener(this);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        exitButton.setBounds(294, 409, 109, 23);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

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
        frame.add(reviewsBackgroundLabel);
        frame.add(scrollBar);
        frame.add(createReviewButton);
        frame.add(exitButton);
        frame.add(backgroundLabel);
        frame.add(backgroundLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"createReviewButton".equalsIgnoreCase(windowEventSource)) {
                    parentFrame.setVisible(true);
                }
            }
        });
    }

    void getReviews() {
        this.reviewDtos = this.reviewService.getReviewsByResortId(resortId);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createReviewButton) {
            this.windowEventSource = "createReviewButton";
            frame.dispose();
            new WriteReview(parentFrame, userId, resortId, reviewService);
        } else {
            frame.dispose();
        }
    }
}
