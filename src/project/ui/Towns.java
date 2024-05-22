package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.UserDto;
import project.ui.town.Alcoy;
import project.ui.town.Barili;
import project.ui.town.Carcar;
import project.ui.town.Moalboal;
import project.ui.town.Oslob;
import project.ui.town.SanTander;

public class Towns extends JFrame implements ActionListener { // Prompts after user log in
	private final UserDto userDto;
	private final JFrame frame = new JFrame("Where to?");
	private final JLabel townLabel = new JLabel("TOWN");
	private final JButton carcarButton = new JButton("Carcar"); // CARCAR BUTTON id: 3
	private final JButton bariliButton = new JButton("Barili");// Barili BUTTON id: 2
	private final JButton moalBoalButton = new JButton("Moalboal");// Moalboal BUTTON id: 4
	private final JButton alcoyButton = new JButton("Alcoy");// Alcoy BUTTON id: 1
	private final JButton sanTanderButton = new JButton("SanTander");// SAN TANDER BUTTON id: 6
	private final JButton oslobButton = new JButton("Oslob");// OSLOB BUTTON id: 5
	private final JButton backButton = new JButton("BACK");// EXIT BUTTON
	private final JFrame customerMenuFrame;
	private String closeSource;

	public Towns(JFrame customerMenuFrame, UserDto userDto) {
		this.customerMenuFrame = customerMenuFrame;
		this.userDto = userDto;
		// Set logo to the frame
		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);
		// Add components to the frame

		townLabel.setBounds(200, 35, 200, 125);
		townLabel.setFont(new Font("+", Font.PLAIN, 28));

		carcarButton.setBounds(70, 180, 150, 40);
		carcarButton.setFocusable(false);
		carcarButton.addActionListener(this);

		bariliButton.setBounds(250, 180, 150, 40);
		bariliButton.setFocusable(false);
		bariliButton.addActionListener(this);

		moalBoalButton.setBounds(70, 240, 150, 40);
		moalBoalButton.setFocusable(false);
		moalBoalButton.addActionListener(this);

		alcoyButton.setBounds(250, 240, 150, 40);
		alcoyButton.setFocusable(false);
		alcoyButton.addActionListener(this);

		sanTanderButton.setBounds(70, 300, 150, 40);
		sanTanderButton.setFocusable(false);
		sanTanderButton.addActionListener(this);

		oslobButton.setBounds(250, 300, 150, 40);
		oslobButton.setFocusable(false);
		oslobButton.addActionListener(this);

		backButton.setBounds(160, 370, 150, 40);
		backButton.setFocusable(false);
		backButton.addActionListener(this);

		frame.setLocation(300, 250);
		frame.add(carcarButton);
		frame.add(backButton);
		frame.add(oslobButton);
		frame.add(sanTanderButton);
		frame.add(alcoyButton);
		frame.add(moalBoalButton);
		frame.add(bariliButton);
		frame.add(townLabel);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(500, 550);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"carcarButton".equals(closeSource) && !"bariliButton".equals(closeSource)
						&& !"moalBoalButton".equals(closeSource) && !"alcoyButton".equals(closeSource)
						&& !"sanTanderButton".equals(closeSource) && !"oslobButton".equals(closeSource)) {
					customerMenuFrame.setVisible(true);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == carcarButton) {
			this.closeSource = "carcarButton";
			frame.dispose();
			new Carcar(this.userDto, frame);
		} else if (e.getSource() == bariliButton) {
			this.closeSource = "bariliButton";
			frame.dispose();
			new Barili(this.userDto, frame);
		} else if (e.getSource() == moalBoalButton) {
			this.closeSource = "moalBoalButton";
			frame.dispose();
			new Moalboal(this.userDto, frame);
		} else if (e.getSource() == alcoyButton) {
			this.closeSource = "alcoyButton";
			frame.dispose();
			new Alcoy(this.userDto, frame);
		} else if (e.getSource() == sanTanderButton) {
			this.closeSource = "sanTanderButton";
			frame.dispose();
			new SanTander(this.userDto, frame);
		} else if (e.getSource() == oslobButton) {
			this.closeSource = "oslobButton";
			frame.dispose();
			new Oslob(this.userDto, frame);
		} else {
			this.closeSource = "unknown";
			frame.dispose();
			customerMenuFrame.setVisible(true);
		}
	}
}
