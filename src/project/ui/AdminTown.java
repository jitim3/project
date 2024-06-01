package project.ui;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.ui.town.Town;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AdminTown implements Town { // CARCAR FRAME
    private final int townId;
    private final UserDto userDto;
    private final ResortService resortService;
    private final List<ResortDto> resortDtos;
    private final JFrame frame;
    private final JFrame parentFrame;
    private final JButton backButton = new JButton("Back");
    private final List<String> windowEventSources = new ArrayList<>();
    private String windowEventSource = "";
    private final JFrame userMenuFrame;

    public AdminTown(int townId, String townName, JFrame userMenuFrame, UserDto userDto, JFrame parentFrame) {
        this.townId = townId;
        this.userMenuFrame = userMenuFrame;
        this.userDto = userDto;
        this.resortService = new ResortService();
        this.parentFrame = parentFrame;
        this.frame = new JFrame(townName);

        this.resortDtos = this.getRegisteredResorts();
        this.generateButton();
        windowEventSources.add("backButton");

        backButton.setBounds(370, 420, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        ImageIcon icon = new ImageIcon("beach2.png");

        ImageIcon background = new ImageIcon("beach3.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 500, 600);

        frame.add(backButton);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Optional<String> windowEventSourceOptional = windowEventSources.stream()
                        .filter(wes -> wes.equals(windowEventSource))
                        .findFirst();
                if (windowEventSourceOptional.isEmpty()) {
                    userMenuFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            windowEventSource = "backButton";
            frame.dispose();
            this.parentFrame.setVisible(true);
        }
    }

    @Override
    public void generateButton() {
        ImageIcon background = new ImageIcon("beach3.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 500, 600);

        int y = 65;
        for (int i = 0; i < this.resortDtos.size(); i++) {
            String buttonName = "button" + i;
            AtomicReference<String> windowEventSourceRef = new AtomicReference<>(buttonName);
            windowEventSources.add(buttonName);
            ResortDto resortDto = this.resortDtos.get(i);
            JButton resortButton = new JButton(resortDto.name());
            resortButton.setBounds(50, y, 400, 75);
            resortButton.setOpaque(false);
            resortButton.setFocusable(false);
            resortButton.addActionListener(e -> {
                windowEventSource = windowEventSourceRef.get();
                frame.dispose();
                new Reviews(frame, userDto, resortDto.id());
            });
            frame.getContentPane().add(resortButton);
            frame.add(resortButton);

            y = y + 85;
        }

        frame.setLayout(null);
        frame.revalidate();
        frame.repaint();
    }

    private List<ResortDto> getRegisteredResorts() {
        return this.resortService.getResortByUserIdAndTownId(this.userDto.getId(), townId)
                .map(List::of)
                .orElse(List.of());

    }
}