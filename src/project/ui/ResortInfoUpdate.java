package project.ui;

import project.dto.CreateRoomDto;
import project.dto.ResortDto;
import project.dto.RoomDto;
import project.dto.UpdateResortDto;
import project.dto.UpdateRoomDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.service.RoomService;
import project.util.AppUtils;
import project.util.RoomAvailabilityTypes;
import project.util.RoomTypes;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Optional;

public class ResortInfoUpdate implements ActionListener {
    private static final Logger LOGGER = System.getLogger(ResortInfoUpdate.class.getName());
    private final UserDto userDto;
    private final ResortDto resortDto;
    private final ResortService resortService;
    private final RoomService roomService;

    // ==> FRAME
    private final JFrame frame = new JFrame("Edit");
    // ==> LABELS
    private final JLabel label = new JLabel("Edit resort information");
    private final JLabel resortInformation = new JLabel("RESORT DETAILS INFORMATION");
    private final JLabel resortName = new JLabel("Name of the resort");
    private final JLabel uploadResort = new JLabel("Upload image of the resort");
    private final JLabel resortLocation = new JLabel("Resort address/Location");
    private final JLabel resortDescription = new JLabel("Resort description");
    private final JLabel uploadPool = new JLabel("Upload image of the pool");
    private final JLabel resortHTG = new JLabel("How to get there?");
    private final JLabel uploadCottage = new JLabel("Upload image of the cottage");
    private final JLabel resortPriceInformation = new JLabel("RESORT PRICE INFORMATION");
    private final JLabel resortEntranceFee = new JLabel("Enter entrance fee of the resort");
    private final JLabel resortCottageFee = new JLabel("Enter rental fee of the cottage");
    private final JLabel resortPoolFee = new JLabel("Enter pool entrace fee of the resort");
    private final JLabel resortRoomInformation = new JLabel("RESORT ROOMS INFORMATION");
    private final JLabel resortRoomNormal = new JLabel("Normal Room");
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

    private final JLabel resortImageLabel = new JLabel(); // USED FOR UPLOAD RESORT PICTURE
    private final JLabel poolImageLabel = new JLabel(); // USED FOR UPLOAD POOL PICTURE
    private final JLabel cottageImageLabel = new JLabel(); // USED FOR UPLOAD COTTAGE PICTURE

    private final JLabel normalRoomImage1Label = new JLabel(); // USED FOR UPLOAD ROOM 1st PIC
    private final JLabel normalRoomImage2Label = new JLabel(); // USED FOR UPLOAD ROOM 2nd PIC

    private final JLabel familyRoomImage1Label = new JLabel(); // USED FOR UPLOAD FAMILY ROOM 1st PIC
    private final JLabel familyRoomImage2Label = new JLabel(); // USED FOR UPLOAD FAMILY ROOM 2nd PIC

    // ==>COMBOBOX
    String[] roomAvailabilityChoices = RoomAvailabilityTypes.names();
    JComboBox<String> roomAvailabilityTypeComboBox = new JComboBox<>(roomAvailabilityChoices);

    // ==> TEXTFIELDS
    private final JTextField resortNameTextField = new JTextField();
    private final JTextField resortLocationTextField = new JTextField();
    private final JFormattedTextField resortEntranceFeeTextField = new JFormattedTextField();
    private final JFormattedTextField resortCottageFeeTextField = new JFormattedTextField();
    private final JFormattedTextField resortPoolFeeTextField = new JFormattedTextField();
    private final JTextField normalRoomNumberOfPaxTextField = new JTextField();
    private final JFormattedTextField normalRoomRatePerNightTextField = new JFormattedTextField();
    private final JTextField familyRoomNumberPaxTextField = new JTextField();
    private final JFormattedTextField familyRoomRatePerNightTextField = new JFormattedTextField();

    // JTEXTAREA WITH SCROLLPANES
    private final JTextArea resortDescriptionTextArea = new JTextArea(3, 20);
    private final JScrollPane resortNameScrollPane = new JScrollPane(resortDescriptionTextArea);

    private final JTextArea normalRoomDescriptionTextArea = new JTextArea(1, 10);
    private final JScrollPane resortRoomDescriptionScrollPane = new JScrollPane(normalRoomDescriptionTextArea);

    private final JTextArea familyRoomDescriptionTextArea = new JTextArea(1, 10);
    private final JScrollPane resortFamilyRoomDescriptionScrollPane = new JScrollPane(familyRoomDescriptionTextArea);

