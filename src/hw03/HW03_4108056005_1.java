// case1: sandwich algorithm: O(N/2)
package hw03;

public class HW03_4108056005_1 extends HillFinding{
	public static void main(String[] args) 
	{
		System.out.println("case1:");
		HW03_4108056005_1 test = new HW03_4108056005_1();
		int[] A = new TestDataGenerator().readData();
		Stopwatch stopwatch = new Stopwatch();
		System.out.println("hill is: "+test.H_Finding(A));
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
	}
	
	@Override
	public int H_Finding(int[] A) 
	{
		int start = 0;
		int end = A.length-1;
		while(start < end) 
		{
			if(A[start] <= A[start+1])
			{
				start++;
			}
			else
			{
				return A.length-2-start;
			}
			if(A[end] >= A[end-1])
			{
				end--;
			}
			else
			{
				return A.length-1-end;
			}
		}
		return 0;
	}
}
