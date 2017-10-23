package tatai.utils;

import java.nio.file.Paths;

public class UserConstants {
	
	public static final String PATH = Paths.get("").toAbsolutePath().normalize().toString();
	public static final String USER_PATH = PATH.concat("/TataiFiles/Users");
	public static final String FACTS_PATH = PATH.concat("/TataiFiles/MaoriFacts/Maori.txt");
	
	public static String getWorkingDirectory() {
		return PATH;
	}
	
	public static String getUserPath() {
		return USER_PATH;
	}
	
	public static String getFactsPath() {
		return FACTS_PATH;
	}
	

}
