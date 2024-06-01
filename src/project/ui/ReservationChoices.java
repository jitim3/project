package project.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import project.dao.entity.CommissionRate;
import project.dto.ResortDto;

public class ReservationChoices implements ActionListener {
	private final long userId;
	private final ResortDto resortDto;
	private final CommissionRate commissionRate;
	private final JFrame frame = new JFrame("Select reservation choice");
	private final JButton dailyUseButton = new JButton("DAILY USE");
	private final JButton overnightButton = new JButton("OVERNIGHT");
	private final JButton backButton = new JButton("BACK");
	private final JFrame parentFrame;
	private String windowEventSource = "";
	private final JFrame customerMenuFrame;

	public ReservationChoices(JFrame customerMenuFrame, JFrame parentFrame, long userId, ResortDto resortDto, CommissionRate commissionRate) {
		this.customerMenuFrame = customerMenuFrame;
		this.parentFrame = parentFrame;
		this.userId = userId;
		this.resortDto = resortDto;
		this.commissionRate = commissionRate;
		
		dailyUseButton.setBounds(172, 150, 150, 35);
		dailyUseButton.setFocusable(false);
		dailyUseButton.addActionListener(this);
		dailyUseButton.setOpaque(false);

		overnightButton.setBounds(172, 225, 150, 35);
		overnightButton.setFocusable(false);
		overnightButton.addActionListener(this);
		overnightButton.setOpaque(false);

		backButton.setBounds(190, 300, 115, 25);
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		backButton.setOpaque(false);

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500, 500, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500, 500);

		frame.add(backButton);
		frame.add(overnightButton);
		frame.add(dailyUseButton);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!"dailyUseButton".equalsIgnoreCase(windowEventSource) && !"overnightButton".equalsIgnoreCase(windowEventSource) && !"backButton".equals(windowEventSource)) {
					customerMenuFrame.setVisible(true);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dailyUseButton) {
			this.windowEventSource = "dailyUseButton";
			frame.dispose();
			new DisplayCottageResort(customerMenuFrame, userId, resortDto, commissionRate);
		} else if (e.getSource() == overnightButton) {
			this.windowEventSource = "overnightButton";
			frame.dispose();
			new DisplayRoomResort(customerMenuFrame, userId, resortDto, commissionRate);
		} else if (e.getSource() == backButton) {
			this.windowEventSource = "backButton";
			frame.dispose();
			parentFrame.setVisible(true);
		}
	}
}
