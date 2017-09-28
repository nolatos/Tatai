package tatai;

import java.util.*;

public class BashHandler {

	public String translation(int i) {
		String Num;
		String j;
		String k;
		
		
		List<String> Maori = new ArrayList<String>(); {
			Maori.addAll(Arrays.asList("tahi","rua", "toru", "wha", "rima", "ono", "whitu", "waru", "iwa", "tekau"));
		}
		
		if (i < 11) {
			Num = Maori.get(i - 1);
		}
		else if ((i > 10) && (i % 10 == 0)) {
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
	
	public String runVoiceRecognition() {
		int i = (int) (10 * Math.random());
		if (i == 0) {
			i = 1;
		}
		return translation(i);
	}
	
}
