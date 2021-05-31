// case6 - new QuickSort: O(NlogN)
package hw10;

public class HW10_4108056005_6 extends SortingArray {

	static int CUTOFF = 32;

	public static void main(String[] args) {
		HW10_4108056005_6 test = new HW10_4108056005_6();
//		int[] input = { -2, 7, 15, -14, 0, 15, 0, 7, -7, -4, -13, 5, 8, -14, 12 };
//		int[] input = { -1, 2, 5, 9, 8, 7, 1, 3, 2 };
		int[] input = new TestDataGenerator().readData();
		int[] ref = new HW10_4108056005_2().sorting(input);

		System.out.println("case6:");
//		System.out.println("Input array: ");
//		test.printArray(input, input.length);

		Stopwatch stopwatch = new Stopwatch();
		int[] ans = test.sorting(input);
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);

//		System.out.println("Sorted array: ");
//		test.printArray(ans, ans.length);
		
		for(int i = 0; i < input.length; i++) {
			if(ans[i] != ref[i]) {
				System.out.println("Wrong answer in "+i);
				for(int j = Math.max(0, i-20); j < Math.min(input.length, i+20); j++) {
					System.out.print(ans[i]+" ");
				}
				System.out.println();
				for(int j = Math.max(0, i-20); j < Math.min(input.length, i+20); j++) {
					System.out.print(ref[i]+" ");
				}
				System.out.println();
				break;
			}
		}
	}
	
	@Override
	public int[] sorting(int[] A) {

		quickSort(A, 0, A.length - 1);

		return A;
	}

	static void swap(int[] arr, int i, int j)
	{
	    int temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}
	 
	static int partition(int[] arr, int low, int high)
	{
	     
	    // pivot
	    int pivot = arr[high];
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++)
	    {
	        if (arr[j] < pivot)
	        {
	            i++;
	            swap(arr, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    return (i + 1);
	}
	 
	static void quickSort(int[] arr, int low, int high)
	{
		if (high - low <= CUTOFF - 1) {
			insertion(arr, low, high);
			return;
		}
		
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
	}
	 
	// Function to print an array
	static void printArray(int[] arr, int size)
	{
	    for(int i = 0; i < size; i++)
	        System.out.print(arr[i] + " ");
	         
	    System.out.println();
	}

	private static void insertion(int[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			for (int j = i; j > lo; j--) {
				if (a[j] < a[j - 1]) {
					swap(a, j, j-1);
				} else {
					break;
				}
			}
		}
	}
}
