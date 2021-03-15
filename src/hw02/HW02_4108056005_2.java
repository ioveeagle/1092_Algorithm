// case2 - positive and negative: O(N^2*logN)
 package hw02;

public class HW02_4108056005_2 extends ThreeSum {
	
	boolean hasZero=false;
	
	public static void main(String[] args) {
		
		HW02_4108056005_2 test2 = new HW02_4108056005_2();		
		int[] A = new RandomArray().readData();
		Stopwatch stopwatch = new Stopwatch();
		System.out.println("ThreeSum is zero: "+test2.T_sum(A));
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
	}
	
	@Override
	// O(N^2*logN)
	public int T_sum(int[] A) {
		int count = 0;
		this.quickSort(A, 0, A.length-1);	// quickSort: O(NlogN)
//		this.show(A);
		
		// find the index of min positive: O(1)
		int minPosIndex=0;
		for(int i=0; i<A.length; i++) {
			int index = this.rank(A, i, 0, A.length-1);
			if(index != -1) {
				if(i == 0) {
					hasZero = true;
				}
				minPosIndex = index;
				break;
			}
		}
		
		// find start position of positive numbers: O(1)
		int posStart = 0;
		if(hasZero) {
			posStart = minPosIndex+1;
		}
		else {
			posStart = minPosIndex;
		}
//		System.out.println("minPosIndex = "+minPosIndex);
		
		// sum of three numbers is zero: O((N^2)/4*log(N/2))
		for(int i=0; i<minPosIndex; i++) {
			for(int j=posStart; j<A.length; j++) {
				if(-(A[i]+A[j]) > 0) {
					if(this.rank(A, -(A[i]+A[j]), j+1, A.length-1) != -1) {
						count++;
//						System.out.println(A[i]+", "+A[j]+", "+(-(A[i]+A[j])));
					}
					
				}
				else if(-(A[i]+A[j]) < 0) {
					if(this.rank(A, -(A[i]+A[j]), i+1, minPosIndex-1) != -1) {
						count++;
//						System.out.println(A[i]+", "+A[j]+", "+(-(A[i]+A[j])));
					}
				}
				else {
					if(hasZero) {
						count++;
//						System.out.println(A[i]+", "+A[j]+", "+(-(A[i]+A[j])));
					}
				}
			}
		}
		return count;
	}
	
	public void show(int[] A) {
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i]+", ");
		}
		System.out.println();
	}
	
	public int rank(int[] A, int key, int l, int h) {
		int low = l, high = h;
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