// case3_Merge Sort + six 1D-array: O(1/2*N*R)
//package hw08;

public class HW08_4108056005_3 extends Buy_Phone_v2 {
	
	static int[][] array;
	final static public int[][] temp = new int[10000][6];
    final static public int[] d0 = new int[10000];
    final static public int[] d1 = new int[10000];
    final static public int[] d2 = new int[10000];
    final static public int[] d3 = new int[10000];
    final static public int[] d4 = new int[10000];
    final static public int[] d5 = new int[10000];
	static int[][] ans;
	
//	public static void main(String[] args) {
//		HW08_4108056005_3 test = new HW08_4108056005_3();
////		int[][] inputArr = {{8,7,7,4,2,1},{2,4,4,6,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9}};
////		int[][] inputArr = {{5,5,5,1,1,1},{6,3,8,2,1,1},{8,8,3,2,1,1}};
//		int[][] inputArr = {{5,5,5,1,1,1},{6,8,7,2,1,1},{8,3,2,2,1,1}};
//		
//		test.shuffle(inputArr);
//		test.show(inputArr);
//		
//		System.out.println("case3:");
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
		int arrlen = array.length;
		
		sort(array, 0, arrlen-1);	// Merge Sort: O(NlogN)
//		show(array);
		
		// last element must be answer
		d0[0] = array[arrlen-1][0];
		d1[0] = array[arrlen-1][1];
		d2[0] = array[arrlen-1][2];
		d3[0] = array[arrlen-1][3];
		d4[0] = array[arrlen-1][4];
		d5[0] = array[arrlen-1][5];
		
		int top = 1;
		for(int i = arrlen-2; i > -1; i--) {	// traverse all elements to find best phone: O(N*R)
			boolean isAns = true;
			
			for(int j = top-1; j > -1; j--) {
				if(array[i][1] <= d1[j] && array[i][2] <= d2[j] && array[i][3] <= d3[j] && array[i][4] <= d4[j] && array[i][5] <= d5[j]) {
					isAns = false;
					break;
				}
			}
			
			// And put this element to the answer queue.
			if(isAns) {
				d0[top] = array[i][0];
				d1[top] = array[i][1];
				d2[top] = array[i][2];
				d3[top] = array[i][3];
				d4[top] = array[i][4];
				d5[top] = array[i][5];
				top++;
			}
		}
		
		ans =new int[top][6];
        for(int i = 0, lim = top; i < lim; i++) {	// copy answer from six 1D-arrays to a 2D-array, it is faster than only one 2D-array!
        	top--;
        	ans[i][0] = d0[top];
            ans[i][1] = d1[top];
            ans[i][2] = d2[top];
            ans[i][3] = d3[top];
            ans[i][4] = d4[top];
            ans[i][5] = d5[top];
        }
        
		return ans;		// return answer
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
				else if(temp[i][1] < temp[j][1]){		// if y of left is smaller than right, then put left in array
					array[k] = temp[i++];
				}
				else {
					if(temp[i][2] > temp[j][2]) {	// if y of right is smaller than left, then put right in array
						array[k] = temp[j++];
					}
					else if(temp[i][2] < temp[j][2]){		// if y of left is smaller than right, then put left in array
						array[k] = temp[i++];
					}
					else {
						if(temp[i][3] > temp[j][3]) {	// if y of right is smaller than left, then put right in array
							array[k] = temp[j++];
						}
						else if(temp[i][3] < temp[j][3]){		// if y of left is smaller than right, then put left in array
							array[k] = temp[i++];
						}
						else {
							if(temp[i][4] > temp[j][4]) {	// if y of right is smaller than left, then put right in array
								array[k] = temp[j++];
							}
							else if(temp[i][4] < temp[j][4]){		// if y of left is smaller than right, then put left in array
								array[k] = temp[i++];
							}
							else {
								if(temp[i][5] > temp[j][5]) {	// if y of right is smaller than left, then put right in array
									array[k] = temp[j++];
								}
								else{		// if y of left is smaller than right, then put left in array
									array[k] = temp[i++];
								}
							}
						}
					}
				}
			}
		}
    }
	
	private static void show(int[][] a) {
		
		for(int j = 0; j < a[0].length; j++) {
			// print one dimension
			for(int i = 0; i < a.length; i++) {
				System.out.printf("%3d", a[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void shuffle(int[][] a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
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
