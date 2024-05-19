package project.ui.town;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.service.impl.DefaultResortService;
import project.ui.Towns;
import project.util.UserTypes;

public class Oslob implements Town {
	private final UserDto userDto;
	private final int townId = 6;
	private final ResortService resortService;
	private final List<ResortDto> resortDtos;
	private JFrame frame = new JFrame("Oslob");
	private JFrame townsJFrame;
	private JButton back = new JButton("Back");

	public Oslob(UserDto userDto, JFrame townsJFrame) {
		this(userDto, (Long) null);
		this.townsJFrame = townsJFrame;
	}

	public Oslob(UserDto userDto, Long resortId) {
		this.userDto = userDto;
		this.resortService = new DefaultResortService();
		this.resortDtos = this.getRegisteredResorts(resortId);
		this.generateButton();
		
		back.setBounds(370, 420, 100, 25);
		back.setFocusable(false);
		back.addActionListener(this);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);

		frame.setLocation(300, 250);
		frame.add(back);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			frame.dispose();
			if (this.townsJFrame != null) {
				this.townsJFrame.setVisible(true);
			} else {
				new Towns(this.userDto);
			}
		}
	}

	@Override
	public void generateButton() {
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 600);

		int y = 65;
		for (ResortDto resortDto : this.resortDtos) {
			JButton resortButton = new JButton(resortDto.name());
			resortButton.setBounds(50, y, 400, 75);
			resortButton.setOpaque(false);
			resortButton.setFocusable(false);
			frame.getContentPane().add(resortButton);
			frame.add(resortButton);

			y = y + 85;
		}

		frame.setLayout(null);
		frame.revalidate();
		frame.repaint();
	}

	private List<ResortDto> getRegisteredResorts(Long resortId) {
		if (this.userDto != null) {
			int userTypeId = this.userDto.getUserType().id();
			if (UserTypes.ADMIN.id() == userTypeId) {
				if (resortId != null) {
					return this.resortService.getResortById(resortId)
							.map(List::of)
							.orElse(List.of());
				} else {
					return this.resortService.getResortsByUserIdAndTownId(this.userDto.getId(), this.townId);
				}
			} else if (UserTypes.CUSTOMER.id() == userTypeId) {
				return this.resortService.getResortsByTownId(this.townId);
			} else {
				return List.of();
			}
		}

		return List.of();
	}
}