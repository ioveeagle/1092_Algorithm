// case4_Merge Sort + two 1D-array: O(NligN + N)
//package hw07;

public class HW07_4108056005_4 extends Buy_Phone {
	
	static int[][] array;
	final static public int[][] temp = new int[10000][2];
    final static public int[] screen=new int[10000];
    final static public int[] performance=new int[10000];
	static int[][] ans;
	static int max;
	static int arrlen;
	
//	public static void main(String[] args) {
//		HW07_4108056005_4 test = new HW07_4108056005_4();
////		int[][] inputArr = {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
//		int[][] inputArr = {{1,10}, {2,3}, {2,5}, {3,1}, {4,8}, {5,6}, {5,8}, {7,2}, {10,1}, {10,2}};
//		
//		test.shuffle(inputArr);
//		test.show(inputArr);
//		
//		System.out.println("case4:");
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
		
		sort(array, 0, arrlen-1);	// Merge Sort: O(NlogN)
		
		screen[arrlen-1] = array[arrlen-1][0];	// last element must be ans
		max = performance[arrlen-1] = array[arrlen-1][1];		// max is max of right
		
		int top = arrlen-2;
		for(int i = top; i > -1; i--) {	// traverse all elements to find best phone: O(N)
			if(array[i][1] > max) {		// only y bigger than max are answer
				screen[top] = array[i][0];
                performance[top--] = max = array[i][1];
			}
		}
		
		ans =new int[arrlen-top-1][2];
        top++;
        for(int i = 0, lim = arrlen-top; i < lim; i++) {	// copy answer from screen and performance 1D-array
            ans[i][0] = screen[top];
            ans[i][1] = performance[top++];
        }
		
		return ans;
	}
	
    private static void sort(int[][] array, int lo, int hi) { 	// merge sort
        if (hi-lo < 1) 
            return;
        int mid = (hi+lo)>>1;
        sort(array, lo, mid); 
        sort(array, mid+1, hi);
        merge(array, lo, mid, hi); 
    }
    
    private static void merge(int[][] array, int lo, int mid, int hi) { 
    	for(int k = lo; k < hi+1; k++) 
    		temp[k] = array[k];
    	
        int i = lo, j = mid+1;
		for(int k = lo; k < hi+1; k++) {
			if(i > mid) {	// if left subarray is done
				array[k] = temp[j++];
			}
			else if(j > hi) {	// if right subarray is done
				array[k] = temp[i++];
			}
			else if(temp[i][0] > temp[j][0]) {	// if x of right is smaller than left, then put right in array
				array[k] = temp[j++];
			}
			else if(temp[i][0] < temp[j][0]) {	// if x of left is smaller than right, then put left in array
				array[k] = temp[i++];
			}
			else {		// if x of left is equal to right
				if(temp[i][1] > temp[j][1]) {	// if y of right is smaller than left, then put right in array
					array[k] = temp[j++];
				}
				else {		// if y of left is smaller than right, then put left in array
					array[k] = temp[i++];
				}
			}
		}
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
