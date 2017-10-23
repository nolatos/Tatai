package tatai;

public enum Difficulty {

	ONE, TWO, THREE, FOUR, FIVE;
	
	/**
	 * Shows a level up
	 * @return
	 */
	public Difficulty levelUp() {
		switch (this) {
		case ONE: 
			return TWO;
		case TWO:
			return THREE;
		case THREE: 
			return FOUR;
		case FOUR:
			return FIVE;
		default:
			return null;
		}
	}
}
