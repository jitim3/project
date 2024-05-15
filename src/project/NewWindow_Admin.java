package project;
import javax.swing.*;


import project.Towns.Alcoy;
import project.Towns.Barili;
import project.Towns.Carcar;
import project.Towns.Moalboal;
import project.Towns.Oslob;
import project.Towns.SanTander;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class NewWindow_Admin implements ActionListener{
	
	JFrame frame = new JFrame("Admin");
	JLabel label = new JLabel();
	JButton button = new JButton("Log in");
	JButton button1 = new JButton("Sign up");
	JButton button2 = new JButton("EXIT");
	Connection conn;
	PreparedStatement psInsert;
	Statement stmt;
	
	NewWindow_Admin(){
		
		try {
		    String url = "jdbc:mysql://localhost:3306/";
		    String dbName = "admin_resorts";
		    String driver = "com.mysql.cj.jdbc.Driver";
		    String userName = "root";
		    String password = "";
		    Class.forName(driver);
		    conn = DriverManager.getConnection(url + dbName, userName, password);

		    // Prepare the statement for inserting data into the database
		    psInsert = conn.prepareStatement("INSERT INTO resorts (resort_name) VALUES (?)");
		    stmt = conn.createStatement();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		//Import logo for the Customer frame
		ImageIcon icon = new ImageIcon("beach2.png");
				
				
		//Import background for the Customer frame
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);
		
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350,300);
		frame.setIconImage(icon.getImage());
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.add(backgroundLabel);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);		
		button.setBounds(75,50,200,40);
		button.setFocusable(false);
		button.addActionListener(this);
		
		button1.setBounds(75,100,200,40);
		button1.setFocusable(false);
		button1.addActionListener(this);
		
		button2.setBounds(75,150,200,40);
		button2.setFocusable(false);
		button2.addActionListener(this);
		
		
	}
	
	 @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == button) {
	            frame.dispose();
	            adminDatabaseLogin AdminlogInwindow = new adminDatabaseLogin();
	        } else if (e.getSource() == button1) {
	            frame.dispose();
	            adminDatabaseSignup AdminsignUpwindow = new adminDatabaseSignup();
	        } else {
	            frame.dispose();
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }
	    }
	 
	class adminDatabaseLogin extends JFrame implements ActionListener{
		
		JFrame frame = new JFrame("Admin Log in");
		private JTextField usernameField;
	    private JPasswordField passwordField;
	    private Connection conn;
	    private PreparedStatement psInsert;
	    private Statement stmt;
		
		adminDatabaseLogin() {			

	        ImageIcon icon = new ImageIcon("beach2.png");
	        
	        ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 350, 300);

	        JLabel usernameLabel = new JLabel("Username:");
	        usernameField = new JTextField();
	        JLabel passwordLabel = new JLabel("Password:");
	        passwordField = new JPasswordField();
	        JButton exitButton = new JButton("Exit");
	        
	        JButton loginButton = new JButton("Log in");
	        
	        usernameLabel.setBounds(35,40,150,80);
	        usernameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        
	        usernameField.setBounds(125,68,150,25);
			usernameField.setPreferredSize(new Dimension(100,70));
			
			passwordLabel.setBounds(35,90,150,80);
	        passwordLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        
	        passwordField.setBounds(125,118,150,25);
			passwordField.setPreferredSize(new Dimension(100,70));
			
			loginButton.setBounds(50,185,100,30);
	        loginButton.setFocusable(false);
	        loginButton.addActionListener(this);
	        
	        exitButton.setBounds(180,185,100,30);
	        exitButton.setFocusable(false);
	        exitButton.addActionListener(this);
	        
	        frame.add(usernameLabel);
	        frame.add(usernameField);
	        frame.add(passwordLabel);
	        frame.add(passwordField);
	        frame.add(loginButton);
	        frame.add(exitButton);
	        frame.setIconImage(icon.getImage());
	        frame.add(backgroundLabel);
	        frame.setResizable(false);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(350,300);
	        frame.setLocationRelativeTo(null);
	        

	        

	        // Database connection setup
	        try {
	            String url = "jdbc:mysql://localhost:3306/";
	            String dbName = "admin account";
	            String driver = "com.mysql.cj.jdbc.Driver";
	            String userName = "root";
	            String password = "";
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            

	            psInsert = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
	            stmt = conn.createStatement();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Exit")) { // Check if the action command is "Exit"
	            System.exit(0); // Exit the program
	        } else if (e.getActionCommand().equals("Log in")) {
	        	boolean loginSuccessful = false;
	            while (!loginSuccessful) {
	            String username = usernameField.getText();
	            String password = new String(passwordField.getPassword());
	            try {
	                String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
	                ResultSet rs = stmt.executeQuery(sql);
	                if (rs.next()) {
	                    JOptionPane.showMessageDialog(this, "Login successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
	                    loginSuccessful = true;
						menuAdmin menuAdmin = new menuAdmin();
	                     frame.dispose();
	                    
	                } else {
	                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                
	            }
	         }
	     }
     }			
 }
	 class adminDatabaseSignup extends JFrame implements ActionListener{
		
		JFrame frame = new JFrame("Admin Sign up");
		private JTextField usernameField;
	    private JPasswordField passwordField;
	    private Connection conn;
	    private PreparedStatement psInsert;
	    private Statement stmt;
	    private JButton signUpButton;
	    private JButton exitButton;
	    
		public adminDatabaseSignup() {
			
	        
	              
	        ImageIcon icon = new ImageIcon("beach2.png");
	        
	        ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 350, 300);

	        JLabel usernameLabel = new JLabel("Username:");
	        usernameField = new JTextField();
	        JLabel passwordLabel = new JLabel("Password:");
	        passwordField = new JPasswordField();
	        exitButton = new JButton("Exit");
	        signUpButton = new JButton("Sign up");
	        
	        usernameLabel.setBounds(35,40,150,80);
	        usernameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        
	        usernameField.setBounds(125,70,150,25);
			usernameField.setPreferredSize(new Dimension(100,70));
			
			passwordLabel.setBounds(35,90,150,80);
	        passwordLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        
	        passwordField.setBounds(125,118,150,25);
			passwordField.setPreferredSize(new Dimension(100,70));
	        
	        signUpButton.setBounds(50,185,100,30);
	        signUpButton.setFocusable(false);
	        signUpButton.addActionListener(this);
	        
	        exitButton.setBounds(180,185,100,30);
	        exitButton.setFocusable(false);
	        exitButton.addActionListener(this);
	        
	        
	        
	        
	        
	        
	        frame.setLayout(null);	                   
	        frame.add(usernameField);
	        frame.add(passwordLabel);
	        frame.add(passwordField);
	        frame.add(signUpButton);
	        frame.add(exitButton);   
	        frame.add(usernameLabel);
	        frame.add(backgroundLabel);
	        frame.setIconImage(icon.getImage());  
	        frame.setSize(350,300);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        try {
	            String url = "jdbc:mysql://localhost:3306/";
	            String dbName = "admin account";
	            String driver = "com.mysql.cj.jdbc.Driver";
	            String userName = "root";
	            String password = "";
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url + dbName, userName, password);

	            psInsert = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
	            stmt = conn.createStatement();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exitButton) {
	            frame.dispose();
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Close the sign-up window when the "Exit" button is clicked
	        } else if (e.getSource() == signUpButton) {
	            String username = usernameField.getText();
	            String password = new String(passwordField.getPassword());
	            try {
	                String checkUsernameQuery = "SELECT * FROM users WHERE username = ?";
	                PreparedStatement checkUsernameStatement = conn.prepareStatement(checkUsernameQuery);
	                checkUsernameStatement.setString(1, username);
	                ResultSet rs = checkUsernameStatement.executeQuery();
	                if (rs.next()) {
	                    JOptionPane.showMessageDialog(this, "Error: Username already exists. Please choose a different username.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                psInsert.setString(1, username);
	                psInsert.setString(2, password);
	                int rowsAffected = psInsert.executeUpdate();
	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(this, "Account created successfully!", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
	                    frame.dispose();
	                    adminDatabaseLogin window = new adminDatabaseLogin();
	                } else {
	                    JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
			
		}
	
	 }
	 public class townRegister implements ActionListener{
		 
		 JFrame frame = new JFrame ("Select Town to Register");
		 JLabel label = new JLabel ("Welcome Admin!");
		 JLabel label1 = new JLabel("Please select town to register");
		 JLabel label2 = new JLabel("Enter name of the resort");
		 JRadioButton button = new JRadioButton("Carcar");
		 JRadioButton button1 = new JRadioButton("Barili");
		 JRadioButton button2 = new JRadioButton("Moalboal");
		 JRadioButton button3 = new JRadioButton("Alcoy");
		 JRadioButton button4 = new JRadioButton("SanTander");
		 JRadioButton button5 = new JRadioButton("Oslob");
		 JTextField field = new JTextField();
		 JButton display = new JButton("Display");
		 public String resortName;
		 townRegister(){
			 
			 
			 
			 ImageIcon background = new ImageIcon("beach3.jpg");
			 Image backgroundImage = background.getImage().getScaledInstance(500, 550, Image.SCALE_DEFAULT);
			 JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			 backgroundLabel.setBounds(0, 0, 500, 550);
			 
			 ImageIcon icon = new ImageIcon("beach2.png");
			
			 button.setBounds(75,120,200,70);
			 button.setFocusable(false);
			 button.addActionListener(this);
			 button.setOpaque(false);
			 
			 button1.setBounds(135,120,200,70);
			 button1.setFocusable(false);
			 button1.addActionListener(this);
			 button1.setOpaque(false);
			 
			 button2.setBounds(190,120,200,70);
			 button2.setFocusable(false);
			 button2.addActionListener(this);
			 button2.setOpaque(false);
			 
			 button3.setBounds(265,120,200,70);
			 button3.setFocusable(false);
			 button3.addActionListener(this);
			 button3.setOpaque(false);
			 
			 button4.setBounds(320,120,200,70);
			 button4.setFocusable(false);
			 button4.addActionListener(this);
			 button4.setOpaque(false);
			 
			 button5.setBounds(200,160,150,30);
			 button5.setFocusable(false);
			 button5.addActionListener(this);
			 button5.setOpaque(false);
			 
			 display.setBounds(200,280,150,30);
			 display.setFocusable(false);
			 display.addActionListener(this);

			 ButtonGroup group = new ButtonGroup();
			 group.add(button);
			 group.add(button1);
			 group.add(button2);
			 group.add(button3);
			 group.add(button4);
			 group.add(button5);
			 
			 label2.setBounds(30,190,200,125);
			 label2.setFont(new Font("+",Font.PLAIN,12));
			 label2.setForeground(Color.BLACK);
			
			 label1.setBounds(121,35,350,125);
			 label1.setFont(new Font("+",Font.PLAIN,18));
			 label1.setForeground(Color.BLACK);
			 
			 label.setBounds(141,25,300,100);
			 label.setFont(new Font("+",Font.BOLD,26));
			 label.setForeground(Color.BLACK);
			 
			 field.setBounds(180,243,200,20);
			 field.setPreferredSize(new Dimension(50,190));
			 
			 frame.setLayout(null);
			 frame.add(field);
			 frame.add(display);
			 frame.add(button5);
			 frame.add(button4);
			 frame.add(button3);
			 frame.add(button2);
			 frame.add(button1);
			 frame.add(button);
			 frame.add(label2);
			 frame.add(label);
			 frame.add(label1);
			 frame.setIconImage(icon.getImage());
			 frame.add(backgroundLabel);
			 frame.setVisible(true);
			 frame.setSize(500,400);
			 frame.setResizable(false);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
		 }

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == display) {
				resortName = field.getText();	       
						try {
		            // Insert resort name into the database
		            psInsert.setString(1, resortName);
		            psInsert.executeUpdate();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
						System.out.println(resortName);
		        if (button.isSelected()) {
		        	JOptionPane.showMessageDialog(null,"Information successfully added.","Success",JOptionPane.INFORMATION_MESSAGE);
		        	frame.dispose();
		        	Carcar carcar = new Carcar(resortName);
		        	Carcar.generateButton(carcar.getFrame(), resortName);
		        } else if (button1.isSelected()) {
		        	JOptionPane.showMessageDialog(null,"Information successfully added.","Success",JOptionPane.INFORMATION_MESSAGE);
		        	frame.dispose();
		        	Barili barili = new Barili();
		        	Barili.generateButton(barili.getFrame(), resortName);
		        } else if (button2.isSelected()) {	
		        	JOptionPane.showMessageDialog(null,"Information successfully added.","Success",JOptionPane.INFORMATION_MESSAGE);
		        	frame.dispose();
		        	Moalboal moalboal = new Moalboal();
		        	Moalboal.generateButton(moalboal.getFrame(), resortName);
		        } else if (button3.isSelected()) {	
			        JOptionPane.showMessageDialog(null,"Information successfully added.","Success",JOptionPane.INFORMATION_MESSAGE);
			        frame.dispose();
			        Alcoy alcoy = new Alcoy();
			        Alcoy.generateButton(alcoy.getFrame(), resortName);
		        } else if (button4.isSelected()) {
		        	JOptionPane.showMessageDialog(null,"Information successfully added.","Success",JOptionPane.INFORMATION_MESSAGE);
			        frame.dispose();
			        SanTander santander = new SanTander();
			        SanTander.generateButton(santander.getFrame(), resortName); 
		        } else if (button5.isSelected()) {
		        	JOptionPane.showMessageDialog(null,"Information successfully added.","Success",JOptionPane.INFORMATION_MESSAGE);
			        frame.dispose();
			        Oslob oslob = new Oslob();
			        //Oslob.generateButton(oslob.getFrame(), resortName); 
		        }
		        
			}

		        
			}
		
	        
	       
			
	 }
	 class menuAdmin implements ActionListener{
		 JFrame frame = new JFrame("Menu");
		 JButton button = new JButton("Register resort");
		 JButton button1 = new JButton("View registered resort");
		 JButton button2 = new JButton("EXIT");
		 
		 menuAdmin(){
			 
			 ImageIcon icon = new ImageIcon("beach2.png");
			 
			 
			 
			 ImageIcon background = new ImageIcon("beach3.jpg");
			 Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
			 JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			 backgroundLabel.setBounds(0, 0, 350, 300);
			 
			 button.setBounds(75,50,200,40);
			 button.setFocusable(false);
			 button.addActionListener(this);
				
			 button1.setBounds(75,100,200,40);
			 button1.setFocusable(false);
			 button1.addActionListener(this);
				
			 button2.setBounds(75,150,200,40);
			 button2.setFocusable(false);
			 button2.addActionListener(this);
			
			frame.add(button);
			frame.add(button1);
			frame.add(button2);
			frame.setResizable(false);
			frame.add(backgroundLabel);
			frame.setIconImage(icon.getImage());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(350,300);
			frame.setLayout(null);
			frame.setVisible(true);
		 }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) { //For the Log in menu
				frame.dispose();
				townRegister townregister = new townRegister();
			}else if (e.getSource()==button1) { //For the sign up menu
				 try {
	                    String[] registeredResorts = getRegisteredResorts();
	                    viewResort viewResortFrame = new viewResort(registeredResorts);
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
			} else {
				frame.dispose();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}	
		
	 }
	 private String[] getRegisteredResorts() throws SQLException {
	        String[] resorts = null;
	        ResultSet rs = stmt.executeQuery("SELECT resort_name FROM resorts");
	        rs.last();
	        int numRows = rs.getRow();
	        rs.beforeFirst();

	        resorts = new String[numRows];
	        int i = 0;
	        while (rs.next()) {
	            resorts[i] = rs.getString("resort_name");
	            i++;
	        }
	        return resorts;
	    }
	 class viewResort {
	        JFrame frame = new JFrame("View Resort");

	        viewResort(String[] registeredResorts) {
	            frame.setLayout(new GridLayout(registeredResorts.length, 1));

	            for (String resortName : registeredResorts) {
	                JLabel resortLabel = new JLabel(resortName);
	                frame.add(resortLabel);
	            }

	            frame.setSize(500, 550);
	            frame.setVisible(true);
	            frame.setResizable(false);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }
	    }
	}