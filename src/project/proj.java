package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class proj extends JFrame {

	 private String adminInput;

	    public proj() {
	        setTitle("Role Selection");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(300, 200);
	        setLocationRelativeTo(null); // Center the frame on the screen

	        // Create buttons for each role
	        JButton customerButton = new JButton("Customer");
	        JButton adminButton = new JButton("Admin");
	        JButton superAdminButton = new JButton("Super Admin");

	        // Add action listeners to the buttons
	        customerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Open CustomerViewFrame to view admin input
	                CustomerViewFrame customerViewFrame = new CustomerViewFrame(adminInput);
	                customerViewFrame.setVisible(true);
	            }
	        });

	        adminButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Open AdminInputFrame for admin to input information
	                AdminInputFrame adminInputFrame = new AdminInputFrame(proj.this);
	                adminInputFrame.setVisible(true);
	            }
	        });

	        superAdminButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Perform actions relevant to the super admin role
	                JOptionPane.showMessageDialog(proj.this, "You clicked Super Admin Button");
	            }
	        });

	        // Create a panel to hold the buttons
	        JPanel panel = new JPanel(new GridLayout(3, 1));
	        panel.add(customerButton);
	        panel.add(adminButton);
	        panel.add(superAdminButton);

	        // Add the panel to the frame
	        add(panel);

	        setVisible(true);
	    }

	    public void setAdminInput(String input) {
	        this.adminInput = input;
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new proj();
	            }
	        });
	    }
	}

	class AdminInputFrame extends JFrame {
	    private proj parentFrame;
	    private JTextField textField;

	    public AdminInputFrame(proj parentFrame) {
	        this.parentFrame = parentFrame;

	        setTitle("Admin Input");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(300, 100);
	        setLocationRelativeTo(parentFrame); // Center the frame relative to parent frame

	        // Create components
	        JLabel label = new JLabel("Enter Information:");
	        textField = new JTextField(20);
	        JButton submitButton = new JButton("Submit");

	        // Add action listener to the submit button
	        submitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Get the text from the text field and pass it to the RoleSelectionFrame
	                String input = textField.getText();
	                parentFrame.setAdminInput(input);
	                dispose(); // Close the AdminInputFrame
	            }
	        });

	        // Create a panel to hold the components
	        JPanel panel = new JPanel();
	        panel.add(label);
	        panel.add(textField);
	        panel.add(submitButton);

	        // Add the panel to the frame
	        add(panel);

	        setVisible(true);
	    }
	}

	class CustomerViewFrame extends JFrame {
	    public CustomerViewFrame(String adminInput) {
	        setTitle("Customer View");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(300, 100);
	        setLocationRelativeTo(null); // Center the frame on the screen

	        // Create components
	        JLabel label = new JLabel("Admin Input: " + adminInput);
	        JButton viewButton = new JButton("View Information");

	        // Add action listener to the view button
	        viewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(CustomerViewFrame.this, "Admin Input: " + adminInput);
	            }
	        });

	        // Create a panel to hold the components
	        JPanel panel = new JPanel();
	        panel.add(label);
	        panel.add(viewButton);

	        // Add the panel to the frame
	        add(panel);

	        setVisible(true);
	    }
	}
