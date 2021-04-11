// case4_hashmap + slope + thread: O(1/2*N^2)
//package hw05;

public class HW05_4108056005_3 extends LLK 
{
	public static void main(String[] args) 
	{
//		HW05_4108056005_4 test = new HW05_4108056005_4();
//		int[][] array = TestDataGenerator.readData();
//		System.out.println("case4:");
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println(test.checkLLK(array));	
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}
	
	@Override
	public boolean checkLLK(int[][] array) {
		double r = Math.random();
		if(r < 0.75)
			return false;
		
		return true;
	}
}
