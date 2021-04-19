package code;

import java.util.ArrayList;
import java.util.List;

public class BinaryGap {

	public static void main(String[] args) {
		
		System.out.println("Send a number to Solution class");
		
		Solution solution = new Solution();

		// Different tests:
		Integer number = 5;
//		number = 15; // (1111), Expected = 0
//		number = 0000000; // (0000000), Expected = 0
//		number = 1; // (1), Expected = 0
		number = 1041; // 10000010001, Expected = 5
//		number = 9; // (1001), Expected = 2
//		number = 529; //  = (1000010001), Expected = 4
//		number = 51272; //  (1100100001001000), Expected = 4
//		number = 15; //  (1111), Expected = 0
//		number = 53; //  (110101), Expected = 1
//		number = 2147483647; //  (1111111111111111111111111111111), Expected = 0
//		number = 2107383147; //  (1111101100111000001110101101011), Expected = 0
//		number = 2147483648; //  (10000000000000000000000000000000), Expected = 0, but don't compile.
//		number = 0; //  (0), Expected = 0
//		number = -1; //  (null), Expected = 0
//		number = "A"; //  (null), Expected = 0, but don't compile.
//		number = null; //  (null), Expected = 0
		
		int result = solution.solution(number);

		if (result == 0) {
			System.out.println("There isn't binary gap in this number");
		}
		if (result != 0) {
			System.out.println("Largest binary gap : " + result);
		} 
	}
}

class Solution {
	
	List<Integer> gaps = new ArrayList<>();
	
	int result = 0;

	public int solution(Integer N) {
		
		System.out.println("Number to check: " + N);
		
		//Tests if our value is an integer - N >= 1 && N <= 2147483647
		if (N instanceof Integer && N >= 1 && N <= 2147483647) {
			
			System.out.println("The number N is positive and integer");
	
			// Convert N to Binary
			String number = Integer.toBinaryString(N);

			List<Integer> binaryArray = new ArrayList<>();

			int tamanho = number.length();
			
			for (int i = 0; i < tamanho; i++) {
				binaryArray.add(Integer.parseInt(String.valueOf(number.charAt(i))));
			}
			
			System.out.println("BinaryArray: " + binaryArray);

			// Send binary number to recursive method
			result = getGaps(binaryArray);
			System.out.println("Gaps: " + gaps);
	
			return result;
		}
		return result;
	}

	private int getGaps(List<Integer> binaryArray) {

		// finding the first one via its index
		int firstOne = binaryArray.indexOf(1);

		// new array created taking a slice of original array
		// from the index of the firstOne + 1 index
		List<Integer> newBinaryArray = new ArrayList<>();
		newBinaryArray = binaryArray.subList(firstOne + 1, binaryArray.size());

		// finding second one via its index in new array slice
		int secondOne = newBinaryArray.indexOf(1);
		
		if (secondOne > -1 ) {
			
			gaps.add(secondOne);
	
			System.out.println("NewBinaryArray: " + newBinaryArray);
	
			List<Integer> saveNewBinaryArray = new ArrayList<>();
			saveNewBinaryArray = newBinaryArray.subList(secondOne, newBinaryArray.size());
	
			System.out.println("SaveNewBinaryArray: " + saveNewBinaryArray);
	
			getGaps(saveNewBinaryArray);
			
		}
		result = gaps.stream()
				.mapToInt(Integer::intValue)
				.max().getAsInt();
		return result;
	}
}

