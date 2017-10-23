package tatai.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SpeechRecognition {
	
	private static String _path = UserConstants.getWorkingDirectory();
	
	/**
	 * Uses Bash terminal to execute the SpeechRecognition Script
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String runVoiceRecognition() throws IOException, InterruptedException {

		try {
			ProcessBuilder _builder = new ProcessBuilder("/bin/bash","-c","./GoSpeech");
			Process process = _builder.start();
			process.waitFor();


		}
		catch (Exception e) {
			e.printStackTrace();
		}



		return getResults();
		
//		
//		int i = (int) (10 * Math.random());
//		if (i == 0) {
//			i = 1;
//			
//		}
//		return translation(i);


	}
	
	/**
	 * Returns the words recognised by GoSpeech
	 * @return
	 * @throws FileNotFoundException
	 */
	private static String getResults() throws FileNotFoundException {

		String result = "";

		File file;

		file = new File(_path.concat("/recout.mlf"));
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
	
	public static void playback() {
		try {
			ProcessBuilder _play = new ProcessBuilder("/bin/bash", "-c", "aplay foo.wav");
			Process process = _play.start();
			process.waitFor();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

        public static void removeAudioFile() {
		try {
			ProcessBuilder _play = new ProcessBuilder("/bin/bash", "-c", "rm foo.wav");
			Process process = _play.start();
			process.waitFor();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Translates a number into the Maori spelling for said number for comparison when checking against the 
	 * speech recognitions result.
	 * @param i
	 * @return
	 */
	public static String translation(int i) {

		String Num;
		String j;
		String k;

		List<String> Maori = new ArrayList<String>(); {
			Maori.addAll(Arrays.asList("tahi","rua", "toru", "whaa", "rima", "ono", "whitu", "waru", "iwa", "tekau"));
		}

		if (i < 11) {
			Num = Maori.get(i-1);
		}
		else if ((i > 10) && (i%10 == 0)) {
			j = Maori.get((i/10)-1);
			Num = j + " tekau";
		}
		else if ((i > 10) && (i < 20)) {
			k = Maori.get((i%10)-1);
			Num = "tekau maa " + k;
		}
		else {
			j = Maori.get((i/10)-1);
			k = Maori.get((i%10)-1);
			Num = j + " tekau maa " + k;
		}
		return Num;
	}

}
