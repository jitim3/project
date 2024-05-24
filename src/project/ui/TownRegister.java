package project.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import project.dto.CreateResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.ui.town.Carcar;
import project.ui.town.Town;
import project.util.AppUtils;

public class TownRegister implements ActionListener {
	private final UserDto userDto;
	private final ResortService resortService;
	private final JFrame frame = new JFrame("Select Town to Register");
	private final JLabel label = new JLabel("Welcome Admin!");
	private final JLabel label1 = new JLabel("Please select town to register");
	private final JLabel label2 = new JLabel("Enter name of the resort");
	private final JTextField field = new JTextField();
	private final JButton display = new JButton("Display");
	private final ButtonGroup group = new ButtonGroup();
	private final List<TownHolder> townHolders;

	TownRegister(final UserDto userDto, final ResortService resortService) {
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

		this.townHolders.forEach(townHolder -> {
			group.add(townHolder.button());
		});

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
			.forEach(townHolder -> {
				frame.add(townHolder.button());
			});
		frame.add(label2);
		frame.add(label);
		frame.add(label1);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(500, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == display) {
			String resortName = field.getText();
			Optional<TownHolder> selectedTownHolderOptional = this.townHolders.stream()
					.filter(townHolder -> townHolder.button().isSelected())
					.findFirst();
			selectedTownHolderOptional.ifPresent(townHolder -> {
				int townId = townHolder.townId();
			    long resortId = this.resortService.createResort(new CreateResortDto(resortName, this.userDto.getId(), townId, Instant.now()));
				
				JOptionPane.showMessageDialog(null, "Information successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed to Register Information Fill up?", "Confirmation", JOptionPane.YES_NO_OPTION);
				frame.dispose();
				group.clearSelection();
				field.setText(null);
				
				if (choice == JOptionPane.YES_OPTION) {
					new ResortInfo(frame, userDto.getId(), resortId, resortName, this.resortService);
				} else {
					frame.dispose();
					BiFunction<UserDto, Long, Town> townToOpen = townHolder.town();
					townToOpen.apply(this.userDto, resortId);
				}
			});
		}
	}
	
	private List<TownHolder> townHolders() {
		JRadioButton carcarButton = new JRadioButton("Carcar"); // id: 1
		carcarButton.setBounds(75, 120, 200, 70);
		carcarButton.setFocusable(false);
		carcarButton.addActionListener(this);
		carcarButton.setOpaque(false);
		BiFunction<UserDto, Long, Town> carcarTown = (userDto, resortId) -> new Carcar(userDto, frame, resortId);
		TownHolder carcarTownHolder = new TownHolder(1, carcarButton, carcarTown);

		JRadioButton bariliButton = new JRadioButton("Barili"); // id: 2
		bariliButton.setBounds(135, 120, 200, 70);
		bariliButton.setFocusable(false);
		bariliButton.addActionListener(this);
		bariliButton.setOpaque(false);
		BiFunction<UserDto, Long, Town> bariliTown = (userDto, resortId) -> new Carcar(userDto, frame, resortId);
		TownHolder bariliTownHolder = new TownHolder(2, bariliButton, bariliTown);

		JRadioButton moalboalButton = new JRadioButton("Moalboal"); // id: 3
		moalboalButton.setBounds(190, 120, 200, 70);
		moalboalButton.setFocusable(false);
		moalboalButton.addActionListener(this);
		moalboalButton.setOpaque(false);
		BiFunction<UserDto, Long, Town> moalboalTown = (userDto, resortId) -> new Carcar(userDto, frame, resortId);
		TownHolder moalboalTownHolder = new TownHolder(3, moalboalButton, moalboalTown);
		
		JRadioButton alcoyButton = new JRadioButton("Alcoy"); // id: 4
		alcoyButton.setBounds(265, 120, 200, 70);
		alcoyButton.setFocusable(false);
		alcoyButton.addActionListener(this);
		alcoyButton.setOpaque(false);
		BiFunction<UserDto, Long, Town> alcoyTown = (userDto, resortId) -> new Carcar(userDto, frame, resortId);
		TownHolder alcoyTownHolder = new TownHolder(4, alcoyButton, alcoyTown);

		JRadioButton santanderButton = new JRadioButton("SanTander"); // id: 5
		santanderButton.setBounds(320, 120, 200, 70);
		santanderButton.setFocusable(false);
		santanderButton.addActionListener(this);
		santanderButton.setOpaque(false);
		BiFunction<UserDto, Long, Town> santanderTown = (userDto, resortId) -> new Carcar(userDto, frame, resortId);
		TownHolder santanderTownHolder = new TownHolder(5, santanderButton, santanderTown);

		JRadioButton oslobButton = new JRadioButton("Oslob"); // id: 6
		oslobButton.setBounds(200, 160, 150, 30);
		oslobButton.setFocusable(false);
		oslobButton.addActionListener(this);
		oslobButton.setOpaque(false);
		BiFunction<UserDto, Long, Town> oslobTown = (userDto, resortId) -> new Carcar(userDto, frame, resortId);
		TownHolder oslobTownHolder = new TownHolder(6, oslobButton, oslobTown);
		
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
