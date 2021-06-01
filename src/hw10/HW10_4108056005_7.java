// case5 - ShellSort
package hw10;

public class HW10_4108056005_7 extends SortingArray {

//	final private static int[] gap_seq={19903198,8845866,3931496,1747331,7765915,345152,153401,68178,30301,13467,5985,2660,1182,525,233,103,46,20,9,4,1};
	final private static int[] gap_seq={153401,68178,30301,13467,5985,2660,1182,525,233,103,46,20,9,4,1};
	
	public static void main(String[] args) {
		HW10_4108056005_7 test = new HW10_4108056005_7();
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
		sort(a, 0, a.length);
	}
	
	public static void sort(int a[], int start, int end) {
		for (int gap : gap_seq) {
			for (int i = start + gap; i < end; i++) {
				int temp = a[i];
				int j;
				for (j = i; j >= gap + start && a[j - gap] > temp; j -= gap)
					a[j] = a[j - gap];
				a[j] = temp;
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
