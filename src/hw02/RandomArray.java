package hw02;

import java.util.stream.IntStream;

public class RandomArray {
	public int[] createArray(int len,int value) {
		int[] A = new int[len];
		int i=0;
		while(i<len) {
			int ran = (int)(Math.random()*(2*value)) - value;
			if(!IntStream.of(A).anyMatch(x -> x == ran)) {
				A[i] = ran;
				i++;
			}
		}
		return A;
	}
}
