// case3: binary search + Thread: O(logN)
//package hw03;

public class HW03_4108056005_3 extends HillFinding{
	
	static int threadNum = 8;
	int[] A;
	volatile static int _ans = -1;
	volatile static boolean _isFind = false;
	
	public static void main(String[] args) 
	{
//		System.out.println("case3:");
//		HW03_4108056005_3 test = new HW03_4108056005_3();
//		int[] A = new TestDataGenerator().readData();
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println("hill is: "+test.H_Finding(A));
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}
	
	@Override
	public int H_Finding(int[] A) 
	{
		this.A = A;
		
		// 檢查陣列長度是否小於執行序數量，如果沒有就用執行序
		if(A.length >= threadNum*2) 
		{
			MultiThread[] mt = new MultiThread[threadNum];
			
			// 將陣列切成八等分，分散給各個執行序執行
			for(int tr=0; tr<threadNum; tr++) 
			{
				mt[tr] = new MultiThread(tr);
				mt[tr].start();
			}
			
			try{
	            for(int tr=0; tr<threadNum; tr++) 
	            {
	                mt[tr].join();	// 最後將所有執行序結果和併，並等待結束
	            }
	        }
			catch(InterruptedException e) {}
			
			return _ans;
		}
		else 
		{
			// 不用執行序
			int start = 0;
			int end = A.length-1;
			while(start < end) 
			{
				if(A[start] <= A[start+1])
				{
					start++;
				}
				else
				{
					return A.length-2-start;
				}
				if(A[end] >= A[end-1])
				{
					end--;
				}
				else
				{
					return A.length-1-end;
				}
			}
			return -1;
		}
	}
	
	class MultiThread extends Thread 
	{  	
    	int tr;
    	
    	public MultiThread(int tr) 
    	{
    		this.tr = tr;
    	}
    	
    	public void run() 
    	{ 		
    		int start = A.length/(threadNum)*tr;
			int end = tr!=7 ? A.length/threadNum*(tr+1)+1 : A.length-1;
			int ans = this.HF(A, start, end);
			if(ans != -1)
			{
				_ans = ans;
				_isFind = true;
			}
    	}
    	
    	public int HF(int[] A, int start, int end) 
    	{
    		if(A[start] < A[end]) return -1;
    		while(start+1 < end) 
    		{
    			if(_isFind) return -1;
    			int mid = (start+end) >> 1;
    			if(A[mid] > A[start])
    			{
    				start = mid;
    			}
    			else if(A[mid] < A[start])
    			{
    				end = mid;
    			}
    			else
    			{
    				while(start < end) 
    				{
    					if(A[start] <= A[start+1])
    					{
    						start++;
    					}
    					else
    					{
    						return A.length-2-start;
    					}
    					if(A[end] >= A[end-1])
    					{
    						end--;
    					}
    					else
    					{
    						return A.length-1-end;
    					}
    				}
    				return -1;
    			}
    		}
    		if(A[start] > A[end])
    		{
    			return A.length-end-1;
    		}
    		else
    		{
    			return -1;
    		}
    	}
    }
}
