// case5 - ShellSort
package hw10;

public class HW10_4108056005_5 extends SortingArray {

	public static void main(String[] args) {
		HW10_4108056005_5 test = new HW10_4108056005_5();
//		int[] input = { -2, 7, 15, -14, 0, 15, 0, 7, -7, -4, -13, 5, 8, -14, 12 };
		int[] input = { -1, 2, 5, 9, 8, 7, 1, 3, 2 };

		System.out.println("case5:");
		System.out.println("Input array: ");
		test.printArray(input, input.length);

		Stopwatch stopwatch = new Stopwatch();
		int[] ans = test.sorting(input);
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);

		System.out.println("Sorted array: ");
		test.printArray(ans, ans.length);
	}

	@Override
	public int[] sorting(int[] A) {
		
		shellSort(A);

		return A;
	}
    
	public static void shellSort(int[] a) {
		int N = a.length;

		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;

		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && a[j] < a[j - h]; j -= h)
					exch(a, j, j - h);
			}
			h = h / 3;
		}
	}

	private static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void printArray(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
	}
}
