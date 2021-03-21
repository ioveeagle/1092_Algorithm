// case4 - sandwich algorithm + Thread: better than O(N^2)?
package hw02;

public class HW02_4108056005_4 extends ThreeSum {
	
	static int threadNum = 8;	// CPU數量
    volatile static int _count;	// 合為零的組合數, volatile宣告同步變數
	int[] A;
	int limit;
	
	public static void main(String[] args) {
		System.out.println("case4:");
		HW02_4108056005_4 test4 = new HW02_4108056005_4();
		int[] A = new RandomArray().readData();
		Stopwatch stopwatch = new Stopwatch();
		System.out.println("ThreeSum is zero: "+test4.T_sum(A));
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
	}
	
	@Override
	// O(N^2*logN)
	public int T_sum(int[] A) {
		
		this.A=A;
		this.merge_sort(this.A);	// mergeSort: O(NlogN)，比quickSort穩定
//		this.show(A);
		
		limit = this.binarySearch(A, 0, 0, this.A.length-1);	// 取第一個正數的位置: O(logN)
		_count = 0;
		
		// 檢查陣列長度是否小於執行序數量，如果沒有就用執行序
		if(limit >= threadNum) {
			MultiThread[] mt = new MultiThread[threadNum];
			
			// 將陣列切成八等分，分散給各個執行序執行
			for(int tr=0; tr<threadNum; tr++) {
				mt[tr] = new MultiThread(tr);
				mt[tr].start();
			}
			
			try{
	            for(int tr=0; tr<threadNum; tr++) {
	                mt[tr].join();	// 最後將所有執行序結果和併，並等待結束
	            }
	        }
			catch(InterruptedException e) {}
		}
		else {
			// 不用執行序
			int count = 0;
			for(int i = 0; i < limit; i++) {
				int left = i+1;
				int right = A.length-1;
				
				while(left < right) {
					int sum = A[i]+A[left]+A[right];
					if(sum > 0) {
						right--;
					}
					else if(sum < 0) {
						left++;
					}
					else {
						count++;
						right--;
						left++;
					}
				}
			}
			
			_count += count;
		}

		return _count;
	}
	
	public void show(int[] A) {
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i]+", ");
		}
		System.out.println();
	}
	
	public int binarySearch(int[] A, int key, int l, int h) {
		if(key < A[l]) return -1;
		if(key > A[h]) return -1;
		if(key == A[l]) return -1;
		if(key == A[h]) return -1;
		int low = l, high = h;
		while(low+1 < high) {
			int mid = (low+high)/2;
			if(key < A[mid]) high = mid;
			else if(key > A[mid]) low = mid;
			else return mid;
		}
		return high;
	}
	
    public int binarySearch_left(int[] A, int key, int l, int h) {
        int low = l, high = h;
        while(low+1 < high) {
            int mid = (low+high)/2;
            if(key < A[mid]) high = mid;
            else if(key > A[mid]) low = mid;
            else return mid;
        }
        return high;
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
	
    class MultiThread extends Thread {
    	
    	int tr;
    	
    	public MultiThread(int tr) {
    		this.tr = tr;
    	}
    	
    	public void run() {
    		int count = 0;
    		int i_end = tr!=7 ? limit/threadNum*(tr+1) : limit;
    		for(int i = limit/(threadNum)*tr; i < i_end; i++) {
    			int right = A.length-1;
                int left = binarySearch_left(A, -(A[i]+A[right]), i, A.length-1);    // left從合為零的數字開始，沒有的話就找比他大的下一個數字
    			
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
    		
    		_count += count;
    	}
    }
}