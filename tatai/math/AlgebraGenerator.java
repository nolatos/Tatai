package tatai.math;

/**
 * Generates linear and quadratic equations
 * @author olive
 *
 */
public class AlgebraGenerator implements MathGenerator {
	
//	private static final String PI = ;
	
	private int _answer;

	public String generateQuestion() {
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
			return str;
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
			return str;
		}
		else {
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
			return str;
		}
	}
	
	public int getAnswer() {
		return _answer;
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
