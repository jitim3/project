package project.ui.town;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.service.impl.DefaultResortService;
import project.ui.DisplayFrame;
import project.util.UserTypes;

public class Alcoy implements Town {
	private final int townId = 4;
	private final UserDto userDto;
	private final ResortService resortService;
	private final List<ResortDto> resortDtos;
	private JFrame frame = new JFrame("Alcoy");
	private JFrame parentFrame;
	private JButton back = new JButton("Back");

	public Alcoy(UserDto userDto, JFrame parentFrame) {
		this(userDto, parentFrame, null);
	}

	public Alcoy(UserDto userDto, JFrame parentFrame, Long resortId) {
		this.userDto = userDto;
		this.resortService = new DefaultResortService();
		this.parentFrame = parentFrame;

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

		frame.add(back);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				parentFrame.setVisible(true);
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
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
		for (ResortDto resortDto : this.resortDtos) {
			JButton resortButton = new JButton(resortDto.name());
			resortButton.setBounds(50, y, 400, 75);
			resortButton.setOpaque(false);
			resortButton.setFocusable(false);
			resortButton.addActionListener(e -> new DisplayFrame(this.resortService, this.userDto.getId(), resortDto.id()));
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
					return this.resortService.getResortByUserIdAndTownId(this.userDto.getId(), this.townId)
							.map(List::of)
							.orElse(List.of());
				}
			} else if (UserTypes.CUSTOMER.id() == userTypeId) {
				// only approved resort available to customers
				return this.resortService.getResortsByTownId(this.townId, true);
			} else {
				return List.of();
			}
		}

		return List.of();
	}
}