package project.ui.town;

import project.dto.ResortDto;
import project.dto.UserDto;
import project.service.ResortService;
import project.service.impl.DefaultResortService;
import project.ui.ResortView;
import project.util.AppUtils;
import project.util.ResortViewEvent;

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

public class Moalboal implements Town {
	private final int townId = 3;
	private final UserDto userDto;
	private final ResortService resortService;
	private final List<ResortDto> resortDtos;
	private final JFrame frame = new JFrame("Moalboal");
	private final JFrame parentFrame;
	private final JButton back = new JButton("Back");
	private final List<String> windowEventSources = new ArrayList<>();
	private String windowEventSource = "";

	public Moalboal(UserDto userDto, JFrame parentFrame) {
		this(userDto, parentFrame, null);
	}

	public Moalboal(UserDto userDto, JFrame parentFrame, Long resortId) {
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
					parentFrame.setVisible(true);
				}
			}
		});
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
				new ResortView(frame, ResortViewEvent.CUSTOMER_VIEW, this.resortService, this.userDto, resortDto.id());
			});
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
			if (AppUtils.isUserTypeAdmin(userTypeId)) {
				if (resortId != null) {
					return this.resortService.getResortById(resortId)
							.map(List::of)
							.orElse(List.of());
				} else {
					return this.resortService.getResortByUserIdAndTownId(this.userDto.getId(), this.townId)
							.map(List::of)
							.orElse(List.of());
				}
			} else if (AppUtils.isUserTypeCustomer(userTypeId)) {
				return this.resortService.getResortsByTownId(this.townId);
			} else {
				return List.of();
			}
		}

		return List.of();
	}
}
