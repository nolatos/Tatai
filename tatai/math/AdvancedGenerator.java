package tatai.math;

import java.util.*;

import tatai.Difficulty;

public class AdvancedGenerator implements MathGenerator {
	
	private int _answer;
	private List<Integer> _possibleAnswers = new ArrayList<Integer>();
	
	public AdvancedGenerator() {
		_possibleAnswers.addAll(Arrays.asList(1, 10, 11, 12, 13, 2, 27, 3, 4, 5, 56, 6, 7, 8, 9));
	}

	@Override
	/**
	 * Returns a String representing a URL of an image to be displayed
	 */
	public Question generateQuestion() {
		int i = (int)(_possibleAnswers.size() * Math.random());
		_answer = _possibleAnswers.get(i);
		String str = getClass().getResource("" + _answer + ".png").toString();
		Question question = new Question(_answer, str, Difficulty.FIVE);
		return question;
	}

	public String getWorking() {
		String str = getClass().getResource("working/" + _answer + ".png").toString();
		return str;
	}

}