    private final JTextArea howToGetThereTextArea = new JTextArea(1, 10);
    private final JScrollPane resortHTGFieldScrollPane = new JScrollPane(howToGetThereTextArea);

    // ==> BUTTONS
    private final JButton resortImageButton = new JButton("Browse");
    private final JButton poolImageButton = new JButton("Browse");
    private final JButton cottageImageButton = new JButton("Browse");
    private final JButton updateButton = new JButton("Update");
    private final JButton normalRoomImage1Button = new JButton("Add Image");
    private final JButton normalRoomImage2Button = new JButton("Add Image");
    private final JButton familyRoomImage1Button = new JButton("Add Image");
    private final JButton familyRoomImage2Button = new JButton("Add Image");

    private File resortImageFile;
    private File poolImageFile;
    private File cottageImageFile;
    private File normalRoomImage1File;
    private File normalRoomImage2File;
    private File familyRoomImage1File;
    private File familyRoomImage2File;
    private final JFrame userMenuFrame;
    private String windowEventSource = "";

    private RoomDto normalRoomDto;
    private RoomDto familyRoomDto;

    public ResortInfoUpdate(JFrame userMenuFrame, UserDto userDto, ResortDto resortDto, ResortService resortService) {
        this.userMenuFrame = userMenuFrame;
        this.userDto = userDto;
        this.resortDto = resortDto;
        this.resortService = resortService;
        this.roomService = new RoomService();

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

        resortLocationTextField.setBounds(625, 153, 150, 25);
        resortLocationTextField.setPreferredSize(new Dimension(200, 175));

        resortEntranceFeeTextField.setBounds(275, 1228, 150, 25);
        resortEntranceFeeTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.currency(resortEntranceFeeTextField);

        resortCottageFeeTextField.setBounds(275, 1278, 150, 25);
        resortCottageFeeTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.currency(resortCottageFeeTextField);

        resortPoolFeeTextField.setBounds(295, 1328, 150, 25);
        resortPoolFeeTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.currency(resortPoolFeeTextField);

        normalRoomNumberOfPaxTextField.setBounds(245, 1527, 150, 25);
        normalRoomNumberOfPaxTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.numeric(normalRoomNumberOfPaxTextField);

        normalRoomRatePerNightTextField.setBounds(245, 1560, 150, 25);
        normalRoomRatePerNightTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.currency(normalRoomRatePerNightTextField);

        familyRoomNumberPaxTextField.setBounds(245, 2006, 150, 25);
        familyRoomNumberPaxTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.numeric(familyRoomNumberPaxTextField);

        familyRoomRatePerNightTextField.setBounds(245, 2048, 150, 25);
        familyRoomRatePerNightTextField.setPreferredSize(new Dimension(200, 175));
        AppUtils.currency(familyRoomRatePerNightTextField);

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
        howToGetThereTextArea.setLineWrap(true);
        howToGetThereTextArea.setWrapStyleWord(true);

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

        updateButton.setBounds(372, 2500, 150, 25);
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);
        updateButton.setOpaque(false);

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

        panel.add(updateButton);
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
        panel.add(howToGetThereTextArea);
        panel.setLayout(null);
        panel.add(resortLocationTextField);
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
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Display existing data
        this.resortNameTextField.setText(this.resortDto.name());
        this.resortLocationTextField.setText(this.resortDto.location());
        this.resortDescriptionTextArea.setText(this.resortDto.description());
        this.howToGetThereTextArea.setText(this.resortDto.howToGetThere());
        String resortImagePath = AppUtils.imagePath(this.resortDto.resortImage()).orElse("");
        ImageIcon resortImageIcon = new ImageIcon(resortImagePath);
        Image resortImage = resortImageIcon.getImage().getScaledInstance(resortImageLabel.getWidth(),
                resortImageLabel.getHeight(), Image.SCALE_SMOOTH);
        resortImageLabel.setIcon(new ImageIcon(resortImage));
        String poolImagePath = AppUtils.imagePath(this.resortDto.poolImage()).orElse("");
        ImageIcon poolImageIcon = new ImageIcon(poolImagePath);
        Image poolImage = poolImageIcon.getImage().getScaledInstance(poolImageLabel.getWidth(),
                poolImageLabel.getHeight(), Image.SCALE_SMOOTH);
        poolImageLabel.setIcon(new ImageIcon(poolImage));
        String cottageImagePath = AppUtils.imagePath(this.resortDto.cottageImage()).orElse("");
        ImageIcon cottageImageIcon = new ImageIcon(cottageImagePath);
        Image cottageImage = cottageImageIcon.getImage().getScaledInstance(cottageImageLabel.getWidth(),
                cottageImageLabel.getHeight(), Image.SCALE_SMOOTH);
        cottageImageLabel.setIcon(new ImageIcon(cottageImage));
        String resortFee = this.resortDto.resortFee() != null
                ? this.resortDto.resortFee().setScale(2, RoundingMode.HALF_UP).toString()
                : "0.00";
        this.resortEntranceFeeTextField.setText(resortFee);
        String cottageFee = this.resortDto.cottageFee() != null
                ? this.resortDto.cottageFee().setScale(2, RoundingMode.HALF_UP).toString()
                : "0.00";
        this.resortCottageFeeTextField.setText(cottageFee);
        String poolFee = this.resortDto.poolFee() != null
                ? this.resortDto.poolFee().setScale(2, RoundingMode.HALF_UP).toString()
                : "0.00";
        this.resortPoolFeeTextField.setText(poolFee);

