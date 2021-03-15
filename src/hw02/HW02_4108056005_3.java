// case3 - sandwich algorithm: O(N^2)
// package hw02;

public class HW02_4108056005_3 extends ThreeSum {
	
	public static void main(String[] args) {
//		System.out.println("case3:");
//		HW02_4108056005_3 test3 = new HW02_4108056005_3();
//		int[] A = new RandomArray().readData();
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println("ThreeSum is zero: "+test3.T_sum(A));
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}
	
	@Override
	// O(N^2)
	public int T_sum(int[] A) {
		int count = 0;
		
		this.merge_sort(A);		// mergeSort: O(NlogN)，比quickSort穩定
//		this.show(A);
		
		int limit = this.binarySearch(A, 0, 0, A.length-1);	// 取第一個正數的位置: O(logN)
		
		// 從 最小 跑到 最大 的負數: O(N)
		for(int i = 0; i < limit; i++) {
			int left = i+1;
			int right = A.length-1;
			
			// 一直夾擊直到左右標記交會: O(N)
			while(left < right) {
				int sum = A[i]+A[left]+A[right];
				if(sum > 0) {
					right--;	// 和太大，right向左移
				}
				else if(sum < 0) {
					left++;		// 和太小，left向右移
				}
				else {
					count++;	// 和等於零，count+1，right和left各向內移
					right--;
					left++;
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
	
	public int binarySearch(int[] A, int key, int l, int h) {
		if(key < A[l]) return -1;	// 陣列全部都是正數
		if(key > A[h]) return -1;	// 陣列全部都是負數
		if(key == A[l]) return -1;	// 陣列是0+正數
		if(key == A[h]) return -1;	// 陣列是0+負數
		int low = l, high = h;
		while(low+1 < high) {
			int mid = (low+high)/2;
			if(key < A[mid]) high = mid;	// 要是key小於中間值，代表key會在左區段(low~mid)
			else if(key > A[mid]) low = mid;	// 要是key大於中間值，代表key會在右區段(mid~high)
			else return mid;	// 等於就回傳key的index
		}
		return high;	// 沒找到則回傳離key最近且比key大的數的index
	}
	
	private void merge_sort(int[] arr){
        int[] orderedArr = new int[arr.length];
        for(int i = 2; i < arr.length << 1; i = i << 1) {
            for(int j = 0; j < (arr.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = (left + (i >> 1)) >= arr.length ? (arr.length - 1) : (left + (i >> 1));
                int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (arr[l] < arr[m]) {
                        orderedArr[start++] = arr[l++];
                    } else {
                        orderedArr[start++] = arr[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = arr[l++];
                while (m <= right)
                    orderedArr[start++] = arr[m++];
                System.arraycopy(orderedArr, left, arr, left, right - left + 1);
            }
        }
    }
}