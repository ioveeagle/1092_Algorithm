// case2_two stack: O(N ~ (1/2)*N^2)
//package hw07;

public class HW07_4108056005_2 extends Buy_Phone {
	
	static int[][] array;
	static int[][] stack1 = new int[10000][2];
	static int[][] stack2 = new int[10000][2];
	static int[][] ans;
	static int right, max;
	static int arrlen;
	
	public static void main(String[] args) {
//		HW07_4108056005_2 test = new HW07_4108056005_2();
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
		
		// stack initial
		stack1[0][0] = array[arrlen-1][0];
		stack1[0][1] = array[arrlen-1][1];
		int top1 = 1, top2 = -1;
		for(int i = arrlen-2; i >= 0; i--) {	// traverse all elements to find best phone: O(N)
			int x = array[i][0];
			int y = array[i][1];
			if(x > stack1[top1][0]) {
				while(top1 >= 0 && x > stack1[top1][0]) {
					if(y < stack1[top1][1]) {
						top2++;
						stack2[top2][0] = stack1[top1][0];
						stack2[top2][1] = stack1[top1][1];
						top1--;
					}
					else {
						top1--;
					}
				}
				if(top1 == -1) {
					top1++;
					stack1[top1][0] = x;
					stack1[top1][1] = y;
				}
				else if(y > stack1[top1][1]) {
					if(x == stack1[top1][0]) {
						stack1[top1][1] = y;
					}
					else {
						top1++;
						stack1[top1][0] = x;
						stack1[top1][1] = y;
					}
				}
				else {}
				
				while(top2 >= 0) {
					top1++;
					stack1[top1][0] = stack2[top2][0];
					stack1[top1][1] = stack2[top2][1];
					top2--;
				}
			}
			else if(x < stack1[top1][0]) {
				if(y > stack1[top1][1]) {
					top1++;
					stack1[top1][0] = x;
					stack1[top1][1] = y;
				}
			}
			else {
				if(y > stack1[top1][1]) {
					stack1[top1][1] = y;
				}
			}
			
//			// debug
//			System.out.println("top1 = "+top1);
//			System.out.println("top2 = "+top2);
//			System.out.println("x = "+x+", y = "+y);
//			show(stack1);
		}
		
		
		ans = new int[top1+1][2];
		for(int i = 0; i < top1+1; i++) {		// resizing result
			ans[i][0] = stack1[top1-i][0];
			ans[i][1] = stack1[top1-i][1];
		}
		
		return ans;
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
	
	private static void exch(int[][] A, int a, int b)
	{
		int[] temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
}
