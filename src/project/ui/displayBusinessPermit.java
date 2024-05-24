package project.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class displayBusinessPermit extends JFrame implements ActionListener {
	private final long resortId;
	JFrame frame = new JFrame("BUSINESS PERMIT");
	JLabel label = new JLabel("UPLOADED DOCUMENTS");
	JButton approve = new JButton("APPROVE");
	JButton decline = new JButton("DECLINE");

	public displayBusinessPermit(long resortId, String imageBusniessPermitPath) {
		this.resortId = resortId;

		decline.setBounds(350, 425, 150, 25);
		decline.setFocusable(false);
		decline.addActionListener(this);
		decline.setOpaque(false);

		approve.setBounds(75, 425, 150, 25);
		approve.setFocusable(false);
		approve.addActionListener(this);
		approve.setOpaque(false);

		label.setBounds(180, 50, 800, 80);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));

		JLabel imageLabel = new JLabel(); // IMPORT THE RESORT JFILECHOOSER FROM FILL UP FORM
		imageLabel.setBounds(40, 130, 500, 250); // Set bounds as per your requirement
		ImageIcon imageIcon = new ImageIcon(imageBusniessPermitPath);
		Image img = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
				Image.SCALE_AREA_AVERAGING);
		imageLabel.setIcon(new ImageIcon(img));

		ImageIcon icon = new ImageIcon("beach2.png");

		ImageIcon background = new ImageIcon("figmapic.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 750, 750);

		frame.add(decline);
		frame.add(approve);
		frame.add(label);
		frame.add(imageLabel);
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.add(backgroundLabel);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == approve) {
			JOptionPane.showMessageDialog(null, "The resort has been approved", "SUCCESS",
					JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
		} else if (e.getSource() == decline) {
			JOptionPane.showMessageDialog(null, "The resort has been declined", "DECLINED", JOptionPane.ERROR_MESSAGE);
		}

	}
}
