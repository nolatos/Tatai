package tatai.math;

import tatai.Difficulty;
import tatai.models.Play;

/**
 * Class designed for creating arithmetic questions
 * @author olive
 *
 */
public class ArithmeticGenerator implements MathGenerator {
	
	private final static String MULTIPLICATION_SYMBOL = "×";
	private final static String DIVISION_SYMBOL = "÷";
	private final static String ADDITION_SYMBOL = "+";
	private final static String SUBTRACTION_SYMBOL = "-";
	
	private int _answer;

	/**
	 * Returns a String which represents an equation. 
	 */
	public Question generateQuestion() {
		_answer = (int) (99 * Math.random());
		filterAnswer();
		String str;
		double probability = Math.random();
		if (probability < 0.25) {
			//Addition
			int number1 = (int)(_answer * Math.random());
			int number2 = _answer - number1;
			str =  "" + number1 + " " + ADDITION_SYMBOL + " " + number2;
		}
		else if (probability < 0.5) {
			//Subtraction
			int number1 = (int) (150 * Math.random());
			int number2 = number1 + _answer;
			str = "" + number2 + " " + SUBTRACTION_SYMBOL + " " + number1;
		}
		else if (probability < 0.75) {
			//Multiplication
			int number1 = (int) (20 * Math.random());
			
			//Making sure we don't divide by zero
			if (number1 <= 0) {
				number1 = 1;
			}
			
			int number2 = (int) ((99 / number1 - 1) * Math.random());
			
			if (number2 <= 0) {
				number2 = 1;
			}
			
			_answer = number1 * number2;
			str = "" + number1 + " " + MULTIPLICATION_SYMBOL + " " + number2;
		}
		else {
			
			//Division			
			int number1 = (int) ((300 / _answer) * Math.random());
			
			//Make sure we don't divide by zero
			if (number1 == 0) {
				number1 = 1;
			}
			int number2 = number1 * _answer;
			str = "" + number2 + " " + DIVISION_SYMBOL + " " + number1;
		}
		Question question = new Question(_answer, str, Difficulty.THREE);
		return question;
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
