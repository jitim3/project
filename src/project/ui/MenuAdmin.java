package project.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.service.impl.DefaultResortService;

public class MenuAdmin implements ActionListener {
	private final UserDto userDto;
	private final ResortService resortService;
	JFrame frame = new JFrame("Menu");
	JButton button = new JButton("Register resort");
	JButton button1 = new JButton("View registered resort");
	JButton button2 = new JButton("EXIT");

	public MenuAdmin(UserDto userDto) {
		this.userDto = userDto;
		this.resortService = new DefaultResortService();

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);

		button.setBounds(75, 50, 200, 40);
		button.setFocusable(false);
		button.addActionListener(this);

		button1.setBounds(75, 100, 200, 40);
		button1.setFocusable(false);
		button1.addActionListener(this);

		button2.setBounds(75, 150, 200, 40);
		button2.setFocusable(false);
		button2.addActionListener(this);

		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.setResizable(false);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) { // For the Log in menu
			frame.dispose();
			new TownRegister(this.userDto, this.resortService);
		} else if (e.getSource() == button1) { // For the sign up menu
			List<ResortDto> registeredResorts = this.resortService.getResortsByUserId(this.userDto.getId());
			new ViewResort(registeredResorts);
		} else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
