package tatai.math;

import javafx.beans.property.SimpleStringProperty;
import tatai.Difficulty;
import tatai.utils.SpeechRecognition;

public class Question {

	private final int _answer;
	private final String _question;
	private Difficulty _difficulty;

	private final SimpleStringProperty answerString;
	private final SimpleStringProperty questionString;
	private final SimpleStringProperty recognisedString = new SimpleStringProperty("skipped");

	/**
	 * Copnstructor takes in a String and an answer as question
	 * @param answer
	 * @param question
	 */
	public Question(int answer, String question, Difficulty difficulty) {
		_answer = answer;
		_question = question;

		//Makoing the String equal to "advanced", because otherwise too hard to determine
		answerString = new SimpleStringProperty(SpeechRecognition.translation(_answer));
		if (difficulty == Difficulty.FIVE) {
			questionString = new SimpleStringProperty("advanced");
		}
		else {
			questionString = new SimpleStringProperty(_question);
		}
	}
	
	/**
	 * Constructor makes the question and answer the same
	 * @param answer
	 * @param difficulty
	 */
	public Question(int answer, Difficulty difficulty) {
		this(answer, "" + answer, difficulty);
	}

	/**
	 * Sets the recognised String
	 * @param recognised
	 */
	public void setRecognisedString(String recognised) {
		recognisedString.set(recognised);;
	}

	public String getRecognisedString() {
		return recognisedString.get();
	}
	
	
	public void setAnswerString(String answer) {
		answerString.set(answer);
	}
	
	/**
	 * Returns the answer as a String
	 * @return
	 */
	public String getAnswerString() {
		return answerString.get();
	}

	
	public void setQuestionString(String question) {
		questionString.set(question);
	}
	
	/**
	 * Returns the question as a String. If level five, it 
	 * will return an empty String
	 * @return
	 */
	public String getQuestionString() {
		return questionString.get();
	}

	
	
	public int getAnswer() {
		return _answer;
	}
	
	public String getQuestion() {
		return _question;
	}
}