        Optional<RoomDto> normalRoomDtoOptional = this.resortDto.roomDtos().stream()
                .filter(roomDto -> RoomTypes.NORMAL.value().equals(roomDto.roomType())).findFirst();
        if (normalRoomDtoOptional.isPresent()) {
            normalRoomDto = normalRoomDtoOptional.get();
            int normalRoomNumberOfPaxValue = normalRoomDto.numberOfPax();
            BigDecimal ratePerNight = normalRoomDto.ratePerNight();
            String normalRoomRatePerNightValue = ratePerNight != null
                    ? ratePerNight.setScale(2, RoundingMode.HALF_UP).toString()
                    : "0.00";

            this.roomAvailabilityTypeComboBox.setSelectedItem(normalRoomDto.roomAvailabilityTypeDto().name());

            this.normalRoomNumberOfPaxTextField.setText(String.valueOf(normalRoomNumberOfPaxValue));
            this.normalRoomRatePerNightTextField.setText(normalRoomRatePerNightValue);
            this.normalRoomDescriptionTextArea.setText(normalRoomDto.description());

            String normalRoomImagePath1 = AppUtils.imagePath(normalRoomDto.roomImage1()).orElse("");
            ImageIcon normalRoomImageIcon1 = new ImageIcon(normalRoomImagePath1);
            Image normalRoomImage1 = normalRoomImageIcon1.getImage().getScaledInstance(normalRoomImage1Label.getWidth(),
                    normalRoomImage1Label.getHeight(), Image.SCALE_SMOOTH);
            normalRoomImage1Label.setIcon(new ImageIcon(normalRoomImage1));

            String normalRoomImagePath2 = AppUtils.imagePath(normalRoomDto.roomImage2()).orElse("");
            ImageIcon normalRoomImageIcon2 = new ImageIcon(normalRoomImagePath2);
            Image normalRoomImage2 = normalRoomImageIcon2.getImage().getScaledInstance(normalRoomImage2Label.getWidth(),
                    normalRoomImage2Label.getHeight(), Image.SCALE_SMOOTH);
            normalRoomImage2Label.setIcon(new ImageIcon(normalRoomImage2));
        }

        Optional<RoomDto> familyRoomDtoOptional = this.resortDto.roomDtos().stream()
                .filter(roomDto -> RoomTypes.FAMILY.value().equals(roomDto.roomType())).findFirst();
        if (familyRoomDtoOptional.isPresent()) {
            familyRoomDto = familyRoomDtoOptional.get();
            int familyRoomNumberOfPaxValue = familyRoomDto.numberOfPax();
            BigDecimal ratePerNight = familyRoomDto.ratePerNight();
            String familyRoomRatePerNightValue = ratePerNight != null
                    ? ratePerNight.setScale(2, RoundingMode.HALF_UP).toString()
                    : "0.00";

            this.familyRoomNumberPaxTextField.setText(String.valueOf(familyRoomNumberOfPaxValue));
            this.familyRoomRatePerNightTextField.setText(familyRoomRatePerNightValue);
            this.familyRoomDescriptionTextArea.setText(familyRoomDto.description());

            String familyRoomImagePath1 = AppUtils.imagePath(familyRoomDto.roomImage1()).orElse("");
            ImageIcon familyRoomImageIcon1 = new ImageIcon(familyRoomImagePath1);
            Image familyRoomImage1 = familyRoomImageIcon1.getImage().getScaledInstance(familyRoomImage1Label.getWidth(),
                    familyRoomImage1Label.getHeight(), Image.SCALE_SMOOTH);
            familyRoomImage1Label.setIcon(new ImageIcon(familyRoomImage1));

            String familyRoomImagePath2 = AppUtils.imagePath(familyRoomDto.roomImage2()).orElse("");
            ImageIcon familyRoomImageIcon2 = new ImageIcon(familyRoomImagePath2);
            Image familyRoomImage2 = familyRoomImageIcon2.getImage().getScaledInstance(familyRoomImage2Label.getWidth(),
                    familyRoomImage2Label.getHeight(), Image.SCALE_SMOOTH);
            familyRoomImage2Label.setIcon(new ImageIcon(familyRoomImage2));
        }
        // End: Display existing data

