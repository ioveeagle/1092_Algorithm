// case4 - MergeSort: O(NlogN)
//package hw10;

public class HW10_4108056005_4 extends SortingArray {

	static int CUTOFF = 16;
	
//	public static void main(String[] args) {
//		HW10_4108056005_4 test = new HW10_4108056005_4();
//		int[] input = { -2, 7, 15, -14, 0, 15, 0, 7, -7, -4, -13, 5, 8, -14, 12 };
////		int[] input = { -1, 2, 5, 9, 8, 7, 1, 3, 2 };
//
//		System.out.println("case4:");
//		System.out.println("Input array: ");
//		test.printArray(input, input.length);
//
//		Stopwatch stopwatch = new Stopwatch();
//		int[] ans = test.sorting(input);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//
//		System.out.println("Sorted array: ");
//		test.printArray(ans, ans.length);
//	}

	@Override
	public int[] sorting(int[] A) {

		mergeSort(A);

		return A;
	}

	private void mergeSort(int[] a) {
		int[] aux = new int[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private void sort(int[] a, int[] aux, int lo, int hi) {
		if (hi - lo <= CUTOFF - 1) {
			insertion(a, lo, hi);
			return;
		}

		int mid = (lo + hi) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		if (a[mid] <= a[mid + 1])
			return;
		merge(a, aux, lo, mid, hi);
	}

	// Utility function to print the Array
	private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		System.arraycopy(a, lo, aux, lo, hi - lo + 1);

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[i] <= aux[j])
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
		}
	}
	
	private void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private void insertion(int[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			for (int j = i; j > lo; j--) {
				if (a[j] < a[j - 1]) {
					exch(a, j, j-1);
				} else {
					break;
				}
			}
		}
	}

	public static void printArray(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
	}
}
