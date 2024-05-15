package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;



public class NewWindow_Customer implements ActionListener{
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	JButton button = new JButton("Log in");
	JButton button1 = new JButton("Sign up");
	JButton button2 = new JButton("EXIT");
	

	NewWindow_Customer(){
		//Import logo for the Customer frame
		ImageIcon icon = new ImageIcon("beach2.png");
		
		
		//Import background for the Customer frame
		ImageIcon background = new ImageIcon("beach3.jpg");
		Image backgroundImage = background.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, 350, 300);
		//==> Setting up the components
		label.setBounds(150,50,150,100);
		label.setFont(new Font("Times New Roman",Font.PLAIN,20));
		
		frame.setLocation(300, 250);
		frame.add(label);
		frame.setTitle("Selection tab");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350,300);
		frame.setLayout(null);
		frame.setVisible(true);
		
		frame.setIconImage(icon.getImage());
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		frame.add(backgroundLabel);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) { //For the Log in menu
			frame.dispose();
			databaseLogin logInwindow = new databaseLogin();
		}else if (e.getSource()==button1) { //For the sign up menu
			frame.dispose();
			databaseSignup signUpwindow = new databaseSignup();
		}else {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
class databaseSignup extends JFrame implements ActionListener { // Sign up Menu
	
	JFrame frame = new JFrame("User Sign up");
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection conn;
    private PreparedStatement psInsert;
    private Statement stmt;
    private JButton signUpButton;
    private JButton exitButton;

    databaseSignup() {

  
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
        
        
        
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signUpButton);
        frame.add(exitButton);
        frame.setIconImage(icon.getImage());
        frame.add(backgroundLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(350, 300);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    	frame.setResizable(false);

        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "projdatabase";
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
            dispose(); // Close the sign-up window when the "Exit" button is clicked
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
                    databaseLogin window = new databaseLogin();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
		
class databaseLogin extends JFrame implements ActionListener {
	
	private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection conn;
    private PreparedStatement psInsert;
    private Statement stmt;
    private Towns townsFrame;
    private JFrame successFrame;
  
    databaseLogin() {
    	
        frame = new JFrame("User Log in");
        
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
        exitButton.addActionListener(this);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        successFrame = new JFrame("Successful Login"); //HIMOAN UG CLASS FOR DESIGN
        JButton okButton = new JButton("OK");
        okButton.setBounds(150, 100, 100, 30);
        okButton.addActionListener(e -> {
            successFrame.dispose(); // Close the success frame
            frame.dispose(); // Close the login frame
            townsFrame = new Towns(); // Open the Towns frame
        });
        successFrame.add(okButton);
        successFrame.setSize(400, 200);
        successFrame.setLayout(null);
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setResizable(false);
        successFrame.setLocationRelativeTo(null);

        // Database connection setup
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "projdatabase";
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

    public void actionPerformed(ActionEvent e) {
    	
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Login")) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    successFrame.setVisible(true); // Show the success frame
                } else {
                	 
                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                	 }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    	//new databaseLogin();
    	//new Towns();

        SwingUtilities.invokeLater(databaseSignup::new);
        

    }
}

  class Towns extends JFrame implements ActionListener { // Prompts after user log in
	 
	 JFrame frame = new JFrame("Where to?");
	 JLabel label = new JLabel("TOWN");
	 JButton button = new JButton("Carcar"); //CARCAR BUTTON
	 JButton button1 = new JButton("Barili");//Barili BUTTON
	 JButton button2 = new JButton("Moalboal");//Moalboal BUTTON
	 JButton button3 = new JButton("Alcoy");//Alcoy BUTTON	
	 JButton button4 = new JButton("SanTander");//SAN TANDER BUTTON
	 JButton button5 = new JButton("Oslob");//OSLOB BUTTON
	 JButton button6 = new JButton("EXIT");//EXIT BUTTON
	 //
	 Towns(){
		 
		 //Set logo to the frame
		 ImageIcon icon = new ImageIcon("beach2.png");
		 
		 
		 ImageIcon background = new ImageIcon("beach3.jpg");
		 Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
		 JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		 backgroundLabel.setBounds(0, 0, 500, 600);
		 //Add components to the frame
		 
		 
		
		 label.setBounds(200,35,200,125);
		 label.setFont(new Font("+",Font.PLAIN,28));
		 
		 
		 button.setBounds(70,180,150,40);
		 button.setFocusable(false);
		 button.addActionListener(this);
		 
		 button1.setBounds(250,180,150,40);
		 button1.setFocusable(false);
		 button1.addActionListener(this);
		 
		 button2.setBounds(70,240,150,40);
		 button2.setFocusable(false);
		 button2.addActionListener(this);
		 
		 button3.setBounds(250,240,150,40);
		 button3.setFocusable(false);
		 button3.addActionListener(this);
		 
		 button4.setBounds(70,300,150,40);
		 button4.setFocusable(false);
		 button4.addActionListener(this);
		 
		 button5.setBounds(250,300,150,40);
		 button5.setFocusable(false);
		 button5.addActionListener(this);
		 
		 button5.setBounds(250,300,150,40);
		 button5.setFocusable(false);
		 button5.addActionListener(this);
		 
		 button6.setBounds(160,370,150,40);
		 button6.setFocusable(false);
		 button6.addActionListener(this);
		 
		 frame.setLocation(300, 250);
		 frame.add(button);
		 frame.add(button6);
		 frame.add(button5);
		 frame.add(button4);
		 frame.add(button3);
		 frame.add(button2);
		 frame.add(button1);
		 frame.add(label);
		 frame.setIconImage(icon.getImage());
		 frame.add(backgroundLabel);
		 frame.setSize(500,550);
		 frame.setVisible(true);
		 frame.setResizable(false);
		 
	 }
	 
	 public JFrame getFrame() {
	        return frame;
	 }
	 
	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==button) {
			 frame.dispose();
			 Carcar window = new Carcar(resortName);
		 }else if (e.getSource()==button1) {
			 frame.dispose();
			 Barili window = new Barili();
		 }else if (e.getSource()==button2) {
			 frame.dispose();
			 Moalboal window = new Moalboal();
		 }else if (e.getSource()==button3) {
			 frame.dispose();
			 Alcoy window = new Alcoy();

		 }else if (e.getSource()==button4) {
			 frame.dispose();
			 SanTander window = new SanTander();
		 }
		 else if (e.getSource()==button5){
			frame.dispose();
			Oslob window = new Oslob();
			 
		 }else {
			 frame.dispose();
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 }
		 
	 }
	 public static class Carcar implements ActionListener{ //CARCAR FRAME
		 
		 JFrame frame = new JFrame("Carcar");
		 public JButton back = new JButton("Back");
		 
		
		  public Carcar(String resortName){
			//this.resortName = resortName;
			generateButton(frame, resortName);
			frame.setLocationRelativeTo(null);
			
			back.setBounds(370,420,100,25);
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
			frame.setVisible(true);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			
		}
		  public JFrame getFrame() {
		        return frame;
		  }


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==back) {
				frame.dispose();
				new Towns();
			}
			
		}


		public static void generateButton(JFrame frame, String resortName) {
		
	        JButton resortButton = new JButton(resortName);
	        ImageIcon background = new ImageIcon("beach3.jpg");
	        Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
	        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
	        backgroundLabel.setBounds(0, 0, 500, 600);

	        resortButton.setBounds(50,65,400,75);
	        resortButton.setOpaque(false);
	        resortButton.setFocusable(false);

	        frame.getContentPane().add(resortButton);
	        frame.add(resortButton);
	        frame.setLayout(null);
	        frame.revalidate();
	        frame.repaint();
	        frame.setVisible(true);
	    }
	}
	
	 public static class Barili implements ActionListener{
		 JFrame frame = new JFrame("Barili");
		 JButton button = new JButton("Back");
		 
		 Barili(){
			 
			button.setBounds(370,420,100,25);
			button.setFocusable(false);
			button.addActionListener(this);
			
			 
			ImageIcon icon = new ImageIcon("beach2.png");
				
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			frame.setLocation(300, 250);
			frame.add(button);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
		 }
		 public JFrame getFrame() {
		        return frame;
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
			frame.dispose();
			Towns window = new Towns();
			
		}
	 }
		public static void generateButton(JFrame frame,String resortName) {
			
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			JButton resortButton = new JButton(resortName);
	        resortButton.setBounds(50,65,400,75);
	        resortButton.setOpaque(false);
	        resortButton.setFocusable(false);
	        frame.setContentPane(backgroundLabel);
	        frame.getContentPane().add(resortButton);
	        frame.add(resortButton);
	        frame.setLayout(null);
	        frame.revalidate();
	        frame.repaint();
	        frame.setVisible(true);

			
		}
		
	}
	 public static class Moalboal implements ActionListener{
		 JFrame frame = new JFrame("Moalboal");
		 JButton button = new JButton("Back");
		 
		 Moalboal(){
			button.setBounds(370,420,100,25);
			button.setFocusable(false);
			button.addActionListener(this);
			
			ImageIcon icon = new ImageIcon("beach2.png");
			
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			frame.setLocation(300, 250);
			frame.add(button);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
		 }
		 public JFrame getFrame() {
		        return frame;
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				frame.dispose();
				Towns window = new Towns();
		}
		 
		 
	 }
			public static void generateButton(JFrame frame,String resortName) {
			
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			JButton resortButton = new JButton(resortName);
	        resortButton.setBounds(50,65,400,75);
	        resortButton.setOpaque(false);
	        resortButton.setFocusable(false);
	        frame.setContentPane(backgroundLabel);
	        frame.getContentPane().add(resortButton);
	        frame.add(resortButton);
	        frame.setLayout(null);
	        frame.revalidate();
	        frame.repaint();
	        frame.setVisible(true);

			
		}
	}
	 public static class Alcoy implements ActionListener{
		 JFrame frame = new JFrame("Alcoy");
		 JButton button = new JButton("Back");
	
		 Alcoy(){
			button.setBounds(370,420,100,25);
			button.setFocusable(false);
			button.addActionListener(this);
				
			ImageIcon icon = new ImageIcon("beach2.png");
				
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			frame.setLocation(300, 250);
			frame.add(button);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 
		 }
		 public JFrame getFrame() {
		        return frame;
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				frame.dispose();
				Towns window = new Towns();
			
		 }
		}
			public static void generateButton(JFrame frame,String resortName) {
			
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			JButton resortButton = new JButton(resortName);
	        resortButton.setBounds(50,65,400,75);
	        resortButton.setOpaque(false);
	        resortButton.setFocusable(false);
	        frame.setContentPane(backgroundLabel);
	        frame.getContentPane().add(resortButton);
	        frame.add(resortButton);
	        frame.setLayout(null);
	        frame.revalidate();
	        frame.repaint();
	        frame.setVisible(true);

			
		}
	 }
	 public static class SanTander implements ActionListener{
		 JFrame frame = new JFrame("San Tander");
		 JButton button = new JButton("Back");
	
		SanTander(){
			button.setBounds(370,420,100,25);
			button.setFocusable(false);
			button.addActionListener(this);
				
			ImageIcon icon = new ImageIcon("beach2.png");
				
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			frame.setLocation(300, 250);
			frame.add(button);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		 public JFrame getFrame() {
		        return frame;
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				frame.dispose();
				Towns window = new Towns();
			
		 }
		}
			public static void generateButton(JFrame frame,String resortName) {
			
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			JButton resortButton = new JButton(resortName);
	        resortButton.setBounds(50,65,400,75);
	        resortButton.setOpaque(false);
	        resortButton.setFocusable(false);
	        frame.setContentPane(backgroundLabel);
	        frame.getContentPane().add(resortButton);
	        frame.add(resortButton);
	        frame.setLayout(null);
	        frame.revalidate();
	        frame.repaint();
	        frame.setVisible(true);

			
		}
	 }
	 public static class Oslob implements ActionListener{
		 JFrame frame = new JFrame("Oslob");
		 JButton button = new JButton("Back");
	
		 Oslob(){
			button.setBounds(370,420,100,25);
			button.setFocusable(false);
			button.addActionListener(this);
					
			ImageIcon icon = new ImageIcon("beach2.png");
					
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
				
			frame.setLocation(300, 250);
			frame.add(button);
			frame.setIconImage(icon.getImage());
			frame.add(backgroundLabel);
			frame.setVisible(true);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 }
		 public JFrame getFrame() {
		        return frame;
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
			frame.dispose();
			Towns window = new Towns();
			
			}
		}
		public static void generateButton(JFrame frame,String resortName) {
			
			ImageIcon background = new ImageIcon("beach3.jpg");
			Image backgroundImage = background.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0, 0, 500, 600);
			
			JButton resortButton = new JButton(resortName);
	        resortButton.setBounds(50,65,400,75);
	        resortButton.setOpaque(false);
	        resortButton.setFocusable(false);
	        
	        frame.setContentPane(backgroundLabel);
	        frame.getContentPane().add(resortButton);
	        frame.add(resortButton);
	        frame.setLayout(null);
	        frame.revalidate();
	        frame.repaint();
	        frame.setVisible(true);
	        
	 }
	 }
	 
  }
  
  
 
 