        frame.setContentPane(scrollPane);
        frame.setIconImage(icon.getImage());
        frame.setSize(900, 1000);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!"updateButton".equals(windowEventSource)) {
                    userMenuFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resortImageButton) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                resortImageFile = fileChooser.getSelectedFile();
                if (resortImageFile != null) {
                    String filePath = resortImageFile.getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    Image image = imageIcon.getImage().getScaledInstance(resortImageLabel.getWidth(),
                            resortImageLabel.getHeight(), Image.SCALE_SMOOTH);
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
                    Image img = selectedImage.getImage().getScaledInstance(poolImageLabel.getWidth(),
                            poolImageLabel.getHeight(), Image.SCALE_SMOOTH);
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
                Image image = imageIcon.getImage().getScaledInstance(cottageImageLabel.getWidth(),
                        cottageImageLabel.getHeight(), Image.SCALE_SMOOTH);
                cottageImageLabel.setIcon(new ImageIcon(image));
            }
        } else if (e.getSource() == normalRoomImage1Button) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                normalRoomImage1File = fileChooser.getSelectedFile();
                String filePath = normalRoomImage1File.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(filePath);
                Image image = imageIcon.getImage().getScaledInstance(normalRoomImage1Label.getWidth(),
                        normalRoomImage1Label.getHeight(), Image.SCALE_SMOOTH);
                normalRoomImage1Label.setIcon(new ImageIcon(image));
            }
        } else if (e.getSource() == normalRoomImage2Button) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                normalRoomImage2File = fileChooser.getSelectedFile();
                String filePath = normalRoomImage2File.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(filePath);
                Image image = imageIcon.getImage().getScaledInstance(normalRoomImage2Label.getWidth(),
                        normalRoomImage2Label.getHeight(), Image.SCALE_SMOOTH);
                normalRoomImage2Label.setIcon(new ImageIcon(image));
            }
        } else if (e.getSource() == familyRoomImage1Button) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                familyRoomImage1File = fileChooser.getSelectedFile();
                String filePath = familyRoomImage1File.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(filePath);
                Image image = imageIcon.getImage().getScaledInstance(familyRoomImage1Label.getWidth(),
                        familyRoomImage1Label.getHeight(), Image.SCALE_SMOOTH);
                familyRoomImage1Label.setIcon(new ImageIcon(image));
            }
        } else if (e.getSource() == familyRoomImage2Button) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                familyRoomImage2File = fileChooser.getSelectedFile();
                String filePath = familyRoomImage2File.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(filePath);
                Image image = imageIcon.getImage().getScaledInstance(familyRoomImage2Label.getWidth(),
                        familyRoomImage2Label.getHeight(), Image.SCALE_SMOOTH);
                familyRoomImage2Label.setIcon(new ImageIcon(image));
            }
        } else if (e.getSource() == updateButton) {
            this.windowEventSource = "updateButton";
            frame.dispose();

            // Update resort
            String description = resortDescriptionTextArea.getText();
            String location = resortLocationTextField.getText();
            String howToGetThere = howToGetThereTextArea.getText();

            BigDecimal resortFee;
            try {
                resortFee = new BigDecimal(resortEntranceFeeTextField.getText().replace(",", ""));
            } catch (Exception mfe) {
                resortFee = BigDecimal.ZERO;
            }

            resortFee = resortFee.setScale(2, RoundingMode.HALF_UP);

            BigDecimal cottageFee;
            try {
                cottageFee = new BigDecimal(resortCottageFeeTextField.getText().replace(",", ""));
            } catch (NumberFormatException nfe) {
                cottageFee = BigDecimal.ZERO;
            }

            cottageFee = cottageFee.setScale(2, RoundingMode.HALF_UP);

            BigDecimal poolFee;
            try {
                poolFee = new BigDecimal(resortPoolFeeTextField.getText().replace(",", ""));
            } catch (Exception e2) {
                poolFee = BigDecimal.ZERO;
            }

            poolFee = poolFee.setScale(2, RoundingMode.HALF_UP);

            String resortImage = null;
            Optional<String> resortImageFilenameOptional = AppUtils.generateFilename(resortImageFile);
            if (resortImageFilenameOptional.isPresent()) {
                resortImage = resortImageFilenameOptional.get();
                try {
                    AppUtils.saveImage(resortImageFile, resortImage);
                } catch (IOException ioe) {
                    resortImage = null;
                    LOGGER.log(Level.ERROR, "File " + resortImage + " was not saved.");
                }
            }

            resortImage = resortImage == null ? resortDto.resortImage() : resortImage;

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

            poolImage = poolImage == null ? resortDto.poolImage() : poolImage;

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

            cottageImage = cottageImage == null ? resortDto.cottageImage() : cottageImage;

            Instant updatedAt = Instant.now();
            Instant createdAt = updatedAt;

            UpdateResortDto updateResortDto = new UpdateResortDto(
                    resortDto.id(),
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
                normalRoomRatePerNight = new BigDecimal(normalRoomRatePerNightTextField.getText().replace(",", ""));
            } catch (NumberFormatException nfe) {
                normalRoomRatePerNight = BigDecimal.ZERO;
            }

            normalRoomRatePerNight = normalRoomRatePerNight.setScale(2, RoundingMode.HALF_UP);

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

            if (normalRoomDto == null) {
                CreateRoomDto normalRoom = new CreateRoomDto(this.resortDto.id(),
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
            } else {
                normalRoomImage1 = normalRoomImage1 == null ? normalRoomDto.roomImage1() : normalRoomImage1;
                normalRoomImage2 = normalRoomImage2 == null ? normalRoomDto.roomImage2() : normalRoomImage2;
                UpdateRoomDto normalRoom = new UpdateRoomDto(
                        normalRoomDto.id(),
                        roomAvailabilityTypeId,
                        normalNormalNumberOfPax,
                        normalRoomRatePerNight,
                        normalRoomDescription,
                        normalRoomImage1,
                        normalRoomImage2,
                        updatedAt
                );
                this.roomService.updateRoom(normalRoom);
            }

            // Family room
            int familyRoomNumberOfPax;
            try {
                familyRoomNumberOfPax = Integer.parseInt(familyRoomNumberPaxTextField.getText());
            } catch (NumberFormatException nfe) {
                familyRoomNumberOfPax = 0;
            }

            BigDecimal familyRoomRatePerNight;
            try {
                familyRoomRatePerNight = new BigDecimal(familyRoomRatePerNightTextField.getText().replace(",", ""));
            } catch (NumberFormatException nfe) {
                familyRoomRatePerNight = BigDecimal.ZERO;
            }

            familyRoomRatePerNight = familyRoomRatePerNight.setScale(2, RoundingMode.HALF_UP);

            String familyRoomDescription = familyRoomDescriptionTextArea.getText();

            String familyRoomImage1 = null;
            Optional<String> familyRoomImage1NewFilenameOptional = AppUtils.generateFilename(familyRoomImage1File);
            if (familyRoomImage1NewFilenameOptional.isPresent()) {
                familyRoomImage1 = familyRoomImage1NewFilenameOptional.get();
                try {
                    AppUtils.saveImage(familyRoomImage1File, familyRoomImage1);
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

            if (familyRoomDto == null) {
                CreateRoomDto familyRoom = new CreateRoomDto(
                        this.resortDto.id(),
                        roomAvailabilityTypeId,
                        RoomTypes.FAMILY.value(),
                        familyRoomNumberOfPax,
                        familyRoomRatePerNight,
                        familyRoomDescription,
                        familyRoomImage1,
                        familyRoomImage2,
                        createdAt
                );
                this.roomService.createRoom(familyRoom);
            } else {
                familyRoomImage1 = familyRoomImage1 == null ? familyRoomDto.roomImage1() : familyRoomImage1;
                familyRoomImage2 = familyRoomImage2 == null ? familyRoomDto.roomImage2() : familyRoomImage2;
                UpdateRoomDto familyRoom = new UpdateRoomDto(
                        familyRoomDto.id(),
                        roomAvailabilityTypeId,
                        familyRoomNumberOfPax,
                        familyRoomRatePerNight,
                        familyRoomDescription,
                        familyRoomImage1,
                        familyRoomImage2,
                        updatedAt
                );
                this.roomService.updateRoom(familyRoom);
            }

            new Verification(userMenuFrame, userDto, resortDto.id(), resortService);
        }
    }
}
