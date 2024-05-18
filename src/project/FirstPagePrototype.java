package project;

import java.awt.EventQueue;

import project.ui.LaunchPage;
import project.ui.LoginFrame;

public class FirstPagePrototype {

	public static void main(String[] args) {		
//		EventQueue.invokeLater(LaunchPage::new);
		EventQueue.invokeLater(LoginFrame::new);
	}

}
