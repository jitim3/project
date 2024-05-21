package project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

public class AppUtils {
	public static final String UPLOADED_IMAGE = "./uploaded_images/";
	
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
	
	public static boolean isUserTypeCustomer(int userTypeId) {
		return UserTypes.CUSTOMER.id() == userTypeId;
	}
}
