package project;

import java.awt.EventQueue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import project.ui.LaunchPage;
import project.util.AppUtils;

public class FirstPagePrototype {

	public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get(AppUtils.UPLOADED_IMAGE_STRING));
		EventQueue.invokeLater(LaunchPage::new);
		//EventQueue.invokeLater(LoginFrame::new);
	}

}
