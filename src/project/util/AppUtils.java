package project.util;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.InternationalFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.IntStream;

public class AppUtils {
	public static final String UPLOADED_IMAGE = "./uploaded_images/";
	public static final String DATE_FORMAT = "dd-MMM-yyyy";
	
	private AppUtils() {
	}
	
	public static <T> List<T> reverse(final List<T> list) {
	    final int last = list.size() - 1;
	    return IntStream.rangeClosed(0, last)
	        .map(i -> (last - i))
	        .mapToObj(list::get)
	        .toList();
	}
	
	public static Optional<String> generateFilename(File file) {
		if (file != null) {
			String oldFilename = file.getName();
	        String extension = oldFilename.substring(oldFilename.lastIndexOf(".") + 1);
	        return Optional.of(UUID.randomUUID().toString() + "." + extension);
		}
		
		return Optional.empty();
	}
	
	public static void saveImage(File file, String newFilename) throws IOException {
		if (file != null && newFilename != null && !newFilename.isBlank()) {
	        Path path = Paths.get(UPLOADED_IMAGE + newFilename);
	        
	        byte[] byteArray = new byte[(int) file.length()];
	        try (FileInputStream inputStream = new FileInputStream(file)) {
	            inputStream.read(byteArray);
	            Files.write(path, byteArray);
	        }
		}
	}
	
	public static Optional<String> imagePath(String filename) {
		if (filename != null && !filename.isBlank()) {
			String file = UPLOADED_IMAGE + filename;
			Path path = Paths.get(file);
			if (Files.exists(path)) {
				return Optional.of(file);
			}
		}
		
		return Optional.empty();
	}

	public static boolean isUserTypeSuperAdmin(int userTypeId) {
		return UserTypes.SUPER_ADMIN.id() == userTypeId;
	}

	public static boolean isUserTypeAdmin(int userTypeId) {
		return UserTypes.ADMIN.id() == userTypeId;
	}

	public static boolean isUserTypeCustomer(int userTypeId) {
		return UserTypes.CUSTOMER.id() == userTypeId;
	}

	public static void currency(JFormattedTextField field) {
		field.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
			@Override
			public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
				NumberFormat format = NumberFormat.getInstance();
				format.setMinimumFractionDigits(2);
				format.setMaximumFractionDigits(2);
				format.setRoundingMode(RoundingMode.HALF_UP);
				InternationalFormatter formatter = new InternationalFormatter(format);
				formatter.setAllowsInvalid(false);
				return formatter;
			}
		});
	}

	public static void numeric(JTextField field) {
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});
	}

	public static JDatePickerImpl createJDatePicker(String dateFormat) {
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl panel = new JDatePanelImpl(model, p);
		return new JDatePickerImpl(panel, new JFormattedTextField.AbstractFormatter() {
			@Override
			public String valueToString(Object value) {
				if (value != null) {
					Calendar calendar = (Calendar) value;
					String format = (dateFormat == null || dateFormat.isBlank()) ? DATE_FORMAT : dateFormat;
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
					return simpleDateFormat.format(calendar.getTime());
				}

				return "";
			}

			@Override
			public Object stringToValue(String text) {
				return "";
			}
		});
	}

	public static BigDecimal computeRateWithCommissionFee(BigDecimal productRate, BigDecimal commissionRate) {
		if (productRate == null) {
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		if (commissionRate == null) {
			return productRate;
		}

		// ex: 5.00 / 100.00 + 1 = 1.05
		var percentage = commissionRate.divide(BigDecimal.valueOf(100), new MathContext(2))
				.add(BigDecimal.ONE)
				.setScale(2, RoundingMode.HALF_UP);

		// ex: 500 * 1.05 = 525.00
		return productRate.multiply(percentage)
				.setScale(2, RoundingMode.HALF_UP);
	}
}
