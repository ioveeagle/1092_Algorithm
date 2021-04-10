// case5_go back algorithm + thread32 + static + volatile: O(2N)
//package hw06;

public class HW06_4108056005_5 extends Dessert_Desert
{
	public static void main(String[] args) 
	{
//		HW06_4108056005_5 test = new HW06_4108056005_5();
//		int[][] array = {{2, 1, 3, 2},
//				{2, 1, 2, 4, 3, 3, 4},
//				{1, 1, 1, 1, 1, 1, 1},
//				{1, 3, 5, 7, 9},
//				{5, 4, 3, 2, 1},
//				{4, 2, 6, 5, 5},
//				{2, 1, 2, 3, 2, 4, 3, 5, 3, 4, 6, 7, 8, 5, 10, 9}};
//		System.out.println("case5:");
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
	
	static int[][] inputArr;
	static volatile int[] result;
	int tNum = 32;
	Thread[] t = new Thread[tNum];
	
	@Override
	public int[] maxBlocks(int[][] inputArr) 
	{
		this.inputArr = inputArr;
		result = new int[inputArr.length];
		
		// if array length is larger than the number of threads
		if (inputArr.length > tNum*4) 
		{
			MultiThread[] mt = new MultiThread[tNum];
			
			// split array to 8 pieces
			for(int tr=0; tr<tNum; tr++) 
			{
				mt[tr] = new MultiThread(tr);
				mt[tr].start();
			}
			
			try
			{
	            for(int tr=0; tr<tNum; tr++) 
	            {
	                mt[tr].join();	// merge all thread and wait end
	            }
	        }
			catch(InterruptedException e) {}
		} 
		else 
		{
			countMax(0, inputArr.length);
		}
		
		return result;
	}
	
	private static void countMax(int start, int end)
	{
		for(int i = start; i < end; i++)	// each integers array
		{
			int[][] data = new int[inputArr[i].length][2];	// 0 is max, 1 is min
			int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
			for(int j = 0; j < inputArr[i].length; j++)	// each element in array
			{
				if(inputArr[i][j] >= max)	// if next element is bigger than left max
				{
					max = inputArr[i][j];
					min = inputArr[i][j];
				}
				if(inputArr[i][j] < min)	// if next element is smaller than left min
				{
					min = inputArr[i][j];
				}
				data[j][0] = max;	// put max of each element
				data[j][1] = min;	// put min of each element
			}
			// show(i, data);
			
			int rmin = data[inputArr[i].length-1][1], count = 0;	// rmin is the min value from right
			for(int j = inputArr[i].length-2; j >=0; j--)
			{
				/* if rmin is not smaller than the max of this element, it means 
				 * min of right part is larger or equal to the max of left part, 
				 * and when they sorted respectively, all part is sorted, too.
				 * We count plus 1 because this case is the shortest block.
				 */
				if(data[j][0] <= rmin)	// can't merge, so must be one block
				{
					count++;
					rmin = data[j][1];	// new min of the next block
				}
				else
				{
					rmin = Math.min(rmin, data[j][1]);	// merge might get the smaller min.
				}
			}
			count++;	// the last block is included
			
			result[i] = count;
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
    		int start = inputArr.length * tr / tNum;
			int end = inputArr.length * (tr + 1) / tNum;
			
			countMax(start, end);
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
