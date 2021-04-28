// case5_Counting table: O(2N)
//package hw07;

public class HW07_4108056005_5 extends Buy_Phone {
	
	static int[][] array;
	int[] counting = new int[100000];
    final static public int[] screen = new int[10000];
    final static public int[] performance = new int[10000];
	static int[][] ans;
	static int max;
	static int arrlen;
	
//	public static void main(String[] args) {
//		HW07_4108056005_5 test = new HW07_4108056005_5();
////		int[][] inputArr = {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
//		int[][] inputArr = {{1,10}, {2,3}, {2,5}, {3,1}, {4,8}, {5,6}, {5,8}, {7,2}, {10,1}, {10,2}};
//		
//		test.shuffle(inputArr);
//		test.show(inputArr);
//		
//		System.out.println("case5:");
//		Stopwatch stopwatch = new Stopwatch();
//		test.bestPhone(inputArr);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//		
//		test.show(ans);
//	}
	
	@Override
	public int[][] bestPhone(int[][] inputArr) {
		array = inputArr;
		arrlen = array.length;
		
		int right = 0, left = array[0][0];
		for(int i = 0; i < arrlen; i++) {	// counting each x with max of y: O(N)
			if(array[i][1] > counting[array[i][0]]) {	// if new y is larger than old y
				counting[array[i][0]] = array[i][1];
			}
			if(array[i][0] > right) {	// find max of x
				right = array[i][0];
			}
			if(array[i][0] < left) {	// find min of x
				left = array[i][0];
			}
		}
		
		screen[0] = right;	// last element must be ans
		max = performance[0] = counting[right];		// max is y of right
		
		int top = 1;
		for(int i = right; i > left-1; i--) {	// traverse all elements to find best phone: O(N)
			if(counting[i] > max) {		// only y bigger than max are answer
				screen[top] = i;
                max = performance[top++] = counting[i];
			}
		}
		
		ans = new int[top][2];
		top--;
        for(int i = 0, lim = top+1; i < lim; i++) {	// copy answer from screen and performance 1D-array
            ans[i][0] = screen[top];
            ans[i][1] = performance[top--];
        }
		
		return ans;
	}
	
	private static void show(int[][] a) {
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
	
	private static void shuffle(int[][] a) {
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			int r = (int)(Math.random()*(i));
			exch(a, i, r);
		}
	}
	
	private static void exch(int[][] A, int a, int b) {
		int[] temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
}
