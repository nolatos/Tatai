package tatai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BashHandler {

	private ProcessBuilder _builder;
	private  String _command;
	private  String _Path = Paths.get("").toAbsolutePath().normalize().toString();
	private  String _PathOfOutput = _Path.replace("Tatai", "recout.mlf"); 

	


	/**
	 * Runs the voice recognition and returns the result
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public String runVoiceRecognition() throws IOException, InterruptedException {
		int i = (int) (10 * Math.random());
		if (i == 0) {
			i = 1;
		}
		return translation(i);


	}

	private String getResults() throws FileNotFoundException {

		String result = "";

		File file;

		file = new File(_PathOfOutput);
		Scanner sc = new Scanner(file);

		boolean first = true;
		boolean detected = false;
		while (sc.hasNextLine()) {
			String str = sc.nextLine();

			if (detected && str.compareTo("sil") != 0) {
				if (first) {
					result = result + str;
					first = false;
				}
				else {
					result = result + " " + str;
				}
			}

			if (detected && str.compareTo("sil") == 0) {
				break;
			}

			if (str.compareTo("sil") == 0) {
				detected = true;
			}
		}
		sc.close();
		return result;
	}

	//	/**
	//	 * Changes directory MaoriNumbers and then can be combined with other bash commands as needed
	//	 * @param str
	//	 */
	//	private void command(String str) {
	//		_command = "cd " + _Path + "; " + "cd ..; " + str + "; ";
	//	}

	public Path getPath() {
		Path path = Paths.get("TataiFiles/Images").toAbsolutePath().normalize();
		return path;
	}

	public String translation(int i) {

		String Num;
		String j;
		String k;

		List<String> Maori = new ArrayList<String>(); {
			Maori.addAll(Arrays.asList("tahi","rua", "toru", "wha", "rima", "ono", "whitu", "waru", "iwa", "tekau"));
		}

		if (i < 11) {
			Num = Maori.get(i-1);
		}
		else if ((i > 10) && (i%10 == 0)) {
			j = Maori.get((i/10)-1);
			Num = j + " tekau";
		}
		else {
			j = Maori.get((i/10)-1);
			k = Maori.get((i%10)-1);
			Num = j + " tekau maa " + k;
		}
		return Num;
	}
}
