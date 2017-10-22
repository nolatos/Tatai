package tatai.math;

import java.util.*;

public class AdvancedGenerator implements MathGenerator {
	
	private int _answer;
	private List<Integer> _possibleAnswers = new ArrayList<Integer>();
	
	public AdvancedGenerator() {
		_possibleAnswers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 13, 27));
	}

	@Override
	/**
	 * Returns a String representing a URL of an image to be displayed
	 */
	public String generateQuestion() {
		int i = (int)(_possibleAnswers.size() * Math.random());
		_answer = _possibleAnswers.get(i);
		String str = getClass().getResource("" + _answer + ".png").toString();
		return str;
	}

	@Override
	public int getAnswer() {
		return _answer;
	}

}
