package quickcheck;

public class StringGenerator extends Generator<String>{
	
	private static final int DEFAULT_MAX_STR_SIZE = 10;
	
	private int size;
	
	public StringGenerator() {
		this(DEFAULT_MAX_STR_SIZE );
	}

	public StringGenerator(int maxStrSize) {
		this.size = maxStrSize;
	}
	
	/**
	 * Converts a integer to a String of a character
	 * @param num a integer between 1 and 26
	 * @return the corresponding letter A -> Z
	 */
	private String intToStrCharacter(final int num) {
		char s = (char)('a' + (num - 1));
		return  Character.toString(s);         
	}
	
	private int randomInt(final int min, final int max)  {
		return random.nextInt(max) + min;
	}
	
	/**
	 * Returns a random non capital String of characters from a -> z
	 * @param length of the random string, 0 -> ""
	 * @return a String of the characters from a -> z
	 */
	private String randomString(int length) {
		String result = "";
		for (int i = 1; i <= length;i++) {
			String str = intToStrCharacter(randomInt(1,26));
			result = result + str;
		}
		return result;
	}

	@Override
	public String next() {
		return randomString(randomInt(0,this.size));
	}

}
