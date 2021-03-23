//package hw03;

public class HW03_4108056005_ extends HillFinding
{
	public static void main(String[] args) 
	{
//		System.out.println("case1:");
//		HW03_4108056005_1 test = new HW03_4108056005_1();
//		int[] A = new TestDataGenerator().readData();
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println("hill is: "+test.H_Finding(A));
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}
	
	public int H_Finding(int[] A)
	{
        int start = 0;
        int end   = A.length - 1;
        
        if(A[start] < A[end]) return -1;
        for(int mid = (start+end) >> 1; mid!=start; mid = (start+end) >> 1)
        {
            if(A[mid] > A[start])
            {
                start = mid;
            }
            else
            {
                end = mid;
            }
        }
        return A.length - end - 1;
    }
}
