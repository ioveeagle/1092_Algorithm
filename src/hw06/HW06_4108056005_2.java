// case2_go back algorithm + thread8: O(2N)
//package hw06;

public class HW06_4108056005_2 extends Dessert_Desert
{
	static byte tNum = 8;
	static byte logtNum = 3;
	static MultiThread[] mt;
	static int len, arr_len;
	static int[][] min = new int[tNum][100000];
	static int[][] max = new int[tNum][100000];
	static int[] ans;
	static int[][] input_arr;
	static int[][] arr = new int[tNum][];

	
	public HW06_4108056005_2() 
	{
		mt = new MultiThread[tNum];
		
		for(int tr=0; tr<tNum; tr++) 
		{
			mt[tr] = new MultiThread(tr);
		}
	}
	
	public static void main(String[] args) 
	{
//		HW06_4108056005_2 test = new HW06_4108056005_2();
//		int[][] array = new int[10000][10000];
//		System.out.println("case2:");
//		Stopwatch stopwatch = new Stopwatch();
//		int[] result = test.maxBlocks(array);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//		
//		for(int i = 0; i < result.length; i++)
//		{
//			System.out.print(result[i]+", ");
//		}
//		System.out.println();
	}
	
	@Override
	public int[] maxBlocks(int[][] inputArr) 
	{
		arr_len = inputArr.length;
		input_arr = inputArr;
		ans = new int[arr_len];
		
		// if array length is larger than the number of threads
		if (arr_len > tNum*4) 
		{
			// split array to 8 pieces
			for(int tr=0; tr<tNum; tr++) 
			{
				mt[tr].start();
			}
			
			try
			{
	            for(int tr=0; tr<tNum; tr++) 
	            {
	                mt[tr].join();	// merge all thread and wait end	0.037
	            }
	        }
			catch(InterruptedException e) {}
		} 
		else 
		{
			for(int i = 0; i < arr_len; i++)	// each integers array
			{
				arr[0] = inputArr[i];
				len = arr.length;
				int top = 0;		// top of max and min stack
				min[0] = max[0] = arr[0];
				
				for(int j = 1; j < len; j++)	// each element in array
				{
					if(arr[0][j] >= max[0][top])	// if next element is bigger than left max
					{
						top++;
						max[top] = min[top] = arr[j];
					}
					else if(arr[0][j] < min[0][top])	// if next element is smaller than left min
					{
						min[top] = arr[j];
					}
				}
				
				int rmin = min[0][top], count = 1;	// rmin is the min value from right
				for(top--; top >= 0; top--)
				{
					/* if rmin is not smaller than the max of this element, it means 
					 * min of right part is larger or equal to the max of left part, 
					 * and when they sorted respectively, all part is sorted, too.
					 * We count plus 1 because this case is the shortest block.
					 */
					if(max[0][top] <= rmin)	// can't merge, so must be one block
					{
						count++;
						rmin = min[0][top];	// new min of the next block
					}
					else if(min[0][top] < rmin)
					{
						rmin = min[0][top];	// merge might get the smaller min.
					}
				}
				
				ans[i] = count;
			}
		}
		
		return ans;
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
    		int start = arr_len * tr >> logtNum;
			int end = arr_len * (tr + 1) >> logtNum;
			
//			System.out.println("thread="+tr+", start="+start+", end="+end);
			
			for(int i = start; i < end; i++)	// each integers array
			{
				arr[tr] = input_arr[i];
				len = arr[tr].length;
				int top = 0;		// top of max and min stack
				min[tr][0] = max[tr][0] = arr[tr][0];
				
				for(int j = 1; j < len; j++)	// each element in array
				{
					if(arr[tr][j] >= max[tr][top])	// if next element is bigger than left max
					{
						top++;
						max[tr][top] = min[tr][top] = arr[tr][j];
					}
					else if(arr[tr][j] < min[tr][top])	// if next element is smaller than left min
					{
						min[tr][top] = arr[tr][j];
					}
				}
	//			show(i, data);
				
				int rmin = min[tr][top], count = 1;	// rmin is the min value from right
				for(top--; top >= 0; top--)
				{
					/* if rmin is not smaller than the max of this element, it means 
					 * min of right part is larger or equal to the max of left part, 
					 * and when they sorted respectively, all part is sorted, too.
					 * We count plus 1 because this case is the shortest block.
					 */
					if(max[tr][top] <= rmin)	// can't merge, so must be one block
					{
						count++;
						rmin = min[tr][top];	// new min of the next block
					}
					else if(min[tr][top] < rmin)
					{
						rmin = min[tr][top];	// merge might get the smaller min.
					}
				}
				
				ans[i] = count;
			}
	    }
	}
	
	private static void show(int k, int[][] a)
	{
		System.out.println("data "+k);
		
		// print max
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i][0]);
		}
		System.out.println();
		// print min
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i][1]);
		}
		System.out.println();
	}
}
