package project.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class Checkin extends JFrame implements ActionListener{
	JDatePickerImpl datePicker;
	JFrame frame = new JFrame("Check in");
	JLabel label = new JLabel("CHECK IN");
	JLabel label1 = new JLabel("Enter days to Stay");
	JLabel label2 = new JLabel("Enter date");
	JTextField stayTextField = new JTextField();
	JButton exit = new JButton("EXIT");
	JButton next = new JButton("NEXT");
	
public Checkin(){
	
		next.setBounds(250,285,75,25);
		next.setFocusable(false);
		next.addActionListener(this);
		next.setOpaque(false);
			
		exit.setBounds(100,285,75,25);
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setOpaque(false);
	
		label2.setBounds(100,170,800,80);
		label2.setFont(new Font("Times New Roman",Font.BOLD,15));
	
		stayTextField.setBounds(100, 125, 150, 25);
		stayTextField.setPreferredSize(new Dimension(150, 100));
		
		label1.setBounds(100,75,800,80);
		label1.setFont(new Font("Times New Roman",Font.BOLD,15));
	
		label.setBounds(180,15,800,80);
	    label.setFont(new Font("Times New Roman",Font.BOLD,25));
	
		ImageIcon icon = new ImageIcon("beach2.png");
		 
		ImageIcon background = new ImageIcon("figma.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(500,500, Image.SCALE_AREA_AVERAGING);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 500,500);
	
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl panel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(panel, new AbstractFormatter() {
			
			@Override
			public String valueToString(Object value) throws ParseException {
				// TODO Auto-generated method 
				if (value != null) {
					
				Calendar calendar = (Calendar) value;
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
				String strDate = format.format(calendar.getTime());
				
					return strDate;
				}
				return "";
			}
			
			@Override
			public Object stringToValue(String text) throws ParseException {
				// TODO Auto-generated method stub
				return ""	;
			}
		});
		frame.setLayout(null);
		
		
		datePicker.setBounds(100,220, 200, 30); // Adjust these values as needed
		frame.add(next);
		frame.add(exit);
		frame.add(label2);
		frame.add(stayTextField);
		frame.add(label1);
		frame.add(label);
        frame.add(datePicker);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
		frame.pack();
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==exit) {
		System.exit(0);
	}
	else if(e.getSource()==next) {
		CustomerInformation window = new CustomerInformation();
	}
	
}}
