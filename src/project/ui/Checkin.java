package project.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class Checkin {
	private final JFrame frame = new JFrame("Check in");
	private final JDatePickerImpl datePicker;

	public Checkin() {
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl panel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(panel, new AbstractFormatter() {
			@Override
			public String valueToString(Object value) throws ParseException {
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
				return "";
			}
		});
		frame.add(datePicker);
		frame.setSize(500, 500);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
