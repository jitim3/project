package project.ui;

import project.dto.CreateReviewDto;
import project.dto.ReviewDto;
import project.dto.UserDto;
import project.service.ReviewService;

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
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;

public class WriteReview {
    private final UserDto userDto;
    private final Long resortId;
    private final ReviewService reviewService;
    private final JFrame frame = new JFrame("Write Reviews");
    private final JTextField rateTextField = new JTextField("0");
    private final JTextArea commentTextArea = new JTextArea();
    private final JButton saveButton = new JButton("SAVE");
    private final JFrame displayFrame;

    public WriteReview(JFrame displayFrame, UserDto userDto, Long resortId, ReviewService reviewService) {
        this.displayFrame = displayFrame;
        this.userDto = userDto;
        this.resortId = resortId;
        this.reviewService = reviewService;

        JLabel createYourReviewLabel = new JLabel("Create Your Review");
        createYourReviewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        createYourReviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createYourReviewLabel.setBounds(239, 31, 200, 26);

        JLabel rateLabel = new JLabel("RATE FROM 1-10");
        rateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        rateLabel.setBounds(145, 101, 109, 14);
        rateTextField();

        JLabel writeReviewLabel = new JLabel("WRITE YOUR REVIEW");
        writeReviewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        writeReviewLabel.setBounds(153, 151, 138, 14);
        commentTextArea();

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(105, 78, 484, 269);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(new Color(100, 255, 255, 64));

        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        exitButton.setBounds(220, 408, 89, 23);
        exitButton.setFocusable(false);
        exitButton.addActionListener(actionEvent -> {
            frame.dispose();
        });

        saveButton();

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
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                new Reviews(displayFrame, userDto, resortId);
            }
        });
    }

    private void rateTextField() {
        rateTextField.setBounds(264, 98, 96, 20);
        rateTextField.setColumns(10);
        rateTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveButton.setEnabled(!commentTextArea.getText().isBlank() && !rateTextField.getText().isBlank());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveButton.setEnabled(!commentTextArea.getText().isBlank() && !rateTextField.getText().isBlank());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveButton.setEnabled(!commentTextArea.getText().isBlank() && !rateTextField.getText().isBlank());
            }
        });
        rateTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onKeyEvent(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                onKeyEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                onKeyEvent(e);
            }

            private void onKeyEvent(KeyEvent e) {
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
                    boolean verified = (value >= 0) && (value <= 10);
                    if (verified && !commentTextArea.getText().isBlank()) {
                        saveButton.setEnabled(true);
                    }
                    return verified;
                } catch (NumberFormatException e) {
                    saveButton.setEnabled(false);
                    return false;
                }
            }
        });
    }

    private void commentTextArea() {
        commentTextArea.setBounds(145, 176, 398, 138);
        commentTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveButton.setEnabled(!commentTextArea.getText().isBlank() && !rateTextField.getText().isBlank());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveButton.setEnabled(!commentTextArea.getText().isBlank() && !rateTextField.getText().isBlank());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveButton.setEnabled(!commentTextArea.getText().isBlank() && !rateTextField.getText().isBlank());
            }
        });
    }

    private void saveButton() {
        saveButton.setEnabled(false);
        saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        saveButton.setBounds(390, 408, 89, 23);
        saveButton.setFocusable(false);
        saveButton.addActionListener(actionEvent -> {
            int rate = Integer.parseInt(rateTextField.getText());
            String comment = commentTextArea.getText();
            if (comment != null && !comment.isBlank()) {
                CreateReviewDto createReviewDto = new CreateReviewDto(userDto.getId(), resortId, rate, comment, Instant.now());
                ReviewDto createdReviewDto = reviewService.createReview(createReviewDto);
                if (createdReviewDto != null) {
                    JOptionPane.showMessageDialog(null, "Review successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        });
    }
}
