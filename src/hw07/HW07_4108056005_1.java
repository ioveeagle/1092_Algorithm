// case1_Insertion Sort: O(N ~ (1/2)*N^2)
//package hw07;

public class HW07_4108056005_1 extends Buy_Phone {
	
	static int[][] array;
	static int[][] result = new int[10000][2];
	static int[][] ans;
	static int right, max;
	static int arrlen;
	
	public static void main(String[] args) {
//		HW07_4108056005_1 test = new HW07_4108056005_1();
////		int[][] inputArr = {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
//		int[][] inputArr = {{1,10}, {2,3}, {2,5}, {3,1}, {4,8}, {5,6}, {5,8}, {7,2}, {10,1}, {10,2}};
//		
//		test.shuffle(inputArr);
//		test.show(inputArr);
//		
//		System.out.println("case1:");
//		Stopwatch stopwatch = new Stopwatch();
//		test.bestPhone(inputArr);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//		
//		test.show(ans);
	}
	
	@Override
	public int[][] bestPhone(int[][] inputArr) {
		array = inputArr;
		arrlen = array.length;
		
		insertionSort(array);	// insertion sort: O(N^2)
		
		right = array[arrlen-1][0];		// right is min of right
		max = array[arrlen-1][1];		// max is max of right
		result[0][0] = right;
		result[0][1] = max;
		int top = 1;
		for(int i = arrlen-2; i >= 0; i--) {	// traverse all elements to find best phone: O(N)
			if(array[i][0] < right && array[i][1] > max) {
				right = array[i][0];
				max = array[i][1];
				result[top][0] = right;
				result[top][1] = max;
				top++;
			}
		}
		
		ans = new int[top][2];
		for(int i = 0; i < top; i++) {		// resizing result
			ans[i][0] = result[top-i-1][0];
			ans[i][1] = result[top-i-1][1];
		}
		
		return ans;
	}
	
	private static void insertionSort(int[][] A) {
		int N = A.length;
		for(int i = 0; i < N; i++)
		{
			for(int j = i; j > 0; j--)
			{
				if(less(A[j], A[j-1]))
					exch(A, j, j-1);
				else
					break;
			}
		}
	}
	
	private static boolean less(int[] a, int[] b)
	{
		if(a[0] < b[0]) {
			return true;
		}
		else if(a[0] > b[0]) {
			return false;
		}
		else {
			if(a[1] < b[1]) return true;
			else return false;
		}
	}
	
	private static void exch(int[][] A, int a, int b)
	{
		int[] temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	
	private static void show(int[][] a)
	{
		// print x
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i][0]);
		}
		System.out.println();
		// print y
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i][1]);
		}
		System.out.println();
	}
	
	private static void shuffle(int[][] a)
	{
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			int r = (int)(Math.random()*(i));
			exch(a, i, r);
		}
	}
}
