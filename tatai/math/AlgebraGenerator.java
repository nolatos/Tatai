package tatai.math;

import tatai.Difficulty;

/**
 * Generates linear and quadratic equations
 * @author olive
 *
 */
public class AlgebraGenerator implements MathGenerator {
	
//	private static final String PI = ;
	
	private int _answer;

	public Question generateQuestion() {
		double probability = Math.random();
		String str;
		if (probability < 0.5) {
			//Advanced quadratic			
			int a = (int) (5 * Math.random() + 2);
			int number = (int) (10 * Math.random());
			_answer = (int) (10 * Math.random());
			filterAnswer();
			int b = number - a * _answer;
			int c = - number * _answer;
			
			String B;
			String C;
			
			//Filtering the coefficients
			if (b < -1) {
				B = "" + b + "x";
			}
			else if (b == 0) {
				B = "";
			}
			else if (b == 1) {
				B = "+x";
			}
			else if (b == -1) {
				B = "-x";
			}
			else {
				B = "+" + b + "x";
			}
			
			if (c == 0) {
				C = "";
			}
			else if (c > 0){
				C = "+" + c;
			}
			else {
				C = "" + c;
			}
			
			str = "" + a + "x\u00B2" + B + C + "=0";
			Question question = new Question(_answer, str, Difficulty.FOUR);
			return question;
		}
		else if (probability < 0.75) {
			int number = (int) (10 * Math.random());
			_answer = (int) (10 * Math.random());
			filterAnswer();
			int b = number - _answer;
			int c = - number * _answer;
			String B;
			String C;
			
			//Filtering the coefficients
			if (b < -1) {
				B = "" + b + "x";
			}
			else if (b == 0) {
				B = "";
			}
			else if (b == 1) {
				B = "+x";
			}
			else if (b == -1) {
				B = "-x";
			}
			else {
				B = "+" + b + "x";
			}
			
			if (c == 0) {
				C = "";
			}
			else if (c > 0) {
				C = "+" + c;
			}
			else {
				C = "" + c;
			}
			
			str = "x\u00B2" + B + C + "=0";
			Question question = new Question(_answer, str, Difficulty.FOUR);
			return question;
		}
		else {
			
			_answer = (int) (99 * Math.random());
			int a = (int) (20 * Math.random() + 2);
			filterAnswer();
			
			int number = a * _answer;
			int b;
			double probabilityTwo = Math.random();
			
			if (probabilityTwo < 0.5) {
				b = (int)(-100 * (Math.random()));
			}
			else {
				b = (int)(100 * (Math.random()));
			}
			
			int c = _answer * a + b;
			
			String B;
			String C;
			
			//Filtering the coefficients
			if (b < -1) {
				B = "" + b;
			}
			else if (b == 0) {
				B = "";
			}
			else {
				B = "+" + b;
			}
			
			C = "=" + c;
			
			String result = "" + a + "x" + B + C;
			Question question = new Question(_answer, result, Difficulty.FOUR);
			return question;
		}
	}
	

	
	/**
	 * Filters the answer so it is between 1 and 99 inclusive
	 */
	private void filterAnswer() {
		if (_answer <= 0) {
			_answer = 1;
		}
		
		if (_answer >= 100) {
			_answer = 99;
		}
	}
}
