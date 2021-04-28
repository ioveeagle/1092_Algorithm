// case1_Tim Sort: O(N ~ NlogN)
package hw07;

public class HW07_4108056005_1 extends Buy_Phone {

	static int[][] array;
    final static public int[] screen=new int[10000];
    final static public int[] performance=new int[10000];
	static int[][] ans;
	static int arrlen;

	public static void main(String[] args) {
		HW07_4108056005_1 test = new HW07_4108056005_1();
//		int[][] inputArr = {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
		int[][] inputArr = { { 1, 10 }, { 2, 3 }, { 2, 5 }, { 3, 1 }, { 4, 8 }, { 5, 6 }, { 5, 8 }, { 7, 2 }, { 10, 1 },{ 10, 2 } };

		test.shuffle(inputArr);
		test.show(inputArr);

		System.out.println("case1:");
		Stopwatch stopwatch = new Stopwatch();
		test.bestPhone(inputArr);
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);

		test.show(ans);
	}

	@Override
	public int[][] bestPhone(int[][] inputArr) {
		array = inputArr;
		arrlen = array.length;

		timSort(array, arrlen);
		show(array);

		screen[arrlen-1] = array[arrlen-1][0];	// last element must be ans
		int max = performance[arrlen-1] = array[arrlen-1][1];		// max is max of right
		
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

	static int MIN_MERGE = 32;

	public static int minRunLength(int n) {
		assert n >= 0;

		// Becomes 1 if any 1 bits are shifted off
		int r = 0;
		while (n >= MIN_MERGE) {
			r |= (n & 1);
			n >>= 1;
		}
		return n + r;
	}

	// This function sorts array from left index to
	// to right index which is of size atmost RUN
	public static void insertionSort(int[][] arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			int x = arr[i][0], y = arr[i][1];
			int j = i - 1;
			while (j >= left && arr[j][0] >= x && arr[j][1] > y) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1][0] = x;
			arr[j + 1][1] = y;
		}
	}

	// Merge function merges the sorted runs
	public static void merge(int[][] arr, int l, int m, int r) {
		// Original array is broken in two parts
		// left and right array
		int len1 = m - l + 1, len2 = r - m;
		int[][] left = new int[len1][2];
		int[][] right = new int[len2][2];
		for (int x = 0; x < len1; x++) {
			left[x] = arr[l + x];
		}
		for (int x = 0; x < len2; x++) {
			right[x] = arr[m + 1 + x];
		}

		int i = 0;
		int j = 0;
		int k = l;

		// After comparing, we merge those two array
		// in larger sub array
		while (i < len1 && j < len2) {
			if (left[i][0] < right[j][0]) {
				arr[k] = left[i];
				i++;
			}
			else if(left[i][0] > right[j][0]) {
				arr[k] = right[j];
				j++;
			}
			else {
				if(left[i][1] < right[j][1]) {
					arr[k] = left[i];
					i++;
				}
				else {
					arr[k] = right[j];
					j++;
				}
			}
			k++;
		}

		// Copy remaining elements
		// of left, if any
		while (i < len1) {
			arr[k] = left[i];
			k++;
			i++;
		}

		// Copy remaining element
		// of right, if any
		while (j < len2) {
			arr[k] = right[j];
			k++;
			j++;
		}
	}

	// Iterative Timsort function to sort the
	// array[0...n-1] (similar to merge sort)
	public static void timSort(int[][] arr, int n) {
		int minRun = minRunLength(MIN_MERGE);

		// Sort individual subarrays of size RUN
		for (int i = 0; i < n; i += minRun) {
			insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
		}

		// Start merging from size
		// RUN (or 32). It will
		// merge to form size 64,
		// then 128, 256 and so on
		// ....
		for (int size = minRun; size < n; size = 2 * size) {

			// Pick starting point
			// of left sub array. We
			// are going to merge
			// arr[left..left+size-1]
			// and arr[left+size, left+2*size-1]
			// After every merge, we
			// increase left by 2*size
			for (int left = 0; left < n; left += 2 * size) {

				// Find ending point of left sub array
				// mid+1 is starting point of right sub
				// array
				int mid = left + size - 1;
				int right = Math.min((left + 2 * size - 1), (n - 1));

				// Merge sub array arr[left.....mid] &
				// arr[mid+1....right]
				if (mid < right)
					merge(arr, left, mid, right);
			}
		}
	}

	private static void show(int[][] a) {
		// print x
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%3d", a[i][0]);
		}
		System.out.println();
		// print y
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%3d", a[i][1]);
		}
		System.out.println();
	}

	private static void shuffle(int[][] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = (int) (Math.random() * (i));
			exch(a, i, r);
		}
	}
	
	private static void exch(int[][] A, int a, int b) {
		int[] temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
}
