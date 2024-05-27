package project.ui;

import project.dto.CreateResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.ui.town.Alcoy;
import project.ui.town.Barili;
import project.ui.town.Carcar;
import project.ui.town.Moalboal;
import project.ui.town.Oslob;
import project.ui.town.SanTander;
import project.ui.town.Town;
import project.util.AppUtils;
import project.util.TownEnum;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.util.List;
import java.util.function.BiFunction;

public class AdminResortRegistration implements ActionListener {
    private final UserDto userDto;
    private final ResortService resortService;
    private final JFrame frame = new JFrame("Select Town to Register");
    private final JLabel label = new JLabel("Welcome Admin!");
    private final JLabel label1 = new JLabel("Please select town to register");
    private final JLabel label2 = new JLabel("Enter name of the resort");
    private final JTextField field = new JTextField();
    private final JButton display = new JButton("Display");
    private final List<TownHolder> townHolders;
    private final JFrame adminMenuFrame;
    private String windowEventSource = "unknown";

    AdminResortRegistration(JFrame adminMenuFrame, UserDto userDto, ResortService resortService) {
        this.adminMenuFrame = adminMenuFrame;
        this.userDto = userDto;
        this.resortService = resortService;

        ImageIcon background = new ImageIcon("beach3.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(500, 550, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 500, 550);

        ImageIcon icon = new ImageIcon("beach2.png");

        this.townHolders = this.townHolders();

        display.setBounds(200, 280, 150, 30);
        display.setFocusable(false);
        display.addActionListener(this);

        label2.setBounds(30, 190, 200, 125);
        label2.setFont(new Font("+", Font.PLAIN, 12));
        label2.setForeground(Color.BLACK);

        label1.setBounds(121, 35, 350, 125);
        label1.setFont(new Font("+", Font.PLAIN, 18));
        label1.setForeground(Color.BLACK);

        label.setBounds(141, 25, 300, 100);
        label.setFont(new Font("+", Font.BOLD, 26));
        label.setForeground(Color.BLACK);

        field.setBounds(180, 243, 200, 20);
        field.setPreferredSize(new Dimension(50, 190));

        frame.setLayout(null);
        frame.add(field);
        frame.add(display);
        AppUtils.reverse(this.townHolders)
                .forEach(townHolder -> frame.add(townHolder.townCheckBox()));
        frame.add(label2);
        frame.add(label);
        frame.add(label1);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if ("unknown".equals(windowEventSource)) {
                    adminMenuFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == display) {
            windowEventSource = "display";
            String resortName = field.getText();
            List<Integer> selectedTownIds = this.townHolders.stream()
                    .filter(townHolder -> townHolder.townCheckBox().isSelected())
                    .map(TownHolder::townId)
                    .toList();
            if (!selectedTownIds.isEmpty()) {
                long resortId = this.resortService.createResort(new CreateResortDto(resortName, this.userDto.getId(), selectedTownIds, Instant.now()));
                JOptionPane.showMessageDialog(null, "Information successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                field.setText(null);
                new ResortInfo(adminMenuFrame, userDto, resortId, resortName, this.resortService);
            }
        }
    }

    private List<TownHolder> townHolders() {
        JCheckBox carcarCheckBox = new JCheckBox(TownEnum.CARCAR.value()); // id: 1
        carcarCheckBox.setBounds(75, 120, 200, 70);
        carcarCheckBox.setFocusable(false);
        carcarCheckBox.addActionListener(this);
        carcarCheckBox.setOpaque(false);
        BiFunction<UserDto, Long, Town> carcarTown = (user, resortId) -> new Carcar(user, frame, resortId);
        TownHolder carcarTownHolder = new TownHolder(1, carcarCheckBox, carcarTown);

        JCheckBox bariliCheckBox = new JCheckBox(TownEnum.BARILI.value()); // id: 2
        bariliCheckBox.setBounds(135, 120, 200, 70);
        bariliCheckBox.setFocusable(false);
        bariliCheckBox.addActionListener(this);
        bariliCheckBox.setOpaque(false);
        BiFunction<UserDto, Long, Town> bariliTown = (user, resortId) -> new Barili(user, frame, resortId);
        TownHolder bariliTownHolder = new TownHolder(2, bariliCheckBox, bariliTown);

        JCheckBox moalboalCheckBox = new JCheckBox(TownEnum.MOALBOAL.value()); // id: 3
        moalboalCheckBox.setBounds(190, 120, 200, 70);
        moalboalCheckBox.setFocusable(false);
        moalboalCheckBox.addActionListener(this);
        moalboalCheckBox.setOpaque(false);
        BiFunction<UserDto, Long, Town> moalboalTown = (user, resortId) -> new Moalboal(user, frame, resortId);
        TownHolder moalboalTownHolder = new TownHolder(3, moalboalCheckBox, moalboalTown);

        JCheckBox alcoyCheckBox = new JCheckBox(TownEnum.ALCOY.value()); // id: 4
        alcoyCheckBox.setBounds(265, 120, 200, 70);
        alcoyCheckBox.setFocusable(false);
        alcoyCheckBox.addActionListener(this);
        alcoyCheckBox.setOpaque(false);
        BiFunction<UserDto, Long, Town> alcoyTown = (user, resortId) -> new Alcoy(user, frame, resortId);
        TownHolder alcoyTownHolder = new TownHolder(4, alcoyCheckBox, alcoyTown);

        JCheckBox sanTanderCheckBox = new JCheckBox(TownEnum.SANTANDER.value()); // id: 5
        sanTanderCheckBox.setBounds(320, 120, 200, 70);
        sanTanderCheckBox.setFocusable(false);
        sanTanderCheckBox.addActionListener(this);
        sanTanderCheckBox.setOpaque(false);
        BiFunction<UserDto, Long, Town> santanderTown = (user, resortId) -> new SanTander(user, frame, resortId);
        TownHolder santanderTownHolder = new TownHolder(5, sanTanderCheckBox, santanderTown);

        JCheckBox oslobCheckBox = new JCheckBox(TownEnum.OSLOB.value()); // id: 6
        oslobCheckBox.setBounds(200, 160, 150, 30);
        oslobCheckBox.setFocusable(false);
        oslobCheckBox.addActionListener(this);
        oslobCheckBox.setOpaque(false);
        BiFunction<UserDto, Long, Town> oslobTown = (user, resortId) -> new Oslob(user, frame, resortId);
        TownHolder oslobTownHolder = new TownHolder(6, oslobCheckBox, oslobTown);

        return List.of(
                carcarTownHolder,
                bariliTownHolder,
                moalboalTownHolder,
                alcoyTownHolder,
                santanderTownHolder,
                oslobTownHolder
        );
    }
}
