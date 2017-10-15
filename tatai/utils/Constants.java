package tatai.utils;

import java.nio.file.Paths;

public class Constants {
	
	public static final String PATH = Paths.get("").toAbsolutePath().normalize().toString();
	public static final String USER_PATH = PATH.concat("/TataiFiles/Users");
	public static final String IMAGE_PATH = PATH.concat("/TataiFiles/Images");
	
	public static String getWorkingDirectory() {
		return PATH;
	}
	
	public static String getUserPath() {
		return USER_PATH;
	}
	
	public static String getImagePath() {
		return IMAGE_PATH;
	}
	

}
