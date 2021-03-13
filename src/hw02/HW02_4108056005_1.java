// case1 - normal case: O(N^2*logN)
package hw02;

public class HW02_4108056005_1 extends ThreeSum {
	
	public HW02_4108056005_1() {}
	
	public static void main(String[] args) {
		HW02_4108056005_1 test = new HW02_4108056005_1();
		int[] A = new RandomArray().readData();
		Stopwatch stopwatch = new Stopwatch();
		System.out.println("ThreeSum is zero: "+test.T_sum(A));
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
	}

	@Override
	// O(N^2*logN)
	public int T_sum(int[] A) {
		int count = 0;
		this.quickSort(A, 0, A.length-1);	// quickSort O(NlogN)
//		this.show(A);
		// O(N^2*logN)
		for(int i=0; i<A.length; i++) {
			for(int j=i+1; j<A.length; j++) {
				if(this.rank(A, -(A[i]+A[j])) > j) {
					count++;
				}
			}
		}
		return count;
	}
	
	public void show(int[] A) {
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i]+" ");
		}
		System.out.println();
	}
	
	public int rank(int[] A, int key) {
		int low = 0, high = A.length-1;
		while(low <= high) {
			int mid = low+(high-low)/2;
			if(key < A[mid]) high = mid-1;
			else if(key > A[mid]) low = mid+1;
			else return mid;
		}
		return -1;
	}
	
	public void quickSort(int arr[], int begin, int end) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);

	        quickSort(arr, begin, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}
	
	private int partition(int arr[], int begin, int end) {
	    int pivot = arr[end];
	    int i = (begin-1);

	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;

	            int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }

	    int swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;

	    return i+1;
	}
}
